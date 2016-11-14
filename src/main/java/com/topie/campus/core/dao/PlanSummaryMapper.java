package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.PlanSummary;

import tk.mybatis.mapper.common.Mapper;

public interface PlanSummaryMapper extends Mapper<PlanSummary> {

	List<PlanSummary> findByPage(@Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize,@Param("planSummary") PlanSummary planSummary);

	Long countByPage(@Param("planSummary") PlanSummary planSummary);
	
	List<PlanSummary> findByExcel();
}