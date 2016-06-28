package com.orange.security.dao;

import com.orange.security.model.Role;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {

    int insertRoleFunction(int roleId, int functionId);

    List<Map> findRoleMatchUpFunctions();
}
