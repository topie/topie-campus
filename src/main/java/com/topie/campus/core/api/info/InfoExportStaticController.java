package com.topie.campus.core.api.info;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Msg;
import com.topie.campus.core.model.PlanSummary;
import com.topie.campus.core.model.RecordExcel;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.core.service.IMsgService;
import com.topie.campus.core.service.IPlanSummaryService;
import com.topie.campus.core.service.IStudentRecordService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.core.service.impl.StudentRecordService;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelUtil;

@Controller
@RequestMapping("/api/info/exportStatic")
public class InfoExportStaticController {

	@Autowired
	IMsgService msgService;
	
	@Autowired
	IInfoBasicService iInfoBasicService;
	
	@Autowired
	IStudentRecordService studentRecordService;
	
	@Autowired
	ITeacherService teacherService;
	
	@Autowired
	IPlanSummaryService planSummaryService;
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(int pageNum,int pageSize,Msg msg)
	{
		SimplePageInfo<Msg> pageInfo = msgService.findByPage(pageNum, pageSize, msg);
		return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	}
	
	@RequestMapping("/planList")
    @ResponseBody
    public Result findByPage(PlanSummary planSummary, int pageSize, int pageNum) {
        SimplePageInfo<PlanSummary> pageInfo = planSummaryService.findByPage(pageNum, pageSize, planSummary);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
	
	@RequestMapping("/teacherPage")
	@ResponseBody
    public Result page(Teacher teacher,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Teacher> pageInfo = iInfoBasicService.findTeacherList(teacher, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
	
	@RequestMapping("/excelMsg")
	public void exportStatic(HttpServletResponse response,Msg msg)
	{
			String headers[] = {"职工号","导师姓名","发送内容","短信签名","导师类型"};
			List<Msg> msgs = msgService.selectAll();
			try {
				ExcelFileUtil.reponseXlsx(response, "短信内容.xlsx", headers, msgs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@RequestMapping("/excelRecord")
	public void excelRecord(HttpServletResponse response)
	{
			String headers[] = {"学年","学期","职工号","导师姓名","记录内容","学号","学生姓名","记录时间"};
			List<RecordExcel> recordExcels = studentRecordService.findByExcel();
			try {
				ExcelFileUtil.reponseXlsx(response, "指导学生记录.xlsx", headers, recordExcels);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@RequestMapping("/excelPlanSummary")
	public void excelPlanSummary(HttpServletResponse response)
	{
			String headers[] = {"学年","学期","职工号","导师姓名","内容","类型(0,工作计划，1工作总结)","记录时间"};
			List<PlanSummary> recordExcels = planSummaryService.findByExcel();
			try {
				ExcelFileUtil.reponseXlsx(response, "工作计划与总结.xlsx", headers, recordExcels);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
