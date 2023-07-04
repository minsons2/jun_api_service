package com.jun.plugin.system.common.exception;

import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jun.plugin.system.common.exception.code.BaseResponseCode;
import com.jun.plugin.system.common.utils.DataResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * RestExceptionHandler
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     * 系统繁忙，请稍候再试"
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public DataResult handleException(Exception e) {
        log.error("Exception,exception:{}", e, e);
        return DataResult.getResult(500,"系统繁忙，请稍候再试!异常信息："+e.getMessage());
        //return DataResult.getResult(BaseResponseCode.SYSTEM_BUSY);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.dao.QueryTimeoutException.class)
    public DataResult handleQueryTimeoutException(Exception e) {
    	log.error("Exception,exception:{}", e, e);
    	return DataResult.getResult(BaseResponseCode.SYSTEM_REDIS_BUSY);
    }

    /**
     * 自定义全局异常处理
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    DataResult businessExceptionHandler(BusinessException e) {
        log.error("Exception,exception:{}", e, e);
        return new DataResult(e.getMessageCode(), e.getDetailMessage());
    }

    /**
     * 没有权限 返回403视图
     */
//    @ExceptionHandler(value = AuthorizationException.class)
//    public DataResult errorPermission(AuthorizationException e) {
//        log.error("Exception,exception:{}", e, e);
//        return new DataResult(BaseResponseCode.UNAUTHORIZED_ERROR);
//    }

    /**
     * 处理validation 框架异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    DataResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return DataResult.getResult(BaseResponseCode.METHODARGUMENTNOTVALIDEXCEPTION.getCode(), errors.get(0).getDefaultMessage());
    }

    /**
     * 校验List<entity>类型， 需要controller添加@Validated注解
     * 处理Validated List<entity> 异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public DataResult handle(ConstraintViolationException exception) {
        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", exception, exception);
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            builder.append(violation.getMessage());
            break;
        }
        return DataResult.getResult(BaseResponseCode.METHODARGUMENTNOTVALIDEXCEPTION.getCode(), builder.toString());
    }

}
