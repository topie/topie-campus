package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Faculty;

import tk.mybatis.mapper.common.Mapper;

public interface FacultyMapper extends Mapper<Faculty> {

	List<String> selectByCollegeId(@Param("collegeId") String f);
	
	
}