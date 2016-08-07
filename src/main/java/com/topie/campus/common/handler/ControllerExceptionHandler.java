package com.topie.campus.common.handler;

import com.topie.campus.common.utils.HttpResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/6 说明：
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public void handleAllException(HttpServletResponse response, Exception e) {
        try {
            e.printStackTrace();
            HttpResponseUtil.writeJson(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
