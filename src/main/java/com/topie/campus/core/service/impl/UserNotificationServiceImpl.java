package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.core.dao.UserNotificationMapper;
import com.topie.campus.core.model.UserNotification;
import com.topie.campus.core.service.IUserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/25.
 */
@Service
public class UserNotificationServiceImpl extends BaseService<UserNotification> implements IUserNotificationService {

    @Autowired
    UserNotificationMapper userNotificationMapper;

    @Override
    public int insertOrUpdateToIncrNewMessageCount(Integer userId, Integer sendUserId, String name) {
        return userNotificationMapper.insertOrUpdateToIncrNewMessageCount(userId, sendUserId, name);
    }

    @Override
    public int insertOrUpdateToIncrNewReplyCount(Integer userId, Integer sendUserId, String name) {
        return userNotificationMapper.insertOrUpdateToIncrNewReplyCount(userId, sendUserId, name);
    }

    @Override
    public int updateToClearNewMessageCount(Integer userId) {
        return userNotificationMapper.updateToClearNewMessageCount(userId);
    }

    @Override
    public int updateToClearNewReplyCount(Integer userId) {
        return userNotificationMapper.updateToClearNewReplyCount(userId);
    }

    @Override
    public List<UserNotification> selectMessageTop5(Integer userId) {
        return userNotificationMapper.selectMessageTop5(userId);
    }

    @Override
    public List<UserNotification> selectReplyTop5(Integer userId) {
        return userNotificationMapper.selectReplyTop5(userId);
    }
}
