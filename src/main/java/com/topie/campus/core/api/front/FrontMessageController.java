package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.service.IMessageService;
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

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public Result receive(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SimplePageInfo<Message> pageInfo = iMessageService.findReceiveMessageListByPage(userId, pageNum, pageSize);
        return ResponseUtil.success(pageInfo);
    }


    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public Result send(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SimplePageInfo<Message> pageInfo = iMessageService.findSendMessageListByPage(userId, pageNum, pageSize);
        return ResponseUtil.success(pageInfo);
    }

}