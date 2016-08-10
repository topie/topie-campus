package com.topie.campus.security.security;

import com.topie.campus.common.utils.HttpResponseUtil;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/10 说明：
 */
public class OrangeHttpAccessDeniedHandler implements AccessDeniedHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        HttpResponseUtil.error(response, HttpServletResponse.SC_FORBIDDEN, "没有权限访问");
    }

}
