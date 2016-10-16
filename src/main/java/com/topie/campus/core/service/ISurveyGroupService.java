package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.SurveyGroup;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface ISurveyGroupService extends IService<SurveyGroup> {

    SimplePageInfo<SurveyGroup> selectByPage(SurveyGroup surveyGroup, Integer pageNum, Integer pageSize);

    int insertGroupQuestionRelate(Integer groupId, Integer questionId);

    int updateGroupQuestionSort(Integer groupId, Integer questionId, Integer sort);

    int deleteGroupQuestionRelate(Integer groupId, Integer questionId);

    int countGroupQuestionRelate(Integer groupId);

    int updateIsOnline(Integer groupId, Boolean isOnline);

    int insertInitGroupStudent(Integer groupId,Integer typeId);

    SimplePageInfo<SurveyGroup> selectByPageByStudentId(SurveyGroup surveyGroup, Integer studentId, int pageNum, int pageSize);

    Integer selectTypeIdByGroupId(Integer groupId);

    int updateGroupStudentComplete(Integer groupId, Integer studentId);
}
