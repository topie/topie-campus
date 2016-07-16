package com.topie.campus.common.utils;

/**
 * Created by cgj on 2015/12/22.
 */
public class HttpResult<T> {
    private int code = 0;
    private String token;
    private T data;

    public HttpResult() {
    }

    public HttpResult(int code) {
        this.code = code;
    }

    public HttpResult(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public HttpResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResult(String token, T data) {
        this.token = token;
        this.data = data;
    }

    public HttpResult(int code, String token, T data) {
        this.code = code;
        this.token = token;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
