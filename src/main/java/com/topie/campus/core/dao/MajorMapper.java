package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Major;

import tk.mybatis.mapper.common.Mapper;

public interface MajorMapper extends Mapper<Major> {

	List<String> selectByFacultyId(@Param("facultyId") String f);
}