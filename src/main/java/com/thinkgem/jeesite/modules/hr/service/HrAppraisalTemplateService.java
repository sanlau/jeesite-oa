/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplate;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalTemplateDao;

/**
 * 考核模板管理Service
 * @author lbx
 * @version 2018-11-16
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalTemplateService extends CrudService<HrAppraisalTemplateDao, HrAppraisalTemplate> {

	public HrAppraisalTemplate get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalTemplate> findList(HrAppraisalTemplate hrAppraisalTemplate) {
		return super.findList(hrAppraisalTemplate);
	}
	
	public Page<HrAppraisalTemplate> findPage(Page<HrAppraisalTemplate> page, HrAppraisalTemplate hrAppraisalTemplate) {
		return super.findPage(page, hrAppraisalTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalTemplate hrAppraisalTemplate) {
		super.save(hrAppraisalTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalTemplate hrAppraisalTemplate) {
		super.delete(hrAppraisalTemplate);
	}
	
}