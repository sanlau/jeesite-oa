/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;

/**
 * 考核模板评分管理DAO接口
 * @author lbx
 * @version 2018-11-19
 */
@MyBatisDao
public interface HrAppraisalTemplateScoreDao extends CrudDao<HrAppraisalTemplateScore> {
	/**
	 * 删除all数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int deleteall(HrAppraisalTemplateScore entity);
	
	public List<HrAppraisalTemplateScore> getTemplateid(String id);
	
}