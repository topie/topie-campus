package com.topie.campus.core.dao;

import com.topie.campus.core.dto.MessageSearchParams;
import com.topie.campus.core.dto.MessageSimpleDto;
import com.topie.campus.core.model.Message;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MessageMapper extends Mapper<Message> {

    List<Message> findMessageByPageNumAndPageSize(@Param("message") Message arg, @Param("pageOffset") int pageOffset,
            @Param("pageSize") int pageSize, @Param("sortType") int sortType);

    Long countMessage(@Param("message") Message arg);

    int updateTimeByPrimaryKey(@Param("messageId") Integer messageId);

    List<Map> findReceiveMessageStat(@Param("userId") Integer userId);

    List<MessageSimpleDto> findMessageDtoByPageNumAndPageSize(@Param("param") MessageSearchParams messageSearchParams,
            @Param("pageOffset") int pageOffset, @Param("pageSize") int pageSize);

    Long countMessageDto(@Param("param") MessageSearchParams messageSearchParams);

    List<MessageSimpleDto> findMessageDtoList(@Param("param") MessageSearchParams messageSearchParams);

    String selectContentByReplyId(@Param("contentId") Integer contentId);

    Integer selectIdByReplyId(@Param("contentId") Integer contentId);

    Message selectByReplyId(@Param("contentId") Integer contentId);

}
