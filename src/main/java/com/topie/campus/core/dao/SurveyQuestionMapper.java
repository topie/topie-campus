package com.topie.campus.core.dao;

import com.topie.campus.core.model.SurveyQuestion;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SurveyQuestionMapper extends Mapper<SurveyQuestion> {

    List<SurveyQuestion> selectByPage(@Param("surveyQuestion") SurveyQuestion surveyQuestion,
            @Param("pageOffset") Integer pageOffset, @Param("pageSize") Integer pageSize);

    Long count(@Param("surveyQuestion") SurveyQuestion surveyQuestion);

    List<SurveyQuestion> selectByGroupIdByPage(@Param("surveyQuestion") SurveyQuestion surveyQuestion,
            @Param("groupId") Integer groupId, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countByGroupId(@Param("surveyQuestion") SurveyQuestion surveyQuestion, @Param("groupId") Integer groupId);
}
