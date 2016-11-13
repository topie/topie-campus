package com.topie.campus.core.dao;

import com.topie.campus.core.dto.SurveyAnswerExcelDto;
import com.topie.campus.core.model.GroupStudentStat;
import com.topie.campus.core.model.GroupTeacherStat;
import com.topie.campus.core.model.SurveyGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SurveyGroupMapper extends Mapper<SurveyGroup> {

    List<SurveyGroup> selectByPage(@Param("surveyGroup") SurveyGroup surveyGroup,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    Long count(@Param("surveyGroup") SurveyGroup surveyGroup);

    int insertGroupQuestionRelate(@Param("groupId") Integer groupId, @Param("questionId") Integer questionId);

    int updateGroupQuestionSort(@Param("groupId") Integer groupId, @Param("questionId") Integer questionId,
            @Param("sort") Integer sort);

    int deleteGroupQuestionRelate(@Param("groupId") Integer groupId, @Param("questionId") Integer questionId);

    int countGroupQuestionRelate(@Param("groupId") Integer groupId);

    int updateOnlineStatus(@Param("groupId") Integer groupId, @Param("onlineStatus") Integer onlineStatus);

    int insertGroupStudentRelate(@Param("groupId") Integer groupId, @Param("studentId") Integer studentId,
            @Param("isComplete") Boolean isComplete);

    List<SurveyGroup> selectByPageByStudentId(@Param("surveyGroup") SurveyGroup surveyGroup,
            @Param("studentId") Integer studentId, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countByStudentId(@Param("surveyGroup") SurveyGroup surveyGroup, @Param("studentId") Integer studentId);

    Integer selectTypeIdByGroupId(@Param("groupId") Integer groupId);

    int updateGroupStudentComplete(@Param("groupId") Integer groupId, @Param("studentId") Integer studentId);

    Boolean selectCompleteByStudentId(@Param("groupId") Integer groupId, @Param("studentId") Integer studentId);

    List<GroupTeacherStat> selectTeacherStatByGroupId(@Param("groupId") Integer groupId);

    List<SurveyGroup> selectByCurrentForStartTask(@Param("current") String current);

    List<SurveyGroup> selectByCurrentForEndTask(@Param("current") String current);

    List<SurveyAnswerExcelDto> selectSurveyComment(@Param("groupId") Integer groupId,
            @Param("groupType") Integer groupType);

    List<Map> selectStudentProcessByGroupId(@Param("groupId") Integer groupId);

    int insertGroupTeacherRelate(@Param("groupId") Integer groupId, @Param("teacherId") Integer teacherId,
            @Param("isComplete") Boolean isComplete);

    List<SurveyGroup> selectByPageByTeacherId(@Param("surveyGroup") SurveyGroup surveyGroup,
            @Param("teacherId") Integer teacherId, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countByTeacherId(@Param("surveyGroup") SurveyGroup surveyGroup, @Param("teacherId") Integer teacherId);

    int updateGroupTeacherComplete(@Param("groupId") Integer groupId, @Param("teacherId") Integer teacherId);

    Boolean selectCompleteByTeacherId(@Param("groupId") Integer groupId, @Param("teacherId") Integer teacherId);

    List<GroupStudentStat> selectStudentStatByGroupId(@Param("groupId") Integer groupId);

    List<Map> selectTeacherProcessByGroupId(@Param("groupId") Integer groupId);

    int deleteGroupStudentRelate(@Param("groupId") Integer groupId);

    int deleteGroupTeacherRelate(@Param("groupId") Integer groupId);

    List<SurveyAnswerExcelDto> selectSurveyAnswer(@Param("groupId") Integer groupId, @Param("groupType") Integer groupType);
}
