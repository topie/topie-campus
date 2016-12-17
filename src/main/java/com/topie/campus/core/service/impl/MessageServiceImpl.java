package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.MessageMapper;
import com.topie.campus.core.dto.MessageSearchParams;
import com.topie.campus.core.dto.MessageSimpleDto;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class MessageServiceImpl extends BaseService<Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public SimplePageInfo<Message> findReceiveMessageListByPage(Integer toUserId, int pageNum, int pageSize,
            int sortType) {
        Message arg = new Message();
        arg.setMessageToUserId(toUserId);
        List<Message> list = messageMapper
                .findMessageByPageNumAndPageSize(arg, (pageNum - 1) * pageSize, pageSize, sortType);
        Long total = messageMapper.countMessage(arg);
        SimplePageInfo<Message> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<Message> findSendMessageListByPage(Integer fromUserId, int pageNum, int pageSize,
            int sortType) {
        Message arg = new Message();
        arg.setMessageFromUserId(fromUserId);
        List<Message> list = messageMapper
                .findMessageByPageNumAndPageSize(arg, (pageNum - 1) * pageSize, pageSize, sortType);
        Long total = messageMapper.countMessage(arg);
        SimplePageInfo<Message> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public int updateMessageUpdateTime(Integer messageId) {
        return messageMapper.updateTimeByPrimaryKey(messageId);
    }

    @Override
    public List<Map> findReceiveMessageStat(Integer userId) {
        return messageMapper.findReceiveMessageStat(userId);
    }

    @Override
    public SimplePageInfo<MessageSimpleDto> findMessageByPage(MessageSearchParams messageSearchParams, int pageNum,
            int pageSize) {
        List<MessageSimpleDto> list = messageMapper
                .findMessageDtoByPageNumAndPageSize(messageSearchParams, (pageNum - 1) * pageSize, pageSize);
        Long total = messageMapper.countMessageDto(messageSearchParams);
        SimplePageInfo<MessageSimpleDto> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public List<MessageSimpleDto> findMessageList(MessageSearchParams messageSearchParams) {
        List<MessageSimpleDto> list = messageMapper
                .findMessageDtoList(messageSearchParams);
        return list;
    }

    @Override
    public String selectContentByReplyId(Integer contentId) {
        return messageMapper.selectContentByReplyId(contentId);
    }

    @Override
    public Integer selectIdByReplyId(Integer contentId) {
        return messageMapper.selectIdByReplyId(contentId);
    }

    @Override
    public Message selectByReplyId(Integer contentId) {
        return messageMapper.selectByReplyId(contentId);
    }

}
