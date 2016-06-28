package com.orange.security;

import com.orange.BasicTest;
import com.orange.security.utils.SecurityUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cgj on 2016/4/10.
 */
public class EncodePassWordTest extends BasicTest {

    @Test
    public void test() {
        String password = "user";
        String encodedPassword = SecurityUtils.encodeString(password);
        System.out.println(encodedPassword);
        Assert.assertTrue(SecurityUtils.matchString(password, encodedPassword));
    }
}
