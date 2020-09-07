package com.vaneqi.common;

/**
 * @author qinlei
 * @Date 2020/6/15
 * @Description: 自定义响应状态码
 *              1：表示成功
 *              0：表示错误，错误信息在msg字段中
 *              400：用户在异地登录
 *              500：异常抛出信息
 */
public class Result<T> {
    /**
     * 响应业务状态
     */
    private Integer success;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T body;

    public static <T> Result<T> build(Integer success, String msg, T body) {
        return new Result<T>(success, msg, body);
    }

    public static <T> Result<T> ok(T body) {
        return new Result<T>(1, "OK", body);
    }

    public static <T> Result<T> ok() {
        return new Result<T>(1, "OK", null);
    }

    public static <T> Result<T> errorMsg(String msg) {
        return new Result<T>(0, msg, null);
    }

    public static <T> Result<T> errorUser(String msg) {
        return new Result<T>(400, msg, null);
    }

    public static <T> Result<T> errorException(String msg) {
        return new Result<T>(500, msg, null);
    }

    public Result() {
    }

    private Result(Integer success, String msg, T body) {
        this.success = success;
        this.msg = msg;
        this.body = body;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
