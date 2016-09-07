package com.topie.campus.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topie.campus.common.Sortable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Table(name = "sys_user")
public class User extends Sortable {

    private static final long serialVersionUID = 4973095724072990604L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    @NotEmpty
    @Size(min = 4, max = 20)
    private String loginName;

    /**
     * 密码
     */

    @NotEmpty
    @Size(min = 8, max = 64)
    @JsonIgnore
    private String password;

    /**
     * 显示名称
     */
    @Column(name = "display_name")
    @NotEmpty
    @Size(max = 20)
    private String displayName;

    /**
     * 状态，0=冻结，1=正常
     */
    private Boolean enabled;

    /**
     * 未锁定状态，0=正常，1=锁定
     */
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    /**
     * 账号过期状态，1=正常，0=过期
     */
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    /**
     * 密码失效状态：1：未失效 0：已失效
     */
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    /**
     * 登陆IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 插入时间
     */
    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 上次密码重置时间
     */
    @Column(name = "last_password_reset")
    private Date lastPasswordReset;

    /**
     * 用户roles_id 集合
     */
    @Transient
    private List<Integer> roles;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取显示名称
     *
     * @return display_name - 显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置显示名称
     *
     * @param displayName 显示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 获取状态，0=冻结，1=正常
     *
     * @return enabled - 状态，0=冻结，1=正常
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置状态，0=冻结，1=正常
     *
     * @param enabled 状态，0=冻结，1=正常
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取未锁定状态，0=正常，1=锁定
     *
     * @return account_non_locked - 未锁定状态，0=正常，1=锁定
     */
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 设置未锁定状态，0=正常，1=锁定
     *
     * @param accountNonLocked 未锁定状态，0=正常，1=锁定
     */
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * 获取账号过期状态，1=正常，0=过期
     *
     * @return account_non_expired - 账号过期状态，1=正常，0=过期
     */
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * 设置账号过期状态，1=正常，0=过期
     *
     * @param accountNonExpired 账号过期状态，1=正常，0=过期
     */
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * 获取密码失效状态：1：未失效 0：已失效
     *
     * @return credentials_non_expired - 密码失效状态：1：未失效 0：已失效
     */
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * 设置密码失效状态：1：未失效 0：已失效
     *
     * @param credentialsNonExpired 密码失效状态：1：未失效 0：已失效
     */
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * 获取登陆IP
     *
     * @return last_login_ip - 登陆IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置登陆IP
     *
     * @param lastLoginIp 登陆IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取最后登陆时间
     *
     * @return last_login_time - 最后登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登陆时间
     *
     * @param lastLoginTime 最后登陆时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
     * 获取电话
     *
     * @return contact_phone - 电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置电话
     *
     * @param contactPhone 电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取插入时间
     *
     * @return insert_time - 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置插入时间
     *
     * @param insertTime 插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}
