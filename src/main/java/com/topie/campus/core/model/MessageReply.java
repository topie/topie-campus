package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_message_reply")
public class MessageReply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyId;

    /**
     * 留言id
     */
    @Column(name = "message_id")
    private Integer messageId;

    /**
     * 回复内容
     */
    @Column(name = "reply_content")
    private String replyContent;

    /**
     * 回复用户id
     */
    @Column(name = "reply_user_id")
    private Integer replyUserId;

    /**
     * 回复用户名称
     */
    @Column(name = "reply_user_name")
    private String replyUserName;

    /**
     * 留言时间
     */

    @Column(name = "reply_date_time")
    private Date replyDateTime;

    /**
     * 是否读取:0 未读取 1:已读取
     */
    @Column(name = "is_read")
    private Boolean isRead;

    /**
     * @return reply_id
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * @param replyId
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取留言id
     *
     * @return message_id - 留言id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * 设置留言id
     *
     * @param messageId 留言id
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取回复内容
     *
     * @return reply_content - 回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 设置回复内容
     *
     * @param replyContent 回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 获取回复用户id
     *
     * @return reply_user_id - 回复用户id
     */
    public Integer getReplyUserId() {
        return replyUserId;
    }

    /**
     * 设置回复用户id
     *
     * @param replyUserId 回复用户id
     */
    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    /**
     * 获取回复用户名称
     *
     * @return reply_user_name - 回复用户名称
     */
    public String getReplyUserName() {
        return replyUserName;
    }

    /**
     * 设置回复用户名称
     *
     * @param replyUserName 回复用户名称
     */
    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    /**
     * 获取留言时间
     *
     * @return reply_date_time - 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getReplyDateTime() {
        return replyDateTime;
    }

    /**
     * 设置留言时间
     *
     * @param replyDateTime 留言时间
     */
    public void setReplyDateTime(Date replyDateTime) {
        this.replyDateTime = replyDateTime;
    }

    /**
     * 获取是否读取:0 未读取 1:已读取
     *
     * @return is_read - 是否读取:0 未读取 1:已读取
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * 设置是否读取:0 未读取 1:已读取
     *
     * @param isRead 是否读取:0 未读取 1:已读取
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
