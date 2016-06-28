package com.orange.security.service;

import com.github.pagehelper.PageInfo;
import com.orange.security.model.User;
import com.orange.basedao.service.IService;

import java.util.List;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface UserService extends IService<User> {
    int insertUser(User user);

    int updateUser(User user);

    User findUserById(int id);

    User findUserByLoginName(String loginName);

    int deleteUser(int id);

    int insertUserRole(int userId, int roleId);

    List<Integer> findUserRoleByUserId(int userId);

    PageInfo<User> findUserList(Integer pageNum, Integer pageSize, User user);

    int findExistUser(User user);
}
