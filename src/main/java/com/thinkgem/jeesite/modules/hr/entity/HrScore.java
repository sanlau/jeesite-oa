package com.thinkgem.jeesite.modules.hr.entity;

import com.alibaba.druid.util.StringUtils;
import com.thinkgem.jeesite.common.utils.IdGen;

public class HrScore {

	private String name;		// name
	private String standardScore;		// standard_score
	private String lowScope;		// low_scope
	private String highScope;		// high_scope
	private String description;		// description
 
	
	private String id;
	
	public String getId() {
		if(StringUtils.isEmpty(id))
		{
		setId(IdGen.uuid());
		}
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
	public String getStandardScore() {
		return standardScore;
	}
	public void setStandardScore(String standardScore) {
		this.standardScore = standardScore;
	}
	public String getLowScope() {
		return lowScope;
	}
	public void setLowScope(String lowScope) {
		this.lowScope = lowScope;
	}
	public String getHighScope() {
		return highScope;
	}
	public void setHighScope(String highScope) {
		this.highScope = highScope;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
	
}
