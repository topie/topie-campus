package com.topie.campus.core.api.sys;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtils;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cgj on 2016/4/9.
 */
@Controller
@RequestMapping("/api/sys/user")
public class UserController {

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public Result users(HttpServletRequest request, User user,
            @RequestParam(value = "page_num", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "page_size", required = false, defaultValue = "15") int pageSize) {
        PageInfo<User> pageInfo = userService.findUserList(pageNum, pageSize, user);
        Map params = new HashMap();
        params.put("list", pageInfo.getList());
        String html = freeMarkerUtil.getStringFromTemplate("/sys/user/", "page_list.ftl", params);
        Map result = new HashMap();
        result.put("html", html);
        result.put("pageNum", pageInfo.getPageNum());
        result.put("pages", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        return ResponseUtil.success(result);
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
