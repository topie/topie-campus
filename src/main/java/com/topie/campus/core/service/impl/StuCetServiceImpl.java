package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StuCetMapper;
import com.topie.campus.core.model.StuCet;
import com.topie.campus.core.service.IStuCetService;
import com.topie.campus.core.service.IStudentScoreService;

@Service
public class StuCetServiceImpl extends BaseService<StuCet> implements IStuCetService {

	@Autowired
	StuCetMapper stuCetMapper;
	
	@Override
	public SimplePageInfo<StuCet> findByPage(int pageNum, int pageSize,
			StuCet stuCet) {
		// TODO Auto-generated method stub
		List<StuCet> stuCets = stuCetMapper.findByPage(stuCet, (pageNum - 1) * pageSize, pageSize);
		Long total = stuCetMapper.count(stuCet);
		SimplePageInfo<StuCet> pageInfo = new SimplePageInfo<StuCet>(pageNum, pageSize, total, stuCets);
		return pageInfo;
	}

	@Override
	public List<StuCet> findByStuNoAndStudyYear(StuCet stuCet) {
		// TODO Auto-generated method stub
		return stuCetMapper.findByStuNoAndStudyYear(stuCet);
	}

	@Override
	public Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum) {
		// TODO Auto-generated method stub
		return stuCetMapper.deleteByStudyYearAndStudyYearNum(studyYear,studyYearNum);
	}

}
