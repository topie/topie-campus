package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.StuScore;
import com.topie.campus.core.model.StuSeleCourse;

import tk.mybatis.mapper.common.Mapper;

public interface StuSeleCourseMapper extends Mapper<StuSeleCourse> {
	
	List<StuSeleCourse> findByPage(@Param("stuSeleCourse") StuSeleCourse stuSeleCourse, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	Long count(@Param("stuSeleCourse") StuSeleCourse stuSeleCourse);
	Long  countByStuIdAndCourseNumAndStudyYear(@Param("stuSeleCourse") StuSeleCourse stuSeleCourse);
	
	Integer deleteByStudyYearAndStudyYearNum(@Param("studyYear") String studyYear,
			@Param("studyYearNum") String studyYearNum);
}