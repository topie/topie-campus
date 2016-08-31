package com.topie.campus.core.api.common;

import com.topie.campus.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenguojun on 8/31/16.
 */
@Controller
@RequestMapping("/api/noneAuth")
public class NoneAuthController {
    @Autowired
    private UserService userService;

    @RequestMapping("/unique")
    @ResponseBody
    public Object unique(@RequestParam(value = "loginName", required = false) String loginName) {
        int count = userService.countByLoginName(loginName);
        return count > 0 ? false : true;
    }
}
