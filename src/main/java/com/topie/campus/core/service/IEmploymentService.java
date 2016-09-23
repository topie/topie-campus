package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Employment;


public interface IEmploymentService extends IService<Employment>{

	SimplePageInfo<Employment> findByPage(int pageNum, int pageSize,
			Employment employMent);

}
