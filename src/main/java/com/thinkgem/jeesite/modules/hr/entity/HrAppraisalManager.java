/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考核管理Entity
 * @author lbx
 * @version 2018-11-28
 */
public class HrAppraisalManager extends DataEntity<HrAppraisalManager> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private Date startTime;		// start_time
	private Date endTime;		// end_time
	private String status;		// status
	private String executorId;		// executor_id
	private String appraisalTemplateId;		// appraisal_template_id
	private String examineeUserId;		// examinee_user_id
	private String examinerUserId;		// examiner_user_id
	
	
	private String examineeUserIdName;		// examinee_user_id
	private String examinerUserIdName;		// examiner_user_id
	private String templateName;
	private String examineeNum;
	public HrAppraisalManager() {
		super();
	}

	public HrAppraisalManager(String id){
		super(id);
	}

	@Length(min=0, max=500, message="name长度必须介于 0 和 500 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="start_time不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="end_time不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="executor_id长度必须介于 0 和 64 之间")
	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}
	
	@Length(min=0, max=64, message="appraisal_template_id长度必须介于 0 和 64 之间")
	public String getAppraisalTemplateId() {
		return appraisalTemplateId;
	}

	public void setAppraisalTemplateId(String appraisalTemplateId) {
		this.appraisalTemplateId = appraisalTemplateId;
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

	public String getExamineeUserIdName() {
		return examineeUserIdName;
	}

	public void setExamineeUserIdName(String examineeUserIdName) {
		this.examineeUserIdName = examineeUserIdName;
	}

	public String getExaminerUserIdName() {
		return examinerUserIdName;
	}

	public void setExaminerUserIdName(String examinerUserIdName) {
		this.examinerUserIdName = examinerUserIdName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getExamineeNum() {
		return examineeNum;
	}

	public void setExamineeNum(String examineeNum) {
		this.examineeNum = examineeNum;
	}
	
}