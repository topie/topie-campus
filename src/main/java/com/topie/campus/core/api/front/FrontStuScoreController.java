package com.topie.campus.core.api.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.StuScore;
import com.topie.campus.core.service.IStudentScoreService;
import com.topie.campus.security.model.User;
import com.topie.campus.security.utils.SecurityUtil;

@Controller
@RequestMapping("/api/front/student")
public class FrontStuScoreController {
	
	@Autowired
	IStudentScoreService studentScoreService;
	
	@RequestMapping("/score")
	@ResponseBody
	public Result findByPage(StuScore stuScore,int pageSize ,int pageNum)
	{
		String  loginName  = SecurityUtil.getCurrentSecurityUser().getLoginName();
		stuScore.setStuId(loginName);
		SimplePageInfo<StuScore> pageInfo = studentScoreService.findByPage(pageNum, pageSize, stuScore);
		return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	}
}
