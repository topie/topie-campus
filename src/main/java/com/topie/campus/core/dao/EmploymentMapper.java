package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.StaticEmployment;

import tk.mybatis.mapper.common.Mapper;

public interface EmploymentMapper extends Mapper<Employment> {

	List<Employment> findByPage(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);

	Long countEmploy(@Param("employment") Employment employment);
	
	List<Employment> findByStuId(@Param("stuId") String stuId);

	List<StaticEmployment> findByPageGroupByMajor(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);

	Long countByPageGroupByMajor(@Param("employment") Employment employment);
	
	List<StaticEmployment> findByPageGroupByClassNum(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
   
	Long countByPageGroupByClassNum(@Param("employment") Employment employment);
	
	List<StaticEmployment> findByPageGroupByTutor(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	   
	Long countByPageGroupByTutor(@Param("employment") Employment employment);
	
	List<StaticEmployment> findOtherByPageGroupByMajor(@Param("employment") Employment employment);
	
	Long countOtherByPageGroupByMajor(@Param("employment") Employment employment);
	
	List<StaticEmployment> findOtherByPageGroupByClassNum(@Param("employment") Employment employment);
	
	Long countOtherByPageGroupByClassNum(@Param("employment") Employment employment);
	
	List<StaticEmployment> findByPageGroupByFaculty(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	
	Long countByPageGroupByFaculty(@Param("employment") Employment employment);
	
	List<StaticEmployment> findByPageGroupByCollege(@Param("employment") Employment employment, @Param("pageOffset") int pageOffset,@Param("pageSize") int pageSize);
	
	Long countByPageGroupByCollege (@Param("employment") Employment employment);
}