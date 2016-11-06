package com.topie.campus.core.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "t_student_record")
public class StudentRecord {
	
	 @Id
    private Integer id;

    private String content;

    @Column(name = "study_year")
    private String studyYear;

    @Column(name = "study_year_num")
    private String studyYearNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "teacher_no")
    private String teacherNo;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return study_year
     */
    public String getStudyYear() {
        return studyYear;
    }

    /**
     * @param studyYear
     */
    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * @return study_year_num
     */
    public String getStudyYearNum() {
        return studyYearNum;
    }

    /**
     * @param studyYearNum
     */
    public void setStudyYearNum(String studyYearNum) {
        this.studyYearNum = studyYearNum;
    }

    /**
     * @return create_time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacher_no
     */
    public String getTeacherNo() {
        return teacherNo;
    }

    /**
     * @param teacherNo
     */
    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }
}