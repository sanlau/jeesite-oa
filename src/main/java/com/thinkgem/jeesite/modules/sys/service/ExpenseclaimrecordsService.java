/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Expenseclaimrecords;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.sys.dao.ExpenseclaimrecordsDao;

/**
 * 报销记录表Service
 * @author lbx
 * @version 2018-11-15
 */
@Service
@Transactional(readOnly = true)
public class ExpenseclaimrecordsService extends CrudService<ExpenseclaimrecordsDao, Expenseclaimrecords> {


	public static final String AUDIT_STATUS="0";
	public static final String UN_SERVER_STATUS="1";
	public static final String SEVER_STATUS="2";
	@Autowired
	private ActTaskService actTaskService;
	
	public Expenseclaimrecords get(String id) {
		return super.get(id);
	}
	
	public List<Expenseclaimrecords> findList(Expenseclaimrecords expenseclaimrecords) {
		return super.findList(expenseclaimrecords);
	}
	
	public Page<Expenseclaimrecords> findPage(Page<Expenseclaimrecords> page, Expenseclaimrecords expenseclaimrecords) {
		return super.findPage(page, expenseclaimrecords);
	}
	
	@Transactional(readOnly = false)
	public void save(Expenseclaimrecords expenseclaimrecords) {
		super.save(expenseclaimrecords);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expenseclaimrecords expenseclaimrecords) {
		super.delete(expenseclaimrecords);
	}

	@Transactional(readOnly = false)
	public int saveWithReturn(Expenseclaimrecords expenseclaimrecords) throws Exception {
		// TODO Auto-generated method stub
		if(exists(expenseclaimrecords))
		{
			return doExsits(expenseclaimrecords);
		}
		else
		{
			expenseclaimrecords.preInsert();
			//表中无状态
//			expenseclaimrecords.setStatus(AUDIT_STATUS);
			dao.insert(expenseclaimrecords);
			
			Map<String,Object> vars=Maps.newHashMap();
			User u= UserUtils.getAuditor();
			vars.put("auditor", u.getLoginName());
			String procIns=actTaskService.startProcess(ActUtils.PD_ADD_EXPENSE[0], ActUtils.PD_ADD_EXPENSE[1], expenseclaimrecords.getId(),expenseclaimrecords.getProjectName()+"审核",vars);
			if(StringUtils.isNotEmpty(procIns))
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}

	private int doExsits(Expenseclaimrecords expenseclaimrecords) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean exists(Expenseclaimrecords expenseclaimrecords) {
		// TODO Auto-generated method stub
		return dao.exists(expenseclaimrecords);
	}

	@Transactional(readOnly = false)
	public void addAudit(Expenseclaimrecords expenseclaimrecords) {
		// TODO Auto-generated method stub
		Act act=expenseclaimrecords.getAct();
		boolean agree="yes".equals(act.getFlag());
		act.setComment(agree ? "[同意]":"[驳回]"+act.getComment());
		expenseclaimrecords.preUpdate();
		String taskDefKey=act.getTaskDefKey();
		Map<String,Object> vars=Maps.newHashMap();
		switch(taskDefKey)
		{
		case "modify":
			if(agree)
			{
				dao.update(expenseclaimrecords);
//				dao.updateStatus();
			}
			else
			{
				delete(expenseclaimrecords);
			}
			break;
		case "audit":
			if(agree)
			{
				dao.update(expenseclaimrecords);
//				dao.updateStatus(entity)
			}
			break;
			default:
				break;
		}
		vars.put("pass", agree ? "1":"0");
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), vars);
		
	}
	@Transactional(readOnly = false)
	public int modifySave(Expenseclaimrecords expenseclaimrecords) throws Exception {
		// TODO Auto-generated method stub
		Act act=expenseclaimrecords.getAct();
		Map<String,Object> vars=Maps.newHashMap();
		if(act.getVars()!=null && act.getVars().getMap()!=null)
		{
			vars.putAll(act.getVars().getMap());
		}
		User u=UserUtils.getAuditor();
		vars.put("auditor", u.getLoginName());
		//当有多个调用审核方时，查询数据库获得多个审核方
//		Set<String> callers=dao.getCaller(expenseclaimrecords);
//		vars.put("callers", callers);
		vars.put("agreeCount", 0);
		String procIns=actTaskService.startProcess(ActUtils.PD_MODIFY_EXPENSE[0], ActUtils.PD_MODIFY_EXPENSE[1], expenseclaimrecords.getId(),expenseclaimrecords.getProjectName()+"审核",vars);
		if(StringUtils.isNotEmpty(procIns))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	@Transactional(readOnly = false)
	public void modifyAudit(Expenseclaimrecords expenseclaimrecords) {
		// TODO Auto-generated method stub
		Act act=expenseclaimrecords.getAct();
		boolean agree="yes".equals(act.getFlag());
		act.setComment(agree ? "[同意]":"[驳回]"+act.getComment());
		expenseclaimrecords.preUpdate();
		String taskDefKey=act.getTaskDefKey();
		Map<String,Object> vars=Maps.newHashMap();
		switch(taskDefKey)
		{
		case "audit1":
			if(agree)
			{
//				int agreeCount=1;
//				Integer tmp=(Integer)act.getVars().getMap().get("agreeCount");
//				if(tmp!=null)
//				{
//					agreeCount+=tmp;
//				}
//				vars.put("agreeCount", agreeCount);
			}
			break;
		case "reapply":
			if(agree)
			{
//				Set<String> callers=dao.getCaller(expenseclaimrecords);
//				vars.put("callers", callers);
//				vars.put("agreeCount", 0);
			}
			break;
		case "allowModify":
			break;
		case "modify":
			if(agree)
			{
				dao.update(expenseclaimrecords);
//				dao.updateStatus(Sets.newHashSet(expenseclaimrecords.getId()),AUDIT_STATUS);
//				Set<String> servIds=dao.getServId(expenseclaimrecords.getId());
//				if(!servIds.isEmpty())
//				{
//					ExpenseclaimrecordsService
//				}
			}
			break;
		case "audit2":
			if(agree)
			{
//				dao.updateStatus(Sets.newHashSet(expenseclaimrecords.getId()),UN_SERVER_STATUS);
			}
		default:
				break;
		}
		vars.put("pass", agree ? "1":"0");
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), vars);
		
	}
	
	@Transactional(readOnly = false)
	public int deleteSave1(Expenseclaimrecords expenseclaimrecords) throws Exception {
		// TODO Auto-generated method stub
		Act act=expenseclaimrecords.getAct();
		Map<String,Object> vars=Maps.newHashMap();
		if(act.getVars()!=null && act.getVars().getMap()!=null)
		{
			vars.putAll(act.getVars().getMap());
		}
		User u=UserUtils.getAuditor();
		vars.put("auditor", u.getLoginName());
		Set<String> callers=dao.getCaller(expenseclaimrecords);
		vars.put("callers", callers);
		vars.put("agreeCount", 0);
		String procIns=actTaskService.startProcess(ActUtils.PD_DELETE_EXPENSE[0], ActUtils.PD_DELETE_EXPENSE[1], expenseclaimrecords.getId(),expenseclaimrecords.getProjectName()+"审核",vars);
		if(StringUtils.isNotEmpty(procIns))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	@Transactional(readOnly = false)
	public void deleteAudit(Expenseclaimrecords expenseclaimrecords) {
		// TODO Auto-generated method stub
		Act act=expenseclaimrecords.getAct();
		boolean agree="yes".equals(act.getFlag());
		act.setComment(agree ? "[同意]":"[驳回]"+act.getComment());
		expenseclaimrecords.preUpdate();
		String taskDefKey=act.getTaskDefKey();
		Map<String,Object> vars=Maps.newHashMap();
		switch(taskDefKey)
		{
		case "audit1":
			if(agree)
			{
				int agreeCount=1;
				Integer tmp=(Integer)act.getVars().getMap().get("agreeCount");
				if(tmp!=null)
				{
					agreeCount+=tmp;
				}
				vars.put("agreeCount", agreeCount);
			}
			break;
		case "reapply":
			if(agree)
			{
				Set<String> callers=dao.getCaller(expenseclaimrecords);
				vars.put("callers", callers);
				vars.put("agreeCount", 0);
			}
			break;
		case "delete":
			delete(expenseclaimrecords);
			break;
		
		default:
				break;
		}
		vars.put("pass", agree ? "1":"0");
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), vars);
		
	}
}