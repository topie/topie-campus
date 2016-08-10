package com.topie.campus.core.api.sys;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;

/**
 * Created by cgj on 2016/4/9.
 */
@Controller
@RequestMapping("/api/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result users(User user,
                        @RequestParam(value = "page_num", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "page_size", required = false, defaultValue = "15") int pageSize) {
        PageInfo<User> pageInfo = userService.findUserList(pageNum, pageSize, user);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.error(result);
        }
        if (userService.findExistUser(user) > 0) {
            throw new AuthBusinessException(AuBzConstant.LOGIN_NAME_EXIST);
        }
        userService.insertUser(user);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.error(result);
        }
        if (userService.findExistUser(user) > 0) {
            throw new AuthBusinessException(AuBzConstant.LOGIN_NAME_EXIST);
        }
        userService.updateNotNull(user);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/load/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadUser(@PathVariable(value = "user_id") int userId) {
        User user = userService.findUserById(userId);
        return ResponseUtil.success(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delUser(@RequestParam(value = "user_id", required = true) int userId) {
        if (SecurityUtils.getCurrentSecurityUser().getId() == userId) {
            throw new AuthBusinessException(AuBzConstant.CANNOT_DEL_CURRENT_USER);
        }
        userService.delete(userId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/roles/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result userRoles(@PathVariable(value = "user_id") int userId) {
        List roles = userService.findUserRoleByUserId(userId);
        return ResponseUtil.success(roles);
    }
}
