package com.topie.campus.core.api.front;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by chenguojun on 9/24/16.
 */
@Controller
@RequestMapping("/api/frontCommon")
public class FrontCommonController {
    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public Result profile() {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        List<Integer> roles = SecurityUtil.getCurrentRoles();
        if (roles.contains(1)) {
            Teacher teacher = iTeacherService.findTeacherByUserId(userId);
            return ResponseUtil.success(teacher);
        }
        return ResponseUtil.success();
    }
}
