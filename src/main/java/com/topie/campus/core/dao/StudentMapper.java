package com.topie.campus.core.dao;

import com.topie.campus.core.model.Student;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentMapper extends Mapper<Student> {

    List<Student> findStudentByPageNumAndPageSize(@Param("student") Student student,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    Long countStudent(@Param("student") Student student);
}
