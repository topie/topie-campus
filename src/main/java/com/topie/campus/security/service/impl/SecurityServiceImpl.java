package com.topie.campus.security.service.impl;

import com.topie.campus.security.SecurityConstant;
import com.topie.campus.security.model.Role;
import com.topie.campus.security.model.User;
import com.topie.campus.security.security.OrangeSecurityUser;
import com.topie.campus.security.service.RoleService;
import com.topie.campus.security.service.SecurityService;
import com.topie.campus.security.service.UserService;
import com.topie.campus.tools.redis.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RedisCache redisCache;

    @Override
    public OrangeSecurityUser loadSecurityUserByLoginName(String loginName) {
        User user = userService.findUserByLoginName(loginName);
        if (user == null) {
            return null;
        }
        Collection<GrantedAuthority> userGrantedAuthorities = new ArrayList<GrantedAuthority>();
        List<Integer> grantedAuthorities = userService.findUserRoleByUserId(user.getId());
        if (grantedAuthorities != null && grantedAuthorities.size() > 0) {
            for (Integer grantedAuthority : grantedAuthorities) {
                GrantedAuthority ga = new SimpleGrantedAuthority(String.valueOf(grantedAuthority));
                userGrantedAuthorities.add(ga);
            }
        }
        OrangeSecurityUser orangeSecurityUser = new OrangeSecurityUser(user, userGrantedAuthorities);
        return orangeSecurityUser;
    }

    @Override
    public Map<String, Collection<ConfigAttribute>> getCacheResourceMap() {
        List<Map> roleFunctions = (List<Map>) redisCache.get(SecurityConstant.RESOURCE_MAP);
        if (roleFunctions == null) {
            roleFunctions = roleService.findRoleMatchUpFunctions();
            redisCache.set(SecurityConstant.RESOURCE_MAP, roleFunctions);
        }
        return getResourceMap(roleFunctions);
    }

    @Override
    public String getDefaultAction(int roleId) {
        Role role = roleService.findRoleById(roleId);
        if (StringUtils.isNotBlank(role.getDefaultAction())) return role.getDefaultAction();
        return "";
    }

    @Override
    public Map<String, Collection<ConfigAttribute>> getDbResourceMap() {
        List<Map> roleFunctions = roleService.findRoleMatchUpFunctions();
        redisCache.set(SecurityConstant.RESOURCE_MAP, roleFunctions);
        return getResourceMap(roleFunctions);
    }

    private Map<String, Collection<ConfigAttribute>> getResourceMap(List<Map> roleFunctions) {
        Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();
        if (roleFunctions != null && roleFunctions.size() > 0) {
            for (Map roleFunction : roleFunctions) {
                String url = (String) roleFunction.get("function");
                Integer role = (Integer) roleFunction.get("role");
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                if (!resourceMap.containsKey(url)) {
                    configAttributes.add(new SecurityConfig(String.valueOf(role)));
                    resourceMap.put(url, configAttributes);
                } else {
                    ConfigAttribute configAttribute = new SecurityConfig(String.valueOf(role));
                    configAttributes = resourceMap.get(url);
                    configAttributes.add(configAttribute);
                    resourceMap.put(url, configAttributes);
                }
            }
        }
        return resourceMap;
    }
}
