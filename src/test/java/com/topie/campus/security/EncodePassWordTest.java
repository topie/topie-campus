package com.topie.campus.security;

import com.topie.campus.BasicTest;
import com.topie.campus.core.api.info.InfoController;
import com.topie.campus.security.utils.SecurityUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cgj on 2016/4/10.
 */
public class EncodePassWordTest{

    public static void main(String[] args) {
        String password = "info";
        String encodedPassword = SecurityUtils.encodeString(password);
        System.out.println(encodedPassword);
        Assert.assertTrue(SecurityUtils.matchString(password, encodedPassword));
    }
}
