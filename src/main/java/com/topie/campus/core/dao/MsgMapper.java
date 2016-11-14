package com.topie.campus.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topie.campus.core.model.Msg;

import tk.mybatis.mapper.common.Mapper;

public interface MsgMapper extends Mapper<Msg> {
	
   List<Msg> findMsgByPage(@Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize,@Param("msg") Msg msg);

      Long countMsg(@Param("msg") Msg msg);
}