package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Student;

/**
 * Created by chenguojun on 9/13/16.
 */
public interface IStudentService extends IService<Student> {

    SimplePageInfo<Student> findStudentList(Student teacher, Integer pageNum, Integer pageSize);
}
