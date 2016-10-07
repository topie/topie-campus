package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_timetable")
public class StuTimeTable {
    private Integer id;

    /**
     * 学年
     */
    @Column(name = "study_year")
    private String studyYear;

    /**
     * 学期
     */
    @Column(name = "study_year_num")
    private String studyYearNum;

    /**
     * 学号
     */
    @Column(name = "stu_id")
    private String stuId;

    /**
     * 选课课号
     */
    @Column(name = "select_course_num")
    private String selectCourseNum;

    /**
     * 星期几
     */
    private String week;

    /**
     * 第几节
     */
    private String section;

    @Column(name = "section_length")
    private String sectionLength;

    @Column(name = "start_week")
    private String startWeek;

    /**
     * 结束周
     */
    @Column(name = "end_week")
    private String endWeek;

    /**
     * 课表内容
     */
    private String content;

    @Column(name = "is_selected")
    private String isSelected;

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
     * 获取学号
     *
     * @return stu_id - 学号
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * 设置学号
     *
     * @param stuId 学号
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * 获取选课课号
     *
     * @return select_course_num - 选课课号
     */
    public String getSelectCourseNum() {
        return selectCourseNum;
    }

    /**
     * 设置选课课号
     *
     * @param selectCourseNum 选课课号
     */
    public void setSelectCourseNum(String selectCourseNum) {
        this.selectCourseNum = selectCourseNum;
    }

    /**
     * 获取星期几
     *
     * @return week - 星期几
     */
    public String getWeek() {
        return week;
    }

    /**
     * 设置星期几
     *
     * @param week 星期几
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * 获取第几节
     *
     * @return section - 第几节
     */
    public String getSection() {
        return section;
    }

    /**
     * 设置第几节
     *
     * @param section 第几节
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return section_length
     */
    public String getSectionLength() {
        return sectionLength;
    }

    /**
     * @param sectionLength
     */
    public void setSectionLength(String sectionLength) {
        this.sectionLength = sectionLength;
    }

    /**
     * @return start_week
     */
    public String getStartWeek() {
        return startWeek;
    }

    /**
     * @param startWeek
     */
    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    /**
     * 获取结束周
     *
     * @return end_week - 结束周
     */
    public String getEndWeek() {
        return endWeek;
    }

    /**
     * 设置结束周
     *
     * @param endWeek 结束周
     */
    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    /**
     * 获取课表内容
     *
     * @return content - 课表内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置课表内容
     *
     * @param content 课表内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return is_selected
     */
    public String getIsSelected() {
        return isSelected;
    }

    /**
     * @param isSelected
     */
    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}