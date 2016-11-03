package com.topie.campus.core.dto;

import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.tools.excel.ExcelCell;

import java.io.Serializable;
import java.util.Date;

public class TeacherExcelDto implements Serializable {

    private static final long serialVersionUID = -3730836890003416948L;

    @ExcelCell(index = 0)
    private String employeeNo;

    @ExcelCell(index = 1)
    private String name;

    @ExcelCell(index = 2)
    private String gender;

    @ExcelCell(index = 45)
    private String ethnicGroup;

    @ExcelCell(index = 3)
    private String birth;

    @ExcelCell(index = 44)
    private String politicalStatus;

    @ExcelCell(index = 7)
    private String academe;

    @ExcelCell(index = 8)
    private String department;

    @ExcelCell(index = 4)
    private String contactPhone;

    @ExcelCell(index = 5)
    private String email;

    @ExcelCell(index = 20)
    private String graduatedUniversity;

    @ExcelCell(index = 21)
    private String graduatedMajor;

    @ExcelCell(index = 15)
    private String educationBackground;

    @ExcelCell(index = 46)
    private String degree;

    @ExcelCell(index = 10)
    private String professionalTitle;

    @ExcelCell(index = 11)
    private String professionalPosition;

    @ExcelCell(index = 16)
    private String subjectDirection;

    @ExcelCell(index = 24)
    private String teacherCertificateNo;

    //@ExcelCell(index = 1)
    private String staffNo;

    @ExcelCell(index = 29)
    private String isLabStaff;

    /**
     * 简介
     */
    @ExcelCell(index = 12)
    private String shortIntroduce;
    
    /**
     * 所属专业（教那个系）
     */
    @ExcelCell(index = 19)
    private String teachMajor;

    /**
     * 是否教师资格证(1是，0无）
     */
    @ExcelCell(index = 23)
    private String hasTeacherCertificate;

    /**
     * 教师类型
     */
    @ExcelCell(index = 25)
    private String teacherType;

    /**
     * 教师级别
     */
    @ExcelCell(index = 26)
    private String teacherLever;

    /**
     * 是否外聘
     */
    @ExcelCell(index = 30)
    private String isOutside;

    /**
     * 姓名简拼
     */
    @ExcelCell(index = 55)
    private String shortName;
    
    /**
     * 教师密码
     */
    @ExcelCell(index = 70)
    private String jsmm;

    public String getShortIntroduce() {
        return shortIntroduce;
    }

    public void setShortIntroduce(String shortIntroduce) {
        this.shortIntroduce = shortIntroduce;
    }

    public String getHasTeacherCertificate() {
        return hasTeacherCertificate;
    }

    public void setHasTeacherCertificate(String hasTeacherCertificate) {
        this.hasTeacherCertificate = hasTeacherCertificate;
    }

    public String getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType;
    }

    public String getTeacherLever() {
        return teacherLever;
    }

    public void setTeacherLever(String teacherLever) {
        this.teacherLever = teacherLever;
    }

    public String getIsOutside() {
        return isOutside;
    }

    public void setIsOutside(String isOutside) {
        this.isOutside = isOutside;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeachMajor() {
        return teachMajor;
    }

    public void setTeachMajor(String teachMajor) {
        this.teachMajor = teachMajor;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getAcademe() {
        return academe;
    }

    public void setAcademe(String academe) {
        this.academe = academe;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGraduatedUniversity() {
        return graduatedUniversity;
    }

    public void setGraduatedUniversity(String graduatedUniversity) {
        this.graduatedUniversity = graduatedUniversity;
    }

    public String getGraduatedMajor() {
        return graduatedMajor;
    }

    public void setGraduatedMajor(String graduatedMajor) {
        this.graduatedMajor = graduatedMajor;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getProfessionalPosition() {
        return professionalPosition;
    }

    public void setProfessionalPosition(String professionalPosition) {
        this.professionalPosition = professionalPosition;
    }

    public String getSubjectDirection() {
        return subjectDirection;
    }

    public void setSubjectDirection(String subjectDirection) {
        this.subjectDirection = subjectDirection;
    }

    public String getTeacherCertificateNo() {
        return teacherCertificateNo;
    }

    public void setTeacherCertificateNo(String teacherCertificateNo) {
        this.teacherCertificateNo = teacherCertificateNo;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getIsLabStaff() {
        return isLabStaff;
    }

    public void setIsLabStaff(String isLabStaff) {
        this.isLabStaff = isLabStaff;
    }
    
    public String getJsmm() {
		return jsmm;
	}

	public void setJsmm(String jsmm) {
		this.jsmm = jsmm;
	}

	public Teacher buildTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName(this.name);
        teacher.setEmail(this.email);
        teacher.setBirth(this.birth);
        teacher.setStaffNo(this.staffNo);
        teacher.setAcademe(this.academe);
        teacher.setDepartment(this.department);
        teacher.setEmployeeNo(this.employeeNo);
        teacher.setContactPhone(this.contactPhone);
        teacher.setDegree(Degree.getCode(this.degree));
        teacher.setGender(Gender.getCode(this.gender));
        teacher.setGraduatedMajor(this.graduatedMajor);
        teacher.setSubjectDirection(this.subjectDirection);
        teacher.setProfessionalTitle(this.professionalTitle);
        teacher.setGraduatedUniversity(this.graduatedUniversity);
        teacher.setPoliticalStatus(PoliticalStatus.getCode(this.politicalStatus));
        teacher.setTeacherCertificateNo(this.teacherCertificateNo);
        teacher.setProfessionalPosition(this.professionalPosition);
        teacher.setIsLabStaff("是".equals(this.isLabStaff) ? true : false);
        teacher.setEthnicGroup(EthnicGroup.getCode(this.getEthnicGroup()));
        teacher.setEducationBackground(EducationBackground.getCode(this.educationBackground));
        teacher.setShortIntroduce(this.shortIntroduce);
        teacher.setHasTeacherCertificate("有".equals(this.hasTeacherCertificate) ? true : false);
        teacher.setTeacherType(this.teacherType);
        teacher.setTeacherLever(this.teacherLever);
        teacher.setTeachMajor(this.teachMajor);
        teacher.setIsOutside("是".equals(this.isOutside) ? 1 : 0);
        teacher.setPassword(this.jsmm);
        return teacher;
    }
}
