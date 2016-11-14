package com.topie.campus.core.dto;

/**
 * Created by chenguojun on 2016/11/13.
 */
public class MessageSearchParams {

    private String messageTimeFrom;

    private String messageTimeTo;

    private String replyTimeFrom;

    private String replyTimeTo;

    public String getReplyTimeFrom() {
        return replyTimeFrom;
    }

    public void setReplyTimeFrom(String replyTimeFrom) {
        this.replyTimeFrom = replyTimeFrom;
    }

    public String getReplyTimeTo() {
        return replyTimeTo;
    }

    public void setReplyTimeTo(String replyTimeTo) {
        this.replyTimeTo = replyTimeTo;
    }

    public String getMessageTimeFrom() {
        return messageTimeFrom;
    }

    public void setMessageTimeFrom(String messageTimeFrom) {
        this.messageTimeFrom = messageTimeFrom;
    }

    public String getMessageTimeTo() {
        return messageTimeTo;
    }

    public void setMessageTimeTo(String messageTimeTo) {
        this.messageTimeTo = messageTimeTo;
    }
}
