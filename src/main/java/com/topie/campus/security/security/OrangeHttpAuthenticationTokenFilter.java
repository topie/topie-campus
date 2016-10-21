package com.topie.campus.security.security;

import com.topie.campus.security.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OrangeHttpAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final String PARAM_TOKEN = "topie_token";

    @Value("${security.token.header}")
    private String tokenHeader;

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    private UserCache userCache;

    public void setTokenUtils(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(this.tokenHeader);
        if (StringUtils.isEmpty(authToken)) {
            authToken = request.getParameter(PARAM_TOKEN);
        }
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        if (username != null && !this.tokenUtils.isTokenExpired(authToken)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userCache.getUserFromCache(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                //authentication.setDetails(new OrangeHttpAuthenticationDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

}
