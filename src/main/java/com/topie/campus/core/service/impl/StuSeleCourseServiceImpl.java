package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StuScoreMapper;
import com.topie.campus.core.dao.StuSeleCourseMapper;
import com.topie.campus.core.model.StuSeleCourse;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.core.service.IStudentScoreService;

@Service
public class StuSeleCourseServiceImpl extends BaseService<StuSeleCourse> implements IStuSeleCourseService 
{
	@Autowired
	StuSeleCourseMapper stuSeleCourseMapper;
	
	@Override
	public SimplePageInfo<StuSeleCourse> findByPage(int pageNum, int pageSize,
			StuSeleCourse stuSeleCourse) {
		// TODO Auto-generated method stub
		List<StuSeleCourse> stuSeleCourses = stuSeleCourseMapper.findByPage(stuSeleCourse, (pageNum - 1) * pageSize, pageSize);
		Long total = stuSeleCourseMapper.count(stuSeleCourse);
		SimplePageInfo<StuSeleCourse> pageInfo = new SimplePageInfo<StuSeleCourse>(pageNum, pageSize, total, stuSeleCourses);
		return pageInfo;
	}

	@Override
	public long countByStuIdAndCourseNumAndStudyYear(StuSeleCourse stuSeleCourse) {
		// TODO Auto-generated method stub
		return stuSeleCourseMapper.countByStuIdAndCourseNumAndStudyYear(stuSeleCourse);
	}

	@Override
	public Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum) {
		// TODO Auto-generated method stub
		return stuSeleCourseMapper.deleteByStudyYearAndStudyYearNum(studyYear,studyYearNum);
	}

}
