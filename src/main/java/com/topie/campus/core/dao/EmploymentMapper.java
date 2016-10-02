package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Employment;

import tk.mybatis.mapper.common.Mapper;

public interface EmploymentMapper extends Mapper<Employment> {

	List<Employment> findByPage(Employment employMent, int i, int pageSize);

	Long countEmploy(Employment employMent);
	
	List<Employment> findByStuId(@Param("stuId") String stuId);
}