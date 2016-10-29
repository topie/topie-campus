package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.Option;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.TreeNode;
import com.topie.campus.core.model.TeacherType;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/14.
 */
public interface ITeacherTypeService extends IService<TeacherType> {

    SimplePageInfo<TeacherType> selectByPage(TeacherType teacherType, int pageNum, int pageSize);

    List<Option> selectOptions(TeacherType teacherType);

    List<TreeNode> selectTreeNodes(TeacherType teacherType);

    List<Integer> selectTypeIdsByTeacherId(Integer teacherId);

    int insertTeacherAndTeacherTypeRelate(Integer teacherId, Integer typeId);

    int deleteTeacherAndTeacherTypeRelate(Integer teacherId);

	List<TreeNode> selectTreeNodesByTeacherId(Integer teacherId);
}
