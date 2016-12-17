package com.topie.campus.core.dao;

import com.topie.campus.common.TreeNode;
import com.topie.campus.core.model.TeacherStudent;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherStudentMapper extends Mapper<TeacherStudent> {

    int insertIgnore(@Param("ts") TeacherStudent teacherStudent);

    List<Integer> selectStudentIdsAndTypeId(@Param("typeId") Integer typeId);

    List<Integer> selectStudentByTeacherIdAndTypeIds(@Param("teacherId") Integer teacherId,
            @Param("typeIds") List<Integer> typeIds);

    List<Integer> selectTeacherIdByTypeId(@Param("typeId") Integer typeId);
    
    TeacherStudent selectTeacherRelationByTypeIdAndStudentId(@Param("typeId") Integer typeId,@Param("studentId") Integer studentId);

	Integer deleteByStudyYearAndStudyYearNum();

    List<TreeNode> selectStudentByTeacherIdAndTypeId(@Param("teacherId") Integer teacherId,@Param("typeId") Integer typeId);
}
