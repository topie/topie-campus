package com.topie.campus.core.api.info;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.model.Teacher;
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
import java.util.HashMap;
import java.util.Map;

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
        Map params = new HashMap<>();
        params.put("gender", Gender.getNameList());
        params.put("eb", EducationBackground.getNameList());
        params.put("degree", Degree.getNameList());
        params.put("ps", PoliticalStatus.getNameList());
        String html = freeMarkerUtil.getStringFromTemplate("/info/", "upload.ftl", params);
        return ResponseUtil.success(html);
    }

    @RequestMapping(value = "/user/downloadTemplate", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource(excelTemplate + "template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "模板.xlsx");
    }

    @RequestMapping(value = "/user/uploadExcel", method = RequestMethod.POST)
    @ResponseBody
    public Result userUpload(HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return ResponseUtil.error("附件为空");
        }
        ExcelLogs logs = new ExcelLogs();
        iInfoBasicService.userUpload(file, logs);
        return ResponseUtil.success(logs.getErrorLogList());
    }

    @RequestMapping(value = "/teacher/pageList", method = RequestMethod.GET)
    @ResponseBody
    public Result teacherList(Teacher teacher,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        PageInfo<Teacher> pageInfo = iInfoBasicService.findTeacherList(pageNum, pageSize, teacher);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

}
