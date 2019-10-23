/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplate;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateCategory1;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateCategory1Service;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateScoreService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 考核模板管理Controller
 * @author lbx
 * @version 2018-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalTemplate")
public class HrAppraisalTemplateController extends BaseController {

	@Autowired
	private HrAppraisalTemplateService hrAppraisalTemplateService;
	@Autowired
	private HrAppraisalTemplateCategory1Service hrAppraisalTemplateCategory1Service;
	@Autowired
	private SystemService systemService;
	@Autowired
	private HrAppraisalTemplateScoreService hrAppraisalTemplateScoreService;
	
	@ModelAttribute
	public HrAppraisalTemplate get(@RequestParam(required=false) String id) {
		HrAppraisalTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalTemplateService.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalTemplate hrAppraisalTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalTemplate> page = hrAppraisalTemplateService.findPage(new Page<HrAppraisalTemplate>(request, response), hrAppraisalTemplate); 
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalTemplateList";
	}

	@RequiresPermissions("hr:hrAppraisalTemplate:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalTemplate hrAppraisalTemplate, Model model) {
		
		HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1=new HrAppraisalTemplateCategory1();
		List<HrAppraisalTemplateCategory1> list=hrAppraisalTemplateCategory1Service.findList(hrAppraisalTemplateCategory1);
		
		HrAppraisalTemplateScore hrAppraisalTemplateScore = new HrAppraisalTemplateScore();
		hrAppraisalTemplateScore.setAppraisalTemplateId(hrAppraisalTemplate.getAppraisalTemplateId());
		if(hrAppraisalTemplate.getAppraisalTemplateId()!=null)
		{
			List<HrAppraisalTemplateScore> scoreList= hrAppraisalTemplateScoreService.findList(hrAppraisalTemplateScore);
			model.addAttribute("scoreList", scoreList);
		}
//		String tempId=hrAppraisalTemplate.getAppraisalTemplateId();
		
		Role role =new Role();
		List<Role> rolelist = systemService.findRole(role);
		
		model.addAttribute("hrAppraisalTemplate", hrAppraisalTemplate);
		model.addAttribute("list", list);
		model.addAttribute("rolelist", rolelist);
		
		
		return "modules/hr/hrAppraisalTemplateForm";
	}

	@RequiresPermissions("hr:hrAppraisalTemplate:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalTemplate hrAppraisalTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalTemplate)){
			return form(hrAppraisalTemplate, model);
		}
		hrAppraisalTemplateService.save(hrAppraisalTemplate);
		HrAppraisalTemplateScore hrAppraisalTemplateScore=new HrAppraisalTemplateScore();
		hrAppraisalTemplateScore.setAppraisalTemplateId(hrAppraisalTemplate.getAppraisalTemplateId());
//		hrAppraisalTemplateScore.setName(hrAppraisalTemplate.getScore_name());
		hrAppraisalTemplateScore.setHrscore(hrAppraisalTemplate.getHrscore());
		
		String id2s=hrAppraisalTemplateScore.getHrscore()[0].getId();
		int num=hrAppraisalTemplateScore.getHrscore().length;
		String id1s[]=new String[num];
		int i=0;
		for(i=0;i<num;i++)
		{
			id1s[i]=hrAppraisalTemplateScore.getHrscore()[i].getId();
		}
				
		hrAppraisalTemplateScore.setIds(id1s);
		hrAppraisalTemplateScore.getIds(); 
		//
		hrAppraisalTemplateScoreService.deleteSave(hrAppraisalTemplateScore);
		addMessage(redirectAttributes, "保存考核模板成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplate/?repage";
	}
	
	@RequiresPermissions("hr:hrAppraisalTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(HrAppraisalTemplate hrAppraisalTemplate, RedirectAttributes redirectAttributes) {
		hrAppraisalTemplateService.delete(hrAppraisalTemplate);
		addMessage(redirectAttributes, "删除考核模板成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplate/?repage";
	}

}