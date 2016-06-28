package com.orange.security.service;

import com.orange.security.model.Role;
import com.orange.basedao.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/3 说明：
 */
public interface RoleService extends IService<Role> {
    int insertRole(Role role);

    int updateRole(Role role);

    Role findRoleById(int id);

    int deleteRole(int id);

    int insertRoleFunction(int roleId, int functionId);

    List<Map> findRoleMatchUpFunctions();
}
