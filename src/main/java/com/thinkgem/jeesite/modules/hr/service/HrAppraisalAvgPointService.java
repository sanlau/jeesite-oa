/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalAvgPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalAvgPointDao;

/**
 * 考核平均分Service
 * @author lbx
 * @version 2019-03-19
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalAvgPointService extends CrudService<HrAppraisalAvgPointDao, HrAppraisalAvgPoint> {

	public HrAppraisalAvgPoint get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalAvgPoint> findList(HrAppraisalAvgPoint hrAppraisalAvgPoint) {
		return super.findList(hrAppraisalAvgPoint);
	}
	
	public Page<HrAppraisalAvgPoint> findPage(Page<HrAppraisalAvgPoint> page, HrAppraisalAvgPoint hrAppraisalAvgPoint) {
		return super.findPage(page, hrAppraisalAvgPoint);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalAvgPoint hrAppraisalAvgPoint) {
		super.save(hrAppraisalAvgPoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalAvgPoint hrAppraisalAvgPoint) {
		super.delete(hrAppraisalAvgPoint);
	}
	@Transactional(readOnly = false)
	public List<HrAppraisalAvgPoint> findAll(String managerId)
	{
		return dao.findAll(managerId);
	}
}