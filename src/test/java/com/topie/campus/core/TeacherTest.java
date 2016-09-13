package com.topie.campus.core;

import com.topie.campus.BasicTest;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.TeacherMapper;
import com.topie.campus.core.model.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
public class TeacherTest extends BasicTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void testMapper() {
        Teacher param = new Teacher();
        Integer pageNum = 1;
        Integer pageSize = 3;
        List<Teacher> list = teacherMapper.findTeacherByPageNumAndPageSize(param, (pageNum - 1) * pageSize, pageSize);
        Long total = teacherMapper.countTeacher(param);
        SimplePageInfo<Teacher> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        System.out.println(pageInfo);

    }
}
