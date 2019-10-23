/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考核评分Entity
 * @author lbx
 * @version 2019-01-08
 */
public class HrAppraisalPoint extends DataEntity<HrAppraisalPoint> {
	
	private static final long serialVersionUID = 1L;
	private String examineeUserId;		// examinee_user_id
	private String examinerUserId;		// examiner_user_id
	private String appraisalScoreId;		// appraisal_score_id
	private String appraisalManagerId;		// appraisal_manager_id
	private String point;		// point
	private String comment;		// comment
	
	private String examineeUserName;
	private String appraisalScoreName;
	private String appraisalManagerName;
	
	public HrAppraisalPoint() {
		super();
	}

	public HrAppraisalPoint(String id){
		super(id);
	}

	@Length(min=0, max=5000, message="examinee_user_id长度必须介于 0 和 5000 之间")
	public String getExamineeUserId() {
		return examineeUserId;
	}

	public void setExamineeUserId(String examineeUserId) {
		this.examineeUserId = examineeUserId;
	}
	
	@Length(min=0, max=5000, message="examiner_user_id长度必须介于 0 和 5000 之间")
	public String getExaminerUserId() {
		return examinerUserId;
	}

	public void setExaminerUserId(String examinerUserId) {
		this.examinerUserId = examinerUserId;
	}
	
	@Length(min=0, max=5000, message="appraisal_score_id长度必须介于 0 和 5000 之间")
	public String getAppraisalScoreId() {
		return appraisalScoreId;
	}

	public void setAppraisalScoreId(String appraisalScoreId) {
		this.appraisalScoreId = appraisalScoreId;
	}
	
	@Length(min=0, max=5000, message="appraisal_manager_id长度必须介于 0 和 5000 之间")
	public String getAppraisalManagerId() {
		return appraisalManagerId;
	}

	public void setAppraisalManagerId(String appraisalManagerId) {
		this.appraisalManagerId = appraisalManagerId;
	}
	
	@Length(min=0, max=10, message="point长度必须介于 0 和 10 之间")
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExamineeUserName() {
		return examineeUserName;
	}

	public void setExamineeUserName(String examineeUserName) {
		this.examineeUserName = examineeUserName;
	}

	public String getAppraisalScoreName() {
		return appraisalScoreName;
	}

	public void setAppraisalScoreName(String appraisalScoreName) {
		this.appraisalScoreName = appraisalScoreName;
	}

	public String getAppraisalManagerName() {
		return appraisalManagerName;
	}

	public void setAppraisalManagerName(String appraisalManagerName) {
		this.appraisalManagerName = appraisalManagerName;
	}
	
	
}