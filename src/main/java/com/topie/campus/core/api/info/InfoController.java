package com.topie.campus.core.api.info;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenguojun on 8/10/16.
 */
@Controller
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @Value("${excel.template}")
    private String excelTemplate;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @RequestMapping(value = "/user/upload", method = RequestMethod.GET)
    @ResponseBody
    public Object page() throws Exception {
        String html = freeMarkerUtil.getStringFromTemplate("/info/", "upload.ftl", null);
        return ResponseUtil.success(html);
    }

    @RequestMapping(value = "/user/downloadTemplate", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource(excelTemplate + "template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "模板.xlsx");
    }

    @RequestMapping(value = "/user/uploadExcel", method = RequestMethod.POST)
    public void userUpload(HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            ResponseUtil.writeJson(response, new Result(500, "附件为空"));
            return;
        }
        ExcelLogs logs = new ExcelLogs();
        iInfoBasicService.userUpload(file, logs);
        ResponseUtil.writeJson(response, new Result(200, logs.getErrorLogList()));
    }

}
