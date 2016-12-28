package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.service.IInfoBasicService;
import com.topie.campus.core.service.ITeacherTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenguojun on 9/21/16.
 */
@Controller
@RequestMapping("/api/info/teacher")
public class InfoTeacherController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @Autowired
    private ITeacherTypeService iTeacherTypeService;
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(Teacher teacher,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Teacher> pageInfo = iInfoBasicService.findTeacherList(teacher, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
    
    @RequestMapping(value = "/leaderPage", method = RequestMethod.GET)
    @ResponseBody
    public Result leaderPage(Teacher teacher,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Teacher> pageInfo = iInfoBasicService.findTeacherList(teacher, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{teacherId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadTeacher(@PathVariable(value = "teacherId") Integer teacherId) {
        Teacher teacher = iInfoBasicService.findOneByTeacherId(teacherId);
        List<Integer> typeIds = iTeacherTypeService.selectTypeIdsByTeacherId(teacherId);
        teacher.setTypeIds(typeIds);
        return ResponseUtil.success(teacher);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result teacherInsert(Teacher teacher) {
        int result = iInfoBasicService.insertTeacher(teacher);
        if (result > 0) {
            for (Integer typeId : teacher.getTypeIds()) {
                iTeacherTypeService.insertTeacherAndTeacherTypeRelate(teacher.getId(), typeId);
            }
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result teacherUpdate(Teacher teacher) {
        int result = iInfoBasicService.updateTeacher(teacher);
        if (result > 0) {
            iTeacherTypeService.deleteTeacherAndTeacherTypeRelate(teacher.getId());
            for (Integer typeId : teacher.getTypeIds()) {
                iTeacherTypeService.insertTeacherAndTeacherTypeRelate(teacher.getId(), typeId);
            }
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    @ResponseBody
    public Result resetPassword(@RequestParam(value ="teacherId") Integer teacherId) {
        iInfoBasicService.updateToResetPassword(teacherId);
        return ResponseUtil.success(ResultCode.OP_SUCCESS);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result teacherDelete(@RequestParam("teacherId") Integer teacherId) {
        int result = iInfoBasicService.deleteTeacher(teacherId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseBody
    public Result student(StudentSimpleDto studentSimpleDto, @RequestParam("typeId") Integer typeId,
            @RequestParam(value ="teacherId",required = false) Integer teacherId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<StudentSimpleDto> pageInfo = iInfoBasicService
                .findStudentSimpleDtoListWithBindInfo(studentSimpleDto, typeId, teacherId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

}
