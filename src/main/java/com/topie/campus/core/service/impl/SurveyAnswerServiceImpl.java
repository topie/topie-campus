package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.core.dao.SurveyAnswerMapper;
import com.topie.campus.core.model.SurveyAnswer;
import com.topie.campus.core.service.ISurveyAnswerService;
import com.topie.campus.core.service.ISurveyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/16.
 */
@Service
public class SurveyAnswerServiceImpl extends BaseService<SurveyAnswer> implements ISurveyAnswerService {

    @Autowired
    private SurveyAnswerMapper surveyAnswerMapper;

    @Autowired
    private ISurveyGroupService iSurveyGroupService;

    @Override
    public void insertStudentAnswerList(List<SurveyAnswer> answerList, Integer groupId, Integer studentId) {
        for (SurveyAnswer surveyAnswer : answerList) {
            surveyAnswer.setStudentId(studentId);
            surveyAnswerMapper.insertOrUpdate(surveyAnswer);
        }
        iSurveyGroupService.updateGroupStudentComplete(groupId, studentId);
    }

    @Override
    public void insertTeacherAnswerList(List<SurveyAnswer> answerList, Integer groupId, Integer teacherId) {
        for (SurveyAnswer surveyAnswer : answerList) {
            surveyAnswer.setTeacherId(teacherId);
            surveyAnswerMapper.insertOrUpdate(surveyAnswer);
        }
        iSurveyGroupService.updateGroupTeacherComplete(groupId, teacherId);
    }
}
