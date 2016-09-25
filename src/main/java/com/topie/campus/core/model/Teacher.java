package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.topie.campus.common.Sortable;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.PoliticalStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_teacher")
public class Teacher extends Sortable {

    private static final long serialVersionUID = -5640740199490764736L;

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
     * 职工号
     */
    @Column(name = "employee_no")
    private String employeeNo;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
     * 系(所)
     */
    private String department;

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
     * 毕业院校
     */
    @Column(name = "graduated_university")
    private String graduatedUniversity;

    /**
     * 毕业专业
     */
    @Column(name = "graduated_major")
    private String graduatedMajor;

    /**
     * 学历
     */
    @Column(name = "education_background")
    private Integer educationBackground;

    /**
     * 学位
     */
    private Integer degree;

    /**
     * 职称
     */
    @Column(name = "professional_title")
    private String professionalTitle;

    /**
     * 职务
     */
    @Column(name = "professional_position")
    private String professionalPosition;

    /**
     * 学科方向
     */
    @Column(name = "subject_direction")
    private String subjectDirection;

    /**
     * 教师资格证号
     */
    @Column(name = "teacher_certificate_no")
    private String teacherCertificateNo;

    /**
     * 主讲教师资格证号
     */
    @Column(name = "main_teacher_certificate_no")
    private String mainTeacherCertificateNo;

    /**
     * 人事职工号
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * 是否实验室人员 0：否 1：是
     */
    @Column(name = "is_lab_staff")
    private Boolean isLabStaff;

    /**
     * 教学质量评价
     */
    @Column(name = "teach_quality_comment")
    private String teachQualityComment;

    public String getDegreeStr() {
        return Degree.getName(degree);
    }

    public String getEducationBackgroundStr() {
        return EducationBackground.getName(educationBackground);
    }

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
     * 获取职工号
     *
     * @return employee_no - 职工号
     */
    public String getEmployeeNo() {
        return employeeNo;
    }

    /**
     * 设置职工号
     *
     * @param employeeNo 职工号
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public String getEthnicGroupStr() {
        return EthnicGroup.getName(ethnicGroup);
    }

    /**
     * 获取出生日期
     *
     * @return birth - 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
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

    public String getPoliticalStatusStr() {
        return PoliticalStatus.getName(politicalStatus);
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
     * 获取系(所)
     *
     * @return department - 系(所)
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置系(所)
     *
     * @param department 系(所)
     */
    public void setDepartment(String department) {
        this.department = department;
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

    /**
     * 获取毕业院校
     *
     * @return graduated_university - 毕业院校
     */
    public String getGraduatedUniversity() {
        return graduatedUniversity;
    }

    /**
     * 设置毕业院校
     *
     * @param graduatedUniversity 毕业院校
     */
    public void setGraduatedUniversity(String graduatedUniversity) {
        this.graduatedUniversity = graduatedUniversity;
    }

    /**
     * 获取毕业专业
     *
     * @return graduated_major - 毕业专业
     */
    public String getGraduatedMajor() {
        return graduatedMajor;
    }

    /**
     * 设置毕业专业
     *
     * @param graduatedMajor 毕业专业
     */
    public void setGraduatedMajor(String graduatedMajor) {
        this.graduatedMajor = graduatedMajor;
    }

    /**
     * 获取学历
     *
     * @return education_background - 学历
     */
    public Integer getEducationBackground() {
        return educationBackground;
    }

    /**
     * 设置学历
     *
     * @param educationBackground 学历
     */
    public void setEducationBackground(Integer educationBackground) {
        this.educationBackground = educationBackground;
    }

    /**
     * 获取学位
     *
     * @return degree - 学位
     */
    public Integer getDegree() {
        return degree;
    }

    /**
     * 设置学位
     *
     * @param degree 学位
     */
    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    /**
     * 获取职称
     *
     * @return professional_title - 职称
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * 设置职称
     *
     * @param professionalTitle 职称
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    /**
     * 获取职务
     *
     * @return professional_position - 职务
     */
    public String getProfessionalPosition() {
        return professionalPosition;
    }

    /**
     * 设置职务
     *
     * @param professionalPosition 职务
     */
    public void setProfessionalPosition(String professionalPosition) {
        this.professionalPosition = professionalPosition;
    }

    /**
     * 获取学科方向
     *
     * @return subject_direction - 学科方向
     */
    public String getSubjectDirection() {
        return subjectDirection;
    }

    /**
     * 设置学科方向
     *
     * @param subjectDirection 学科方向
     */
    public void setSubjectDirection(String subjectDirection) {
        this.subjectDirection = subjectDirection;
    }

    /**
     * 获取教师资格证号
     *
     * @return teacher_certificate_no - 教师资格证号
     */
    public String getTeacherCertificateNo() {
        return teacherCertificateNo;
    }

    /**
     * 设置教师资格证号
     *
     * @param teacherCertificateNo 教师资格证号
     */
    public void setTeacherCertificateNo(String teacherCertificateNo) {
        this.teacherCertificateNo = teacherCertificateNo;
    }

    /**
     * 获取主讲教师资格证号
     *
     * @return main_teacher_certificate_no - 主讲教师资格证号
     */
    public String getMainTeacherCertificateNo() {
        return mainTeacherCertificateNo;
    }

    /**
     * 设置主讲教师资格证号
     *
     * @param mainTeacherCertificateNo 主讲教师资格证号
     */
    public void setMainTeacherCertificateNo(String mainTeacherCertificateNo) {
        this.mainTeacherCertificateNo = mainTeacherCertificateNo;
    }

    /**
     * 获取人事职工号
     *
     * @return staff_no - 人事职工号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置人事职工号
     *
     * @param staffNo 人事职工号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取是否实验室人员 0：否 1：是
     *
     * @return is_lab_staff - 是否实验室人员 0：否 1：是
     */
    public Boolean getIsLabStaff() {
        return isLabStaff;
    }

    /**
     * 设置是否实验室人员 0：否 1：是
     *
     * @param isLabStaff 是否实验室人员 0：否 1：是
     */
    public void setIsLabStaff(Boolean isLabStaff) {
        this.isLabStaff = isLabStaff;
    }

    /**
     * 获取教学质量评价
     *
     * @return teach_quality_comment - 教学质量评价
     */
    public String getTeachQualityComment() {
        return teachQualityComment;
    }

    /**
     * 设置教学质量评价
     *
     * @param teachQualityComment 教学质量评价
     */
    public void setTeachQualityComment(String teachQualityComment) {
        this.teachQualityComment = teachQualityComment;
    }
}
