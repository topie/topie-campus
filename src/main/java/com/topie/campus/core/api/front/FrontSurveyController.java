package com.topie.campus.core.api.front;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.model.SurveyQuestion;
import com.topie.campus.core.service.*;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 2016/10/16.
 */
@Controller
@RequestMapping("/api/front/survey")
public class FrontSurveyController {

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

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    @ResponseBody
    public Result survey(@RequestParam(value = "groupId") Integer groupId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SurveyGroup surveyGroup = iSurveyGroupService.selectByKey(groupId);
        if (surveyGroup == null) {
            return ResponseUtil.error(500, "问卷调查不存在");
        }
        if (surveyGroup.getOnlineStatus() == 0) {
            return ResponseUtil.error(500, "问卷调查未开始");
        }
        if (surveyGroup.getOnlineStatus() == 2) {
            return ResponseUtil.error(500, "问卷调查已结束");
        }
        Integer typeId = surveyGroup.getTypeId();
        if (surveyGroup.getGroupType() == 1) {
            Integer studentId = iStudentService.findStudentIdByUserId(userId);
            if (studentId == null) {
                return ResponseUtil.error(401, "当前用户非学生角色");
            }
            Boolean isComplete = iSurveyGroupService.selectCompleteByStudentId(groupId, studentId);
            if (isComplete == null) {
                return ResponseUtil.error(500, "没有权限参与该问卷");
            }
            List<TeacherSimpleDto> teacherSimpleDtoList = new ArrayList<>();
            if (typeId != null) {
                teacherSimpleDtoList = iTeacherService.findTeacherByStudentIdAndTypeId(studentId, typeId);
            }
            List<SurveyQuestion> surveyQuestions = iSurveyQuestionService.selectByGroupId(groupId);
            Map result = new HashMap();
            result.put("groupType", surveyGroup.getGroupType());
            result.put("group", surveyGroup);
            result.put("teacher", teacherSimpleDtoList);
            result.put("questions", surveyQuestions);
            result.put("isComplete", isComplete);
            return ResponseUtil.success(result);
        } else {
            Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
            if (teacherId == null) {
                return ResponseUtil.error(401, "当前用户非导师角色");
            }
            Boolean isComplete = iSurveyGroupService.selectCompleteByTeacherId(groupId, teacherId);
            if (isComplete == null) {
                return ResponseUtil.error(500, "没有权限参与该问卷");
            }

            List<StudentSimpleDto> studentSimpleDtos = new ArrayList<>();
            if (typeId != null) {
                studentSimpleDtos = iStudentService.findStudentByTeacherIdAndTypeId(teacherId, typeId);
            }
            List<SurveyQuestion> surveyQuestions = iSurveyQuestionService.selectByGroupId(groupId);
            Map result = new HashMap();
            result.put("groupType", surveyGroup.getGroupType());
            result.put("group", surveyGroup);
            result.put("student", studentSimpleDtos);
            result.put("questions", surveyQuestions);
            result.put("isComplete", isComplete);
            return ResponseUtil.success(result);
        }
    }
}
