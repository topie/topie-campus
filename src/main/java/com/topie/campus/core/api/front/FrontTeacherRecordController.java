package com.topie.campus.core.api.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.PlanSummary;
import com.topie.campus.core.model.StudentRecord;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IPlanSummaryService;
import com.topie.campus.core.service.IStudentRecordService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.security.utils.SecurityUtil;

@Controller
@RequestMapping("/api/front/teacherRecord")
public class FrontTeacherRecordController {

	     @Autowired
	    IStudentRecordService stuRecordService;

	    @Autowired
	    IPlanSummaryService planSummaryService;
	    
	    @Autowired
	    ITeacherService teacherService;
	    
	    @RequestMapping("/planList")
	    @ResponseBody
	    public Result findByPage(PlanSummary planSummary, int pageSize, int pageNum) {
	        Integer userId = SecurityUtil.getCurrentUserId();
	        if (userId == null) {
	            return ResponseUtil.error(401, "未登录");
	        }
	        Integer teacherId = teacherService.findTeacherIdByUserId(userId);
	        if (teacherId==null) {
	            return ResponseUtil.error(401, "当前用户错误");
	        }
	        planSummary.setTeacherId(teacherId);
	        SimplePageInfo<PlanSummary> pageInfo = planSummaryService.findByPage(pageNum, pageSize, planSummary);
	        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	    }
	    
	    @RequestMapping("/insertOrUpdatePlan")
	    @ResponseBody
	    public Result insertPlan(PlanSummary planSummary) {
	        Integer userId = SecurityUtil.getCurrentUserId();
	        if (userId == null) {
	            return ResponseUtil.error(401, "未登录");
	        }
	        Teacher teacher = teacherService.findTeacherByUserId(userId);
	        if (teacher==null) {
	            return ResponseUtil.error(401, "当前用户错误");
	        }
	        if(planSummary.getId()!=null)
	        {
	        	planSummaryService.updateSelective(planSummary);
	        }
	        else
	        {
	        planSummary.setTeacherId(teacher.getId());
	        planSummary.setTeacherNo(teacher.getStaffNo());
	        planSummaryService.insert(planSummary);
	        }
	        return ResponseUtil.success("保存成功！");
	    }
	    
	    
	    
	    @RequestMapping("/deletePlan")
	    @ResponseBody
	    public Result deletePlan(Integer id) {
	    	planSummaryService.delete(planSummaryService.selectByKey(id));
	    	return ResponseUtil.success("删除成功！");
	    }
	    
	    @RequestMapping("/loadPlan")
	    @ResponseBody
	    public Result loadPlan(Integer id) {
	    	return ResponseUtil.success(planSummaryService.selectByKey(id));
	    }
	    
	    @RequestMapping("/loadRecord")
	    @ResponseBody
	    public Result loadRecord(Integer id) {
	    	return ResponseUtil.success(stuRecordService.selectByKey(id));
	    }
	    
	    @RequestMapping("/recordList")
	    @ResponseBody
	    public Result recordList(StudentRecord studentRecord, int pageSize, int pageNum) {
	        Integer userId = SecurityUtil.getCurrentUserId();
	        if (userId == null) {
	            return ResponseUtil.error(401, "未登录");
	        }
	        Integer teacherId = teacherService.findTeacherIdByUserId(userId);
	        if (teacherId==null) {
	            return ResponseUtil.error(401, "当前用户错误");
	        }
	        studentRecord.setTeacherId(teacherId);
	        SimplePageInfo<StudentRecord> pageInfo = stuRecordService.findByPage(pageNum, pageSize, studentRecord);
	        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	    }
	    
	    @RequestMapping("/sysRecordList")
	    @ResponseBody
	    public Result sysRecordList(StudentRecord studentRecord, int pageSize, int pageNum) {
	        SimplePageInfo<StudentRecord> pageInfo = stuRecordService.findByPage(pageNum, pageSize, studentRecord);
	        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	    }
	    
	    @RequestMapping("/insertOrUpdateRecord")
	    @ResponseBody
	    public Result insertOrUpdateRecord(StudentRecord studentRecord) {
	        Integer userId = SecurityUtil.getCurrentUserId();
	        if (userId == null) {
	            return ResponseUtil.error(401, "未登录");
	        }
	        Teacher teacher = teacherService.findTeacherByUserId(userId);
	        if (teacher==null) {
	            return ResponseUtil.error(401, "当前用户错误");
	        }
	        if(studentRecord.getId()!=null)
	        {
	        	stuRecordService.updateSelective(studentRecord);
	        }
	        else
	        {
	        	studentRecord.setTeacherId(teacher.getId());
	        	studentRecord.setTeacherNo(teacher.getStaffNo());
	        	stuRecordService.insert(studentRecord);
	        }
	        return ResponseUtil.success("保存成功！");
	    }
	    
	    @RequestMapping("/deleteStudentRecord")
	    @ResponseBody
	    public Result deleteStudentRecord(Integer id) {
	    	stuRecordService.delete(stuRecordService.selectByKey(id));
	    	return ResponseUtil.success("删除成功！");
	    }
}
