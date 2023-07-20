package com.jun.plugin.jwt.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jun.plugin.jwt.annotation.IgnorePermissions;
import com.jun.plugin.jwt.enums.JwtResultEnums;
import com.jun.plugin.jwt.utlis.JwtUtil;
import com.jun.plugin.jwt.config.properties.JwtProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * jwt权限拦截器
 * @author 小懒虫
 * @date 2019/4/12
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProjectProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 判断请求映射的方式是否忽略权限验证
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnorePermissions.class)) {
            return true;
        }

        // 获取请求对象头部token数据
        String token = JwtUtil.getRequestToken(request);

        // 验证token数据是否正确
        try {
            JwtUtil.verifyToken(token, jwtProperties.getSecret());
        } catch (TokenExpiredException e) {
            throw new RuntimeException(JwtResultEnums.TOKEN_EXPIRED.getMessage());
        } catch (JWTVerificationException e) {
            throw new RuntimeException(JwtResultEnums.TOKEN_ERROR.getMessage());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
