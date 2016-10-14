package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.Option;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.TeacherTypeMapper;
import com.topie.campus.core.model.TeacherType;
import com.topie.campus.core.service.ITeacherTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/14.
 */
@Service
public class TeacherTypeServiceImpl extends BaseService<TeacherType> implements ITeacherTypeService {

    @Autowired
    private TeacherTypeMapper teacherTypeMapper;

    @Override
    public SimplePageInfo<TeacherType> selectByPage(TeacherType teacherType, int pageNum, int pageSize) {
        List<TeacherType> list = teacherTypeMapper.selectByPage(teacherType, (pageNum - 1) * pageSize, pageSize);
        Long total = teacherTypeMapper.count(teacherType);
        SimplePageInfo<TeacherType> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public List<Option> selectOptions(TeacherType teacherType) {
        return teacherTypeMapper.selectOptions(teacherType);
    }
}
