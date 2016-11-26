package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.dto.ScoreCourceType;
import com.topie.campus.core.dto.StaticScoreDto;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.StuScore;

import tk.mybatis.mapper.common.Mapper;

public interface StuScoreMapper extends Mapper<StuScore> {
	
	List<StuScore> findByPage(@Param("stuScore") StuScore stuScore, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	Long count(@Param("stuScore") StuScore stuScore);
	
	 List<StaticScoreDto> findByStuScoreStaticAvgCredit(@Param("studentId") String studentId);
	 
	 List<StaticScoreDto> findByStuScoreStaticAvgScore(@Param("studentId") String studentId);
	    
	 List<ScoreCourceType> findByStuScoreByCourseType(@Param("studentId") String studentId);
	 
	  List<StuScore> findByStuNoAndCourseNum(@Param("courseNum") String courseNum, @Param("studentId") String studentId);
	  
	Integer deleteByStudyYearAndStudyYearNum(@Param("studyYear") String studyYear, @Param("studyYearNum") String studyYearNum);
}