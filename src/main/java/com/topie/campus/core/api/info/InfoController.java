package com.topie.campus.core.api.info;

import com.topie.campus.tools.excel.ExcelFileUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenguojun on 8/10/16.
 */
@Controller
@RequestMapping("/api/info")
public class InfoController {
    @RequestMapping(value = "/template/download", method = RequestMethod.GET)
    public void myFunction(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource("/excel/template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "模板.xlsx");
    }
}
