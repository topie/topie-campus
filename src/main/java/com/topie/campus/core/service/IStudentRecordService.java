package com.topie.campus.core.service;

import java.util.List;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.PlanSummary;
import com.topie.campus.core.model.RecordExcel;
import com.topie.campus.core.model.StudentRecord;

public interface IStudentRecordService  extends IService<StudentRecord>{

	SimplePageInfo<StudentRecord> findByPage(int pageNum, int pageSize,
			StudentRecord studentRecord);

	List<RecordExcel> findByExcel();


}
