package com.topie.campus.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.TreeNode;
import com.topie.campus.core.dto.HasRoleUserDto;
import com.topie.campus.security.dao.RoleMapper;
import com.topie.campus.security.model.Role;
import com.topie.campus.security.security.OrangeSecurityMetadataSourceImpl;
import com.topie.campus.security.security.OrangeSideUserCache;
import com.topie.campus.security.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/3 说明：
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    OrangeSideUserCache orangeSideUserCache;

    @Override
    public int insertRole(Role role) {
        int result = roleMapper.insertSelective(role);
        if (result > 0) {
            if (CollectionUtils.isNotEmpty(role.getFunctions())) {
                for (Integer functionId : role.getFunctions()) {
                    insertRoleFunction(role.getId(), functionId);
                }
            }
        }
        return result;
    }

    @Override
    public int updateRole(Role role) {
        int result = getMapper().updateByPrimaryKey(role);
        if (result > 0) {
            deleteFunctionByRoleId(role.getId());
            if (CollectionUtils.isNotEmpty(role.getFunctions())) {
                for (Integer functionId : role.getFunctions()) {
                    insertRoleFunction(role.getId(), functionId);
                }
            }
        }
        return result;
    }

    @Override
    public Role findRoleById(Integer id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int deleteRole(Integer id) {
        int result = getMapper().deleteByPrimaryKey(id);
        if (result > 0) {
            roleMapper.deleteUserRoleRelateByRoleId(id);
            roleMapper.deleteFunctionByRoleId(id);
            refreshAuthAndResource(id);
        }
        return result;
    }

    @Override
    public int insertRoleFunction(Integer roleId, Integer functionId) {
        int result = roleMapper.insertRoleFunction(roleId, functionId);
        if (result > 0) {
            refreshAuthAndResource(roleId);
        }
        return result;
    }
    @Override
    public void refreshAuthAndResource(Integer roleId) {
        List<Integer> userIds = roleMapper.findFunctionByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (Integer userId : userIds) {
                orangeSideUserCache.removeUserFromCacheByUserId(userId);
            }
        }
        OrangeSecurityMetadataSourceImpl.refreshResourceMap();
    }

    @Override
    public List<Map> findRoleMatchUpFunctions() {
        return roleMapper.findRoleMatchUpFunctions();
    }

    @Override
    public List<TreeNode> getRoleTreeNodes(Role role) {
        return roleMapper.selectRoleTreeNodes(role);
    }

    @Override
    public PageInfo<Role> findRoleList(int pageNum, int pageSize, Role role) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.findRoleList(role);
        PageInfo<Role> page = new PageInfo<Role>(list);
        return page;
    }

    @Override
    public List<Role> findRoleList(Role role) {
        return roleMapper.findRoleList(role);
    }

    @Override
    public int deleteFunctionByRoleId(Integer roleId) {
        return roleMapper.deleteFunctionByRoleId(roleId);
    }

    @Override
    public List<Integer> findFunctionByRoleId(Integer roleId) {
        return roleMapper.findFunctionByRoleId(roleId);
    }

    @Override
    public List<Integer> findHasRoleUserIdsByRoleId(Integer roleId) {
        return roleMapper.findHasRoleUserIdsByRoleId(roleId);
    }

    @Override
    public PageInfo<HasRoleUserDto> findHasRoleUserDtoListByRoleId(int pageNum, int pageSize,
            HasRoleUserDto hasRoleUserDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<HasRoleUserDto> list = roleMapper.findHasRoleUserDtoList(hasRoleUserDto);
        PageInfo<HasRoleUserDto> page = new PageInfo<HasRoleUserDto>(list);
        return page;
    }
}
