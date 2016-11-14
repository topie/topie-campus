package com.topie.campus.core.dao;

import com.topie.campus.core.dto.LoginInfoSearchParams;
import com.topie.campus.core.model.LoginInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LoginInfoMapper extends Mapper<LoginInfo> {

    List<LoginInfo> findStudentLoginInfoByPageNumAndPageSize(
            @Param("param") LoginInfoSearchParams loginInfoSearchParams, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countStudentLoginInfo(@Param("param") LoginInfoSearchParams loginInfoSearchParams);

    List<LoginInfo> findTeacherLoginInfoByPageNumAndPageSize(
            @Param("param") LoginInfoSearchParams loginInfoSearchParams, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countTeacherLoginInfo(@Param("param") LoginInfoSearchParams loginInfoSearchParams);

    List<LoginInfo> findStudentLoginInfoList(@Param("param") LoginInfoSearchParams loginInfoSearchParams);

    List<LoginInfo> findTeacherLoginInfoList(@Param("param") LoginInfoSearchParams loginInfoSearchParams);

}
