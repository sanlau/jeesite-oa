/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalAvgPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;

/**
 * 考核平均分DAO接口
 * @author lbx
 * @version 2019-03-19
 */
@MyBatisDao
public interface HrAppraisalAvgPointDao extends CrudDao<HrAppraisalAvgPoint> {
	public List<HrAppraisalAvgPoint> findAll(@Param("id")String managerId);
	
}