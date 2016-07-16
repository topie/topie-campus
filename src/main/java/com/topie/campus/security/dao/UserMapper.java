package com.topie.campus.security.dao;

import com.topie.campus.security.model.User;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User findUserByLoginName(String loginName);

    int insertUserRole(int userId, int roleId);

    List<Integer> findUserRoleByUserId(int userId);

    List<User> findUserList(User user);

    int findExistUser(User user);
}
