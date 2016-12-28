package com.topie.campus.security.service;

import com.github.pagehelper.PageInfo;
import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.TreeNode;
import com.topie.campus.security.model.User;

import java.util.List;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface UserService extends IService<User> {

    int insertUser(User user);

    int updateUser(User user);

    int updateUserWithOnlyUserCache(User user);

    User findUserById(Integer id);

    User findUserByLoginName(String loginName);

    int deleteUser(Integer id);

    int insertUserRole(Integer userId, Integer roleId);

    int deleteUserAllRoles(Integer userId);

    List<Integer> findUserRoleByUserId(int userId);

    PageInfo<User> findUserList(Integer pageNum, Integer pageSize, User user);

    int findExistUser(User user);

    List<TreeNode> findUserFunctionByLoginName(String loginName);

    List<TreeNode> findUserFunctionByLoginNameAndDisplayType(String loginName,Integer displayType);

    int countByLoginName(String loginName);

    int updateLockStatusByUserId(int userId, Boolean accountNonLocked);

    String findLoginNameByUserId(Integer userId);

    int deleteUserRole(Integer userId, Integer roleId);

    PageInfo<User> findUserListByRoleId(int pageNum, int pageSize, User user, Integer roleId);
}
