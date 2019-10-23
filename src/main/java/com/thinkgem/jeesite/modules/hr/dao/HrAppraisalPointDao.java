/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;

/**
 * 考核评分DAO接口
 * @author lbx
 * @version 2019-01-08
 */
@MyBatisDao
public interface HrAppraisalPointDao extends CrudDao<HrAppraisalPoint> {
	Set<String> findId(@Param("id")String managerId);
	public List<HrAppraisalPoint> findAllPoint(@Param("id")String managerId);
	public List<HrAppraisalPoint> findDetail(@Param("id")String managerId,@Param("examineeUserId")String examineeId);
	public List<HrAppraisalPoint> findAll(@Param("id")String managerId);
}