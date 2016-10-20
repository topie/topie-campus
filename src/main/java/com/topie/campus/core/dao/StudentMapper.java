package com.topie.campus.core.dao;

import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {

    List<Student> findStudentByPageNumAndPageSize(@Param("student") Student student,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    Long countStudent(@Param("student") Student student);

    List<StudentSimpleDto> findStudentSimpleDtoByTeacherIdAndPageNumAndPageSize(
            @Param("student") StudentSimpleDto studentSimpleDto, @Param("typeId") Integer typeId,
            @Param("teacherId") Integer teacherId, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countStudentSimpleDtoListByTeacherId(@Param("student") StudentSimpleDto studentSimpleDto,
            @Param("typeId") Integer typeId, @Param("teacherId") Integer teacherId);

    Integer findStudentIdByUserId(@Param("userId") Integer userId);

    String findStudentNameByUserId(@Param("userId") Integer userId);

    Student findStudentByUserId(@Param("userId") Integer userId);

    Integer findIdByStudentNo(@Param("studentNo") String studentNo);

    String findStudentNoByUserId(@Param("userId")Integer userId);
}
