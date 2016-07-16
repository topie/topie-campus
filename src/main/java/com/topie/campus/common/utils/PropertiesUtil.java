package com.topie.campus.common.utils;

import com.topie.campus.common.exception.DefaultBusinessException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/7 说明：
 */
public class PropertiesUtil {
    public static String get(String properties, String key) {
        try {
            Properties pro = new Properties();
            Resource resource = new ClassPathResource(properties);
            PropertiesLoaderUtils.fillProperties(pro, resource);
            return pro.getProperty(key, "未识别的业务异常");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DefaultBusinessException("properties异常");
        }
    }
}
