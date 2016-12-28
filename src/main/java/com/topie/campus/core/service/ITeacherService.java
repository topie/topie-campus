package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Teacher;

import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
public interface ITeacherService extends IService<Teacher> {

    SimplePageInfo<Teacher> findTeacherList(Teacher teacher, Integer pageNum, Integer pageSize);

    void insertToBindStudent(Integer typeId, Integer studentId, Integer teacherId, String studentNo, String teacherNo);

    void deleteToUnBindStudent(Integer typeId, Integer studentId, Integer teacherId);

    Teacher findTeacherByUserId(Integer UserId);

    Integer findTeacherIdByUserId(Integer userId);

    String findTeacherNameByUserId(Integer userId);

    SimplePageInfo<TeacherSimpleDto> findTeacherSimpleDtoListWithBindInfo(TeacherSimpleDto teacherSimpleDto,
            Integer typeId, Integer studentId, Integer pageNum, Integer pageSize);

    Integer findIdByEmployeeNo(String employeeNo);

    List<TeacherSimpleDto> findTeacherByStudentIdAndTypeId(Integer studentId, Integer typeId);

    List<TeacherSimpleDto> findTeacherByTypeId(Integer typeId);
    
    Long findCountTeacherByStudentNo(Integer studentId);

    SimplePageInfo<TeacherSimpleDto> findTeacherByStudentNo(Integer studentId,
			Integer pageNum, Integer pageSize);

    Teacher findTeacherById(Integer id);
}
