package com.topie.campus;

import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.junit.*;
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

    @Test
    public void testFreemarker(){
        Map params = new HashMap();
        params.put("name","guojun.chen");
        String out = freeMarkerUtil.getStringFromTemplate("/","test.ftl",params);
        System.out.println(out);
    }
}
