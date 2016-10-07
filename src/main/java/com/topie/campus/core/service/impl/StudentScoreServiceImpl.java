package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StuCetMapper;
import com.topie.campus.core.dao.StuScoreMapper;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.StuScore;
import com.topie.campus.core.service.IStudentScoreService;

@Service
public class StudentScoreServiceImpl extends BaseService<StuScore> implements IStudentScoreService {

	@Autowired
	StuScoreMapper stuScoreMapper;
	
	@Override
	public SimplePageInfo<StuScore> findByPage(int pageNum, int pageSize,
			StuScore stuScore) {
		// TODO Auto-generated method stub
		List<StuScore> stuScores = stuScoreMapper.findByPage(stuScore, (pageNum - 1) * pageSize, pageSize);
		Long total = stuScoreMapper.count(stuScore);
		SimplePageInfo<StuScore> pageInfo = new SimplePageInfo<StuScore>(pageNum, pageSize, total, stuScores);
		return pageInfo;
	}
}