package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.StudentMapper;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
@Service
public class StudentServiceImpl extends BaseService<Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public SimplePageInfo<Student> findStudentList(Student student, Integer pageNum, Integer pageSize) {
        List<Student> list = studentMapper.findStudentByPageNumAndPageSize(student, (pageNum - 1) * pageSize, pageSize);
        Long total = studentMapper.countStudent(student);
        SimplePageInfo<Student> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer typeId, Integer teacherId, Integer pageNum, Integer pageSize) {
        List<StudentSimpleDto> list = studentMapper
                .findStudentSimpleDtoByTeacherIdAndPageNumAndPageSize(studentSimpleDto, typeId, teacherId,
                        (pageNum - 1) * pageSize, pageSize);
        Long total = studentMapper.countStudentSimpleDtoListByTeacherId(studentSimpleDto, typeId, teacherId);
        SimplePageInfo<StudentSimpleDto> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Student findStudentByUserId(Integer userId) {
        return studentMapper.findStudentByUserId(userId);
    }

    @Override
    public Integer findStudentIdByUserId(Integer userId) {
        return studentMapper.findStudentIdByUserId(userId);
    }

    @Override
    public String findStudentNameByUserId(Integer userId) {
        return studentMapper.findStudentNameByUserId(userId);
    }

    @Override
    public Integer findIdByStudentNo(String studentNo) {
        return studentMapper.findIdByStudentNo(studentNo);
    }

}
