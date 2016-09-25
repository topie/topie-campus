package com.topie.campus.security.dao;

import com.topie.campus.common.TreeNode;
import com.topie.campus.security.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User findUserByLoginName(@Param("loginName") String loginName);

    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteUserAllRoles(@Param("userId") Integer userId);

    int deleteUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    List<Integer> findUserRoleByUserId(int userId);

    List<User> findUserList(User user);

    int findExistUser(User user);

    List<TreeNode> findUserFunctionByLoginName(@Param("loginName") String loginName);

    List<TreeNode> findUserFunctionByLoginNameAndDisplayType(@Param("loginName") String loginName,
            @Param("displayType") Integer displayType);

    int countByLoginName(@Param("loginName") String loginName);

    int updateAccountNonLocked(@Param("userId") Integer UserId, @Param("accountNonLocked") Boolean accountNonLocked);

    String findLoginNameByUserId(@Param("userId") Integer userId);

    List<User> findUserListByRoleId(@Param("user") User user, @Param("roleId") Integer roleId);
}
