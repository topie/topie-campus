package com.topie.campus.security.api;

import com.topie.campus.common.utils.HttpResponseUtil;
import com.topie.campus.common.utils.RequestUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.SecurityConstant;
import com.topie.campus.security.security.OrangeAuthenticationRequest;
import com.topie.campus.security.security.OrangeHttpAuthenticationDetails;
import com.topie.campus.security.security.OrangeSideUserCache;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.security.utils.TokenUtils;
import com.topie.campus.tools.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${security.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisCache redisCache;

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request,
            @RequestBody OrangeAuthenticationRequest authenticationRequest) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());
        usernamePasswordAuthenticationToken.setDetails(new OrangeHttpAuthenticationDetails());

        Authentication authentication = null;
        try {
            authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (authentication == null) {
                return ResponseEntity.ok(HttpResponseUtil.error("未检测到验证信息"));
            }
        } catch (InternalAuthenticationServiceException failed) {
            logger.error("An internal error occurred while trying to authenticate the user.", failed);
            return ResponseEntity.ok(HttpResponseUtil.error(failed.getMessage()));
        } catch (AuthenticationException failed) {
            return ResponseEntity.ok(HttpResponseUtil.error(failed.getMessage()));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) redisCache
                .get(SecurityConstant.USER_CACHE_PREFIX + authenticationRequest.getUsername());
        if (userDetails == null) {
            userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            redisCache.set(SecurityConstant.USER_CACHE_PREFIX + authenticationRequest.getUsername(), userDetails);
        }
        String token = this.tokenUtils.generateToken(userDetails);
        logger.info("登录成功;日志类型:{};用户:{};登录IP:{};", "登录", SecurityUtil.getCurrentUserName(),
                RequestUtil.getIpAddress(request));
        return ResponseEntity.ok(HttpResponseUtil.success(token));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> protect() {
        return ResponseEntity.ok(HttpResponseUtil.data("YEAH!"));
    }

}
