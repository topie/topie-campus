package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.core.model.InfoBasic;
import com.topie.campus.tools.excel.ExcelLogs;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface InfoBasicService extends IService<InfoBasic> {
    void upload(MultipartFile file, ExcelLogs logs) throws IOException;
}
