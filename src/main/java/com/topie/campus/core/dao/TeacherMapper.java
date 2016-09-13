package com.topie.campus.core.dao;

import com.topie.campus.core.model.Teacher;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {

    List<Teacher> findTeacherByPageNumAndPageSize(@Param("teacher") Teacher teacher, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    long countTeacher(@Param("teacher") Teacher param);
}
