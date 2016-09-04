package com.topie.campus.tools.redis;

import com.topie.campus.common.IBasicCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenguojun on 8/30/16.
 */
@Component
public class RedisCache implements IBasicCache<String, Object> {

    private final static int DEFAULT_EXPIRE_SECOND = 3 * 24 * 60 * 60;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_EXPIRE_SECOND, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, int seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expire(String key, int seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void delByPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        for (String key : keys) {
            del(key);
        }
    }
}
