package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.SurveyQuestion;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface ISurveyQuestionService extends IService<SurveyQuestion> {

    SimplePageInfo<SurveyQuestion> selectByPage(SurveyQuestion surveyQuestion, Integer pageNum, Integer pageSize);

    SimplePageInfo<SurveyQuestion> selectByGroupIdByPage(SurveyQuestion surveyQuestion, Integer groupId, int pageNum, int pageSize);

    List<SurveyQuestion> selectByGroupId(Integer groupId);
}
