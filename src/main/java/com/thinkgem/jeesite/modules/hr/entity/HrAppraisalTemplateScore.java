/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考核模板评分管理Entity
 * @author lbx
 * @version 2018-11-19
 */
public class HrAppraisalTemplateScore extends DataEntity<HrAppraisalTemplateScore> {
	
	private static final long serialVersionUID = 1L;
	private String appraisalTemplateId;		// appraisal_template_id
	private HrScore[] hrscore; 

	private String name;		// name
	private String standardScore;		// standard_score
	private String lowScope;		// low_scope
	private String highScope;		// high_scope
	private String description;		// description
	private String[] ids;
	
	

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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

	public HrScore[] getHrscore() {
		return hrscore;
	}

	public void setHrscore(HrScore[] hrscore) {
		this.hrscore = hrscore;
	}

	public HrAppraisalTemplateScore() {
		super();
	}

	public HrAppraisalTemplateScore(String id){
		super(id);
	}

	@Length(min=1, max=64, message="appraisal_template_id长度必须介于 1 和 64 之间")
	public String getAppraisalTemplateId() {
		return appraisalTemplateId;
	}

	public void setAppraisalTemplateId(String appraisalTemplateId) {
		this.appraisalTemplateId = appraisalTemplateId;
	}
	

	
	
}