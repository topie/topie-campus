package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.AtMeMapper;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.MessageReply;
import com.topie.campus.core.service.IAtMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/12/10.
 */
@Service
public class AtMeServiceImpl extends BaseService<AtMe> implements IAtMeService {

    private static final Integer MESSAGE = 1;

    private static final Integer REPLY = 2;

    @Autowired
    private AtMeMapper atMeMapper;

    @Override
    public SimplePageInfo<AtMe> selectRecordByPage(AtMe atMe, Integer pageNum, Integer pageSize) {
        List<AtMe> list = atMeMapper.selectRecordByLimitOffset(atMe, (pageNum - 1) * pageSize, pageSize);
        Long total = atMeMapper.count(atMe);
        SimplePageInfo<AtMe> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public int insertByMessage(Message message) {
        AtMe atMe = new AtMe();
        atMe.setToUserId(message.getMessageToUserId());
        atMe.setFromUserId(message.getMessageFromUserId());
        atMe.setFromUserName(message.getMessageFromUserName());
        atMe.setAtTime(message.getMessageDateTime());
        atMe.setContent(message.getMessageContent());
        atMe.setContentId(message.getMessageId());
        atMe.setContentType(MESSAGE);
        return insertSelective(atMe);
    }

    @Override
    public int insertByReply(Integer receiveUser, MessageReply messageReply) {
        AtMe atMe = new AtMe();
        atMe.setToUserId(receiveUser);
        atMe.setFromUserId(messageReply.getReplyUserId());
        atMe.setFromUserName(messageReply.getReplyUserName());
        atMe.setAtTime(messageReply.getReplyDateTime());
        atMe.setContent(messageReply.getReplyContent());
        atMe.setContentId(messageReply.getReplyId());
        atMe.setContentType(REPLY);
        return insertSelective(atMe);
    }
}
