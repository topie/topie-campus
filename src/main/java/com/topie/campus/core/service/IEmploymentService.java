package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.StaticEmployment;
import com.topie.campus.tools.excel.ExcelLogs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IEmploymentService extends IService<Employment> {

    SimplePageInfo<Employment> findByPage(int pageNum, int pageSize, Employment employMent);

    void employUpload(MultipartFile file, ExcelLogs logs) throws IOException;

    SimplePageInfo<StaticEmployment> findByPageGroupByMajor(int pageNum, int pageSize, Employment employment);

    SimplePageInfo<StaticEmployment> findByPageGroupByClassNum(int pageNum, int pageSize, Employment employment);

    SimplePageInfo<StaticEmployment> findByPageGroupByTutor(int pageNum, int pageSize, Employment employment);

    void updateEmploymentStatus(Employment employment);

	SimplePageInfo<StaticEmployment> findByPageGroupByCollege(int pageNum,
			int pageSize, Employment employment);

	SimplePageInfo<StaticEmployment> findByPageGroupByFaculty(int pageNum,
			int pageSize, Employment employment);

}
