package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_common_notice")
public class Notice {

    /**
     * 通知公告id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Integer noticeId;

    /**
     * 通知公告标题
     */
    @Column(name = "notice_title")
    private String noticeTitle;

    /**
     * 通知公告发布时间
     */
    @Column(name = "notice_publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticePublishTime;

    @Column(name = "notice_publish_user")
    private String noticePublishUser;

    /**
     * 通知公告内容
     */
    @Column(name = "notice_content")
    private String noticeContent;

    @Transient
    private String attachments;

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getNoticePublishUser() {
        return noticePublishUser;
    }

    public void setNoticePublishUser(String noticePublishUser) {
        this.noticePublishUser = noticePublishUser;
    }

    /**
     * 获取通知公告id
     *
     * @return notice_id - 通知公告id
     */
    public Integer getNoticeId() {
        return noticeId;
    }

    /**
     * 设置通知公告id
     *
     * @param noticeId 通知公告id
     */
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * 获取通知公告标题
     *
     * @return notice_title - 通知公告标题
     */
    public String getNoticeTitle() {
        return noticeTitle;
    }

    /**
     * 设置通知公告标题
     *
     * @param noticeTitle 通知公告标题
     */
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    /**
     * 获取通知公告发布时间
     *
     * @return notice_publish_time - 通知公告发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getNoticePublishTime() {
        return noticePublishTime;
    }

    /**
     * 设置通知公告发布时间
     *
     * @param noticePublishTime 通知公告发布时间
     */
    public void setNoticePublishTime(Date noticePublishTime) {
        this.noticePublishTime = noticePublishTime;
    }

    /**
     * 获取通知公告内容
     *
     * @return notice_content - 通知公告内容
     */
    public String getNoticeContent() {
        return noticeContent;
    }

    /**
     * 设置通知公告内容
     *
     * @param noticeContent 通知公告内容
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
}
