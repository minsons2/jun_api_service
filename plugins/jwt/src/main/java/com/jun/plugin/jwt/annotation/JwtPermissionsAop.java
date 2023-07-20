package com.jun.plugin.jwt.annotation;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jun.plugin.jwt.enums.JwtResultEnums;
import com.jun.plugin.jwt.utlis.JwtUtil;
import com.jun.plugin.jwt.config.properties.JwtProjectProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Jwt权限注解AOP
 * @author 小懒虫
 * @date 2019/4/13
 */
@Aspect
@Component
@ConditionalOnProperty(name = "project.jwt.pattern-anno", havingValue = "true", matchIfMissing = true)
public class JwtPermissionsAop {

    @Autowired
    private JwtProjectProperties jwtProperties;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.jun.plugin.jwt.annotation.JwtPermissions)")
    public void jwtPermissions() {};

    @Around("jwtPermissions()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {

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

        return point.proceed();
    }
}
