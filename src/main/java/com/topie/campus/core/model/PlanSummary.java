package com.topie.campus.core.model;

import java.util.Date;

import javax.persistence.*;

import com.topie.campus.tools.excel.ExcelCell;

@Table(name = "t_plan_summary")
public class PlanSummary {
	
	 @Id
    private Integer id;

    /**
     * 内容
     */
	 @ExcelCell(index=4)
    private String content;

    /**
     * 类型0,工作计划，1工作总结
     */
	@ExcelCell(index=5)
    private Integer type;

    /**
     * 学年
     */
    @ExcelCell(index=0)
    @Column(name = "study_year")
    private String studyYear;

    /**
     * 学期
     */
    @ExcelCell(index=1)
    @Column(name = "study_year_num")
    private String studyYearNum;

    /**
     * 教师id
     */
    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 职工号
     */
    @ExcelCell(index=2)
    @Column(name = "teacher_no")
    private String teacherNo;

    @ExcelCell(index=6)
    @Column(name = "create_time")
    private Date createTime;
    
    @Transient
    @ExcelCell(index=3)
    private String teacherName;
    
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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取类型0,工作计划，1工作总结
     *
     * @return type - 类型0,工作计划，1工作总结
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型0,工作计划，1工作总结
     *
     * @param type 类型0,工作计划，1工作总结
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取学年
     *
     * @return study_year - 学年
     */
    public String getStudyYear() {
        return studyYear;
    }

    /**
     * 设置学年
     *
     * @param studyYear 学年
     */
    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * 获取学期
     *
     * @return study_year_num - 学期
     */
    public String getStudyYearNum() {
        return studyYearNum;
    }

    /**
     * 设置学期
     *
     * @param studyYearNum 学期
     */
    public void setStudyYearNum(String studyYearNum) {
        this.studyYearNum = studyYearNum;
    }

    /**
     * 获取教师id
     *
     * @return teacher_id - 教师id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置教师id
     *
     * @param teacherId 教师id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取职工号
     *
     * @return teacher_no - 职工号
     */
    public String getTeacherNo() {
        return teacherNo;
    }

    /**
     * 设置职工号
     *
     * @param teacherNo 职工号
     */
    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
    
}