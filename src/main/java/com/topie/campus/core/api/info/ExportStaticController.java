package com.topie.campus.core.api.info;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Msg;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IMsgService;
import com.topie.campus.tools.excel.ExcelUtil;

@Controller
@RequestMapping("/api/info/exportStatic")
public class ExportStaticController {

	@Autowired
	IMsgService msgService;
	
	@RequestMapping("/msg")
	@ResponseBody
	public Result page(int pageNum,int pageSize,Msg msg)
	{
		SimplePageInfo<Msg> pageInfo = msgService.findByPage(pageNum, pageSize, msg);
		return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
	}
	
	@RequestMapping("/excelMsg")
	public void exportStatic(HttpServletResponse response,Msg msg)
	{
		response.setContentType("application/vnd.ms-excel;charset=ISO8859_1"); 
		try {
			String fileName=new String(("短信通知表").getBytes(), "ISO8859_1");
			OutputStream outputStream = response.getOutputStream();
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			String headers[] = {"职工号","导师姓名","发送内容","短信签名","导师类型"};
			//List<Msg> msgs = msgService.findByPage(pageNum, pageSize, msg);
			//ExcelUtil.exportExcelX(headers, dataset, outputStream, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
