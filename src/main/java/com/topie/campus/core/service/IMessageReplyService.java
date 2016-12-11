package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.model.MessageReply;

import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface IMessageReplyService extends IService<MessageReply> {

    SimplePageInfo<MessageReply> findMessageReplyList(MessageReply messageReply, Integer pageNum, Integer pageSize);

    Long countMessageReply(MessageReply messageReply);

    Long countMessageReplyByMessageId(Integer messageId);

    List<Map> findMessageReplyStat(Integer userId);

    int insertByAtMe(AtMe atMe, String replyContent, Integer userId, String userName);
}
