package com.topie.campus.core.model;

import java.io.Serializable;

/**
 * Created by chenguojun on 2016/10/19.
 */
public class GroupStudentStat implements Serializable {

    private static final long serialVersionUID = 2615836599897011983L;

    private Integer studentId;

    private Integer questionId;

    private Float record;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
