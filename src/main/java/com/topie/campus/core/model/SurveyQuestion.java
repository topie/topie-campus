package com.topie.campus.core.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_survey_question")
public class SurveyQuestion {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(name = "question_type")
    private Integer questionType;

    /**
     * 调查问卷问题内容
     */
    @Column(name = "question_content")
    private String questionContent;

    /**
     * 创建时间
     */
    @Column(name = "c_time")
    private Date cTime;

    /**
     * 修改时间
     */
    @Column(name = "u_time")
    private Date uTime;

    @Transient
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return question_id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取调查问卷问题内容
     *
     * @return question_content - 调查问卷问题内容
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * 设置调查问卷问题内容
     *
     * @param questionContent 调查问卷问题内容
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * 获取创建时间
     *
     * @return c_time - 创建时间
     */
    public Date getcTime() {
        return cTime;
    }

    /**
     * 设置创建时间
     *
     * @param cTime 创建时间
     */
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    /**
     * 获取修改时间
     *
     * @return u_time - 修改时间
     */
    public Date getuTime() {
        return uTime;
    }

    /**
     * 设置修改时间
     *
     * @param uTime 修改时间
     */
    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }
}
