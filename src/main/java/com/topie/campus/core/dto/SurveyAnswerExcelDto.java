package com.topie.campus.core.dto;

import com.topie.campus.tools.excel.ExcelCell;

import java.io.Serializable;

/**
 * Created by chenguojun on 2016/10/20.
 */
public class SurveyAnswerExcelDto implements Serializable {

    private static final long serialVersionUID = 462013729082576953L;

    @ExcelCell(index = 0)
    private String groupId;

    @ExcelCell(index = 1)
    private String teacherName;

    @ExcelCell(index = 2)
    private String employeeNo;

    @ExcelCell(index = 3)
    private String studentName;

    @ExcelCell(index = 4)
    private String studentNo;

    @ExcelCell(index = 5)
    private String questionContent;

    @ExcelCell(index = 6)
    private Integer record;

    @ExcelCell(index = 7)
    private String comment;

    private String getRecordStr() {
        return record == null ? "-" : String.valueOf(record);
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
