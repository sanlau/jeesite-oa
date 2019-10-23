/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考核平均分Entity
 * @author lbx
 * @version 2019-03-19
 */
public class HrAppraisalAvgPoint extends DataEntity<HrAppraisalAvgPoint> {
	
	private static final long serialVersionUID = 1L;
	private String examineeUserId;		// examinee_user_id
	private String appraisalManagerId;		// appraisal_manager_id
	private String avgPoint;		// avg_point
	private String examineeUserName;
	private String appraisalManagerName;
	public HrAppraisalAvgPoint() {
		super();
	}

	public HrAppraisalAvgPoint(String id){
		super(id);
	}

	@Length(min=0, max=5000, message="examinee_user_id长度必须介于 0 和 5000 之间")
	public String getExamineeUserId() {
		return examineeUserId;
	}

	public void setExamineeUserId(String examineeUserId) {
		this.examineeUserId = examineeUserId;
	}
	
	@Length(min=0, max=5000, message="appraisal_manager_id长度必须介于 0 和 5000 之间")
	public String getAppraisalManagerId() {
		return appraisalManagerId;
	}

	public void setAppraisalManagerId(String appraisalManagerId) {
		this.appraisalManagerId = appraisalManagerId;
	}
	
	public String getAvgPoint() {
		return avgPoint;
	}

	public void setAvgPoint(String avgPoint) {
		this.avgPoint = avgPoint;
	}

	public String getExamineeUserName() {
		return examineeUserName;
	}

	public void setExamineeUserName(String examineeUserName) {
		this.examineeUserName = examineeUserName;
	}

	public String getAppraisalManagerName() {
		return appraisalManagerName;
	}

	public void setAppraisalManagerName(String appraisalManagerName) {
		this.appraisalManagerName = appraisalManagerName;
	}
	
}