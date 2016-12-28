package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.TeacherMapper;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
@Service
public class TeacherServiceImpl extends BaseService<Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public SimplePageInfo<Teacher> findTeacherList(Teacher teacher, Integer pageNum, Integer pageSize) {
        List<Teacher> list = teacherMapper.findTeacherByPageNumAndPageSize(teacher, (pageNum - 1) * pageSize, pageSize);
        Long total = teacherMapper.countTeacher(teacher);
        SimplePageInfo<Teacher> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public void insertToBindStudent(Integer typeId, Integer studentId, Integer teacherId, String studentNo,
            String teacherNo) {
        teacherMapper.insertRelate(typeId, studentId, teacherId, studentNo, teacherNo);
    }

    @Override
    public void deleteToUnBindStudent(Integer typeId, Integer studentId, Integer teacherId) {
        teacherMapper.deleteRelate(typeId, studentId, teacherId);
    }

    @Override
    public Teacher findTeacherByUserId(Integer UserId) {
        return teacherMapper.selectOneByUserId(UserId);
    }

    @Override
    public Integer findTeacherIdByUserId(Integer userId) {
        return teacherMapper.selectTeacherIdByUserId(userId);
    }

    @Override
    public String findTeacherNameByUserId(Integer userId) {
        return teacherMapper.selectTeacherNameByUserId(userId);
    }

    @Override
    public SimplePageInfo<TeacherSimpleDto> findTeacherSimpleDtoListWithBindInfo(TeacherSimpleDto teacherSimpleDto,
            Integer typeId, Integer studentId, Integer pageNum, Integer pageSize) {
        List<TeacherSimpleDto> list = teacherMapper
                .findTeacherSimpleDtoByStudentIdAndTypeIdAndPageNumAndPageSize(teacherSimpleDto, typeId, studentId,
                        (pageNum - 1) * pageSize, pageSize);
        Long total = teacherMapper.countTeacherSimpleDtoListByTeacherId(teacherSimpleDto, typeId, studentId);
        SimplePageInfo<TeacherSimpleDto> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Integer findIdByEmployeeNo(String employeeNo) {
        return teacherMapper.selectIdByEmployeeNo(employeeNo);
    }

    @Override
    public List<TeacherSimpleDto> findTeacherByStudentIdAndTypeId(Integer studentId, Integer typeId) {
        return teacherMapper.findTeacherByStudentIdAndTypeId(studentId, typeId);
    }

    @Override
    public List<TeacherSimpleDto> findTeacherByTypeId(Integer typeId) {
        return teacherMapper.findTeacherByAndTypeId(typeId);
    }

    @Override
    public SimplePageInfo<TeacherSimpleDto> findTeacherByStudentNo(Integer studentId, Integer pageNum,
            Integer pageSize) {
        // TODO Auto-generated method stub
        List<TeacherSimpleDto> list = teacherMapper
                .findTeacherByStudentNo(studentId, (pageNum - 1) * pageSize, pageSize);
        Long total = teacherMapper.findCountTeacherByStudentNo(studentId);
        if (total == null) total = 0L;
        SimplePageInfo<TeacherSimpleDto> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Teacher findTeacherById(Integer id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public Long findCountTeacherByStudentNo(Integer studentId) {
        // TODO Auto-generated method stub
        return teacherMapper.findCountTeacherByStudentNo(studentId);
    }

}
