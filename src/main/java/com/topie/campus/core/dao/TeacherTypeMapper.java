package com.topie.campus.core.dao;

import com.topie.campus.common.Option;
import com.topie.campus.common.TreeNode;
import com.topie.campus.core.model.TeacherType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherTypeMapper extends Mapper<TeacherType> {

    List<TeacherType> selectByPage(@Param("teacherType") TeacherType teacherType,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    List<Option> selectOptions(@Param("teacherType") TeacherType teacherType);

    Long count(@Param("teacherType") TeacherType teacherType);

    List<TreeNode> selectTreeNodes(@Param("teacherType") TeacherType teacherType);

    List<Integer> selectTypeIdsByTeacherId(@Param("teacherId") Integer teacherId);

    int insertTeacherAndTeacherTypeRelate(@Param("teacherId") Integer teacherId, @Param("typeId") Integer typeId);

    int deleteTeacherAndTeacherTypeRelate(@Param("teacherId") Integer teacherId);

	List<TreeNode> selectTreeNodesByTeacherId(@Param("teacherId") Integer teacherId);
}
