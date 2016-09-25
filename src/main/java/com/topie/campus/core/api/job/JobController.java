package com.topie.campus.core.api.job;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.service.IEmploymentService;
import com.topie.campus.security.model.User;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;

/**
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private IEmploymentService employmentService;
	
	@Autowired
    private FreeMarkerUtil freeMarkerUtil;
	
	 @RequestMapping(value = "/page", method = RequestMethod.GET)
	    @ResponseBody
	    public Result users(Employment employMent,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
		 SimplePageInfo<Employment> pageInfo = employmentService.findByPage(pageNum, pageSize, employMent);
	        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	    }
	 
	 @RequestMapping(value = "/importPage", method = RequestMethod.GET)
	    @ResponseBody
	    public Object page() throws Exception {
		   Map params = new HashMap();
	        String html = freeMarkerUtil.getStringFromTemplate("/info/", "jobupload.ftl", params);
	        return ResponseUtil.success(html);
	 }	 
	 @RequestMapping(value = "/load/{employId}", method = RequestMethod.GET)
	    @ResponseBody
	    public Result load(@PathVariable("employId") String id) {
		 Employment emp = employmentService.selectByKey(id);
		 return ResponseUtil.success(emp);
	    }
	 
	 @RequestMapping(value = "/importJobData", method = RequestMethod.POST)
	    @ResponseBody
	    public Result jobUpload(HttpServletResponse response,
	            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
	        if (file == null || file.isEmpty()) {
	            return ResponseUtil.error("附件为空");
	        }
	        ExcelLogs logs = new ExcelLogs();
	        employmentService.employUpload(file, logs);
	        if(logs.getErrorLogList().size()>0)
	        return ResponseUtil.success(logs.getErrorLogList());
	        else
	        	 return ResponseUtil.success("导入成功！");
	    }
}
