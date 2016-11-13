package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Msg;

public interface IMsgService extends IService<Msg>{

	SimplePageInfo<Msg> findByPage(int pageNum, int pageSize, Msg msg);


}
