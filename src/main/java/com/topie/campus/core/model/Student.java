package com.topie.campus.core.model;

import com.topie.campus.common.Sortable;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_student")
public class Student extends Sortable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

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
    private String gender;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 政治面貌
     */
    @Column(name = "political_status")
    private String politicalStatus;

    /**
     * 民族
     */
    @Column(name = "ethnic_group")
    private String ethnicGroup;

    /**
     * 入取地区
     */
    @Column(name = "src_region")
    private String srcRegion;

    /**
     * 学院
     */
    private String academe;

    /**
     * 专业
     */
    private String subject;

    private String grade;

    /**
     * 学制
     */
    @Column(name = "shool_len")
    private String shoolLen;

    /**
     * 学籍
     */
    @Column(name = "school_roll")
    private String schoolRoll;

    /**
     * 年级
     */
    @Column(name = "grade_year")
    private String gradeYear;

    /**
     * 专业方向
     */
    @Column(name = "major_direction")
    private String majorDirection;

    /**
     * 入学日期
     */
    @Column(name = "enter_date")
    private String enterDate;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String comment;

    /**
     * 学生类别（英语A,英语B）
     */
    @Column(name = "english_type")
    private String englishType;

    /**
     * 全拼
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 专业代码
     */
    @Column(name = "major_code")
    private String majorCode;

    /**
     * 层次（本科，高职等）
     */
    private String gradation;

    /**
     * 是否在校
     */
    private String inschool;

    /**
     * 是否注册
     */
    @Column(name = "is_register")
    private String isRegister;

    /**
     * 毕业日期
     */
    @Column(name = "graduate_date")
    private String graduateDate;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 是否毕业
     */
    @Column(name = "is_graduate")
    private String isGraduate;

    /**
     * 学院
     */
    private String college;

    private String password;

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
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别:0 女 1 男
     *
     * @param gender 性别:0 女 1 男
     */
    public void setGender(String gender) {
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
     * 获取政治面貌
     *
     * @return political_status - 政治面貌
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalStatus 政治面貌
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取民族
     *
     * @return ethnic_group - 民族
     */
    public String getEthnicGroup() {
        return ethnicGroup;
    }

    /**
     * 设置民族
     *
     * @param ethnicGroup 民族
     */
    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    /**
     * 获取入取地区
     *
     * @return src_region - 入取地区
     */
    public String getSrcRegion() {
        return srcRegion;
    }

    /**
     * 设置入取地区
     *
     * @param srcRegion 入取地区
     */
    public void setSrcRegion(String srcRegion) {
        this.srcRegion = srcRegion;
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
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取学制
     *
     * @return shool_len - 学制
     */
    public String getShoolLen() {
        return shoolLen;
    }

    /**
     * 设置学制
     *
     * @param shoolLen 学制
     */
    public void setShoolLen(String shoolLen) {
        this.shoolLen = shoolLen;
    }

    /**
     * 获取学籍
     *
     * @return school_roll - 学籍
     */
    public String getSchoolRoll() {
        return schoolRoll;
    }

    /**
     * 设置学籍
     *
     * @param schoolRoll 学籍
     */
    public void setSchoolRoll(String schoolRoll) {
        this.schoolRoll = schoolRoll;
    }

    /**
     * 获取年级
     *
     * @return grade_year - 年级
     */
    public String getGradeYear() {
        return gradeYear;
    }

    /**
     * 设置年级
     *
     * @param gradeYear 年级
     */
    public void setGradeYear(String gradeYear) {
        this.gradeYear = gradeYear;
    }

    /**
     * 获取专业方向
     *
     * @return major_direction - 专业方向
     */
    public String getMajorDirection() {
        return majorDirection;
    }

    /**
     * 设置专业方向
     *
     * @param majorDirection 专业方向
     */
    public void setMajorDirection(String majorDirection) {
        this.majorDirection = majorDirection;
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

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取学生类别（英语A,英语B）
     *
     * @return english_type - 学生类别（英语A,英语B）
     */
    public String getEnglishType() {
        return englishType;
    }

    /**
     * 设置学生类别（英语A,英语B）
     *
     * @param englishType 学生类别（英语A,英语B）
     */
    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    /**
     * 获取全拼
     *
     * @return full_name - 全拼
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全拼
     *
     * @param fullName 全拼
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取专业代码
     *
     * @return major_code - 专业代码
     */
    public String getMajorCode() {
        return majorCode;
    }

    /**
     * 设置专业代码
     *
     * @param majorCode 专业代码
     */
    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    /**
     * 获取层次（本科，高职等）
     *
     * @return gradation - 层次（本科，高职等）
     */
    public String getGradation() {
        return gradation;
    }

    /**
     * 设置层次（本科，高职等）
     *
     * @param gradation 层次（本科，高职等）
     */
    public void setGradation(String gradation) {
        this.gradation = gradation;
    }

    /**
     * 获取是否在校
     *
     * @return inschool - 是否在校
     */
    public String getInschool() {
        return inschool;
    }

    /**
     * 设置是否在校
     *
     * @param inschool 是否在校
     */
    public void setInschool(String inschool) {
        this.inschool = inschool;
    }

    /**
     * 获取是否注册
     *
     * @return is_register - 是否注册
     */
    public String getIsRegister() {
        return isRegister;
    }

    /**
     * 设置是否注册
     *
     * @param isRegister 是否注册
     */
    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
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
     * 获取是否毕业
     *
     * @return is_graduate - 是否毕业
     */
    public String getIsGraduate() {
        return isGraduate;
    }

    /**
     * 设置是否毕业
     *
     * @param isGraduate 是否毕业
     */
    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
