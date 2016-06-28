package com.orange.common.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/7 说明：
 */
public class PropertiesUtil {
    public static String get(String properties, String key) {
        try {
            //Properties props = PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
            return PropertiesLoaderUtils.loadAllProperties(properties).getProperty(key, "未识别的业务异常");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
