package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.MessageReplyMapper;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.MessageReply;
import com.topie.campus.core.service.IAtMeService;
import com.topie.campus.core.service.IMessageReplyService;
import com.topie.campus.core.service.IMessageService;
import com.topie.campus.core.service.IUserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class MessageReplyServiceImpl extends BaseService<MessageReply> implements IMessageReplyService {

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private IAtMeService iAtMeService;

    @Autowired
    private IUserNotificationService iUserNotificationService;

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

    @Override
    public List<Map> findMessageReplyStat(Integer userId) {
        return messageReplyMapper.findMessageReplyStat(userId);
    }

    @Override
    public int insertByAtMe(AtMe atMe, String replyContent, Integer userId, String userName) {
        if (atMe.getContentType() == 1) {
            MessageReply messageReply = new MessageReply();
            messageReply.setMessageId(atMe.getContentId());
            messageReply.setReplyUserId(userId);
            messageReply.setReplyUserName(userName);
            messageReply.setReplyDateTime(new Date());
            messageReply.setIsRead(false);
            messageReply.setReplyContent(replyContent);
            insertSelective(messageReply);
            iMessageService.updateMessageUpdateTime(atMe.getContentId());
            Message message = iMessageService.selectByKey(atMe.getContentId());
            Integer receiveUser = message.getMessageFromUserId();
            iUserNotificationService.insertOrUpdateToIncrNewReplyCount(receiveUser,userId,userName);
            iAtMeService.insertByReply(receiveUser,messageReply);
        } else {
            Message message = iMessageService.selectByReplyId(atMe.getContentId());
            MessageReply messageReply = new MessageReply();
            messageReply.setMessageId(message.getMessageId());
            messageReply.setReplyUserId(userId);
            messageReply.setReplyUserName(userName);
            messageReply.setReplyDateTime(new Date());
            messageReply.setIsRead(false);
            messageReply.setReplyContent(replyContent);
            insertSelective(messageReply);
            iMessageService.updateMessageUpdateTime(message.getMessageId());
            Integer receiveUser = message.getMessageFromUserId();
            if (userId.intValue() != receiveUser.intValue()) {
                iUserNotificationService.insertOrUpdateToIncrNewReplyCount(receiveUser,userId,userName);
            }
            MessageReply fromMessageReply = selectByKey(atMe.getContentId());
            receiveUser = fromMessageReply.getReplyUserId();
            iAtMeService.insertByReply(receiveUser,messageReply);
        }
        return 1;
    }
}
