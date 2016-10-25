package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.core.model.UserNotification;

/**
 * Created by chenguojun on 2016/10/25.
 */
public interface IUserNotification extends IService<UserNotification> {

    int insertOrUpdateToIncrNewMessageCount(Integer userId);

    int insertOrUpdateToIncrNewReplyCount(Integer userId);

    int updateToClearNewMessageCount(Integer userId);

    int updateToClearNewReplyCount(Integer userId);
}
