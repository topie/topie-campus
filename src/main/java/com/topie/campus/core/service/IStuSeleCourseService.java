package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.StuSeleCourse;

public interface IStuSeleCourseService extends IService<StuSeleCourse>{

	SimplePageInfo<StuSeleCourse> findByPage(int pageNum, int pageSize,
			StuSeleCourse stuSeleCourse);

	long countByStuIdAndCourseNumAndStudyYear(StuSeleCourse stuSeleCourse);

	Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum);
	
}
