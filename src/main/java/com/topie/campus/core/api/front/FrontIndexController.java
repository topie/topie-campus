package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.TreeNode;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.model.Notice;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.model.UserNotification;
import com.topie.campus.core.service.*;
import com.topie.campus.security.SecurityConstant;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.tools.redis.RedisCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 9/24/16.
 */
@Controller
@RequestMapping("/api/frontIndex")
public class FrontIndexController {

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private INoticeService iNoticeService;

    @Autowired
    private IUserNotificationService iUserNotificationService;

    @Autowired
    private IMessageReplyService iMessageReplyService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public Result myFunction() {
        String currentUserName = SecurityUtil.getCurrentUserName();
        if (StringUtils.isEmpty(currentUserName)) {
            throw new AuthBusinessException(AuBzConstant.IS_NOT_LOGIN);
        }
        List<TreeNode> function = (List<TreeNode>) redisCache
                .get(SecurityConstant.FUNCTION_CACHE_FRONT_PREFIX + currentUserName);
        if (function == null) {
            function = userService.findUserFunctionByLoginNameAndDisplayType(currentUserName, SecurityConstant.FRONT);
            redisCache.set(SecurityConstant.FUNCTION_CACHE_FRONT_PREFIX + currentUserName, function);
        }
        return ResponseUtil.success(function);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public Result profile() {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        List<Integer> roles = SecurityUtil.getCurrentRoles();
        Integer role = 0;
        Map result = new HashMap<>();
        if (roles.contains(SecurityConstant.ROLE_TEACHER)) {
            Teacher teacher = iTeacherService.findTeacherByUserId(userId);
            role = SecurityConstant.ROLE_TEACHER;
            result.put("data", teacher);
        } else if (roles.contains(SecurityConstant.ROLE_STUDENT)) {
            Student student = iStudentService.findStudentByUserId(userId);
            student = iStudentService.findByStudentId(student.getId());
            role = SecurityConstant.ROLE_STUDENT;
            result.put("data", student);
        }else if (roles.contains(SecurityConstant.ROLE_LEADER)) {
            role = SecurityConstant.ROLE_LEADER;
            User user = userService.findUserById(userId);
            result.put("data", user);
        }
        result.put("role", role);
        return ResponseUtil.success(result);
    }

    @RequestMapping(value = "/messageCenter", method = RequestMethod.GET)
    @ResponseBody
    public Result messageCenter() {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Map result = new HashMap<>();
        List<UserNotification> messageList = iUserNotificationService.selectMessageTop5(userId);
        result.put("message", messageList);
        List<UserNotification> replyList = iUserNotificationService.selectReplyTop5(userId);
        result.put("reply", replyList);
        List<Map> messageStat = iMessageService.findReceiveMessageStat(userId);
        result.put("messageStat", messageStat);
        List<Map> replyStat = iMessageReplyService.findMessageReplyStat(userId);
        result.put("replyStat", replyStat);
        return ResponseUtil.success(result);
    }

    @RequestMapping(value = "/noticeTop5", method = RequestMethod.GET)
    @ResponseBody
    public Result noticeTop5() {
        SimplePageInfo<Notice> pageInfo = iNoticeService.findNoticeList(new Notice(), 1, 5);
        return ResponseUtil.success(pageInfo.getList());
    }
}
