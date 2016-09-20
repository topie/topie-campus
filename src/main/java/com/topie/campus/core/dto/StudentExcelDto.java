package com.topie.campus.core.dto;

import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.model.Student;
import com.topie.campus.tools.excel.ExcelCell;

import java.io.Serializable;
import java.util.Date;

public class StudentExcelDto implements Serializable {

    private static final long serialVersionUID = -3730836890003416948L;

    @ExcelCell(index = 0)
    private String studentNo;

    @ExcelCell(index = 1)
    private String name;

    @ExcelCell(index = 2)
    private String gender;

    @ExcelCell(index = 3)
    private String ethnicGroup;

    @ExcelCell(index = 4)
    private String politicalStatus;

    @ExcelCell(index = 5)
    private String contactPhone;

    @ExcelCell(index = 6)
    private String email;

    @ExcelCell(index = 7)
    private Date birth;

    @ExcelCell(index = 8)
    private String academe;

    @ExcelCell(index = 9)
    private String subject;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAcademe() {
        return academe;
    }

    public void setAcademe(String academe) {
        this.academe = academe;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Student buildStudent() {
        Student teacher = new Student();
        teacher.setName(this.name);
        teacher.setEmail(this.email);
        teacher.setBirth(this.birth);
        teacher.setAcademe(this.academe);
        teacher.setStudentNo(this.studentNo);
        teacher.setContactPhone(this.contactPhone);
        teacher.setGender(Gender.getCode(this.gender));
        teacher.setEthnicGroup(EthnicGroup.getCode(this.getEthnicGroup()));
        teacher.setSubject(this.getSubject());
        return teacher;
    }
}
