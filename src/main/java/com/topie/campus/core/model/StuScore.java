package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_score")
public class StuScore {
    @Id
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
     * 选课课号
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
    private String scorePoint;

    private String comment;

    /**
     * 
重修标记（0或空，
null为不重修）为首次修读。1为跟班重修3自学重修
     */
    @Column(name = "re_study_flag")
    private String reStudyFlag;

    /**
     * 平时成绩
     */
    @Column(name = "common_score")
    private String commonScore;

    /**
     * 卷面成绩
     */
    @Column(name = "page_score")
    private String pageScore;

    @Column(name = "second_score")
    private String secondScore;

    /**
     * 重修成绩
     */
    @Column(name = "restudy_score")
    private String restudyScore;

    @Column(name = "cource_type")
    private String courceType;

    @Column(name = "cource_code")
    private String courceCode;

    /**
     * 辅修标记
0正常，1辅修
     */
    @Column(name = "minor_flag")
    private String minorFlag;

    /**
     * 课程归属
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
     * 获取选课课号
     *
     * @return course_num - 选课课号
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * 设置选课课号
     *
     * @param courseNum 选课课号
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

    /**
     * @return score_point
     */
    public String getScorePoint() {
        return scorePoint;
    }

    /**
     * @param scorePoint
     */
    public void setScorePoint(String scorePoint) {
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
     * 获取
重修标记（0或空，
null为不重修）为首次修读。1为跟班重修3自学重修
     *
     * @return re_study_flag - 
重修标记（0或空，
null为不重修）为首次修读。1为跟班重修3自学重修
     */
    public String getReStudyFlag() {
        return reStudyFlag;
    }

    /**
     * 设置
重修标记（0或空，
null为不重修）为首次修读。1为跟班重修3自学重修
     *
     * @param reStudyFlag 
重修标记（0或空，
null为不重修）为首次修读。1为跟班重修3自学重修
     */
    public void setReStudyFlag(String reStudyFlag) {
        this.reStudyFlag = reStudyFlag;
    }

    /**
     * 获取平时成绩
     *
     * @return common_score - 平时成绩
     */
    public String getCommonScore() {
        return commonScore;
    }

    /**
     * 设置平时成绩
     *
     * @param commonScore 平时成绩
     */
    public void setCommonScore(String commonScore) {
        this.commonScore = commonScore;
    }

    /**
     * 获取卷面成绩
     *
     * @return page_score - 卷面成绩
     */
    public String getPageScore() {
        return pageScore;
    }

    /**
     * 设置卷面成绩
     *
     * @param pageScore 卷面成绩
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
     * 获取重修成绩
     *
     * @return restudy_score - 重修成绩
     */
    public String getRestudyScore() {
        return restudyScore;
    }

    /**
     * 设置重修成绩
     *
     * @param restudyScore 重修成绩
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
     * 获取辅修标记
0正常，1辅修
     *
     * @return minor_flag - 辅修标记
0正常，1辅修
     */
    public String getMinorFlag() {
        return minorFlag;
    }

    /**
     * 设置辅修标记
0正常，1辅修
     *
     * @param minorFlag 辅修标记
0正常，1辅修
     */
    public void setMinorFlag(String minorFlag) {
        this.minorFlag = minorFlag;
    }

    /**
     * 获取课程归属
     *
     * @return cource_attr - 课程归属
     */
    public String getCourceAttr() {
        return courceAttr;
    }

    /**
     * 设置课程归属
     *
     * @param courceAttr 课程归属
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