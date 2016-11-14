package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.LoginInfoMapper;
import com.topie.campus.core.dto.LoginInfoSearchParams;
import com.topie.campus.core.model.LoginInfo;
import com.topie.campus.core.service.ILoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/11/13.
 */
@Service
public class LoginInfoServiceImpl extends BaseService<LoginInfo> implements ILoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public SimplePageInfo<LoginInfo> findStudentLoginInfoByPage(LoginInfoSearchParams loginInfoSearchParams, int pageNum,
            int pageSize) {
        List<LoginInfo> list = loginInfoMapper
                .findStudentLoginInfoByPageNumAndPageSize(loginInfoSearchParams, (pageNum - 1) * pageSize, pageSize);
        Long total = loginInfoMapper.countStudentLoginInfo(loginInfoSearchParams);
        SimplePageInfo<LoginInfo> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<LoginInfo> findTeacherLoginInfoByPage(LoginInfoSearchParams loginInfoSearchParams,
            int pageNum, int pageSize) {
        List<LoginInfo> list = loginInfoMapper
                .findTeacherLoginInfoByPageNumAndPageSize(loginInfoSearchParams, (pageNum - 1) * pageSize, pageSize);
        Long total = loginInfoMapper.countTeacherLoginInfo(loginInfoSearchParams);
        SimplePageInfo<LoginInfo> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public List<LoginInfo> findStudentLoginInfoList(LoginInfoSearchParams loginInfoSearchParams) {
        List<LoginInfo> list = loginInfoMapper
                .findStudentLoginInfoList(loginInfoSearchParams);
        return list;
    }

    @Override
    public List<LoginInfo> findTeacherLoginInfoList(LoginInfoSearchParams loginInfoSearchParams) {
        List<LoginInfo> list = loginInfoMapper
                .findTeacherLoginInfoList(loginInfoSearchParams);
        return list;
    }

}
