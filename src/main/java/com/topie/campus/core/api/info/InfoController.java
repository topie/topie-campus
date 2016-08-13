package com.topie.campus.core.api.info;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.service.InfoBasicService;
import com.topie.campus.security.service.UserService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenguojun on 8/10/16.
 */
@Controller
@RequestMapping("/api/info")
public class InfoController {
    @Autowired
    private InfoBasicService infoBasicService;

    @RequestMapping(value = "/template/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource("/excel/template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "模板.xlsx");
    }

    @RequestMapping(value = "/excel/upload", method = RequestMethod.POST)
    public void upload(HttpServletResponse response,
                       @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            ResponseUtil.writeJson(response, new Result(500, "附件为空"));
            return;
        }
        ExcelLogs logs = new ExcelLogs();
        infoBasicService.upload(file, logs);
        ResponseUtil.writeJson(response, new Result(200, logs.getErrorLogList()));
    }

}
