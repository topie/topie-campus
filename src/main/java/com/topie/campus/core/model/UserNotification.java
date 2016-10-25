package com.topie.campus.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user_notification")
public class UserNotification {

    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 新的留言数
     */
    @Column(name = "new_message_count")
    private Integer newMessageCount;

    /**
     * 新的回复数
     */
    @Column(name = "new_reply_count")
    private Integer newReplyCount;

    public UserNotification() {
    }

    public UserNotification(Integer userId, int newMessageCount, int newReplyCount) {
        this.userId = userId;
        this.newMessageCount = newMessageCount;
        this.newReplyCount = newReplyCount;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取新的留言数
     *
     * @return new_message_count - 新的留言数
     */
    public Integer getNewMessageCount() {
        return newMessageCount;
    }

    /**
     * 设置新的留言数
     *
     * @param newMessageCount 新的留言数
     */
    public void setNewMessageCount(Integer newMessageCount) {
        this.newMessageCount = newMessageCount;
    }

    /**
     * 获取新的回复数
     *
     * @return new_reply_count - 新的回复数
     */
    public Integer getNewReplyCount() {
        return newReplyCount;
    }

    /**
     * 设置新的回复数
     *
     * @param newReplyCount 新的回复数
     */
    public void setNewReplyCount(Integer newReplyCount) {
        this.newReplyCount = newReplyCount;
    }
}
