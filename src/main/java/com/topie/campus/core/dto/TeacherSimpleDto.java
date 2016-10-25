package com.topie.campus.core.dto;

import java.io.Serializable;

/**
 * Created by chenguojun on 9/22/16.
 */
public class TeacherSimpleDto implements Serializable {

    private static final long serialVersionUID = 3697874889676508444L;

    private Integer id;

    private Integer userId;

    private String employeeNo;

    private String name;

    private String avatar;

    private String contactPhone;

    private Integer isBind;

    private String typeName;

    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
