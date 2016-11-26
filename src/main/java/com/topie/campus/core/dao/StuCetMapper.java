package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.StuCet;
import com.topie.campus.core.model.StuScore;

import tk.mybatis.mapper.common.Mapper;

public interface StuCetMapper extends Mapper<StuCet> {
	
	List<StuCet> findByPage(@Param("stuCet") StuCet stuCet, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	
	Long count(@Param("stuCet") StuCet stuCet);
	
	List<StuCet>  findByStuNoAndStudyYear(@Param("stuCet") StuCet stuCet);

	Integer deleteByStudyYearAndStudyYearNum(@Param("studyYear")String studyYear,
			@Param("studyYearNum") String studyYearNum);
}