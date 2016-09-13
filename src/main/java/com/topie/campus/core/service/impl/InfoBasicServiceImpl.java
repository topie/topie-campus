package com.topie.campus.core.service.impl;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.TeacherExcelDto;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.vo.UserVO;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by chenguojun on 8/10/16.
 */
@Service
public class InfoBasicServiceImpl implements IInfoBasicService {

    @Autowired
    private UserService userService;

    @Autowired
    private ITeacherService iTeacherService;

    @Override
    public void userUpload(MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<TeacherExcelDto> teacherList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            teacherList = ExcelUtil.importExcelX(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            teacherList = ExcelUtil.importExcel(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        Collection<Map> student;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            student = ExcelUtil.importExcelX(Map.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        } else {
            student = ExcelUtil.importExcel(Map.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        }
        for (TeacherExcelDto teacherDto : teacherList) {
            User user = UserVO
                    .buildSimpleUser(teacherDto.getEmployeeNo(), teacherDto.getContactPhone(), teacherDto.getName(),
                            teacherDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            Teacher teacher = teacherDto.buildTeacher();
            teacher.setUserId(user.getId());
            iTeacherService.insertSelective(teacher);
        }

    }

    @Override
    public SimplePageInfo<Teacher> findTeacherList(Teacher teacher, int pageNum, int pageSize) {
        return iTeacherService.findTeacherList(teacher, pageNum, pageSize);
    }

    @Override
    public Teacher findOneById(Integer teacherId) {
        return iTeacherService.selectByKey(teacherId);
    }
}
