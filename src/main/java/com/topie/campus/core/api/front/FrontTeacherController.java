package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.core.service.IMessageService;
import com.topie.campus.core.service.IStudentService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/front/teacher")
public class FrontTeacherController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IMessageService iMessageService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result student(TeacherSimpleDto teacherSimpleDto, @RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        SimplePageInfo<TeacherSimpleDto> pageInfo = iInfoBasicService
                .findTeacherSimpleDtoListWithBindInfo(teacherSimpleDto, typeId, studentId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public Result profile(@RequestParam("teacherId") Integer teacherId) {
        Teacher teacher = iTeacherService.selectByKey(teacherId);
        return ResponseUtil.success(teacher);
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    @ResponseBody
    public Result postMessage(@RequestParam("toUserId") Integer toUserId,
            @RequestParam("messageContent") String messageContent) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String teacherName = iTeacherService.findTeacherNameByUserId(toUserId);
        String studentName = iStudentService.findStudentNameByUserId(userId);
        Message message = new Message();
        message.setIsRead(false);
        message.setMessageFromUserId(userId);
        message.setMessageFromUserName(studentName);
        message.setMessageToUserId(toUserId);
        message.setMessageToUserName(teacherName);
        message.setMessageDateTime(new Date());
        message.setMessageContent(messageContent);
        message.setUpdateTime(new Date());
        iMessageService.insertSelective(message);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/bindTeacher", method = RequestMethod.GET)
    @ResponseBody
    public Result bind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        iInfoBasicService.insertToBindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/unbindTeacher", method = RequestMethod.GET)
    @ResponseBody
    public Result unbind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        iInfoBasicService.deleteToUnbindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }
}
