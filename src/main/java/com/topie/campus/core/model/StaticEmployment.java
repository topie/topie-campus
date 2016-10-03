package com.topie.campus.core.model;

import java.io.Serializable;

public class StaticEmployment implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7920058244540834111L;
	
	private String major;
	
	private String tutor;
	
	private String classNum;
	
	private Double signRate;
	
	private Double employmentRate;

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Double getSignRate() {
		return signRate;
	}

	public void setSignRate(Double signRate) {
		this.signRate = signRate;
	}

	public Double getEmploymentRate() {
		return employmentRate;
	}

	public void setEmploymentRate(Double employmentRate) {
		this.employmentRate = employmentRate;
	}
	
}
