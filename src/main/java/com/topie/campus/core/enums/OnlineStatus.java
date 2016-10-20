package com.topie.campus.core.enums;

import com.topie.campus.common.Option;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 9/6/16.
 */
public enum OnlineStatus {
    PRE(0, "未开始"), ING(1, "进行中"), END(2, "已结束");

    private Integer code;

    private String name;

    OnlineStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (OnlineStatus item : OnlineStatus.values()) {
            if (code.intValue() == item.getCode().intValue()) {
                return item.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        if (StringUtils.isEmpty(name)) return null;
        for (OnlineStatus item : OnlineStatus.values()) {
            if (item.getName().equals(name.trim())) {
                return item.getCode();
            }
        }
        return null;
    }

    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (OnlineStatus item : OnlineStatus.values()) {
            list.add(item.getCode());
        }
        return list;
    }

    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (OnlineStatus item : OnlineStatus.values()) {
            list.add(item.getName());
        }
        return list;
    }

    public static List<Option> getOptions() {
        List<Option> list = new ArrayList<>();
        for (OnlineStatus item : OnlineStatus.values()) {
            Option option = new Option();
            option.setText(item.getName());
            option.setValue(item.getCode());
            list.add(option);
        }
        return list;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
