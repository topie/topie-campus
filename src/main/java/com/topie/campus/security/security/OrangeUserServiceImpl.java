package com.topie.campus.security.security;

import com.topie.campus.security.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
public class OrangeUserServiceImpl implements UserDetailsService {
    @Autowired
    SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OrangeSecurityUser orangeSecurityUser = securityService.loadSecurityUserByLoginName(username);
        if (orangeSecurityUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return orangeSecurityUser;
    }
}
