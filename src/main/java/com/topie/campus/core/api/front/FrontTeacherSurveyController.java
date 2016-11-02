package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.StudentSimpleDto;
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
@RequestMapping("/api/front/teacherSurvey")
public class FrontTeacherSurveyController {

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
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        if (teacherId == null) {
            return ResponseUtil.error(401, "当前用户非导师角色");
        }
        surveyGroup.setStatus(1);
        SimplePageInfo<SurveyGroup> pageInfo = iSurveyGroupService
                .selectByPageByTeacherId(surveyGroup, teacherId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result submit(@RequestParam(value = "groupId") Integer groupId, @RequestBody List<SurveyAnswer> answerList) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        Integer teacherId = iTeacherService.findTeacherIdByUserId(userId);
        if (teacherId == null) {
            return ResponseUtil.error(401, "当前用户非导师角色");
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
        Boolean isComplete = iSurveyGroupService.selectCompleteByTeacherId(groupId, teacherId);
        if (isComplete == null) {
            return ResponseUtil.error(500, "没有权限参与该问卷");
        }
        iSurveyAnswerService.insertTeacherAnswerList(answerList, groupId, teacherId);
        return ResponseUtil.success();
    }
}
