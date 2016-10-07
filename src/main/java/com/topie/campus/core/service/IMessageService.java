package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Message;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface IMessageService extends IService<Message> {

    SimplePageInfo<Message> findReceiveMessageListByPage(Integer toUserId, int pageNum, int pageSize, int sortType);

    SimplePageInfo<Message> findSendMessageListByPage(Integer fromUserId, int pageNum, int pageSize, int sortType);

    int updateMessageUpdateTime(Integer messageId);
}
