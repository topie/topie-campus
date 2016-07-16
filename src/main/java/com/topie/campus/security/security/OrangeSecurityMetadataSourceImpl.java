package com.topie.campus.security.security;

import com.topie.campus.security.service.SecurityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
public class OrangeSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private AntPathRequestMatcher matcher;
    private final Map<String, Collection<ConfigAttribute>> resourceMap;

    private SecurityService securityService;

    public OrangeSecurityMetadataSourceImpl(SecurityService securityService) {
        this.securityService = securityService;
        Map<String, Collection<ConfigAttribute>> resourceMap = getResourceMap();
        this.resourceMap = resourceMap;
    }

    private Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return securityService.getResourceMap();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) o).getRequest();
        Iterator<String> it = resourceMap.keySet().iterator();
        while (it.hasNext()) {
            String resURL = it.next();
            Iterator<String> ite = resourceMap.keySet().iterator();
            matcher = new AntPathRequestMatcher(resURL);
            if (matcher.matches(request)) {
                Collection<ConfigAttribute> returnCollection =
                        resourceMap.get(resURL);
                return returnCollection;
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
