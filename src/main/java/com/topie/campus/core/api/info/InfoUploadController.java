package com.topie.campus.core.api.info;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.service.IEmploymentService;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/api/info/upload")
public class InfoUploadController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @Autowired
    private IEmploymentService employmentService;

    @Value("${excel.template}")
    private String excelTemplate;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
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

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource(excelTemplate + "template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "导入模板.zip");
    }

    /**
     * @param response
     * @param excelType <option value=1>学生基本信息表</option>
     *                  <option value=2>教师基本信息表</option>
     *                  <option value=3>学生成绩信息表</option>
     *                  <option value=4>学生课程信息表</option>
     *                  <option value=5>学生等考信息表</option>
     *                  <option value=6>学生选课信息表</option>
     *                  <option value=7>学生就业信息表</option>
     *                  <option value=7>学生教师关联信息表</option>
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    @ResponseBody
    public Result userUpload(HttpServletResponse response, Integer excelType,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ExcelLogs logs = new ExcelLogs();
        if (file == null || file.isEmpty()) {
            return ResponseUtil.error("附件为空");
        } else if (excelType == null) {
            return ResponseUtil.error("未选择附件类型");
        } else if (excelType == 1) {
            iInfoBasicService.uploadStudentTable(file, logs);
        } else if (excelType == 2) {
            iInfoBasicService.uploadTeacherTable(file, logs);
        } else if (excelType == 3) {
            iInfoBasicService.uploadStuScore(file, logs);
        } else if (excelType == 4) {
            iInfoBasicService.uploadStuTimetable(file, logs);
        } else if (excelType == 5) {
            iInfoBasicService.uploadStuCet(file, logs);
        } else if (excelType == 6) {
            iInfoBasicService.uploadStuSeleCourse(file, logs);
        } else if (excelType == 7) {
            employmentService.employUpload(file, logs);
        } else if (excelType == 8) {
            if (StringUtils.isNotEmpty(typeId)) {
                Integer t = Integer.valueOf(typeId);
                iInfoBasicService.uploadTeacherStudentRelate(t, file, logs);
            } else {
                return ResponseUtil.error("请先选择教师类型");
            }
        }
        return ResponseUtil.success("导入完成！");
    }
}
