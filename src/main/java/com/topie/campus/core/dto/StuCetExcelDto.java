package com.topie.campus.core.dto;

import java.io.Serializable;





import org.springframework.beans.BeanUtils;

import com.topie.campus.core.model.StuCet;
import com.topie.campus.tools.excel.ExcelCell;

public class StuCetExcelDto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -6132161387781568368L;


	    @ExcelCell(index=0)
	    private String studyYear;

	    @ExcelCell(index=1)
	    private String studyYearNum;

	    @ExcelCell(index=2)
	    private String stuId;

	    @ExcelCell(index=4)
	    private String cetName;

	    @ExcelCell(index=5)
	    private String cetTime;

	    @ExcelCell(index=6)
	    private String cetScore;

	    @ExcelCell(index=7)
	    private String comment;

	    @ExcelCell(index=11)
	    private String listenScore;

	    @ExcelCell(index=12)
	    private String readScore;

	    @ExcelCell(index=13)
	    private String writeScore;

	    @ExcelCell(index=14)
	    private String compreScore;

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

		public StuCet buildStuCet(StuCetExcelDto dto) {
			StuCet cet = new StuCet();
			BeanUtils.copyProperties(dto, cet);
			return cet;
		}
}
