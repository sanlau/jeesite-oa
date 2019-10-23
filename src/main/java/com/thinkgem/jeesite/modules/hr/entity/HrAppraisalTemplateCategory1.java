/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 考核模板类型管理Entity
 * @author lbx
 * @version 2018-11-16
 */
public class HrAppraisalTemplateCategory1 extends DataEntity<HrAppraisalTemplateCategory1> {
	
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		categoryId=id;
	}
	
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getCategoryId());
    }

	private static final long serialVersionUID = 1L;
	private String categoryId;		// category_id
	private String name;		// name
	private String description;		// description
	
	public HrAppraisalTemplateCategory1() {
		super();
	}

	public HrAppraisalTemplateCategory1(String id){
		super(id);
	}

	//@Length(min=1, max=64, message="category_id长度必须介于 1 和 64 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=1, max=500, message="name长度必须介于 1 和 500 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1000, message="description长度必须介于 1 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}