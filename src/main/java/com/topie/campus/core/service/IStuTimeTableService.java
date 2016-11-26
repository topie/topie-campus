package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.StuTimeTable;

import java.util.List;

public interface IStuTimeTableService extends IService<StuTimeTable> {

    SimplePageInfo<StuTimeTable> findPage(int pageNum, int pageSize, StuTimeTable stuTimeTable);

    List<StuTimeTable> find(StuTimeTable stuTimeTable);
    
    List<StuTimeTable> findByStuNoAndCourseNum(String courseNum,String studentId);

	Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum);
}
