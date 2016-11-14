package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.LoginInfoSearchParams;
import com.topie.campus.core.model.LoginInfo;

import java.util.List;

/**
 * Created by chenguojun on 2016/11/13.
 */
public interface ILoginInfoService extends IService<LoginInfo> {

    SimplePageInfo<LoginInfo> findStudentLoginInfoByPage(LoginInfoSearchParams loginInfoSearchParams, int pageNum, int pageSize);

    SimplePageInfo<LoginInfo> findTeacherLoginInfoByPage(LoginInfoSearchParams loginInfoSearchParams, int pageNum, int pageSize);

    List<LoginInfo> findStudentLoginInfoList(LoginInfoSearchParams loginInfoSearchParams);

    List<LoginInfo> findTeacherLoginInfoList(LoginInfoSearchParams loginInfoSearchParams);

}
