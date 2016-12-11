package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.MessageReply;
import com.topie.campus.core.service.*;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by chenguojun on 9/26/16.
 */
@Controller
@RequestMapping("/api/front/message")
public class FrontMessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private IAtMeService iAtMeService;

    @Autowired
    private IMessageReplyService iMessageReplyService;

    @Autowired
    private IUserNotificationService iUserNotificationService;

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public Result receive(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
            @RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SimplePageInfo<Message> pageInfo = iMessageService
                .findReceiveMessageListByPage(userId, pageNum, pageSize, sortType);
        for (Message message : pageInfo.getList()) {
            Long count = iMessageReplyService.countMessageReplyByMessageId(message.getMessageId());
            message.setReplayCount(count);
        }
        iUserNotificationService.updateToClearNewMessageCount(userId);
        return ResponseUtil.success(pageInfo);
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public Result send(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
            @RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SimplePageInfo<Message> pageInfo = iMessageService
                .findSendMessageListByPage(userId, pageNum, pageSize, sortType);
        for (Message message : pageInfo.getList()) {
            Long count = iMessageReplyService.countMessageReplyByMessageId(message.getMessageId());
            message.setReplayCount(count);
        }
        //iUserNotificationService.updateToClearNewReplyCount(userId);
        return ResponseUtil.success(pageInfo);
    }

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    @ResponseBody
    public Result reply(@RequestParam(value = "messageId") Integer messageId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        MessageReply messageReply = new MessageReply();
        messageReply.setMessageId(messageId);
        SimplePageInfo<MessageReply> pageInfo = iMessageReplyService
                .findMessageReplyList(messageReply, pageNum, pageSize);
        return ResponseUtil.success(pageInfo);
    }

    @RequestMapping(value = "/postReply", method = RequestMethod.POST)
    @ResponseBody
    public Result postReply(@RequestParam("messageId") Integer messageId,
            @RequestParam("replyContent") String replyContent) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String name = iTeacherService.findTeacherNameByUserId(userId);
        if (name == null) {
            name = iStudentService.findStudentNameByUserId(userId);
        }
        MessageReply messageReply = new MessageReply();
        messageReply.setMessageId(messageId);
        messageReply.setReplyUserId(userId);
        messageReply.setReplyUserName(name);
        messageReply.setReplyDateTime(new Date());
        messageReply.setIsRead(false);
        messageReply.setReplyContent(replyContent);
        iMessageReplyService.insertSelective(messageReply);
        iMessageService.updateMessageUpdateTime(messageId);
        Message message = iMessageService.selectByKey(messageId);
        Integer receiveUser = message.getMessageFromUserId();
        if (userId.intValue() != receiveUser.intValue()) {
            iAtMeService.insertByReply(receiveUser,messageReply);
            iUserNotificationService.insertOrUpdateToIncrNewReplyCount(receiveUser,userId,name);
        }
        return ResponseUtil.success();
    }
}
