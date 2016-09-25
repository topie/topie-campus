package com.topie.campus.core.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Employment;
import com.topie.campus.tools.excel.ExcelLogs;


public interface IEmploymentService extends IService<Employment>{

	SimplePageInfo<Employment> findByPage(int pageNum, int pageSize,
			Employment employMent);

	void employUpload(MultipartFile file, ExcelLogs logs) throws IOException;

}
