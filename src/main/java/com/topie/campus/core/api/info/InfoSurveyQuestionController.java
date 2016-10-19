package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.SurveyQuestion;
import com.topie.campus.core.service.ISurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenguojun on 9/21/16.
 */
@Controller
@RequestMapping("/api/info/surveyQuestion")
public class InfoSurveyQuestionController {

    @Autowired
    private ISurveyQuestionService iSurveyQuestionService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(SurveyQuestion surveyQuestion,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<SurveyQuestion> pageInfo = iSurveyQuestionService
                .selectByPage(surveyQuestion, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadSurveyQuestion(@PathVariable(value = "questionId") Integer questionId) {
        SurveyQuestion surveyQuestion = iSurveyQuestionService.selectByKey(questionId);
        return ResponseUtil.success(surveyQuestion);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result surveyQuestionInsert(SurveyQuestion surveyQuestion) {
        if (surveyQuestion.getQuestionType() == null) {
            return ResponseUtil.error("问题类型不能为空！");
        }
        if (surveyQuestion.getQuestionType() > 2 || surveyQuestion.getQuestionType() < 1) {
            return ResponseUtil.error("问题类型错误！");
        }
        int result = iSurveyQuestionService.insertSelective(surveyQuestion);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result surveyQuestionUpdate(SurveyQuestion surveyQuestion) {
        int result = iSurveyQuestionService.updateSelective(surveyQuestion);
        if (result > 0) {
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result surveyQuestionDelete(@RequestParam("id") Integer surveyQuestionId) {
        int result = iSurveyQuestionService.delete(surveyQuestionId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

}
