package com.topie.campus.core.dao;

import com.topie.campus.core.model.Message;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageMapper extends Mapper<Message> {

    List<Message> findMessageByPageNumAndPageSize(@Param("message") Message arg, @Param("pageOffset") int pageOffset,
            @Param("pageSize") int pageSize);

    Long countMessage(@Param("message") Message arg);
}
