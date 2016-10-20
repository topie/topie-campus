package com.topie.campus.core.dao;

import com.topie.campus.core.model.SurveyAnswer;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SurveyAnswerMapper extends Mapper<SurveyAnswer> {

    void insertOrUpdate(@Param("answer") SurveyAnswer surveyAnswer);
}
