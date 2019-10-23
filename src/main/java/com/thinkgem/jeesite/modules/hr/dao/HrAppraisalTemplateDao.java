/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplate;

/**
 * 考核模板管理DAO接口
 * @author lbx
 * @version 2018-11-16
 */
@MyBatisDao
public interface HrAppraisalTemplateDao extends CrudDao<HrAppraisalTemplate> {
	
}