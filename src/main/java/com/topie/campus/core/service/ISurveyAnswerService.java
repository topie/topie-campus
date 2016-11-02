package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.core.model.SurveyAnswer;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface ISurveyAnswerService extends IService<SurveyAnswer> {

    void insertStudentAnswerList(List<SurveyAnswer> answerList, Integer groupId, Integer studentId);

    void insertTeacherAnswerList(List<SurveyAnswer> answerList, Integer groupId, Integer teacherId);
}
