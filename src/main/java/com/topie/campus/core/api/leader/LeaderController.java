package com.topie.campus.core.api.leader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.service.IStudentService;
import com.topie.campus.security.utils.SecurityUtil;

@Controller
@RequestMapping("/api/leader/student")
public class LeaderController {

	@Autowired
	IStudentService studentService;
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(Integer pageNum,Integer pageSize,Student student)
	{
		Integer userId = SecurityUtil.getCurrentUserId();
		SimplePageInfo<Student> students = studentService.findByLeadRole(userId,pageNum,pageSize,student);
		return ResponseUtil.success(PageConvertUtil.grid(students));
	}
}
