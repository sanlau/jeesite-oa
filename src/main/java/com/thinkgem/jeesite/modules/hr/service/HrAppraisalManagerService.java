/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalManager;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalManagerDao;

/**
 * 考核管理Service
 * @author lbx
 * @version 2018-11-28
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalManagerService extends CrudService<HrAppraisalManagerDao, HrAppraisalManager> {

	public HrAppraisalManager get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalManager> findList(HrAppraisalManager hrAppraisalManager) {
		return super.findList(hrAppraisalManager);
	}
	
	public Page<HrAppraisalManager> findPage(Page<HrAppraisalManager> page, HrAppraisalManager hrAppraisalManager) {
		return super.findPage(page, hrAppraisalManager);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalManager hrAppraisalManager) {
		super.save(hrAppraisalManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalManager hrAppraisalManager) {
		super.delete(hrAppraisalManager);
	}

	public HrAppraisalManager getAll(String id) {
		// TODO Auto-generated method stub
		return	dao.getAll(id);
	}

	public List<HrAppraisalManager> getHrAppraisalManagerList(String id) {
		// TODO Auto-generated method stub
		return	dao.getHrAppraisalManagerList(id);
	}
	@Transactional(readOnly = false)
	public void updateStatus(String id,String status)
	{
		dao.updateStatus(id,status);
	}
	
}