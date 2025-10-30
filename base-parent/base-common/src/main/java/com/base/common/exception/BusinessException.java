package com.base.common.exception;

import lombok.Getter;

/**
 * 自定义业务异常类
 * 用于业务逻辑中主动抛出异常（如参数非法、权限不足、数据不存在等）
 */
@Getter // 提供getter方法，让全局异常处理器能获取code和message
public class BusinessException extends RuntimeException {

    /**
     * 错误码（自定义，如400参数错误、403权限不足、404数据不存在等）
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    // 构造方法1：仅传错误信息（默认错误码500）
    public BusinessException(String message) {
        this.code = 500;
        this.message = message;
    }

    // 构造方法2：传错误码和错误信息（推荐使用）
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    // 构造方法3：传错误码、错误信息和异常原因（用于需要追溯异常链的场景）
    public BusinessException(Integer code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }
}