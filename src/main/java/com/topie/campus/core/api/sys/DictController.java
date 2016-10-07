package com.topie.campus.core.api.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topie.campus.security.model.DictItem;
import com.topie.campus.security.service.IDictItemService;

@Controller
@RequestMapping("/api/dict")
public class DictController {

	@Autowired
	IDictItemService dictItemSerivce;
	
	@RequestMapping("/api/dict/{dictId}")
	@ResponseBody
	public List<DictItem> findByDictId(@PathVariable("dictId") Integer dictId)
	{
		return dictItemSerivce.findByDictId(dictId);
	}
}
