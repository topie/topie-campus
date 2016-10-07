package com.topie.campus.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Map> findByDictId(int dictId)
	{
		List<DictItem> items =  dictItemMapper.findItemsByDictId(dictId);
		List<Map> ls = new ArrayList<Map>();
		for(DictItem item:items){
			Map mp = new HashMap();
			mp.put("text", item.getItemName());
			mp.put("value", item.getItemCode());
			ls.add(mp);
		}
		return ls;
	}
}
