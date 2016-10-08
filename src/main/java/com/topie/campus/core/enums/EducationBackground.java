package com.topie.campus.core.enums;

import com.topie.campus.common.Option;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 9/6/16.
 */
public enum EducationBackground {
    XIAOXUE(1, "小学毕业"), CHUZHONG(2, "初中毕业"), ZHONGZHUAN(3, "中专毕业"), GAOZHONG(4, "高中毕业"), DAZHUAN(5, "大专毕业"), BENKE(6, "本科毕业"),
    YANJIUSHENG(7, "研究生毕业");

    private Integer code;

    private String name;

    EducationBackground(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (EducationBackground item : EducationBackground.values()) {
            if (code.intValue() == item.getCode().intValue()) {
                return item.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        if (StringUtils.isEmpty(name)) return null;
        for (EducationBackground item : EducationBackground.values()) {
            if (item.getName().equals(name.trim())) {
                return item.getCode();
            }
        }
        return null;
    }

    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (EducationBackground item : EducationBackground.values()) {
            list.add(item.getCode());
        }
        return list;
    }

    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (EducationBackground item : EducationBackground.values()) {
            list.add(item.getName());
        }
        return list;
    }

    public static List<Option> getOptions() {
        List<Option> list = new ArrayList<>();
        for (EducationBackground item : EducationBackground.values()) {
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
