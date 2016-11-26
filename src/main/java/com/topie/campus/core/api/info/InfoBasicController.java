package com.topie.campus.core.api.info;

import java.util.List;
import java.util.Map;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TreeDto;
import com.topie.campus.core.model.UserFaculty;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.core.service.IStuCetService;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.core.service.IStuTimeTableService;
import com.topie.campus.core.service.IStudentScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenguojun on 9/26/16.
 */
@Controller
@RequestMapping("/api/info/basic")
public class InfoBasicController {

    @Autowired
    private IInfoBasicService iInfoBasicService;
    
    @Autowired
    private IStudentScoreService  studentScoreService;
    
    @Autowired
    IStuTimeTableService   stuTimeTableService;
    
    @Autowired
    IStuCetService  stuCetService;
    
    @Autowired
    IStuSeleCourseService stuSeleCourseService;


    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    @ResponseBody
    public Result bind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "studentId") Integer studentId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        iInfoBasicService.insertToBindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }
    
    @RequestMapping(value = "/deleteData")
    @ResponseBody
    public Result deleteData(@RequestParam(value = "clearType") Integer clearType,
    		String studyYear,
            String studyYearNum) {
    	Integer num = null ;
    	if(clearType==1)
    	{
    		num = studentScoreService.deleteByStudyYearAndStudyYearNum(studyYear, studyYearNum);
    	}
    	else if(clearType==2)
    	{
    		num = stuTimeTableService.deleteByStudyYearAndStudyYearNum(studyYear, studyYearNum);
    	}
    	else if(clearType==3)
    	{
    		num = stuCetService.deleteByStudyYearAndStudyYearNum(studyYear, studyYearNum);
    	}
    	else if(clearType==4)
    	{
    		num = stuSeleCourseService.deleteByStudyYearAndStudyYearNum(studyYear, studyYearNum);
    	}
    	else if(clearType==5)
    	{
    		num = iInfoBasicService.deleteByStudyYearAndStudyYearNum();
    	}
    	if(num==null)
    		num=0;
        return ResponseUtil.success("清除成功!");
    }


    @RequestMapping(value = "/unbind", method = RequestMethod.GET)
    @ResponseBody
    public Result unbind(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "studentId") Integer studentId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        iInfoBasicService.deleteToUnbindStudentTeacher(typeId, studentId, teacherId);
        return ResponseUtil.success();
    }
    
    @RequestMapping(value = "/collegeTree")
    @ResponseBody
    public List<TreeDto> collegeTree() {
    	 List<TreeDto> collegeTrees = iInfoBasicService.collegeTree();
        return collegeTrees;
    }
    
    
    
    @RequestMapping(value = "/teacherSendMsg")
    @ResponseBody
    public Result teacherSendMsg(String message,String reciever,String sign) {
    	boolean status = iInfoBasicService.teacherSendMsg(message,reciever,sign);
    	if(status)
        return ResponseUtil.success("发送成功！");
    	else
    	return ResponseUtil.error("发送失败！网络异常！");
    }
    
    @RequestMapping(value = "/sendMsg")
    @ResponseBody
    public Result sendMsg(String message,String reciever,String sign) {
    	iInfoBasicService.sendMsg(message,reciever,sign);
        return ResponseUtil.success("发送成功！");
    }
    
    @RequestMapping(value = "/setFaculty")
    @ResponseBody
    public Result setFaculty(Integer userId,String faculty) {
    	iInfoBasicService.insertUserFacultyRation(userId,faculty);
        return ResponseUtil.success("设置成功");
    }
    
    @RequestMapping(value = "/getFacultyByUserId")
    @ResponseBody
    public Result getFacultyByUserId(Integer userId) {
        return ResponseUtil.success(iInfoBasicService.getUserFacultyRation(userId));
    }
}
