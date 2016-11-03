package com.topie.campus.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_survey_group_question_answer")
public class SurveyAnswer {

    /**
     * 问卷组id
     */
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Id
    @Column(name = "group_type")
    private Integer groupType;

    /**
     * 问题id
     */
    @Id
    @Column(name = "question_id")
    private Integer questionId;

    /**
     * 老师id
     */
    @Id
    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 学生id
     */
    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "question_type")
    private Integer questionType;

    private Integer record;

    private String content;

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取问卷组id
     *
     * @return group_id - 问卷组id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置问卷组id
     *
     * @param groupId 问卷组id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取问题id
     *
     * @return question_id - 问题id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置问题id
     *
     * @param questionId 问题id
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取老师id
     *
     * @return teacher_id - 老师id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置老师id
     *
     * @param teacherId 老师id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取学生id
     *
     * @return student_id - 学生id
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置学生id
     *
     * @param studentId 学生id
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取分数
     *
     * @return record - 分数
     */
    public Integer getRecord() {
        return record;
    }

    /**
     * 设置分数
     *
     * @param record 分数
     */
    public void setRecord(Integer record) {
        this.record = record;
    }
}
