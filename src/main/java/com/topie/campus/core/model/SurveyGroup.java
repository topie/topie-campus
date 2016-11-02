package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_survey_group")
public class SurveyGroup {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    /**
     * 问卷组名称
     */
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_type")
    private Integer groupType;

    /**
     * 老师类型id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;

    @Column(name = "per_point")
    private Integer perPoint;

    /**
     * 状态 0:待审核 1：已发布 2：驳回
     */
    private Integer status;

    /**
     * 是否在线 0:否 1:是
     */
    @Column(name = "online_status")
    private Integer onlineStatus;

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getPerPoint() {
        return perPoint;
    }

    public void setPerPoint(Integer perPoint) {
        this.perPoint = perPoint;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取问卷组名称
     *
     * @return group_name - 问卷组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置问卷组名称
     *
     * @param groupName 问卷组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取老师类型id
     *
     * @return type_id - 老师类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置老师类型id
     *
     * @param typeId 老师类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取开始时间
     *
     * @return start - 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getStart() {
        return start;
    }

    /**
     * 设置开始时间
     *
     * @param start 开始时间
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     *
     * @return end - 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     *
     * @param end 结束时间
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * 获取状态 0:待审核 1：已发布 2：驳回
     *
     * @return status - 状态 0:待审核 1：已发布 2：驳回
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0:待审核 1：已发布 2：驳回
     *
     * @param status 状态 0:待审核 1：已发布 2：驳回
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
