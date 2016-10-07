package com.topie.campus.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_message")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    /**
     * 留言内容
     */
    @Column(name = "message_content")
    private String messageContent;

    /**
     * 接收留言用户id
     */
    @Column(name = "message_to_user_id")
    private Integer messageToUserId;

    @Column(name = "message_to_user_name")
    private String messageToUserName;

    /**
     * 留言用户id
     */
    @Column(name = "message_from_user_id")
    private Integer messageFromUserId;

    @Column(name = "message_from_user_name")
    private String messageFromUserName;

    /**
     * 留言时间
     */
    @Column(name = "message_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date messageDateTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否读取:0 未读取 1:已读取
     */
    @Column(name = "is_read")
    private Boolean isRead;

    @Transient
    private Long replayCount;

    public String getMessageToUserName() {
        return messageToUserName;
    }

    public void setMessageToUserName(String messageToUserName) {
        this.messageToUserName = messageToUserName;
    }

    public String getMessageFromUserName() {
        return messageFromUserName;
    }

    public void setMessageFromUserName(String messageFromUserName) {
        this.messageFromUserName = messageFromUserName;
    }

    /**
     * @return message_id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取留言内容
     *
     * @return message_content - 留言内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置留言内容
     *
     * @param messageContent 留言内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * 获取接收留言用户id
     *
     * @return message_to_user_id - 接收留言用户id
     */
    public Integer getMessageToUserId() {
        return messageToUserId;
    }

    /**
     * 设置接收留言用户id
     *
     * @param messageToUserId 接收留言用户id
     */
    public void setMessageToUserId(Integer messageToUserId) {
        this.messageToUserId = messageToUserId;
    }

    /**
     * 获取留言用户id
     *
     * @return message_from_user_id - 留言用户id
     */
    public Integer getMessageFromUserId() {
        return messageFromUserId;
    }

    /**
     * 设置留言用户id
     *
     * @param messageFromUserId 留言用户id
     */
    public void setMessageFromUserId(Integer messageFromUserId) {
        this.messageFromUserId = messageFromUserId;
    }

    /**
     * 获取留言时间
     *
     * @return message_date_time - 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getMessageDateTime() {
        return messageDateTime;
    }

    /**
     * 设置留言时间
     *
     * @param messageDateTime 留言时间
     */
    public void setMessageDateTime(Date messageDateTime) {
        this.messageDateTime = messageDateTime;
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

    public Long getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(Long replayCount) {
        this.replayCount = replayCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
