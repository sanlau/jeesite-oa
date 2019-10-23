/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.act.entity.Act;

/**
 * 报销记录表Entity
 * @author lbx
 * @version 2018-11-15
 */
public class Expenseclaimrecords extends ActEntity<Expenseclaimrecords> {
	
	private static final long serialVersionUID = 1L;
	private String projectName;		// project_name
	private Date date;		// date
	private String total;		// total
	
	public Expenseclaimrecords() {
		super();
	}

	public Expenseclaimrecords(String id){
		super(id);
	}

	@Length(min=1, max=30, message="project_name长度必须介于 1 和 30 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="date不能为空")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	 
}