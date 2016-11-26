package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StuTimeTableMapper;
import com.topie.campus.core.model.StuTimeTable;
import com.topie.campus.core.service.IStuTimeTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuTimeTableServiceImpl extends BaseService<StuTimeTable> implements IStuTimeTableService {

    @Autowired
    StuTimeTableMapper stuTimeTableMapper;

    @Override
    public SimplePageInfo<StuTimeTable> findPage(int pageNum, int pageSize, StuTimeTable stuTimeTable) {
        List<StuTimeTable> stuTimeTables = stuTimeTableMapper
                .findByPage(stuTimeTable, (pageNum - 1) * pageSize, pageSize);
        Long total = stuTimeTableMapper.count(stuTimeTable);
        SimplePageInfo<StuTimeTable> pageInfo = new SimplePageInfo<StuTimeTable>(pageNum, pageSize, total,
                stuTimeTables);
        return pageInfo;
    }

    @Override
    public List<StuTimeTable> find(StuTimeTable stuTimeTable) {
        List<StuTimeTable> stuTimeTables = stuTimeTableMapper.find(stuTimeTable);
        return stuTimeTables;
    }

	@Override
	public List<StuTimeTable> findByStuNoAndCourseNum(String courseNum,
			String studentId) {
		List<StuTimeTable> stuTimeTables = stuTimeTableMapper.findByStuNoAndCourseNum(courseNum, studentId);
        return stuTimeTables;
	}

	@Override
	public Integer deleteByStudyYearAndStudyYearNum(String studyYear,
			String studyYearNum) {
		// TODO Auto-generated method stub
		return stuTimeTableMapper.deleteByStudyYearAndStudyYearNum(studyYear,studyYearNum);
	}
}
