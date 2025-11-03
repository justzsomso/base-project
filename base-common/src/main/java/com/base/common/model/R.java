package com.base.common.model;

import lombok.Data;

@Data
public class R<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.code = 200;
        r.message = "成功";
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = ok();
        r.data = data;
        return r;
    }

    public static <T> R<T> fail(String message) {
        R<T> r = new R<>();
        r.code = 500;
        r.message = message;
        return r;
    }

    public static <T> R<T> fail(Integer code, String message) {
        R<T> r = new R<>();
        r.code = code;
        r.message = message;
        return r;
    }
}