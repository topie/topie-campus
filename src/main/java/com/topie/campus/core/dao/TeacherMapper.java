package com.topie.campus.core.dao;

import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Teacher;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {

    List<Teacher> findTeacherByPageNumAndPageSize(@Param("teacher") Teacher teacher,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    long countTeacher(@Param("teacher") Teacher param);

    void insertRelate(@Param("typeId") Integer typeId, @Param("studentId") Integer studentId,
            @Param("teacherId") Integer teacherId, @Param("studentNo") String studentNo, @Param("teacherNo") String teacherNo);

    void deleteRelate(@Param("typeId") Integer typeId, @Param("studentId") Integer studentId,
            @Param("teacherId") Integer teacherId);

    Teacher selectOneByUserId(@Param("userId") Integer userId);

    Integer selectTeacherIdByUserId(@Param("userId") Integer userId);

    String selectTeacherNameByUserId(@Param("userId") Integer userId);

    List<TeacherSimpleDto> findTeacherSimpleDtoByStudentIdAndTypeIdAndPageNumAndPageSize(
            @Param("teacher") TeacherSimpleDto teacherSimpleDto, @Param("typeId") Integer typeId,
            @Param("studentId") Integer studentId, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countTeacherSimpleDtoListByTeacherId(@Param("teacher") TeacherSimpleDto teacherSimpleDto,
            @Param("typeId") Integer typeId, @Param("studentId") Integer studentId);

    Integer selectIdByEmployeeNo(@Param("employeeNo") String employeeNo);

    List<TeacherSimpleDto> findTeacherByStudentIdAndTypeId(@Param("studentId") Integer studentId,
            @Param("typeId") Integer typeId);

    List<TeacherSimpleDto> findTeacherByAndTypeId(@Param("typeId") Integer typeId);
    
    List<TeacherSimpleDto> findTeacherByStudentNo(@Param("studentId") Integer studentId,@Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);
    
    Long findCountTeacherByStudentNo(@Param("studentId") Integer studentId);
}
