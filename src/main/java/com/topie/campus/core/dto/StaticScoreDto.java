package com.topie.campus.core.dto;

import java.io.Serializable;
import java.util.List;

public class StaticScoreDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754336242728445015L;

	
	private String avgScore;
	
	private String avgCredit;
	
	private List<ScoreCourceType> scoreCourseType;

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public String getAvgCredit() {
		return avgCredit;
	}

	public void setAvgCredit(String avgCredit) {
		this.avgCredit = avgCredit;
	}

	public List<ScoreCourceType> getScoreCourseType() {
		return scoreCourseType;
	}

	public void setScoreCourseType(List<ScoreCourceType> scoreCourseType) {
		this.scoreCourseType = scoreCourseType;
	}
	
}
