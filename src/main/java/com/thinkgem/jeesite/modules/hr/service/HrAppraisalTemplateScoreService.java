/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalTemplateScoreDao;

/**
 * 考核模板评分管理Service
 * @author lbx
 * @version 2018-11-19
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalTemplateScoreService extends CrudService<HrAppraisalTemplateScoreDao, HrAppraisalTemplateScore> {

	public HrAppraisalTemplateScore get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalTemplateScore> findList(HrAppraisalTemplateScore hrAppraisalTemplateScore) {
		return super.findList(hrAppraisalTemplateScore);
	}
	
	public Page<HrAppraisalTemplateScore> findPage(Page<HrAppraisalTemplateScore> page, HrAppraisalTemplateScore hrAppraisalTemplateScore) {
		return super.findPage(page, hrAppraisalTemplateScore);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalTemplateScore hrAppraisalTemplateScore) {
		super.save(hrAppraisalTemplateScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalTemplateScore hrAppraisalTemplateScore) {
		super.delete(hrAppraisalTemplateScore);
	}

	@Transactional(readOnly = false)
	public void deleteSave(HrAppraisalTemplateScore hrAppraisalTemplateScore) {
		super.deleteSave(hrAppraisalTemplateScore);
	}

	public List<HrAppraisalTemplateScore> getTemplateid(String id) {
		// TODO Auto-generated method stub
		return dao.getTemplateid(id);
	}
	
	
}