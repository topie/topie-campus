package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.core.model.UserNotification;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/25.
 */
public interface IUserNotificationService extends IService<UserNotification> {

    int insertOrUpdateToIncrNewMessageCount(Integer userId, Integer sendUserId, String name);

    int insertOrUpdateToIncrNewReplyCount(Integer userId, Integer sendUserId, String name);

    int updateToClearNewMessageCount(Integer userId);

    int updateToClearNewReplyCount(Integer userId);

    List<UserNotification> selectMessageTop5(Integer userId);

    List<UserNotification> selectReplyTop5(Integer userId);
}
