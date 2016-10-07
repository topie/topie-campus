package com.topie.campus.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.security.dao.DictItemMapper;
import com.topie.campus.security.model.Dict;
import com.topie.campus.security.model.DictItem;
import com.topie.campus.security.service.IDictItemService;
import com.topie.campus.security.service.FunctionService;

@Service
public class DictItemServiceImpl extends BaseService<Dict> implements IDictItemService{

	@Autowired
	DictItemMapper dictItemMapper;
	
	@Override
	public List<DictItem> findByDictId(int dictId)
	{
		return dictItemMapper.findItemsByDictId(dictId);
	}
}
