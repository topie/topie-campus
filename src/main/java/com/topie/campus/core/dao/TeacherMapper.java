package com.topie.campus.core.dao;

import com.topie.campus.core.model.Teacher;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {

    List<Teacher> findTeacherByPageNumAndPageSize(@Param("teacher") Teacher teacher,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    long countTeacher(@Param("teacher") Teacher param);

    void insertRelate(@Param("studentId") Integer studentId, @Param("teacherId") Integer teacherId);

    void deleteRelate(@Param("studentId") Integer studentId, @Param("teacherId") Integer teacherId);

    Teacher selectOneByUserId(@Param("userId") Integer userId);

    Integer selectTeacherIdByUserId(@Param("userId") Integer userId);

    String selectTeacherNameByUserId(@Param("userId") Integer userId);
}