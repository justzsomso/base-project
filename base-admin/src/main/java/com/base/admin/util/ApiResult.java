package com.base.admin.util;

/**
 * 统一API响应结果封装类
 */
public class ApiResult<T> {
    /**
     * 响应码
     */
    private int code;
    
    /**
     * 响应消息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T data;
    
    /**
     * 是否成功
     */
    private boolean success;

    public ApiResult() {
    }

    public ApiResult(int code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    /**
     * 成功返回结果
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(200, "操作成功", null, true);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(200, "操作成功", data, true);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> ApiResult<T> success(T data, String message) {
        return new ApiResult<>(200, message, data, true);
    }

    /**
     * 失败返回结果
     */
    public static <T> ApiResult<T> failed() {
        return new ApiResult<>(500, "操作失败", null, false);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ApiResult<T> failed(String message) {
        return new ApiResult<>(500, message, null, false);
    }

    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 提示信息
     */
    public static <T> ApiResult<T> failed(int code, String message) {
        return new ApiResult<>(code, message, null, false);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ApiResult<T> validateFailed() {
        return new ApiResult<>(400, "参数验证失败", null, false);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ApiResult<T> validateFailed(String message) {
        return new ApiResult<>(400, message, null, false);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ApiResult<T> unauthorized(T data) {
        return new ApiResult<>(401, "暂未登录或token已经过期", data, false);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ApiResult<T> forbidden(T data) {
        return new ApiResult<>(403, "没有相关权限", data, false);
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}