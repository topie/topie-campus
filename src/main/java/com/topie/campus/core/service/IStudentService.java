package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;

import java.util.List;

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

    List<String> findPhoneByMajorId(String zydm);

    SimplePageInfo<Student> findByLeadRole(Integer userId, int pageNum, int pageSize, Student student);

	Student findByStudentId(Integer studentId);

    List<StudentSimpleDto> findStudentByTeacherIdAndTypeId(Integer teacherId, Integer typeId);

    List<StudentSimpleDto> findStudentByTypeId(Integer typeId);
}
