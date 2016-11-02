package com.topie.campus.core.dto;

import java.io.Serializable;

/**
 * Created by chenguojun on 9/22/16.
 */
public class StudentSimpleDto implements Serializable {

    private static final long serialVersionUID = 3697874889676508444L;

    private Integer id;

    private Integer userId;

    private String studentNo;

    private String name;

    private String contactPhone;

    private String subject;

    private Integer isBind;

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
