package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.StuTimeTable;

import tk.mybatis.mapper.common.Mapper;

public interface StuTimeTableMapper extends Mapper<StuTimeTable> {
	
	List<StuTimeTable>  findByPage(@Param("stuTimeTable") StuTimeTable stuTimeTable);
}