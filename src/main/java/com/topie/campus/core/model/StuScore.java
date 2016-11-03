package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_score")
public class StuScore {
    @Id
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
     * ѡ�οκ�
     */
    @Column(name = "course_num")
    private String courseNum;

    @Column(name = "stu_id")
    private String stuId;

    private String name;

    @Column(name = "cource_name")
    private String courceName;

    private String credit;

    private String score;

    @Column(name = "score_point")
    private Double scorePoint;

    private String comment;

    /**
     * 
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     */
    @Column(name = "re_study_flag")
    private String reStudyFlag;

    /**
     * ƽʱ�ɼ�
     */
    @Column(name = "common_score")
    private String commonScore;

    /**
     * ����ɼ�
     */
    @Column(name = "page_score")
    private String pageScore;

    @Column(name = "second_score")
    private String secondScore;

    /**
     * ���޳ɼ�
     */
    @Column(name = "restudy_score")
    private String restudyScore;

    @Column(name = "cource_type")
    private String courceType;

    @Column(name = "cource_code")
    private String courceCode;

    /**
     * ���ޱ��
0������1����
     */
    @Column(name = "minor_flag")
    private String minorFlag;

    /**
     * �γ̹���
     */
    @Column(name = "cource_attr")
    private String courceAttr;

    private String invalid;

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
     * ��ȡѡ�οκ�
     *
     * @return course_num - ѡ�οκ�
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * ����ѡ�οκ�
     *
     * @param courseNum ѡ�οκ�
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * @return stu_id
     */
    public String getStuId() {
        return stuId;
    }

    /**
     * @param stuId
     */
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return cource_name
     */
    public String getCourceName() {
        return courceName;
    }

    /**
     * @param courceName
     */
    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    /**
     * @return credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     * @param credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    /**
     * @return score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(String score) {
        this.score = score;
    }

    public Double getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(Double scorePoint) {
		this.scorePoint = scorePoint;
	}

	/**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * ��ȡ
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     *
     * @return re_study_flag - 
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     */
    public String getReStudyFlag() {
        return reStudyFlag;
    }

    /**
     * ����
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     *
     * @param reStudyFlag 
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     */
    public void setReStudyFlag(String reStudyFlag) {
        this.reStudyFlag = reStudyFlag;
    }

    /**
     * ��ȡƽʱ�ɼ�
     *
     * @return common_score - ƽʱ�ɼ�
     */
    public String getCommonScore() {
        return commonScore;
    }

    /**
     * ����ƽʱ�ɼ�
     *
     * @param commonScore ƽʱ�ɼ�
     */
    public void setCommonScore(String commonScore) {
        this.commonScore = commonScore;
    }

    /**
     * ��ȡ����ɼ�
     *
     * @return page_score - ����ɼ�
     */
    public String getPageScore() {
        return pageScore;
    }

    /**
     * ���þ���ɼ�
     *
     * @param pageScore ����ɼ�
     */
    public void setPageScore(String pageScore) {
        this.pageScore = pageScore;
    }

    /**
     * @return second_score
     */
    public String getSecondScore() {
        return secondScore;
    }

    /**
     * @param secondScore
     */
    public void setSecondScore(String secondScore) {
        this.secondScore = secondScore;
    }

    /**
     * ��ȡ���޳ɼ�
     *
     * @return restudy_score - ���޳ɼ�
     */
    public String getRestudyScore() {
        return restudyScore;
    }

    /**
     * �������޳ɼ�
     *
     * @param restudyScore ���޳ɼ�
     */
    public void setRestudyScore(String restudyScore) {
        this.restudyScore = restudyScore;
    }

    /**
     * @return cource_type
     */
    public String getCourceType() {
        return courceType;
    }

    /**
     * @param courceType
     */
    public void setCourceType(String courceType) {
        this.courceType = courceType;
    }

    /**
     * @return cource_code
     */
    public String getCourceCode() {
        return courceCode;
    }

    /**
     * @param courceCode
     */
    public void setCourceCode(String courceCode) {
        this.courceCode = courceCode;
    }

    /**
     * ��ȡ���ޱ��
0������1����
     *
     * @return minor_flag - ���ޱ��
0������1����
     */
    public String getMinorFlag() {
        return minorFlag;
    }

    /**
     * ���ø��ޱ��
0������1����
     *
     * @param minorFlag ���ޱ��
0������1����
     */
    public void setMinorFlag(String minorFlag) {
        this.minorFlag = minorFlag;
    }

    /**
     * ��ȡ�γ̹���
     *
     * @return cource_attr - �γ̹���
     */
    public String getCourceAttr() {
        return courceAttr;
    }

    /**
     * ���ÿγ̹���
     *
     * @param courceAttr �γ̹���
     */
    public void setCourceAttr(String courceAttr) {
        this.courceAttr = courceAttr;
    }

    /**
     * @return invalid
     */
    public String getInvalid() {
        return invalid;
    }

    /**
     * @param invalid
     */
    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}