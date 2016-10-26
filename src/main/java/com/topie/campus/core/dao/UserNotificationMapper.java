package com.topie.campus.core.dao;

import com.topie.campus.core.model.UserNotification;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserNotificationMapper extends Mapper<UserNotification> {

    int insertOrUpdateToIncrNewMessageCount(@Param("userId") Integer userId);

    int insertOrUpdateToIncrNewReplyCount(@Param("userId") Integer userId);

    int updateToClearNewMessageCount(@Param("userId") Integer userId);

    int updateToClearNewReplyCount(@Param("userId") Integer userId);
}
