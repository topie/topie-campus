package com.topie.campus.security.service;

import com.github.pagehelper.PageInfo;
import com.topie.campus.basedao.service.IService;
import com.topie.campus.security.model.User;
import com.topie.campus.security.vo.FunctionVO;

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

    List<FunctionVO> findUserFunctionByLoginName(String loginName);

    int countByLoginName(String loginName);

    int updateLockStatusByUserId(int userId, Boolean accountNonLocked);

    String findLoginNameByUserId(Integer userId);
}
