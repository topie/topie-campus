package com.topie.campus.core.model;

import java.io.Serializable;

/**
 * Created by chenguojun on 2016/10/19.
 */
public class GroupTeacherStat implements Serializable {

    private static final long serialVersionUID = 245207398468014953L;

    private Integer teacherId;

    private Integer questionId;

    private Float record;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Float getRecord() {
        return record;
    }

    public void setRecord(Float record) {
        this.record = record;
    }
}
