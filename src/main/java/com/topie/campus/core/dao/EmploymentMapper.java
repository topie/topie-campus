package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Employment;

import tk.mybatis.mapper.common.Mapper;

public interface EmploymentMapper extends Mapper<Employment> {

	List<Employment> findByPage(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);

	Long countEmploy(@Param("employment") Employment employment);
}