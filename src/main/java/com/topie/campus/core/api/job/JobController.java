package com.topie.campus.core.api.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.service.IEmploymentService;
import com.topie.campus.security.model.User;

/**
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private IEmploymentService employmentService;
	
	 @RequestMapping(value = "/page", method = RequestMethod.GET)
	    @ResponseBody
	    public Result users(Employment employMent,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
		 SimplePageInfo<Employment> pageInfo = employmentService.findByPage(pageNum, pageSize, employMent);
	        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	    }
	 
	 @RequestMapping(value = "/load/{employId}", method = RequestMethod.GET)
	    @ResponseBody
	    public Result load(@PathVariable("employId") String id) {
		 Employment emp = employmentService.selectByKey(id);
		 return ResponseUtil.success(emp);
	    }
}
