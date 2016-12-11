package com.topie.campus.core.service;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.MessageSearchParams;
import com.topie.campus.core.dto.MessageSimpleDto;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.model.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface IMessageService extends IService<Message> {

    SimplePageInfo<Message> findReceiveMessageListByPage(Integer toUserId, int pageNum, int pageSize, int sortType);

    SimplePageInfo<Message> findSendMessageListByPage(Integer fromUserId, int pageNum, int pageSize, int sortType);

    int updateMessageUpdateTime(Integer messageId);

    List<Map> findReceiveMessageStat(Integer userId);

    SimplePageInfo<MessageSimpleDto> findMessageByPage(MessageSearchParams messageSearchParams, int pageNum,
            int pageSize);

    List<MessageSimpleDto> findMessageList(MessageSearchParams messageSearchParams);

    String selectContentByReplyId(Integer contentId);

    Integer selectIdByReplyId(Integer contentId);

    Message selectByReplyId(Integer contentId);
}
