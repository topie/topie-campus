package com.topie.campus.security;

import com.topie.campus.security.utils.SecurityUtil;

import org.junit.Assert;

/**
 * Created by cgj on 2016/4/10.
 */
public class EncodePassWordTest{

    public static void main(String[] args) {
        String password = "job";
        String encodedPassword = SecurityUtil.encodeString(password);
        System.out.println(encodedPassword);
        Assert.assertTrue(SecurityUtil.matchString(password, encodedPassword));
    }
}
