package com.topie.campus;

import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import com.topie.campus.tools.redis.RedisCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzh on 2015/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:config/spring.xml")
public class BasicTest extends Assert {

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testFreemarker() {
        Map params = new HashMap();
        params.put("name", "guojun.chen");
        String out = freeMarkerUtil.getStringFromTemplate("/", "test.ftl", params);
        System.out.println(out);
    }

    @Test
    public void testRedis() {
        redisCache.set("user", "chen", 10);
        System.out.println(redisCache.get("user"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisCache.get("user"));
    }

    @Test
    public void testDelByPattern() {
        for (int i = 0; i < 10; i++) {
            redisCache.set("user" + i, "chen" + i, 1000);
        }
        redisCache.delByPattern("user*");
    }
}
