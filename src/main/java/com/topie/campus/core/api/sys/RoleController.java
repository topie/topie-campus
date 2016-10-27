package com.topie.campus.core.api.sys;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.Option;
import com.topie.campus.common.TreeNode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.HasRoleUserDto;
import com.topie.campus.security.model.Role;
import com.topie.campus.security.service.RoleService;
import com.topie.campus.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgj on 2016/4/9.
 */
@Controller
@RequestMapping("/api/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public Result roles(Role role, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        PageInfo<Role> pageInfo = roleService.findRoleList(pageNum, pageSize, role);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertRole(Role role) {
        int result = roleService.insertRole(role);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateRole(Role role) {
        roleService.updateRole(role);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/load/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadRole(@PathVariable(value = "roleId") Integer roleId) {
        Role role = roleService.findRoleById(roleId);
        List functions = roleService.findFunctionByRoleId(roleId);
        if (functions != null) role.setFunctions(functions);
        return ResponseUtil.success(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam(value = "roleId") Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/treeNodes", method = RequestMethod.POST)
    @ResponseBody
    public Object treeNodes(Role role) {
        List<TreeNode> list = roleService.getRoleTreeNodes(role);
        return list;
    }

    @RequestMapping(value = "/hasRoleUserList", method = RequestMethod.GET)
    @ResponseBody
    public Result hasRoleUserList(HasRoleUserDto hasRoleUserDto,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        if (hasRoleUserDto.getRoleId() == null) {
            return ResponseUtil.error("角色id不能为空");
        }
        PageInfo<HasRoleUserDto> pageInfo = roleService
                .findHasRoleUserDtoListByRoleId(pageNum, pageSize, hasRoleUserDto);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    @ResponseBody
    public Result selectUser(@RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "userId") Integer userId) {
        userService.insertUserRole(userId, roleId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/cancelUser", method = RequestMethod.GET)
    @ResponseBody
    public Result cancelUser(@RequestParam(value = "roleId") Integer roleId,
            @RequestParam(value = "userId") Integer userId) {
        userService.deleteUserRole(userId, roleId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    @ResponseBody
    public Object options() {
        List<Role> list = roleService.findRoleList(null);
        List<Option> options = new ArrayList<>();
        for (Role role : list) {
            Option option = new Option(role.getRoleName(), role.getId());
            options.add(option);
        }
        return options;
    }

}
