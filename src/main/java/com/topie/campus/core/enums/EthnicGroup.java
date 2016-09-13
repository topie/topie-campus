package com.topie.campus.core.enums;

import com.topie.campus.common.Option;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 9/6/16.
 */
public enum EthnicGroup {
    HANZU(1, "汉族"), MENGGUZU(2, "蒙古族"), HUIZU(3, "回族"), CANGZU(4, "藏族"), WEIWUERZU(5, "维吾尔族"), MIAOZU(6, "苗族"),
    YIZU(7, "彝族"), ZHUANGZU(8, "壮族"), BUYIZU(9, "布依族"), CHAOXIANZU(10, "朝鲜族"), MANZU(11, "满族"), DONGZU(12, "侗族"),
    YAOZU(13, "瑶族"), BAIZU(14, "白族"), TUJIAZU(15, "土家族"), HANIZU(16, "哈尼族"), HASAKEZU(17, "哈萨克族"), DAIZU(18, "傣族"),
    LIZU(19, "黎族"), LISUZU(20, "傈僳族"), WAZU(21, "佤族"), ZU(22, "畲族"), GAOSHANZU(23, "高山族"), LAZU(24, "拉祜族"),
    SHUIZU(25, "水族"), DONGXIANGZU(26, "东乡族"), NAXIZU(27, "纳西族"), JINGPOZU(28, "景颇族"), KEERKEZIZU(29, "柯尔克孜族"),
    TUZU(30, "土族"), DAWOERZU(31, "达斡尔族"), MULAOZU(32, "仫佬族"), QIANGZU(33, "羌族"), BULANGZU(34, "布朗族"), SALAZU(35, "撒拉族"),
    MAONANZU(36, "毛南族"), LAOZU(37, "仡佬族"), XIBOZU(38, "锡伯族"), ACHANGZU(39, "阿昌族"), PUMIZU(40, "普米族"),
    TAJIKEZU(41, "塔吉克族"), NUZU(42, "怒族"), WUZIBIEKEZU(43, "乌孜别克族"), ELUOSIZU(44, "俄罗斯族"), EWENKEZU(45, "鄂温克族"),
    DEANGZU(46, "德昂族"), BAOANZU(47, "保安族"), YUGUZU(48, "裕固族"), JINGZU(49, "京族"), TATAERZU(50, "塔塔尔族"),
    DULONGZU(51, "独龙族"), ELUNCHUNZU(52, "鄂伦春族"), HEZHEZU(53, "赫哲族"), MENBAZU(54, "门巴族"), BAZU(55, "珞巴族"),
    JINUOZU(56, "基诺族");

    private Integer code;

    private String name;

    EthnicGroup(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (EthnicGroup item : EthnicGroup.values()) {
            if (code.intValue() == item.getCode().intValue()) {
                return item.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        if (StringUtils.isEmpty(name)) return null;
        for (EthnicGroup item : EthnicGroup.values()) {
            if (item.getName().equals(name.trim())) {
                return item.getCode();
            }
        }
        return null;
    }

    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (EthnicGroup item : EthnicGroup.values()) {
            list.add(item.getCode());
        }
        return list;
    }

    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (EthnicGroup item : EthnicGroup.values()) {
            list.add(item.getName());
        }
        return list;
    }

    public static List<Option> getOptions() {
        List<Option> list = new ArrayList<>();
        for (EthnicGroup item : EthnicGroup.values()) {
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
