package com.topie.campus.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_student")
public class Student {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 平台用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别:0 女 1 男
     */
    private Integer gender;

    /**
     * 民族
     */
    @Column(name = "ethnic_group")
    private Integer ethnicGroup;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 政治面貌
     */
    @Column(name = "political_status")
    private Integer politicalStatus;

    /**
     * 学院
     */
    private String academe;

    /**
     * 专业
     */
    private String subject;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取平台用户id
     *
     * @return user_id - 平台用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置平台用户id
     *
     * @param userId 平台用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取学号
     *
     * @return student_no - 学号
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 设置学号
     *
     * @param studentNo 学号
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取民族
     *
     * @return ethnic_group - 民族
     */
    public Integer getEthnicGroup() {
        return ethnicGroup;
    }

    /**
     * 设置民族
     *
     * @param ethnicGroup 民族
     */
    public void setEthnicGroup(Integer ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
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
     * 获取政治面貌
     *
     * @return political_status - 政治面貌
     */
    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalStatus 政治面貌
     */
    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取学院
     *
     * @return academe - 学院
     */
    public String getAcademe() {
        return academe;
    }

    /**
     * 设置学院
     *
     * @param academe 学院
     */
    public void setAcademe(String academe) {
        this.academe = academe;
    }

    /**
     * 获取专业
     *
     * @return subject - 专业
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置专业
     *
     * @param subject 专业
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取联系电话
     *
     * @return contact_phone - 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系电话
     *
     * @param contactPhone 联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
