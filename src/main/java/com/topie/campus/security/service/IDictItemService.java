package com.topie.campus.security.service;

import java.util.List;
import java.util.Map;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.security.model.Dict;
import com.topie.campus.security.model.DictItem;

public interface IDictItemService extends IService<Dict>{

	List<Map> findByDictId(int dictId);

}
