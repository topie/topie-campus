package com.topie.campus.security.security;

import com.topie.campus.common.utils.HttpResponseUtil;
import com.topie.campus.common.utils.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/9 说明：
 */
public class OrangeHttpAuthenticationFailureHandler
        implements AuthenticationFailureHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public OrangeHttpAuthenticationFailureHandler() {
    }

    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise returns a
     * 401 error code.
     * <p/>
     * If redirecting or forwarding, {@code saveException} will be called to cache the exception for
     * use in the target view.
     */
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        try {
            writeJson(response, exception);
        } finally {
            logger
                    .info("登录系统失败;原因:" + exception.getMessage() + ";日志类型:{};用户:{};登录IP:{};", "登录", "-",
                            RequestUtil.getIpAddress(request));
        }
    }

    private void writeJson(HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        HttpResponseUtil.writeJson(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
    }

}
