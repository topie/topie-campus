package com.topie.campus.core.dao;

import com.topie.campus.core.model.TeacherStudent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TeacherStudentMapper extends Mapper<TeacherStudent> {

    int insertIngore(@Param("ts") TeacherStudent teacherStudent);
}
