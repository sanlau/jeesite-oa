/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateCategory1;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalTemplateCategory1Dao;

/**
 * 考核模板类型管理Service
 * @author lbx
 * @version 2018-11-16
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalTemplateCategory1Service extends CrudService<HrAppraisalTemplateCategory1Dao, HrAppraisalTemplateCategory1> {

	public HrAppraisalTemplateCategory1 get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalTemplateCategory1> findList(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1) {
		return super.findList(hrAppraisalTemplateCategory1);
	}
	
	public Page<HrAppraisalTemplateCategory1> findPage(Page<HrAppraisalTemplateCategory1> page, HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1) {
		return super.findPage(page, hrAppraisalTemplateCategory1);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1) {
		super.save(hrAppraisalTemplateCategory1);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1) {
		super.delete(hrAppraisalTemplateCategory1);
	}

	public static List<HrAppraisalTemplateCategory1> findList(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	
}