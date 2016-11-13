package com.topie.campus.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.MsgMapper;
import com.topie.campus.core.model.Msg;
import com.topie.campus.core.model.Msg;
import com.topie.campus.core.service.IMsgService;

@Service
public class MsgServiceImpl extends BaseService<Msg> implements IMsgService {

	@Autowired
	MsgMapper msgMapper;
	
	
	@Override
	public SimplePageInfo<Msg> findByPage(int pageNum, int pageSize, Msg msg) {
        // TODO Auto-generated method stub
        List<Msg> msgs = msgMapper.findMsgByPage((pageNum-1)*pageSize, pageSize,msg);
        Long total = msgMapper.countMsg(msg);
        SimplePageInfo<Msg> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, msgs);
        return pageInfo;
    }
   

}
