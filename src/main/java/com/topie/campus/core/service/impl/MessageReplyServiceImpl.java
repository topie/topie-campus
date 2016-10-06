package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.MessageReplyMapper;
import com.topie.campus.core.model.MessageReply;
import com.topie.campus.core.service.IMessageReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class MessageReplyServiceImpl extends BaseService<MessageReply> implements IMessageReplyService {

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Override
    public SimplePageInfo<MessageReply> findMessageReplyList(MessageReply messageReply, Integer pageNum,
            Integer pageSize) {
        List<MessageReply> list = messageReplyMapper
                .findMessageReplyByPageNumAndPageSize(messageReply, (pageNum - 1) * pageSize, pageSize);
        Long total = messageReplyMapper.countMessageReply(messageReply);
        SimplePageInfo<MessageReply> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Long countMessageReply(MessageReply messageReply) {
        return messageReplyMapper.countMessageReply(messageReply);
    }

    @Override
    public Long countMessageReplyByMessageId(Integer messageId) {
        MessageReply messageReply = new MessageReply();
        messageReply.setMessageId(messageId);
        return countMessageReply(messageReply);
    }
}
