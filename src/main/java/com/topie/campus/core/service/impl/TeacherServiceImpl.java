package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.TeacherMapper;
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

}
