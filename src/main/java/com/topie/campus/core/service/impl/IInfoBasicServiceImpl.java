package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.core.model.InfoBasic;
import com.topie.campus.core.service.IInfoBasicService;
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
import java.util.Date;
import java.util.Map;

/**
 * Created by chenguojun on 8/10/16.
 */
@Service
public class IInfoBasicServiceImpl extends BaseService<InfoBasic>
        implements IInfoBasicService {
    @Autowired
    private UserService userService;

    @Override
    public void userUpload(MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<Map> teacher;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            teacher = ExcelUtil.importExcelX(Map.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            teacher = ExcelUtil.importExcel(Map.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        Collection<Map> student;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            student = ExcelUtil.importExcelX(Map.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        } else {
            student = ExcelUtil.importExcel(Map.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        }
        for (Map map : teacher) {
            String code = ((Double) map.get("唯一编码")).toString();
            String name = (String) map.get("姓名");
            Date birth = (Date) map.get("出生日期");
            String gender = (String) map.get("性别");
            String mobile = ((Double) map.get("手机")).toString();
            User user = UserVO.buildSimpleUser(mobile, name, "");
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            InfoBasic t = new InfoBasic();
            t.setUserId(user.getId());
            t.setUserType(1);
            t.setUserCode(code);
            t.setUserName(name);
            t.setBirth(birth);
            t.setGender((gender.equals("男") ? 1 : 0));
            t.setMobilePhone(mobile);
            save(t);
        }

        for (Map map : student) {
            String code = ((Double) map.get("唯一编码")).toString();
            String name = (String) map.get("姓名");
            Date birth = (Date) map.get("出生日期");
            String gender = (String) map.get("性别");
            String mobile = ((Double) map.get("手机")).toString();
            User user = UserVO.buildSimpleUser(mobile, name, "");
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            InfoBasic t = new InfoBasic();
            t.setUserId(user.getId());
            t.setUserType(0);
            t.setUserCode(code);
            t.setUserName(name);
            t.setBirth(birth);
            t.setGender((gender.equals("男") ? 1 : 0));
            t.setMobilePhone(mobile);
            save(t);

            String teachers = (String) map.get("教师唯一编码(多个以#分开)");
            String[] teacherCodeArray = teachers.split("#");
            if (teacherCodeArray.length > 0) {

            }
        }
    }
}
