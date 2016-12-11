package com.topie.campus.core.dao;

import com.topie.campus.core.model.AtMe;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AtMeMapper extends Mapper<AtMe> {

    List<AtMe> selectRecordByLimitOffset(@Param("param") AtMe atMe, @Param("pageOffset") Integer pageOffSet,
            @Param("pageSize") Integer pageSize);

    Long count(@Param("param") AtMe atMe);
}
