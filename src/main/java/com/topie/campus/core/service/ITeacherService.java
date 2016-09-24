package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Teacher;

/**
 * Created by chenguojun on 9/13/16.
 */
public interface ITeacherService extends IService<Teacher> {

    SimplePageInfo<Teacher> findTeacherList(Teacher teacher, Integer pageNum, Integer pageSize);

    void insertToBindStudent(Integer studentId, Integer teacherId);

    void deleteToUnBindStudent(Integer studentId, Integer teacherId);

    Teacher findTeacherByUserId(Integer UserId);
}
