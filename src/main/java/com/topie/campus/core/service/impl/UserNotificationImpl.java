package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.core.dao.UserNotificationMapper;
import com.topie.campus.core.model.UserNotification;
import com.topie.campus.core.service.IUserNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenguojun on 2016/10/25.
 */
@Service
public class UserNotificationImpl extends BaseService<UserNotification> implements IUserNotification {

    @Autowired
    UserNotificationMapper userNotificationMapper;

    @Override
    public int insertOrUpdateToIncrNewMessageCount(Integer userId) {
        return userNotificationMapper.insertOrUpdateToIncrNewMessageCount(userId);
    }

    @Override
    public int insertOrUpdateToIncrNewReplyCount(Integer userId) {
        return userNotificationMapper.insertOrUpdateToIncrNewReplyCount(userId);
    }

    @Override
    public int updateToClearNewMessageCount(Integer userId) {
        return userNotificationMapper.updateToClearNewMessageCount(userId);
    }

    @Override
    public int updateToClearNewReplyCount(Integer userId) {
        return userNotificationMapper.updateToClearNewReplyCount(userId);
    }
}
