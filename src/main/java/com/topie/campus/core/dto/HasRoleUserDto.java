package com.topie.campus.core.dto;

import java.io.Serializable;

/**
 * Created by chenguojun on 9/3/16.
 */
public class HasRoleUserDto implements Serializable {

    private static final long serialVersionUID = -8877646388589415875L;

    private Integer userId;

    private Integer roleId;

    private String loginName;

    private String displayName;

    private Boolean hasRole = false;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getHasRole() {
        return (roleId != null && roleId > 0) ? true : false;
    }

    public void setHasRole(Boolean hasRole) {
        this.hasRole = hasRole;
    }
}
