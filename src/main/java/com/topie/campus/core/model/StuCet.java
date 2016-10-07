package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_cet")
public class StuCet {
    @Id
    private Integer id;

    @Column(name = "study_year")
    private String studyYear;

    @Column(name = "study_year_num")
    private String studyYearNum;

    @Column(name = "stu_id")
    private String stuId;

    @Column(name = "cet_name")
    private String cetName;

    @Column(name = "cet_time")
    private String cetTime;

    @Column(name = "cet_score")
    private String cetScore;

    private String comment;

    @Column(name = "listen_score")
    private String listenScore;

    @Column(name = "write_score")
    private String writeScore;

    @Column(name = "read_score")
    private String readScore;

    @Column(name = "compre_score")
    private String compreScore;

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
     * @return cet_name
     */
    public String getCetName() {
        return cetName;
    }

    /**
     * @param cetName
     */
    public void setCetName(String cetName) {
        this.cetName = cetName;
    }

    /**
     * @return cet_time
     */
    public String getCetTime() {
        return cetTime;
    }

    /**
     * @param cetTime
     */
    public void setCetTime(String cetTime) {
        this.cetTime = cetTime;
    }

    /**
     * @return cet_score
     */
    public String getCetScore() {
        return cetScore;
    }

    /**
     * @param cetScore
     */
    public void setCetScore(String cetScore) {
        this.cetScore = cetScore;
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
     * @return listen_score
     */
    public String getListenScore() {
        return listenScore;
    }

    /**
     * @param listenScore
     */
    public void setListenScore(String listenScore) {
        this.listenScore = listenScore;
    }

    /**
     * @return write_score
     */
    public String getWriteScore() {
        return writeScore;
    }

    /**
     * @param writeScore
     */
    public void setWriteScore(String writeScore) {
        this.writeScore = writeScore;
    }

    /**
     * @return read_score
     */
    public String getReadScore() {
        return readScore;
    }

    /**
     * @param readScore
     */
    public void setReadScore(String readScore) {
        this.readScore = readScore;
    }

    /**
     * @return compre_score
     */
    public String getCompreScore() {
        return compreScore;
    }

    /**
     * @param compreScore
     */
    public void setCompreScore(String compreScore) {
        this.compreScore = compreScore;
    }
}