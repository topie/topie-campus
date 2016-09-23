package com.topie.campus.core.service;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.tools.excel.ExcelLogs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface IInfoBasicService {

    void userUpload(MultipartFile file, ExcelLogs logs) throws IOException;

    SimplePageInfo<Teacher> findTeacherList(Teacher teacher, int pageNum, int pageSize);

    Teacher findOneByTeacherId(Integer teacherId);

    int insertTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacher(Integer teacherId);

    SimplePageInfo<Student> findStudentList(Student student, int pageNum, int pageSize);

    SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer teacherId, Integer pageNum, Integer pageSize);

    Student findOneByStudentId(Integer studentId);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer studentId);

    void insertToBindStudent(Integer studentId, Integer teacherId);

    void deleteToUnbindStudent(Integer studentId, Integer teacherId);
}
