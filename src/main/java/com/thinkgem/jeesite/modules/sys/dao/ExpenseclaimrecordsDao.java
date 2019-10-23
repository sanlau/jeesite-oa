/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.Set;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Expenseclaimrecords;

/**
 * 报销记录表DAO接口
 * @author lbx
 * @version 2018-11-15
 */
@MyBatisDao
public interface ExpenseclaimrecordsDao extends CrudDao<Expenseclaimrecords> {

	boolean exists(Expenseclaimrecords expenseclaimrecords);

	Set<String> getCaller(Expenseclaimrecords expenseclaimrecords);

	Set<String> getServId(String id);
	
}