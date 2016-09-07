package com.topie.campus.security.model;

import com.topie.campus.common.Sortable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "sys_role")
public class Role extends Sortable {

    private static final long serialVersionUID = -387429146553168074L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色默认跳转function
     */
    @Column(name = "default_action")
    private String defaultAction;

    /**
     * 是否启用，0=不启用，1=启用
     */
    private Integer state;

    /**
     * 生成时间
     */
    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private List<Integer> functions;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色默认跳转function
     *
     * @return default_action - 角色默认跳转function
     */
    public String getDefaultAction() {
        return defaultAction;
    }

    /**
     * 设置角色默认跳转function
     *
     * @param defaultAction 角色默认跳转function
     */
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }

    /**
     * 获取是否启用，0=不启用，1=启用
     *
     * @return state - 是否启用，0=不启用，1=启用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置是否启用，0=不启用，1=启用
     *
     * @param state 是否启用，0=不启用，1=启用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取生成时间
     *
     * @return insert_time - 生成时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置生成时间
     *
     * @param insertTime 生成时间
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

    public List<Integer> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Integer> functions) {
        this.functions = functions;
    }
}
