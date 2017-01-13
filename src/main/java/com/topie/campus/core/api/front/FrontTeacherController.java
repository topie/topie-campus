package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.*;
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
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/front/teacher")
public class FrontTeacherController {

    @Autowired
    IStudentScoreService studentScoreService;

    @Autowired
    IStuCetService stuCetService;

    @Autowired
    IStuSeleCourseService stuSeleCourseService;

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
    public Result student(TeacherSimpleDto teacherSimpleDto,
            @RequestParam(value = "typeId", required = false) Integer typeId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户无权查看");
        }
        SimplePageInfo<TeacherSimpleDto> pageInfo = iTeacherService
                .findTeacherByStudentNo(studentId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/leaderPage", method = RequestMethod.GET)
    @ResponseBody
    public Result leaderPage(TeacherSimpleDto teacherSimpleDto,
            @RequestParam(value = "typeId", required = false) Integer typeId, Integer studentId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户无权查看");
        }
        SimplePageInfo<TeacherSimpleDto> pageInfo = iTeacherService
                .findTeacherByStudentNo(studentId, pageNum, pageSize);
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
        iAtMeService.insertByMessage(message);
        iUserNotificationService.insertOrUpdateToIncrNewMessageCount(toUserId, userId, studentName);
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
    
    @RequestMapping("/examCourse")
    @ResponseBody
    public Result examCourse(StuSeleCourse stuSeleCourse, int pageSize, int pageNum) {
        stuSeleCourse.setExamTime("filter");
        SimplePageInfo<StuSeleCourse> pageInfo = stuSeleCourseService.findByPage(pageNum, pageSize, stuSeleCourse);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
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

    @RequestMapping("/stuScore")
    @ResponseBody
    public Result findByPage(StuScore stuScore, @RequestParam("studentNo") String studentNo, int pageSize,
            int pageNum) {
        stuScore.setStuId(studentNo);
        SimplePageInfo<StuScore> pageInfo = studentScoreService.findByPage(pageNum, pageSize, stuScore);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/stuCetScore")
    @ResponseBody
    public Result findByPage(StuCet stuCet, @RequestParam("studentNo") String studentNo, int pageSize, int pageNum) {
        stuCet.setStuId(studentNo);
        SimplePageInfo<StuCet> pageInfo = stuCetService.findByPage(pageNum, pageSize, stuCet);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/stuSelectCourse")
    @ResponseBody
    public Result selectCourse(StuSeleCourse stuSeleCourse, @RequestParam("studentNo") String studentNo, int pageSize,
            int pageNum) {
        stuSeleCourse.setStuId(studentNo);
        SimplePageInfo<StuSeleCourse> pageInfo = stuSeleCourseService.findByPage(pageNum, pageSize, stuSeleCourse);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/updateStp")
    @ResponseBody
    public Result updateStp(@RequestParam("studentId") Integer studentId,
            @RequestParam("contactPhone") String contactPhone) {
        Student student = new Student();
        student.setContactPhone(contactPhone);
        student.setId(studentId);
        iStudentService.updateSelective(student);
        return ResponseUtil.success();
    }

}
