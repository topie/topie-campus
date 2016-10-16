package com.topie.campus.core.dao;

import com.topie.campus.core.model.TeacherStudent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherStudentMapper extends Mapper<TeacherStudent> {

    int insertIgnore(@Param("ts") TeacherStudent teacherStudent);

    List<Integer> selectStudentIdsAndTypeId(@Param("typeId") Integer typeId);
}
