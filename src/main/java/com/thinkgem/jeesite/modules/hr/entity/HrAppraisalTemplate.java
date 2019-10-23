/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 考核模板管理Entity
 * @author lbx
 * @version 2018-11-16
 */
public class HrAppraisalTemplate extends DataEntity<HrAppraisalTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String appraisalTemplateId;		// appraisal_template_id
	private String name;		// name
	private String categoryId;		// category_id FK:hr_appraisal_template_category.category_id
	private String creatorUserId;		// creator_user_id
	private Date createTime;		// create_time
	private String description;		// description
	
	private String cateId;//主表传值
	private String cateName;
	private String sysRoleId;//sys_role
	private String sysRoleName;


	private HrScore[] hrscore;
	
	
	public HrScore[] getHrscore() {
		return hrscore;
	}

	public void setHrscore(HrScore[] hrscore) {
		this.hrscore = hrscore;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateId() {
		return cateId;
	}

	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getAppraisalTemplateId());
    }

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}


	public HrAppraisalTemplate() {
		super();
	}
	

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		appraisalTemplateId=id;
	}


	public HrAppraisalTemplate(String id){
		super(id);
	}

//	@Length(min=1, max=64, message="appraisal_template_id长度必须介于 1 和 64 之间")
	public String getAppraisalTemplateId() {
		return appraisalTemplateId;
	}

	public void setAppraisalTemplateId(String appraisalTemplateId) {
		this.appraisalTemplateId = appraisalTemplateId;
	}
	
	@Length(min=1, max=1000, message="name长度必须介于 1 和 1000 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="category_id长度必须介于 1 和 64 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=1, max=64, message="creator_user_id长度必须介于 1 和 64 之间")
	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=1, max=1000, message="description长度必须介于 1 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}