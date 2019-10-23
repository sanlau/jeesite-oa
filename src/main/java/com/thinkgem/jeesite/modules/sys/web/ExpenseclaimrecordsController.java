/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Expenseclaimrecords;
import com.thinkgem.jeesite.modules.sys.service.ExpenseclaimrecordsService;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;


/**
 * 报销记录表Controller
 * @author lbx
 * @version 2018-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/expenseclaimrecords")
public class ExpenseclaimrecordsController extends BaseController {

	@Autowired
	private ExpenseclaimrecordsService expenseclaimrecordsService;
	@Autowired
	private ActTaskService actTaskService;
	
	@ModelAttribute
	public Expenseclaimrecords get(@RequestParam(required=false) String id) {
		Expenseclaimrecords entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expenseclaimrecordsService.get(id);
		}
		if (entity == null){
			entity = new Expenseclaimrecords();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:expenseclaimrecords:view")
	@RequestMapping(value = {"list", ""})
	public String list(Expenseclaimrecords expenseclaimrecords, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Expenseclaimrecords> page = expenseclaimrecordsService.findPage(new Page<Expenseclaimrecords>(request, response), expenseclaimrecords); 
		model.addAttribute("page", page);
		return "modules/sys/expenseclaimrecordsList";
	}

	@RequiresPermissions("sys:expenseclaimrecords:view")
	@RequestMapping(value = "form")
	public String form(Expenseclaimrecords expenseclaimrecords, Model model) {
		model.addAttribute("expenseclaimrecords", expenseclaimrecords);
		return "modules/sys/expenseclaimrecordsForm";
	}

	@RequiresPermissions("sys:expenseclaimrecords:edit")
	@RequestMapping(value = "save")
	public String save(Expenseclaimrecords expenseclaimrecords, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expenseclaimrecords)){
			return form(expenseclaimrecords, model);
		}
		expenseclaimrecordsService.save(expenseclaimrecords);
		addMessage(redirectAttributes, "保存报销记录成功");
		return "redirect:"+Global.getAdminPath()+"/sys/expenseclaimrecords/?repage";
	}
	
	@RequiresPermissions("sys:expenseclaimrecords:edit")
	@RequestMapping(value = "delete")
	public String delete(Expenseclaimrecords expenseclaimrecords, RedirectAttributes redirectAttributes) {
		expenseclaimrecordsService.delete(expenseclaimrecords);
		addMessage(redirectAttributes, "删除报销记录成功");
		return "redirect:"+Global.getAdminPath()+"/sys/expenseclaimrecords/?repage";
	}

	@RequiresPermissions("sys:expenseclaimrecords:add")
	@RequestMapping(value = "add")
	public String add(Expenseclaimrecords expenseclaimrecords, Model model) {

		
		model.addAttribute("expenseclaimrecords", expenseclaimrecords);
		String view = "expenseclaimrecordsForm";
		
		if(StringUtils.isNotBlank(expenseclaimrecords.getId()))
		{
			String taskDefKey=expenseclaimrecords.getAct().getTaskDefKey();
			if(StringUtils.isEmpty(taskDefKey))
			{
				return "modules/sys/add/"+view;
			}
			
			if(expenseclaimrecords.getAct().isFinishTask())
			{
				view="expenseclaimrecordsView";
			}
			else if("modify".equals(taskDefKey))
			{
				view="expenseclaimrecordsForm";
			}
			else if("audit".equals(taskDefKey))
			{
				view="expenseclaimrecordsAudit";
			}
			
		}
		return "modules/sys/add/"+view;
	}
	
	@RequiresPermissions("sys:expenseclaimrecords:add")
	@RequestMapping(value = "addSave")
	public String addSave(Expenseclaimrecords expenseclaimrecords, Model model,RedirectAttributes redirectAttributes) {

		if(!beanValidator(model,expenseclaimrecords))
		{
			return add(expenseclaimrecords,model);
		}
		
		try
		{
			if(expenseclaimrecordsService.saveWithReturn(expenseclaimrecords) < 1)
			{
				addMessage(redirectAttributes, "save error");
			}
			else
			{
				addMessage(redirectAttributes, "save ok");
			}
		}catch(Exception e)
		{
			addMessage(redirectAttributes, "save error"+e);
		}
		return "redirect:"+Global.getAdminPath()+"/sys/expenseclaimrecords/";
	}
	
	@RequiresPermissions("sys:expenseclaimrecords:add")
	@RequestMapping(value = "addAudit")
	public String addAudit(Expenseclaimrecords expenseclaimrecords, Model model,RedirectAttributes redirectAttributes) {

		if(expenseclaimrecords.getAct().isFinishTask())
		{
			addMessage(redirectAttributes, "renwu"+expenseclaimrecords.getId()+"save ok");
		}
		else
		{
			if(StringUtils.isBlank(expenseclaimrecords.getAct().getFlag())||StringUtils.isBlank(expenseclaimrecords.getAct().getComment())) {
				addMessage(model, "please input yijian:");
			}
			else
			{
				expenseclaimrecordsService.addAudit(expenseclaimrecords);
				addMessage(redirectAttributes, "renwu"+expenseclaimrecords.getProjectName()+"add tijiaochenggong");
			}
		}
		
		return "redirect:"+adminPath+"/act/task/todo/";
	}
	
	@RequiresPermissions("sys:expenseclaimrecords:modify")
	@RequestMapping(value = "modify")
	public String modify(Expenseclaimrecords expenseclaimrecords, Model model) {

		
		model.addAttribute("expenseclaimrecords", expenseclaimrecords);
		String view = "expenseclaimrecordsApply";
		
		if(StringUtils.isNoneBlank(expenseclaimrecords.getId()))
		{
			Act act=expenseclaimrecords.getAct();
			String taskDefKey=act.getTaskDefKey();
			if(StringUtils.isEmpty(taskDefKey))
			{
				return "modules/sys/modify/"+view;
			}
			
			if(expenseclaimrecords.getAct().isFinishTask())
			{
				view="expenseclaimrecordsView";
			}
			else {
				Map<String,Object> vars=actTaskService.getProcessVariables(act.getTaskId());
				if(vars!=null)
				{
					act.setVars(vars);
				}
				switch(taskDefKey) {
				case "audit1" :
					view="expenseclaimrecordsAudit1";
					break;
				case "reapply" :
					view="expenseclaimrecordsApply";
					break;
				case "allowModify" :
					view="expenseclaimrecordsAudit1";
					break;
				case "modify" :
					view="expenseclaimrecordsForm";
					break;
				case "audit2" :
					view="expenseclaimrecordsAudit";
					break;
					
				default:
					break;
				}
			}
			
		}
		return "modules/sys/modify/"+view;
	}
	@RequiresPermissions("sys:expenseclaimrecords:modify")
	@RequestMapping(value = "modifySave")
	public String modifySave(Expenseclaimrecords expenseclaimrecords, Model model,RedirectAttributes redirectAttributes) {

		if(!beanValidator(model,expenseclaimrecords))
		{
			return modify(expenseclaimrecords,model);
		}
		
		try
		{
			if(expenseclaimrecordsService.modifySave(expenseclaimrecords) >0)
			{
				addMessage(redirectAttributes, "接口"+expenseclaimrecords.getProjectName() + "修改申请提交成功");
			}
			else
			{
				addMessage(redirectAttributes, "接口"+expenseclaimrecords.getProjectName() + "修改申请提交失败");
			}
		}catch(Exception e)
		{
			addMessage(redirectAttributes, "save error"+e);
		}
		return "redirect:"+Global.getAdminPath()+"/sys/expenseclaimrecords/";
	}
	

	@RequiresPermissions("sys:expenseclaimrecords:modify")
	@RequestMapping(value = "modifyAudit")
	public String modifyAudit(Expenseclaimrecords expenseclaimrecords, Model model,RedirectAttributes redirectAttributes) {

		if(expenseclaimrecords.getAct().isFinishTask())
		{
			addMessage(redirectAttributes, "接口"+expenseclaimrecords.getProjectName()+"删除任务已完成");
		}
		else
		{
			Map<String,Object> vars=actTaskService.getProcessVariables(expenseclaimrecords.getAct().getTaskId());
			if(vars!=null)
			{
				expenseclaimrecords.getAct().setVars(vars);
			}
			expenseclaimrecordsService.modifyAudit(expenseclaimrecords);
			
			/*
			try {
				addMessage(redirectAttributes, "接口"+expenseclaimrecords.getProjectName()+"删除任务已完成");
			}catch (IOException e)
			{
				addMessage(redirectAttributes, "接口"+expenseclaimrecords.getProjectName()+"删除任务已完成");
			}
			*/
		}
		
		return "redirect:"+adminPath+"/act/task/todo/";
	}
	
}

