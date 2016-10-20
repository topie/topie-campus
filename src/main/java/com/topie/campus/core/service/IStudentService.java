package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;

/**
 * Created by chenguojun on 9/13/16.
 */
public interface IStudentService extends IService<Student> {

    SimplePageInfo<Student> findStudentList(Student teacher, Integer pageNum, Integer pageSize);

    SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer typeId, Integer teacherId, Integer pageNum, Integer pageSize);

    Student findStudentByUserId(Integer userId);

    Integer findStudentIdByUserId(Integer userId);

    String findStudentNameByUserId(Integer userId);

    Integer findIdByStudentNo(String studentNo);

    String findStudentNoByUserId(Integer userId);
}
