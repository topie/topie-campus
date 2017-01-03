package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.service.IInfoBasicService;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by chenguojun on 9/21/16.
 */
@Controller
@RequestMapping("/api/info/student")
public class InfoStudentController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(Student student,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Student> pageInfo = iInfoBasicService.findStudentList(student, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadStudent(@PathVariable(value = "studentId") int studentId) {
        Student student = iInfoBasicService.findOneByStudentId(studentId);
        return ResponseUtil.success(student);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result studentInsert(Student student) {
        int result = iInfoBasicService.insertStudent(student);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result studentUpdate(Student student) {
        int result = iInfoBasicService.updateStudent(student);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result studentDelete(@RequestParam("id") Integer studentId) {
        int result = iInfoBasicService.deleteStudent(studentId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    @ResponseBody
    public Result teacher(TeacherSimpleDto teacherSimpleDto, @RequestParam("typeId") Integer typeId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<TeacherSimpleDto> pageInfo = iInfoBasicService
                .findTeacherSimpleDtoListWithBindInfo(teacherSimpleDto, typeId, studentId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/sendOneMsg")
    @ResponseBody
    public Result sendMsg(TeacherSimpleDto teacherSimpleDto, @RequestParam("phone") String phone,
            @RequestParam("sign") String sign, @RequestParam("message") String message)
            throws ClientProtocolException, IOException {
        iInfoBasicService.sendOneMsg(sign, message, phone);
        return ResponseUtil.success("发送成功！");
    }
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    @ResponseBody
    public Result resetPassword(@RequestParam(value ="studentId") Integer studentId) {
        iInfoBasicService.updateStudentToResetPassword(studentId);
        return ResponseUtil.success(ResultCode.OP_SUCCESS);
    }

}
