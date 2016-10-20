package com.topie.campus.core.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.topie.campus.core.model.StuTimeTable;
import com.topie.campus.tools.excel.ExcelCell;


public class StuTimeTableExcelDto implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5149683284709434475L;

	@ExcelCell(index=0)
	private String studyYear;

    /**
     * ѧ��
     */
	@ExcelCell(index=1)
    private String studyYearNum;

    /**
     * ѧ��
     */
	@ExcelCell(index=2)
    private String stuId;

    /**
     * ѡ�οκ�
     */
	@ExcelCell(index=3)
    private String selectCourseNum;

    /**
     * ���ڼ�
     */
	@ExcelCell(index=4)
    private String week;

    /**
     * �ڼ���
     */
	@ExcelCell(index=5)
    private String section;

	@ExcelCell(index=6)
    private String sectionLength;

	@ExcelCell(index=8)
    private String startWeek;

    /**
     * ������
     */
	
	@ExcelCell(index=9)
    private String endWeek;

    /**
     * �α�����
     */
	
	@ExcelCell(index=10)
    private String content;

	@ExcelCell(index=12)
    private String isSelected;


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

	public StuTimeTable buildStuTimeTable(StuTimeTableExcelDto dto) {
		// TODO Auto-generated method stub
		StuTimeTable stuTimeTable = new StuTimeTable();
		BeanUtils.copyProperties(dto, stuTimeTable);
		stuTimeTable.setEndWeek(Integer.valueOf(dto.getEndWeek()));
		stuTimeTable.setStartWeek(Integer.valueOf(dto.getStartWeek()));
		stuTimeTable.setSectionLength(Integer.valueOf(dto.getSectionLength()));
		stuTimeTable.setSection(Integer.valueOf(dto.getSection()));
		stuTimeTable.setWeek(Integer.valueOf(dto.getWeek()));
		return stuTimeTable;
	}
}
