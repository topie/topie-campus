package com.topie.campus.core.dao;

import com.topie.campus.common.Option;
import com.topie.campus.core.model.TeacherType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherTypeMapper extends Mapper<TeacherType> {

    List<TeacherType> selectByPage(@Param("teacherType") TeacherType teacherType,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    List<Option> selectOptions(@Param("teacherType") TeacherType teacherType);

    Long count(@Param("teacherType") TeacherType teacherType);
}
