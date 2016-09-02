package com.topie.campus.security.dao;

import com.topie.campus.security.model.User;
import com.topie.campus.security.vo.FunctionVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User findUserByLoginName(@Param("loginName") String loginName);

    int insertUserRole(int userId, int roleId);

    List<Integer> findUserRoleByUserId(int userId);

    List<User> findUserList(User user);

    int findExistUser(User user);

    List<FunctionVO> findUserFunction(@Param("loginName") String loginName);

    int countByLoginName(@Param("loginName") String loginName);

    int updateAccountNonLocked(@Param("userId") Integer UserId, @Param("accountNonLocked") Boolean accountNonLocked);

    String findLoginNameByUserId(@Param("userId") Integer userId);
}
