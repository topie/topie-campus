package com.topie.campus.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.topie.campus.tools.excel.ExcelCell;

import java.util.Date;

/**
 * Created by chenguojun on 2016/11/13.
 */
public class MessageSimpleDto {

    @ExcelCell(index = 0)
    private Integer messageId;

    @ExcelCell(index = 1)
    private String messageContent;

    @ExcelCell(index = 2)
    private String messageToUserName;

    @ExcelCell(index = 3)
    private String messageFromUserName;

    @ExcelCell(index = 4)
    private Date messageDateTime;

    @ExcelCell(index = 5)
    private String replyContent;

    @ExcelCell(index = 6)
    private String replyUserName;

    @ExcelCell(index = 7)
    private Date replyDateTime;

    public String getReplyContent() {
        return replyContent == null ? "-" : replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyUserName() {
        return replyUserName == null ? "-" : replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getReplyDateTime() {
        return replyDateTime;
    }

    public void setReplyDateTime(Date replyDateTime) {
        this.replyDateTime = replyDateTime;
    }

    public String getMessageContentShort() {
        return messageContent.length() > 15 ? messageContent.substring(0, 15) + "..." : messageContent;
    }

    public String getReplyContentShort() {
        if (replyContent == null) return "-";
        return replyContent.length() > 15 ? replyContent.substring(0, 15) + "..." : replyContent;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Date messageDateTime) {
        this.messageDateTime = messageDateTime;
    }
}
