package com.topie.campus.core.service;

import com.github.pagehelper.PageInfo;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.tools.excel.ExcelLogs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface IInfoBasicService {

    void userUpload(MultipartFile file, ExcelLogs logs) throws IOException;

    PageInfo<Teacher> findTeacherList(Integer pageNum, Integer pageSize, Teacher teacher);
}
