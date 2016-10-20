package com.topie.campus.core.dao;

import com.topie.campus.core.model.GroupStat;
import com.topie.campus.core.model.SurveyGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

    Boolean selectComplete(@Param("groupId") Integer groupId, @Param("studentId") Integer studentId);

    List<GroupStat> selectStatByGroupId(@Param("groupId") Integer groupId);

}
