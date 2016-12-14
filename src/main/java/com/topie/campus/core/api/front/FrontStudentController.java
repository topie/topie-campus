package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.TreeNode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.service.*;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/front/student")
public class FrontStudentController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private IAtMeService iAtMeService;

    @Autowired
    private IUserNotificationService iUserNotificationService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result student(StudentSimpleDto studentSimpleDto, @RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        if (teacherId == null) {
            return ResponseUtil.error(401, "当前用户非教师角色");
        }
        SimplePageInfo<StudentSimpleDto> pageInfo = iInfoBasicService
                .findStudentSimpleDtoListWithBindInfo(studentSimpleDto, typeId, teacherId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/leaderPage", method = RequestMethod.GET)
    @ResponseBody
    public Result leaderPage(StudentSimpleDto studentSimpleDto, Integer teacherId,
            @RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        if (teacherId == null) {
            return ResponseUtil.error(401, "当前用户无此权限角色");
        }
        SimplePageInfo<StudentSimpleDto> pageInfo = iInfoBasicService
                .findStudentSimpleDtoListWithBindInfo(studentSimpleDto, typeId, teacherId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public Result profile(@RequestParam("studentId") Integer studentId) {
        Student student = iStudentService.findByStudentId(studentId);
        return ResponseUtil.success(student);
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public Result postMessage(@RequestParam("toUserId") Integer toUserId,
            @RequestParam("messageContent") String messageContent) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String teacherName = iTeacherService.findTeacherNameByUserId(userId);
        String studentName = iStudentService.findStudentNameByUserId(toUserId);
        Message message = new Message();
        message.setIsRead(false);
        message.setMessageFromUserId(userId);
        message.setMessageFromUserName(teacherName);
        message.setMessageToUserId(toUserId);
        message.setMessageToUserName(studentName);
        message.setMessageDateTime(new Date());
        message.setMessageContent(messageContent);
        message.setUpdateTime(new Date());
        iMessageService.insertSelective(message);
        iUserNotificationService.insertOrUpdateToIncrNewMessageCount(toUserId, userId, teacherName);
        iAtMeService.insertByMessage(message);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/postGroupMessage", method = RequestMethod.POST)
    @ResponseBody
    public Result postGroupMessage(@RequestParam("toUserIds") String toUserIds,
            @RequestParam("messageContent") String messageContent) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String[] toUserIdArray = toUserIds.split(",");
        for (String toUserIdString : toUserIdArray) {
            Integer toUserId = Integer.valueOf(toUserIdString);
            String teacherName = iTeacherService.findTeacherNameByUserId(userId);
            String studentName = iStudentService.findStudentNameByUserId(toUserId);
            Message message = new Message();
            message.setIsRead(false);
            message.setMessageFromUserId(userId);
            message.setMessageFromUserName(teacherName);
            message.setMessageToUserId(toUserId);
            message.setMessageToUserName(studentName);
            message.setMessageDateTime(new Date());
            message.setMessageContent(messageContent);
            message.setUpdateTime(new Date());
            iMessageService.insertSelective(message);
            iUserNotificationService.insertOrUpdateToIncrNewMessageCount(toUserId, userId, teacherName);
            iAtMeService.insertByMessage(message);
        }
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/bindStudent", method = RequestMethod.GET)
    @ResponseBody
    public Result bind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "studentId") Integer studentId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        iInfoBasicService.insertToBindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/unbindStudent", method = RequestMethod.GET)
    @ResponseBody
    public Result unbind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "studentId") Integer studentId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        iInfoBasicService.deleteToUnbindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/treeNodes", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object treeNodes(@RequestParam(value = "typeId", required = false) Integer typeId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        if (teacherId == null) {
            return ResponseUtil.error(401, "当前用户非教师角色");
        }
        if (typeId != null) {
            List<TreeNode> list = iInfoBasicService.getStudentTreeNodes(typeId, teacherId);
            return list;
        }
        return Collections.emptyList();
    }
}
