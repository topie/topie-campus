package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        SimplePageInfo<Teacher> pageInfo = iInfoBasicService.findTeacherList(teacher, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/teacher/load/{teacherId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadTeacher(@PathVariable(value = "teacherId") int teacherId) {
        Teacher teacher = iInfoBasicService.findOneByTeacherId(teacherId);
        return ResponseUtil.success(teacher);
    }

    @RequestMapping(value = "/teacher/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result teacherInsert(Teacher teacher) {
        int result = iInfoBasicService.insertTeacher(teacher);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/teacher/update", method = RequestMethod.POST)
    @ResponseBody
    public Result teacherUpdate(Teacher teacher) {
        int result = iInfoBasicService.updateTeacher(teacher);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/teacher/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result teacherDelete(@RequestParam("id") Integer teacherId) {
        int result = iInfoBasicService.deleteTeacher(teacherId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/student/pageList", method = RequestMethod.GET)
    @ResponseBody
    public Result studentList(Student student,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Student> pageInfo = iInfoBasicService.findStudentList(student, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/student/load/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadStudent(@PathVariable(value = "studentId") int studentId) {
        Student student = iInfoBasicService.findOneByStudentId(studentId);
        return ResponseUtil.success(student);
    }

    @RequestMapping(value = "/student/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result studentInsert(Student student) {
        int result = iInfoBasicService.insertStudent(student);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    @ResponseBody
    public Result studentUpdate(Student student) {
        int result = iInfoBasicService.updateStudent(student);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/student/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result studentDelete(@RequestParam("id") Integer studentId) {
        int result = iInfoBasicService.deleteStudent(studentId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }
}
