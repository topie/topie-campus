package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.model.GroupStat;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.model.SurveyQuestion;
import com.topie.campus.core.service.ISurveyGroupService;
import com.topie.campus.core.service.ISurveyQuestionService;
import com.topie.campus.core.service.ITeacherService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 9/21/16.
 */
@Controller
@RequestMapping("/api/info/surveyGroup")
public class InfoSurveyGroupController {

    @Autowired
    private ISurveyGroupService iSurveyGroupService;

    @Autowired
    private ISurveyQuestionService iSurveyQuestionService;

    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(SurveyGroup surveyGroup,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<SurveyGroup> pageInfo = iSurveyGroupService.selectByPage(surveyGroup, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{surveyGroupId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadSurveyGroup(@PathVariable(value = "surveyGroupId") Integer surveyGroupId) {
        SurveyGroup surveyGroup = iSurveyGroupService.selectByKey(surveyGroupId);
        return ResponseUtil.success(surveyGroup);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result surveyGroupInsert(SurveyGroup surveyGroup) {
        //todo 直接审核通过 待加入审核机制
        surveyGroup.setStatus(1);
        int result = iSurveyGroupService.insertSelective(surveyGroup);
        if (result > 0) {
            Integer groupId = surveyGroup.getGroupId();
            iSurveyGroupService.insertInitGroupStudent(groupId, surveyGroup.getTypeId());
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result surveyGroupUpdate(SurveyGroup surveyGroup) {
        int result = iSurveyGroupService.updateSelective(surveyGroup);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result surveyGroupDelete(@RequestParam("id") Integer surveyGroupId) {
        int result = iSurveyGroupService.delete(surveyGroupId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    @ResponseBody
    public Result questions(SurveyQuestion surveyQuestion, @RequestParam("groupId") Integer groupId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        SimplePageInfo<SurveyQuestion> pageInfo = iSurveyQuestionService
                .selectByGroupIdByPage(surveyQuestion, groupId, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/insertGroupQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Result insertGroupQuestion(@RequestParam("groupId") Integer groupId,
            @RequestParam("questionId") Integer questionId) {
        int maxRelate = 10;
        int count = iSurveyGroupService.countGroupQuestionRelate(groupId);
        if (count >= maxRelate) {
            return ResponseUtil.error(ResultCode.OP_FAIL + "超出最大题目数！");
        }
        int result = iSurveyGroupService.insertGroupQuestionRelate(groupId, questionId);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/updateGroupQuestionSort", method = RequestMethod.GET)
    @ResponseBody
    public Result updateGroupQuestionSort(@RequestParam("groupId") Integer groupId,
            @RequestParam("questionId") Integer questionId, @RequestParam("sort") Integer sort) {
        int result = iSurveyGroupService.updateGroupQuestionSort(groupId, questionId, sort);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/deleteGroupQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteGroupQuestion(@RequestParam("groupId") Integer groupId,
            @RequestParam("questionId") Integer questionId) {
        int result = iSurveyGroupService.deleteGroupQuestionRelate(groupId, questionId);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/updateOnlineBegin", method = RequestMethod.GET)
    @ResponseBody
    public Result updateOnlineBegin(@RequestParam("groupId") Integer groupId) {
        int result = iSurveyGroupService.updateOnlineStatus(groupId, 1);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/updateOnlineEnd", method = RequestMethod.GET)
    @ResponseBody
    public Result updateOnlineEnd(@RequestParam("groupId") Integer groupId) {
        int result = iSurveyGroupService.updateOnlineStatus(groupId, 2);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    @ResponseBody
    public Result stat(@RequestParam(value = "groupId") Integer groupId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        SurveyGroup surveyGroup = iSurveyGroupService.selectByKey(groupId);
        Integer typeId = surveyGroup.getTypeId();
        List<TeacherSimpleDto> teacherSimpleDtoList = new ArrayList<>();
        if (typeId != null) {
            teacherSimpleDtoList = iTeacherService.findTeacherByTypeId(typeId);
        }
        List<SurveyQuestion> surveyQuestions = iSurveyQuestionService.selectByGroupId(groupId);

        List<GroupStat> list = iSurveyGroupService.selectStatByGroupId(groupId);
        Map result = new HashMap();
        result.put("group", surveyGroup);
        result.put("teacher", teacherSimpleDtoList);
        result.put("questions", surveyQuestions);
        result.put("result", list);
        return ResponseUtil.success(result);
    }

}
