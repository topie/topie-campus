package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_timetable")
public class StuTimeTable {
    private Integer id;

    /**
     * ѧ��
     */
    @Column(name = "study_year")
    private String studyYear;

    /**
     * ѧ��
     */
    @Column(name = "study_year_num")
    private String studyYearNum;

    /**
     * ѧ��
     */
    @Column(name = "stu_id")
    private String stuId;

    /**
     * ѡ�οκ�
     */
    @Column(name = "select_course_num")
    private String selectCourseNum;

    /**
     * ���ڼ�
     */
    private String week;

    /**
     * �ڼ���
     */
    private String section;

    @Column(name = "section_length")
    private String sectionLength;

    @Column(name = "start_week")
    private String startWeek;

    /**
     * ������
     */
    @Column(name = "end_week")
    private String endWeek;

    /**
     * �α�����
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
     * ��ȡѧ��
     *
     * @return study_year - ѧ��
     */
    public String getStudyYear() {
        return studyYear;
    }

    /**
     * ����ѧ��
     *
     * @param studyYear ѧ��
     */
    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * ��ȡѧ��
     *
     * @return study_year_num - ѧ��
     */
    public String getStudyYearNum() {
        return studyYearNum;
    }

    /**
     * ����ѧ��
     *
     * @param studyYearNum ѧ��
     */
    public void setStudyYearNum(String studyYearNum) {
        this.studyYearNum = studyYearNum;
    }

    /**
     * ��ȡѧ��
     *
     * @return stu_id - ѧ��
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * ����ѧ��
     *
     * @param stuId ѧ��
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * ��ȡѡ�οκ�
     *
     * @return select_course_num - ѡ�οκ�
     */
    public String getSelectCourseNum() {
        return selectCourseNum;
    }

    /**
     * ����ѡ�οκ�
     *
     * @param selectCourseNum ѡ�οκ�
     */
    public void setSelectCourseNum(String selectCourseNum) {
        this.selectCourseNum = selectCourseNum;
    }

    /**
     * ��ȡ���ڼ�
     *
     * @return week - ���ڼ�
     */
    public String getWeek() {
        return week;
    }

    /**
     * �������ڼ�
     *
     * @param week ���ڼ�
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * ��ȡ�ڼ���
     *
     * @return section - �ڼ���
     */
    public String getSection() {
        return section;
    }

    /**
     * ���õڼ���
     *
     * @param section �ڼ���
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
     * ��ȡ������
     *
     * @return end_week - ������
     */
    public String getEndWeek() {
        return endWeek;
    }

    /**
     * ���ý�����
     *
     * @param endWeek ������
     */
    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    /**
     * ��ȡ�α�����
     *
     * @return content - �α�����
     */
    public String getContent() {
        return content;
    }

    /**
     * ���ÿα�����
     *
     * @param content �α�����
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