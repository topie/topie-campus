package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.SurveyAnswer;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.model.SurveyQuestion;
import com.topie.campus.core.service.*;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 2016/10/16.
 */
@Controller
@RequestMapping("/api/front/studentSurvey")
public class FrontStudentSurveyController {

    @Autowired
    private ISurveyGroupService iSurveyGroupService;

    @Autowired
    private ISurveyQuestionService iSurveyQuestionService;

    @Autowired
    private ISurveyAnswerService iSurveyAnswerService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(SurveyGroup surveyGroup,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        surveyGroup.setStatus(1);
        SimplePageInfo<SurveyGroup> pageInfo = iSurveyGroupService
                .selectByPageByStudentId(surveyGroup, studentId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    @ResponseBody
    public Result survey(@RequestParam(value = "groupId") Integer groupId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        Integer typeId = iSurveyGroupService.selectTypeIdByGroupId(groupId);
        //todo 判断是否可以参与
        List<TeacherSimpleDto> teacherSimpleDtoList = new ArrayList<>();
        if (typeId != null) {
            teacherSimpleDtoList = iTeacherService.findTeacherByStudentIdAndTypeId(studentId, typeId);
        }
        List<SurveyQuestion> surveyQuestions = iSurveyQuestionService.selectByGroupId(groupId);
        Map result = new HashMap();
        result.put("teacher", teacherSimpleDtoList);
        result.put("questions", surveyQuestions);
        return ResponseUtil.success(result);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result submit(@RequestParam(value = "groupId") Integer groupId, @RequestBody List<SurveyAnswer> answerList) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer studentId = iStudentService.findStudentIdByUserId(userId);
        if (studentId == null) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        iSurveyAnswerService.insertAnswerList(answerList, groupId, studentId);
        return ResponseUtil.success();
    }
}
