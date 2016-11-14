package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.PlanSummary;
import com.topie.campus.core.model.RecordExcel;
import com.topie.campus.core.model.StudentRecord;

import tk.mybatis.mapper.common.Mapper;

public interface StudentRecordMapper extends Mapper<StudentRecord> {


	Long countByPage(@Param("studentRecord") StudentRecord studentRecord);

	List<StudentRecord> findByPage(@Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize,
			@Param("studentRecord") StudentRecord studentRecord);
	
	List<RecordExcel> findByExcel();
}