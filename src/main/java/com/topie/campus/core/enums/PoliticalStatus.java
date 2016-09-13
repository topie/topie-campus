package com.topie.campus.core.enums;

import com.topie.campus.common.Option;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 9/6/16.
 */
public enum PoliticalStatus {
    ZHONGGONGDANGYUAN(1, "中共党员"), ZHONGGONGYUBEIDANGYUAN(2, "中共预备党员"), GONGQINGTUANYUAN(3, "共青团员"),
    MINGEDANGYUAN(4, "民革党员"), MINMENGMENGYUAN(5, "民盟盟员"), MINJIANHUIYUAN(6, "民建会员"), MINJINHUIYUAN(7, "民进会员"),
    NONGGONGDANGDANGYUAN(8, "农工党党员"), ZHIGONGDANGDANGYUAN(9, "致公党党员"), JIUSANXUESHESHEYUAN(10, "九三学社社员"),
    TAIMENGMENGYUAN(11, "台盟盟员"), WUDANGPAIMINZHURENSHI(12, "无党派民主人士"), QUNZHONG(13, "群众");

    private Integer code;

    private String name;

    PoliticalStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (PoliticalStatus item : PoliticalStatus.values()) {
            if (code.intValue() == item.getCode().intValue()) {
                return item.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        if (StringUtils.isEmpty(name)) return null;
        for (PoliticalStatus item : PoliticalStatus.values()) {
            if (item.getName().equals(name.trim())) {
                return item.getCode();
            }
        }
        return null;
    }

    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (PoliticalStatus item : PoliticalStatus.values()) {
            list.add(item.getCode());
        }
        return list;
    }

    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (PoliticalStatus item : PoliticalStatus.values()) {
            list.add(item.getName());
        }
        return list;
    }

    public static List<Option> getOptions() {
        List<Option> list = new ArrayList<>();
        for (PoliticalStatus item : PoliticalStatus.values()) {
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
