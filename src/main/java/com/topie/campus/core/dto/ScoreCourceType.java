package com.topie.campus.core.dto;

import java.io.Serializable;

public class ScoreCourceType  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7112220584014453707L;

	private String totalCredit;
	
	private String courceType;

	public String getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getCourceType() {
		return courceType;
	}

	public void setCourceType(String courceType) {
		this.courceType = courceType;
	}
	
}
