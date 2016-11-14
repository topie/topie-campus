package com.topie.campus.core.service;

import java.util.List;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.PlanSummary;

public interface IPlanSummaryService extends IService<PlanSummary>{

	SimplePageInfo<PlanSummary> findByPage(int pageNum, int pageSize,
			PlanSummary planSummary);

	List<PlanSummary> findByExcel();

}
