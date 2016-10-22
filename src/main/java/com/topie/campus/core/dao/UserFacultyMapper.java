package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.UserFaculty;

import tk.mybatis.mapper.common.Mapper;

public interface UserFacultyMapper extends Mapper<UserFaculty> {

	void deleteByUserId(@Param("userId") Integer userId);

	List<UserFaculty> findByUserId(@Param("userId") Integer userId);
}