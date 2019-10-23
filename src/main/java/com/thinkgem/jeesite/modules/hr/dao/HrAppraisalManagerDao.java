/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalManager;

/**
 * 考核管理DAO接口
 * @author lbx
 * @version 2018-11-28
 */
@MyBatisDao
public interface HrAppraisalManagerDao extends CrudDao<HrAppraisalManager> {
	
	public HrAppraisalManager getAll(String id);
	public List<HrAppraisalManager> getHrAppraisalManagerList(String id);
	
	public void updateStatus(@Param("id")String id,@Param("status")String status);
	
	
}