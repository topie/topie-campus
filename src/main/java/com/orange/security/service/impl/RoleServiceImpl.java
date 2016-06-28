package com.orange.security.service.impl;

import com.orange.security.dao.RoleMapper;
import com.orange.security.model.Role;
import com.orange.security.service.RoleService;
import com.orange.basedao.service.impl.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/3 说明：
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role>
        implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) {
        return getMapper().insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return getMapper().updateByPrimaryKey(role);
    }

    @Override
    public Role findRoleById(int id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int deleteRole(int id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insertRoleFunction(int roleId, int functionId) {
        return roleMapper.insertRoleFunction(roleId, functionId);
    }

    @Override
    public List<Map> findRoleMatchUpFunctions() {
        return roleMapper.findRoleMatchUpFunctions();
    }
}
