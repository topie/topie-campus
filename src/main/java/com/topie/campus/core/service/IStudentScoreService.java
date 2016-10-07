package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.StuScore;

public interface IStudentScoreService  extends IService<StuScore> {

	SimplePageInfo<StuScore> findByPage(int pageNum, int pageSize,
			StuScore stuScore);


}
