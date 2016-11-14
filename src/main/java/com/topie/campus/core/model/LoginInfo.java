package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.topie.campus.tools.excel.ExcelCell;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "t_login_info")
public class LoginInfo {

    @Id
    private Integer id;

    /**
     * 登录用户id
     */
    @ExcelCell(index = 0)
    @Column(name = "login_user_id")
    private Integer loginUserId;

    /**
     * 登录用户登录名
     */
    @ExcelCell(index = 1)
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录ip
     */
    @ExcelCell(index = 3)
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @ExcelCell(index = 4)
    @Column(name = "login_time")
    private Date loginTime;

    @Transient
    @ExcelCell(index = 2)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
     * 获取登录用户id
     *
     * @return login_user_id - 登录用户id
     */
    public Integer getLoginUserId() {
        return loginUserId;
    }

    /**
     * 设置登录用户id
     *
     * @param loginUserId 登录用户id
     */
    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    /**
     * 获取登录用户登录名
     *
     * @return login_name - 登录用户登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录用户登录名
     *
     * @param loginName 登录用户登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取登录ip
     *
     * @return login_ip - 登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录ip
     *
     * @param loginIp 登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
