package com.topie.campus.core.service;

import java.util.List;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Notice;
import com.topie.campus.core.model.StuCet;

public interface IStuCetService  extends IService<StuCet>{

	SimplePageInfo<StuCet> findByPage(int pageNum, int pageSize, StuCet stuCet);

	List<StuCet> findByStuNoAndStudyYear(StuCet stuCet);

	Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum);

}
