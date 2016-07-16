package com.topie.campus.security.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/4 说明：
 */
public class OrangeSecurityUser extends User {
    private Integer id;
    private String loginName;
    private String displayName;
    private String email;
    private String contactPhone;
    private Date lastPasswordReset;
    public OrangeSecurityUser(com.topie.campus.security.model.User user,
                              Collection<GrantedAuthority> userGrantedAuthorities) {
        super(user.getLoginName(), user.getPassword(), user.getEnabled(),
                user.getAccountNonExpired(), user.getCredentialsNonExpired(),
                user.getAccountNonLocked(), userGrantedAuthorities);
        if (user != null) {
            setId(user.getId());
            setLoginName(user.getLoginName());
            setDisplayName(user.getDisplayName());
            setEmail(user.getEmail());
            setContactPhone(user.getContactPhone());
            setLastPasswordReset(user.getLastPasswordReset());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }
}
