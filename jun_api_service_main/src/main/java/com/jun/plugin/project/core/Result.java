package com.jun.plugin.project.core;

import com.alibaba.fastjson.JSON;
import com.jun.plugin.project.exception.code.BaseResponseCode;
import com.jun.plugin.project.exception.code.ResponseCodeInterface;

/**
 * 统一API响应结果封装
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result setCode(int resultCode) {
        this.code = resultCode;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    //****************************************************************


    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final int SUCCESS = 200;//成功
    public static final int FAIL = 400;//失败
    public static final int UNAUTHORIZED = 401;//未认证（签名错误）
    public static final int NOT_FOUND = 404;//接口不存在
    public static final int INTERNAL_SERVER_ERROR = 500;//服务器内部错误
    public static final int  PARAM_FAIL = 10001;//参数异常

    public static Result success() {
        return new Result()
                .setCode(SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(T data) {
        return new Result()
                .setCode(SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result fail(String message) {
        return new Result()
                .setCode(FAIL)
                .setMessage(message);
    }

   //****************************************************************
    /**
     * 请求响应code，0为成功 其他为失败
     */
//    private int code;

    /**
     * 响应异常码详细信息
     */
//    private String msg;

//    private  T data;

    public Result(int code,  T data) {
        this.code = code;
        this.data = data;
        this.message = null;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }


    public Result() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.message = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public Result( T data) {
        this.data = data;
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.message = BaseResponseCode.SUCCESS.getMsg();
    }

    public Result(ResponseCodeInterface responseCodeInterface) {
        this.data = null;
        this.code = responseCodeInterface.getCode();
        this.message = responseCodeInterface.getMsg();
    }

//    public Result put(String key, Object value) {
//        super.put(key, value);
//        return this;
//    }

    public Result(ResponseCodeInterface responseCodeInterface,  T data) {
        this.data = data;
        this.code = responseCodeInterface.getCode();
        this.message = responseCodeInterface.getMsg();
    }

    /**
     * 操作成功 data为null
     */
//    public static Result success() {
//        return new Result();
//    }

    /**
     * 自定义返回  data为null
     */
    public static Result getResult(int code, String msg) {
        return new Result(code, msg);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data为空
     */
    public static Result getResult(BaseResponseCode responseCode) {
        return new Result(responseCode);
    }

}
