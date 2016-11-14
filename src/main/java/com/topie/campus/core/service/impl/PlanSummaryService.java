package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.PlanSummaryMapper;
import com.topie.campus.core.model.PlanSummary;
import com.topie.campus.core.service.IPlanSummaryService;

@Service
public class PlanSummaryService extends BaseService<PlanSummary> implements IPlanSummaryService{

	@Autowired
	PlanSummaryMapper planSummaryMapper;
	
	@Override
	public SimplePageInfo<PlanSummary> findByPage(int pageNum, int pageSize,
			PlanSummary planSummary) {
		// TODO Auto-generated method stub
		List<PlanSummary> planSummarys = planSummaryMapper.findByPage((pageNum-1)*pageSize,pageSize,planSummary); 
		Long total = planSummaryMapper.countByPage(planSummary);
		SimplePageInfo<PlanSummary> pageInfo = new SimplePageInfo<PlanSummary>(pageNum,pageSize,total,planSummarys);
		return pageInfo;
	}

	@Override
	public List<PlanSummary> findByExcel()
	{
		List<PlanSummary> planSummarys = planSummaryMapper.findByExcel();
		return planSummarys;
	}

}
