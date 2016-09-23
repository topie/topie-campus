package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.EmploymentMapper;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.service.IEmploymentService;

@Service
public class EmploymentServiceImpl extends BaseService<Employment> implements IEmploymentService {

	@Autowired
	EmploymentMapper employmentMapper;
	
	@Override
	public SimplePageInfo<Employment> findByPage(int pageNum, int pageSize,
			Employment employMent) {
		// TODO Auto-generated method stub
		List<Employment> employments = employmentMapper.findByPage(employMent, (pageNum - 1) * pageSize, pageSize);
		Long total = employmentMapper.countEmploy(employMent);
		SimplePageInfo<Employment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, employments);
		return pageInfo;
	}



}
