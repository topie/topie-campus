package com.topie.campus.core.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.topie.campus.core.model.StuScore;
import com.topie.campus.tools.excel.ExcelCell;

public class StuScoreExcelDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7030846884499460499L;

    /**
     * ѧ��
     */
    @ExcelCell(index=0)
    private String studyYear;

    /**
     * ѧ��
     */
    @ExcelCell(index=1)
    private String studyYearNum;

    /**
     * ѡ�οκ�
     */
    @ExcelCell(index=2)
    private String courseNum;

    @ExcelCell(index=3)
    private String stuId;

    @ExcelCell(index=4)
    private String name;

    @ExcelCell(index=5)
    private String courceName;

    @ExcelCell(index=6)
    private String credit;

    @ExcelCell(index=7)
    private String score;

    @ExcelCell(index=9)
    private String scorePoint;

    
    @ExcelCell(index=10)
    private String comment;

    /**
     * 
���ޱ�ǣ�0��գ�
nullΪ�����ޣ�Ϊ�״��޶���1Ϊ��������3��ѧ����
     */
    @ExcelCell(index=13)
    private String reStudyFlag;

    /**
     * ƽʱ�ɼ�
     */
    @ExcelCell(index=14)
    private String commonScore;

    /**
     * ����ɼ�
     */
    @ExcelCell(index=15)
    private String pageScore;

    @ExcelCell(index=17)
    private String secondScore;

    /**
     * ���޳ɼ�
     */
    @ExcelCell(index=18)
    private String restudyScore;

    @ExcelCell(index=20)
    private String courceType;

    @ExcelCell(index=24)
    private String courceCode;

    /**
     * ���ޱ��
0������1���� 
  */
    @ExcelCell(index=27)
    private String minorFlag;

    /**
     * �γ̹���
     */
    @ExcelCell(index=29)
    private String courceAttr;

    @ExcelCell(index=51)
    private String invalid;

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
    
    public StuScore buildStuScore(StuScoreExcelDto dto)
    {
    	StuScore score = new StuScore();
    	BeanUtils.copyProperties(dto, score);
    	if(StringUtils.isNotEmpty(dto.getScorePoint()))
    	{
    		score.setScorePoint(Double.valueOf(dto.getScorePoint()));
    	}
		return score;
    }
}
