package com.topie.campus.core.model;

import java.io.Serializable;

public class StaticEmployment implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7920058244540834111L;
	
	private String college;
	
	private String faculty;
	
	private String major;
	
	private String tutor;
	
	private String classNum;
	
	private double signRate;
	
	private double tableRate;
	
	private double employmentRate;

	private int man;
	
	private int woman;
	
	private double poorRate;
	
	private String teacherNo;
	
	
	public int getMan() {
		return man;
	}

	public void setMan(int man) {
		this.man = man;
	}

	public int getWoman() {
		return woman;
	}

	public void setWoman(int woman) {
		this.woman = woman;
	}

	public double getPoorRate() {
		return poorRate;
	}

	public void setPoorRate(double poorRate) {
		this.poorRate = poorRate;
	}


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

	public double getSignRate() {
		return signRate;
	}

	public void setSignRate(double signRate) {
		this.signRate = signRate;
	}

	public double getEmploymentRate() {
		return employmentRate;
	}

	public void setEmploymentRate(double employmentRate) {
		this.employmentRate = employmentRate;
	}

	public double getTableRate() {
		return tableRate;
	}

	public void setTableRate(double tableRate) {
		this.tableRate = tableRate;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

}
