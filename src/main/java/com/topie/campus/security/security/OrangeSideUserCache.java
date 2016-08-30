package com.topie.campus.security.security;

import com.topie.campus.common.IBasicCache;
import com.topie.campus.security.SecurityConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Created by cgj on 2016/4/13.
 */
public class OrangeSideUserCache implements UserCache, InitializingBean {

    private IBasicCache<String, UserDetails> cache = new OrangeSideNullCache<String, UserDetails>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache);
    }

    @Override
    public UserDetails getUserFromCache(String username) {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) cache.get(SecurityConstant.USER_CACHE_PREFIX + username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return userDetails == null ? null : userDetails;
        }

    }

    @Override
    public void putUserInCache(UserDetails user) {
        cache.set(SecurityConstant.USER_CACHE_PREFIX + user.getUsername(), user);
    }

    @Override
    public void removeUserFromCache(String username) {
        cache.del(SecurityConstant.USER_CACHE_PREFIX + username);
    }

    public void setCache(IBasicCache<String, UserDetails> cache) {
        this.cache = cache;
    }
}
