package com.topie.campus.core.dao;

import com.topie.campus.core.model.UserNotification;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserNotificationMapper extends Mapper<UserNotification> {

    int insertOrUpdateToIncrNewMessageCount(@Param("userId") Integer userId, @Param("sendUserId") Integer sendUserId,
            @Param("name") String name);

    int insertOrUpdateToIncrNewReplyCount(@Param("userId") Integer userId, @Param("sendUserId") Integer sendUserId,
            @Param("name") String name);

    int updateToClearNewMessageCount(@Param("userId") Integer userId);

    int updateToClearNewReplyCount(@Param("userId") Integer userId);

    List<UserNotification> selectMessageTop5(@Param("userId") Integer userId);

    List<UserNotification> selectReplyTop5(@Param("userId") Integer userId);
}
