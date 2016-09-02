package com.topie.campus.core.api.sys;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by cgj on 2016/4/9.
 */
@Controller
@RequestMapping("/api/sys/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public Result users(HttpServletRequest request, User user,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        PageInfo<User> pageInfo = userService.findUserList(pageNum, pageSize, user);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            logger.debug(result.toString());
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
    public Result updateUser(User user) {
        if (userService.findExistUser(user) > 0) {
            throw new AuthBusinessException(AuBzConstant.LOGIN_NAME_EXIST);
        }
        userService.updateUser(user);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/load/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadUser(@PathVariable(value = "userId") int userId) {
        User user = userService.findUserById(userId);
        return ResponseUtil.success(user);
    }

    @RequestMapping(value = "/lock/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result lock(@PathVariable(value = "userId") int userId) {
        int result = userService.updateLockStatusByUserId(userId, false);
        if (result > 0) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error("操作未成功。");
        }
    }

    @RequestMapping(value = "/unLock/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result unLock(@PathVariable(value = "userId") int userId) {
        int result = userService.updateLockStatusByUserId(userId, true);
        if (result > 0) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.error("操作未成功。");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delUser(@RequestParam(value = "user_id", required = true) int userId) {
        if (SecurityUtil.getCurrentSecurityUser().getId() == userId) {
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
