/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;
import com.thinkgem.jeesite.modules.hr.dao.HrAppraisalPointDao;

/**
 * 考核评分Service
 * @author lbx
 * @version 2019-01-08
 */
@Service
@Transactional(readOnly = true)
public class HrAppraisalPointService extends CrudService<HrAppraisalPointDao, HrAppraisalPoint> {

	public HrAppraisalPoint get(String id) {
		return super.get(id);
	}
	
	public List<HrAppraisalPoint> findList(HrAppraisalPoint hrAppraisalPoint) {
		return super.findList(hrAppraisalPoint);
	}
	
	public Page<HrAppraisalPoint> findPage(Page<HrAppraisalPoint> page, HrAppraisalPoint hrAppraisalPoint) {
		return super.findPage(page, hrAppraisalPoint);
	}
	
	@Transactional(readOnly = false)
	public void save(HrAppraisalPoint hrAppraisalPoint) {
		super.save(hrAppraisalPoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(HrAppraisalPoint hrAppraisalPoint) {
		super.delete(hrAppraisalPoint);
	}
	
	@Transactional(readOnly = false)
	public Set<String> findId(String managerId)
	{
		return dao.findId(managerId);
	}
	@Transactional(readOnly = false)
	public List<HrAppraisalPoint> findAllPoint(String managerId)
	{
		return dao.findAllPoint(managerId);
	}
	
	@Transactional(readOnly = false)
	public List<HrAppraisalPoint> findAll(String managerId)
	{
		return dao.findAll(managerId);
	}
	@Transactional(readOnly = false)
	public List<HrAppraisalPoint> findDetail(String managerId,String examineeId)
	{
		return dao.findDetail(managerId,examineeId);
	}
}