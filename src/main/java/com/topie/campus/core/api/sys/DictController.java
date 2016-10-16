package com.topie.campus.core.api.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.model.DictItem;
import com.topie.campus.security.service.IDictItemService;

@Controller
@RequestMapping("/api/dict")
public class DictController {

	@Autowired
	IDictItemService dictItemSerivce;
	
	@RequestMapping("/{dictId}")
	@ResponseBody
	public List<Map> findByDictId(@PathVariable("dictId") Integer dictId)
	{
		 List<Map> ls = dictItemSerivce.findByDictId(dictId);
		 return ls;
	}
}
