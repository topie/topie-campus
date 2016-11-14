package com.topie.campus.core.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.topie.campus.tools.excel.ExcelCell;

public class RecordExcel {

	private Integer id;

	 @ExcelCell(index=4)
    private String content;

    @ExcelCell(index=0)
    private String studyYear;

    @ExcelCell(index=1)
    private String studyYearNum;

    @ExcelCell(index=7)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer teacherId;
   
    @ExcelCell(index=2)
    private String teacherNo;
    
    private Integer studentId;
    
    @ExcelCell(index=5)
    private String studentNo;
    
    @ExcelCell(index=3)
    private String teacherName;
    
    @ExcelCell(index=6)
    private String studentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(String studyYear) {
		this.studyYear = studyYear;
	}

	public String getStudyYearNum() {
		return studyYearNum;
	}

	public void setStudyYearNum(String studyYearNum) {
		this.studyYearNum = studyYearNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getTeacheName() {
		return teacherName;
	}

	public void setTeacheName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
    
}
