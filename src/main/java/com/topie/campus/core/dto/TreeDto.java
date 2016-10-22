package com.topie.campus.core.dto;

import com.topie.campus.core.model.College;
import com.topie.campus.core.model.Faculty;

public class TreeDto {

	
	private String id;
	
	private String name;
	
	private String pId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
	
	public TreeDto(College c)
	{
		this.id = c.getXydm();
		this.name = c.getXymc();
		this.pId = "0";
	}
	
	public TreeDto(Faculty c)
	{
		this.id = c.getXdm();
		this.name = c.getXmc();
		this.pId = c.getSsxydm();
	}
}
