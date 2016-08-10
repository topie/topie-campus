package com.topie.campus.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_info_basic")
public class InfoBasic {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户类型 0 学生 1 老师 2 领导 3 其他
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 用户唯一编码
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 手机
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 性别:0 女 1 男
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 个人简介
     */
    private String intro;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户类型 0 学生 1 老师 2 领导 3 其他
     *
     * @return user_type - 用户类型 0 学生 1 老师 2 领导 3 其他
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型 0 学生 1 老师 2 领导 3 其他
     *
     * @param userType 用户类型 0 学生 1 老师 2 领导 3 其他
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取用户唯一编码
     *
     * @return user_code - 用户唯一编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户唯一编码
     *
     * @param userCode 用户唯一编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取手机
     *
     * @return mobile_phone - 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机
     *
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取性别:0 女 1 男
     *
     * @return gender - 性别:0 女 1 男
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别:0 女 1 男
     *
     * @param gender 性别:0 女 1 男
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     *
     * @return birth - 出生日期
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置出生日期
     *
     * @param birth 出生日期
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取个人简介
     *
     * @return intro - 个人简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置个人简介
     *
     * @param intro 个人简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}