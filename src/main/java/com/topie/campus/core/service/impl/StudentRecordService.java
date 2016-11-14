package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StudentRecordMapper;
import com.topie.campus.core.model.RecordExcel;
import com.topie.campus.core.model.StudentRecord;
import com.topie.campus.core.service.IStudentRecordService;

@Service
public class StudentRecordService extends BaseService<StudentRecord> implements IStudentRecordService{

	@Autowired
	StudentRecordMapper studentRecordMapper;
	
	@Override
	public SimplePageInfo<StudentRecord> findByPage(int pageNum, int pageSize,
			StudentRecord studentRecord) {
		// TODO Auto-generated method stub
		List<StudentRecord> studentRecords = studentRecordMapper.findByPage((pageNum-1)*pageSize, pageSize, studentRecord);
		Long total  = studentRecordMapper.countByPage(studentRecord);
		SimplePageInfo<StudentRecord> pageInfo = new SimplePageInfo<StudentRecord>(pageNum,pageSize,total,studentRecords);
		return pageInfo;
	}

	@Override
	public List<RecordExcel> findByExcel()
	{
		List<RecordExcel> recordExcels = studentRecordMapper.findByExcel();
		return recordExcels;
	}

}
