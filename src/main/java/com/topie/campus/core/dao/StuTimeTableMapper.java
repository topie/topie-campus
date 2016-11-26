package com.topie.campus.core.dao;

import com.topie.campus.core.model.StuTimeTable;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StuTimeTableMapper extends Mapper<StuTimeTable> {

    List<StuTimeTable> findByPage(@Param("stuTimeTable") StuTimeTable stuTimeTable, @Param("pageOffset") int pageOffset,
            @Param("pageSize") int pageSize);

    Long count(@Param("stuTimeTable") StuTimeTable stuTimeTable);

    List<StuTimeTable> find(@Param("stuTimeTable") StuTimeTable stuTimeTable);
    
    List<StuTimeTable> findByStuNoAndCourseNum(@Param("courseNum") String courseNum, @Param("studentId") String studentId);

	Integer deleteByStudyYearAndStudyYearNum(@Param("studyYear") String studyYear,
			@Param("studyYearNum") String studyYearNum);
}
