package com.jun.plugin.project.base.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.jun.plugin.common.Result;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.project.userservice.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Bean
    LoginInterceptor loginInterceptor() {

        return new LoginInterceptor();
    }

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 按需配置，更多参考FastJson文档哈

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof BusinessException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(Result.FAIL).setMessage(e.getMessage());
                } else if (e instanceof MethodArgumentNotValidException) {//@valid注解验证参数
                    MethodArgumentNotValidException m = (MethodArgumentNotValidException) e;
                    m.getBindingResult().getFieldError().getDefaultMessage();
                    result.setCode(Result.PARAM_FAIL).setMessage(m.getBindingResult().getFieldError().getDefaultMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(Result.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(Result.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(Result.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("forward:" + indexUrl);
        registry.addViewController("/").setViewName("forward:" + "login.html");
    }

    /**
     * 页面跨域访问Controller过滤
     *
     * @return
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }


    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/public/**")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.ico")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.ttf")
                .excludePathPatterns("/**/*.woff2")
                .excludePathPatterns("/assets/**")
                .excludePathPatterns("/json/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register")
                .addPathPatterns("/**");
    }


    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
//        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Global.getProfile() + "/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","classpath:/static2/","classpath:/static3/","classpath:/templates/","classpath:/templates2/","classpath:/templates3/","classpath:/views/");
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","classpath:/static2/","classpath:/static3/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        /** 文件下载映射配置,同下 */
//        registry.addResourceHandler(fileUploadProperties.getAccessUrl()).addResourceLocations("file:" + fileUploadProperties.getPath());
    }


    /**
     * 配置servlet处理
     */
//    @Override
//    public void configureDefaultServletHandling(
//            DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    /**
     * 视图配置
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
        registry.viewResolver(resourceViewResolver());
        /*registry.jsp("/WEB-INF/jsp/",".jsp");*/
    }


    /**
     * 配置请求视图映射
     * @return
     */
    @Bean
    public InternalResourceViewResolver resourceViewResolver()
    {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //请求视图文件的前缀地址
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        //请求视图文件的后缀
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }


}


class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.jwt.enable:false}")
    private boolean enable;

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(enable){
            //拦截接口
            //从header中获取token
            String token = request.getHeader("token");
            //如果header中不存在token，则从参数中获取token
            if(StringUtils.isBlank(token)){
                token = request.getParameter("token");
            }
            //token为空返回
            if(StringUtils.isBlank(token)){
                Result result = new Result();
                result.setCode(Result.UNAUTHORIZED).setMessage("token不能为空");
                responseResult(response, result);
                return false;
            }//  校验并解析token，如果token过期或者篡改，则会返回null
            Claims claims = JwtUtils.checkJWT(token);
            if(null == claims){
                Result result = new Result();
                result.setCode(Result.UNAUTHORIZED).setMessage("登陆失效， 请重新登陆");
                responseResult(response, result);
                return false;
            }
            //  校验通过后，设置用户信息到request里，在Controller中从Request域中获取用户信息
            request.setAttribute(JwtUtils.USER_ID_KEY, claims.get("id"));
            request.setAttribute(JwtUtils.USER_ACCOUNT_KEY, claims.get("account"));
        }
        return true;
    }



    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}

