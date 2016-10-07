package com.topie.campus.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.security.model.DictItem;

import tk.mybatis.mapper.common.Mapper;

public interface DictItemMapper extends Mapper<DictItem> {
	List<DictItem> findItemsByDictId(@Param("dictId") Integer dictId);
}