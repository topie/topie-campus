package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dto.SurveyAnswerExcelDto;
import com.topie.campus.core.model.GroupStudentStat;
import com.topie.campus.core.model.GroupTeacherStat;
import com.topie.campus.core.model.SurveyGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface ISurveyGroupService extends IService<SurveyGroup> {

    SimplePageInfo<SurveyGroup> selectByPage(SurveyGroup surveyGroup, Integer pageNum, Integer pageSize);

    int insertGroupQuestionRelate(Integer groupId, Integer questionId);

    int updateGroupQuestionSort(Integer groupId, Integer questionId, Integer sort);

    int deleteGroupQuestionRelate(Integer groupId, Integer questionId);

    int countGroupQuestionRelate(Integer groupId);

    int updateOnlineStatus(Integer groupId, Integer onlineStatus);

    int insertInitGroupStudent(Integer groupId, Integer typeId);

    SimplePageInfo<SurveyGroup> selectByPageByStudentId(SurveyGroup surveyGroup, Integer studentId, int pageNum,
            int pageSize);

    Integer selectTypeIdByGroupId(Integer groupId);

    int updateGroupStudentComplete(Integer groupId, Integer studentId);

    Boolean selectCompleteByStudentId(Integer groupId, Integer studentId);

    List<GroupTeacherStat> selectTeacherStatByGroupId(Integer groupId);

    List<SurveyGroup> selectByCurrentForStartTask(String current);

    List<SurveyGroup> selectByCurrentForEndTask(String current);

    List<SurveyAnswerExcelDto> selectSurveyComment(Integer groupId, Integer groupType);

    List<Map> selectStudentProcessByGroupId(Integer groupId);

    int insertInitGroupTeacher(Integer groupId, Integer typeId);

    SimplePageInfo<SurveyGroup> selectByPageByTeacherId(SurveyGroup surveyGroup, Integer teacherId, int pageNum, int pageSize);

    Boolean selectCompleteByTeacherId(Integer groupId, Integer teacherId);

    int updateGroupTeacherComplete(Integer groupId, Integer teacherId);

    List<GroupStudentStat> selectStudentStatByGroupId(Integer groupId);

    List<Map> selectTeacherProcessByGroupId(Integer groupId);

    int insertSelectiveSurveyGroup(SurveyGroup surveyGroup);

    int updateSelectiveSurveyGroup(SurveyGroup surveyGroup);

    List<SurveyAnswerExcelDto> selectSurveyAnswer(Integer groupId, Integer groupType);

}
