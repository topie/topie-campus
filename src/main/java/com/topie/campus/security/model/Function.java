package com.topie.campus.security.model;

import com.topie.campus.common.Sortable;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_function")
public class Function extends Sortable {

    private static final long serialVersionUID = -3601166032902209525L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 功能名称
     */
    @Column(name = "function_name")
    private String functionName;

    /**
     * 是否显示到菜单栏
     */
    private Boolean display;

    /**
     * 展示类型 1=后台，2=前台
     */
    @Column(name = "display_type")
    private Integer displayType;

    /**
     * icon
     */
    private String icon;

    /**
     * 请求路径
     */
    private String action;

    /**
     * 排序号
     */
    @Column(name = "function_desc")
    private Integer functionDesc;

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
     * 获取父ID
     *
     * @return parent_id - 父ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取功能名称
     *
     * @return function_name - 功能名称
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * 设置功能名称
     *
     * @param functionName 功能名称
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * 获取是否显示到菜单栏
     *
     * @return display - 是否显示到菜单栏
     */
    public Boolean getDisplay() {
        return display;
    }

    /**
     * 设置是否显示到菜单栏
     *
     * @param display 是否显示到菜单栏
     */

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    /**
     * 获取icon
     *
     * @return icon - icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置icon
     *
     * @param icon icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取请求路径
     *
     * @return action - 请求路径
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置请求路径
     *
     * @param action 请求路径
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取排序号
     *
     * @return function_desc - 排序号
     */
    public Integer getFunctionDesc() {
        return functionDesc;
    }

    /**
     * 设置排序号
     *
     * @param functionDesc 排序号
     */
    public void setFunctionDesc(Integer functionDesc) {
        this.functionDesc = functionDesc;
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
}
