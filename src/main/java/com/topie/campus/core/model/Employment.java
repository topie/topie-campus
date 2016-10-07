package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_employment")
public class Employment {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 学号
     */
    @Column(name = "stu_id")
    private String stuId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 学历
     */
    private String education;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级号
     */
    @Column(name = "class_num")
    private String classNum;

    /**
     * 入学日期
     */
    @Column(name = "enter_date")
    private String enterDate;

    /**
     * 毕业日期
     */
    @Column(name = "graduate_date")
    private String graduateDate;

    /**
     * 身份证号
     */
    @Column(name = "identify_id")
    private String identifyId;

    @Column(name = "home_address")
    private String homeAddress;

    /**
     * 是否领表0,未领取，1领取
     */
    @Column(name = "take_table")
    private String takeTable;

    /**
     * 就业状态，证明，自谋
     */
    @Column(name = "employment_status")
    private String employmentStatus;

    /**
     * 签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    @Column(name = "sign_status")
    private String signStatus;

    /**
     * 困难生
     */
    @Column(name = "poor_student")
    private String poorStudent;

    /**
     * 就业模式：证明、自谋
     */
    @Column(name = "employ_pattern")
    private String employPattern;

    /**
     * 签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    @Column(name = "sign_pattern")
    private String signPattern;

    /**
     * 签约公司
     */
    @Column(name = "sign_company")
    private String signCompany;

    /**
     * 导师
     */
    private String tutor;

    private String comment;

    /**
     * 签约公司地址
     */
    @Column(name = "sign_company_address")
    private String signCompanyAddress;

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
     * 获取学号
     *
     * @return stu_id - 学号
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * 设置学号
     *
     * @param stuId 学号
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
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
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取学院
     *
     * @return college - 学院
     */
    public String getCollege() {
        return college;
    }

    /**
     * 设置学院
     *
     * @param college 学院
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * 获取专业
     *
     * @return major - 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     *
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获取班级号
     *
     * @return class_num - 班级号
     */
    public String getClassNum() {
        return classNum;
    }

    /**
     * 设置班级号
     *
     * @param classNum 班级号
     */
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    /**
     * 获取入学日期
     *
     * @return enter_date - 入学日期
     */
    public String getEnterDate() {
        return enterDate;
    }

    /**
     * 设置入学日期
     *
     * @param enterDate 入学日期
     */
    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    /**
     * 获取毕业日期
     *
     * @return graduate_date - 毕业日期
     */
    public String getGraduateDate() {
        return graduateDate;
    }

    /**
     * 设置毕业日期
     *
     * @param graduateDate 毕业日期
     */
    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    /**
     * 获取身份证号
     *
     * @return identify_id - 身份证号
     */
    public String getIdentifyId() {
        return identifyId;
    }

    /**
     * 设置身份证号
     *
     * @param identifyId 身份证号
     */
    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    /**
     * @return home_address
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * 获取是否领表0,未领取，1领取
     *
     * @return take_table - 是否领表0,未领取，1领取
     */
    public String getTakeTable() {
        return takeTable;
    }

    /**
     * 设置是否领表0,未领取，1领取
     *
     * @param takeTable 是否领表0,未领取，1领取
     */
    public void setTakeTable(String takeTable) {
        this.takeTable = takeTable;
    }

    /**
     * 获取就业状态，证明，自谋
     *
     * @return employment_status - 就业状态，证明，自谋
     */
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    /**
     * 设置就业状态，证明，自谋
     *
     * @param employmentStatus 就业状态，证明，自谋
     */
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     * 获取签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     *
     * @return sign_status - 签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    public String getSignStatus() {
        return signStatus;
    }

    /**
     * 设置签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     *
     * @param signStatus 签约状态：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    /**
     * 获取困难生
     *
     * @return poor_student - 困难生
     */
    public String getPoorStudent() {
        return poorStudent;
    }

    /**
     * 设置困难生
     *
     * @param poorStudent 困难生
     */
    public void setPoorStudent(String poorStudent) {
        this.poorStudent = poorStudent;
    }

    /**
     * 获取就业模式：证明、自谋
     *
     * @return employ_pattern - 就业模式：证明、自谋
     */
    public String getEmployPattern() {
        return employPattern;
    }

    /**
     * 设置就业模式：证明、自谋
     *
     * @param employPattern 就业模式：证明、自谋
     */
    public void setEmployPattern(String employPattern) {
        this.employPattern = employPattern;
    }

    /**
     * 获取签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     *
     * @return sign_pattern - 签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    public String getSignPattern() {
        return signPattern;
    }

    /**
     * 设置签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     *
     * @param signPattern 签约方式：参军、出国、合同、三方、升本、升硕、村官、社区、西部
     */
    public void setSignPattern(String signPattern) {
        this.signPattern = signPattern;
    }

    /**
     * 获取签约公司
     *
     * @return sign_company - 签约公司
     */
    public String getSignCompany() {
        return signCompany;
    }

    /**
     * 设置签约公司
     *
     * @param signCompany 签约公司
     */
    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany;
    }

    /**
     * 获取导师
     *
     * @return tutor - 导师
     */
    public String getTutor() {
        return tutor;
    }

    /**
     * 设置导师
     *
     * @param tutor 导师
     */
    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取签约公司地址
     *
     * @return sign_company_address - 签约公司地址
     */
    public String getSignCompanyAddress() {
        return signCompanyAddress;
    }

    /**
     * 设置签约公司地址
     *
     * @param signCompanyAddress 签约公司地址
     */
    public void setSignCompanyAddress(String signCompanyAddress) {
        this.signCompanyAddress = signCompanyAddress;
    }
}