/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.web;

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
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateCategory1;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateCategory1Service;

/**
 * 考核模板类型管理Controller
 * @author lbx
 * @version 2018-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalTemplateCategory1")
public class HrAppraisalTemplateCategory1Controller extends BaseController {

	@Autowired
	private HrAppraisalTemplateCategory1Service hrAppraisalTemplateCategory1Service;
	
	@ModelAttribute
	public HrAppraisalTemplateCategory1 get(@RequestParam(required=false) String id) {
		HrAppraisalTemplateCategory1 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalTemplateCategory1Service.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalTemplateCategory1();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalTemplateCategory1:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalTemplateCategory1> page = hrAppraisalTemplateCategory1Service.findPage(new Page<HrAppraisalTemplateCategory1>(request, response), hrAppraisalTemplateCategory1); 
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalTemplateCategory1List";
	}

	@RequiresPermissions("hr:hrAppraisalTemplateCategory1:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1, Model model) {
		model.addAttribute("hrAppraisalTemplateCategory1", hrAppraisalTemplateCategory1);
		return "modules/hr/hrAppraisalTemplateCategory1Form";
	}

	@RequiresPermissions("hr:hrAppraisalTemplateCategory1:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalTemplateCategory1)){
			return form(hrAppraisalTemplateCategory1, model);
		}
		hrAppraisalTemplateCategory1Service.save(hrAppraisalTemplateCategory1);
		addMessage(redirectAttributes, "保存模版类型成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplateCategory1/?repage";
	}
	
	@RequiresPermissions("hr:hrAppraisalTemplateCategory1:edit")
	@RequestMapping(value = "delete")
	public String delete(HrAppraisalTemplateCategory1 hrAppraisalTemplateCategory1, RedirectAttributes redirectAttributes) {
		hrAppraisalTemplateCategory1Service.delete(hrAppraisalTemplateCategory1);
		addMessage(redirectAttributes, "删除模版类型成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplateCategory1/?repage";
	}

}