package com.jun.plugin.project.configurer;


import com.alibaba.fastjson.JSON;
import com.jun.plugin.project.core.Result;
import com.jun.plugin.project.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * <p>登陆拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

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
