package com.topie.campus.common.utils;


import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.validation.BindingResult;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/6 说明：
 */
public class ResponseUtil {

    public static void writeJson(HttpServletResponse response, Object o) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
                .createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        objectMapper.writeValue(jsonGenerator, o);
    }

    public static void writeJson(HttpServletResponse response, Integer code,
                                 String message, String targetUrl) throws IOException {
        writeJson(response, new Result(code, message, targetUrl));
    }

    public static Result success() {
        return new Result(HttpServletResponse.SC_OK);
    }

    public static Result success(String msg) {
        return new Result(HttpServletResponse.SC_OK, msg);
    }

        public static <T> Result success(T data) {
        	return new Result(HttpServletResponse.SC_OK, data);
    }

    public static Result error() {
        return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static Result error(String msg) {
        return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(BindingResult result) {
        String msg = "";
        if (result.hasFieldErrors()) {
            msg = result.getFieldErrors().get(0).getField() + ":" + result.getFieldError()
                    .getDefaultMessage();
        }
        return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static void error(HttpServletResponse response, String message) {
        try {
            writeJson(response, error(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
