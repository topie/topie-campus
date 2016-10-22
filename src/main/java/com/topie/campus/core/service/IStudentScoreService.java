package com.topie.campus.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.ScoreCourceType;
import com.topie.campus.core.dto.StaticScoreDto;
import com.topie.campus.core.model.StuScore;

public interface IStudentScoreService  extends IService<StuScore> {

	SimplePageInfo<StuScore> findByPage(int pageNum, int pageSize,
			StuScore stuScore);

	 StaticScoreDto findByStuScoreStatic(String studentId);
	    
	 List<ScoreCourceType> findByStuScoreByCourseType( String studentId);
}
