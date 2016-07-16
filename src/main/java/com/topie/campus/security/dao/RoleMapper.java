package com.topie.campus.security.dao;

import com.topie.campus.security.model.Role;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {

    int insertRoleFunction(int roleId, int functionId);

    List<Map> findRoleMatchUpFunctions();
}
