package com.topie.campus.security.security;

import com.topie.campus.common.utils.HttpResponseUtil;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrangeHttpAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpResponseUtil.writeJson(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, "未授权访问");
    }

}
