package com.topie.campus.core.dao;

import com.topie.campus.core.model.MessageReply;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MessageReplyMapper extends Mapper<MessageReply> {

    List<MessageReply> findMessageReplyByPageNumAndPageSize(@Param("reply") MessageReply messageReply,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    Long countMessageReply(@Param("reply") MessageReply messageReply);

    List<Map> findMessageReplyStat(@Param("userId")Integer userId);
}
