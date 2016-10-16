package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.SurveyQuestionMapper;
import com.topie.campus.core.model.SurveyQuestion;
import com.topie.campus.core.service.ISurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class SurveyQuestionImpl extends BaseService<SurveyQuestion> implements ISurveyQuestionService {

    @Autowired
    private SurveyQuestionMapper surveyQuestionMapper;

    @Override
    public SimplePageInfo<SurveyQuestion> selectByPage(SurveyQuestion surveyQuestion, Integer pageNum,
            Integer pageSize) {
        List<SurveyQuestion> list = surveyQuestionMapper
                .selectByPage(surveyQuestion, (pageNum - 1) * pageSize, pageSize);
        Long total = surveyQuestionMapper.count(surveyQuestion);
        SimplePageInfo<SurveyQuestion> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<SurveyQuestion> selectByGroupIdByPage(SurveyQuestion surveyQuestion, Integer groupId,
            int pageNum, int pageSize) {
        List<SurveyQuestion> list = surveyQuestionMapper
                .selectByGroupIdByPage(surveyQuestion, groupId, (pageNum - 1) * pageSize, pageSize);
        Long total = surveyQuestionMapper.countByGroupId(surveyQuestion, groupId);
        SimplePageInfo<SurveyQuestion> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public List<SurveyQuestion> selectByGroupId(Integer groupId) {
        List<SurveyQuestion> list = surveyQuestionMapper
                .selectByGroupIdByPage(new SurveyQuestion(), groupId, null, null);
        return list;
    }

}
