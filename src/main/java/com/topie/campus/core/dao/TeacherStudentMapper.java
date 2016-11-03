package com.topie.campus.core.dao;

import com.topie.campus.core.model.TeacherStudent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherStudentMapper extends Mapper<TeacherStudent> {

    int insertIgnore(@Param("ts") TeacherStudent teacherStudent);

    List<Integer> selectStudentIdsAndTypeId(@Param("typeId") Integer typeId);

    List<Integer> selectStudentByTeacherIdAndTypeId(@Param("teacherId") Integer teacherId,
            @Param("typeIds") List<Integer> typeIds);

    List<Integer> selectTeacherIdByTypeId(@Param("typeId") Integer typeId);
}
