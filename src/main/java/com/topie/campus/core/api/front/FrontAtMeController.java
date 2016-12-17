package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.service.*;
import com.topie.campus.security.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenguojun on 2016/12/10.
 */
@Controller
@RequestMapping("/api/front/atMe")
public class FrontAtMeController {

    @Autowired
    private IAtMeService iAtMeService;

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private IMessageReplyService iMessageReplyService;

    @Autowired
    private IUserNotificationService iUserNotificationService;

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(@RequestParam(value = "period", required = false) String period,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        AtMe atMe = new AtMe();
        if (StringUtils.isNotEmpty(period)) {
            String[] arr = period.split(" 到 ");
            atMe.setBegin(arr[0]);
            atMe.setEnd(arr[1]);
        }
        if (type != null && type == 1) {
        	atMe.setContentType(type);
            atMe.setFromUserId(SecurityUtil.getCurrentUserId());
        }
        else if(type!=null && type==2)
        {
        	atMe.setContentType(type);
        	atMe.setToUserId(SecurityUtil.getCurrentUserId());
        }
        SimplePageInfo<AtMe> pageInfo = iAtMeService.selectRecordByPage(atMe, pageNum, pageSize);
        iUserNotificationService.updateToClearNewMessageCount(userId);
        iUserNotificationService.updateToClearNewReplyCount(userId);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{atMeId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadAtMe(@PathVariable(value = "atMeId") Integer atMeId) {
        AtMe atMe = iAtMeService.selectByKey(atMeId);
        String referContent = "";
        if (atMe.getContentType() == 2) {
            referContent = iMessageService.selectContentByReplyId(atMe.getContentId());
        }
        atMe.setReferContent(referContent);
        return ResponseUtil.success(atMe);
    }

    @RequestMapping(value = "/postReply", method = RequestMethod.POST)
    @ResponseBody
    public Result postReply(@RequestParam("id") Integer atMeId, @RequestParam("replyContent") String replyContent) {
        AtMe atMe = iAtMeService.selectByKey(atMeId);
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String userName = iTeacherService.findTeacherNameByUserId(userId);
        if (userName == null) {
            userName = iStudentService.findStudentNameByUserId(userId);
        }
        iMessageReplyService.insertByAtMe(atMe, replyContent, userId, userName);
        return ResponseUtil.success();
    }

}
