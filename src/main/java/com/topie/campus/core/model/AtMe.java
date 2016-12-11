package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "t_at_me")
public class AtMe {

    @Transient
    @JsonIgnore
    public String begin;

    @Transient
    @JsonIgnore
    public String end;

    @Transient
    public String referContent;

    @Id
    private Integer id;

    /**
     * 来源用户
     */
    @Column(name = "from_user_id")
    private Integer fromUserId;

    /**
     * 来源用户名
     */
    @Column(name = "from_user_name")
    private String fromUserName;

    /**
     * 目标用户
     */
    @Column(name = "to_user_id")
    private Integer toUserId;

    /**
     * @时间
     */
    @Column(name = "at_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date atTime;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 动态类型 1：留言 2：回复
     */
    @Column(name = "content_type")
    private Integer contentType;

    /**
     * 动态回复ID（留言ID或者回复ID）
     */
    @Column(name = "content_id")
    private Integer contentId;

    public String getReferContent() {
        return referContent;
    }

    public void setReferContent(String referContent) {
        this.referContent = referContent;
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
     * 获取来源用户
     *
     * @return from_user_id - 来源用户
     */
    public Integer getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置来源用户
     *
     * @param fromUserId 来源用户
     */
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取来源用户名
     *
     * @return from_user_name - 来源用户名
     */
    public String getFromUserName() {
        return fromUserName;
    }

    /**
     * 设置来源用户名
     *
     * @param fromUserName 来源用户名
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * 获取目标用户
     *
     * @return to_user_id - 目标用户
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * 设置目标用户
     *
     * @param toUserId 目标用户
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取@时间
     *
     * @return at_time - @时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getAtTime() {
        return atTime;
    }

    /**
     * 设置@时间
     *
     * @param atTime @时间
     */
    public void setAtTime(Date atTime) {
        this.atTime = atTime;
    }

    /**
     * 获取动态内容
     *
     * @return content - 动态内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置动态内容
     *
     * @param content 动态内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取动态类型 1：留言 2：回复
     *
     * @return content_type - 动态类型 1：留言 2：回复
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * 设置动态类型 1：留言 2：回复
     *
     * @param contentType 动态类型 1：留言 2：回复
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取动态回复ID（留言ID或者回复ID）
     *
     * @return content_id - 动态回复ID（留言ID或者回复ID）
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * 设置动态回复ID（留言ID或者回复ID）
     *
     * @param contentId 动态回复ID（留言ID或者回复ID）
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
