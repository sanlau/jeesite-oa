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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateScoreService;

/**
 * 考核模板评分管理Controller
 * @author lbx
 * @version 2018-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalTemplateScore")
public class HrAppraisalTemplateScoreController extends BaseController {

	@Autowired
	private HrAppraisalTemplateScoreService hrAppraisalTemplateScoreService;
	
	@ModelAttribute
	public HrAppraisalTemplateScore get(@RequestParam(required=false) String id) {
		HrAppraisalTemplateScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalTemplateScoreService.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalTemplateScore();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalTemplateScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalTemplateScore hrAppraisalTemplateScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalTemplateScore> page = hrAppraisalTemplateScoreService.findPage(new Page<HrAppraisalTemplateScore>(request, response), hrAppraisalTemplateScore); 
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalTemplateScoreList";
	}

	@RequiresPermissions("hr:hrAppraisalTemplateScore:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalTemplateScore hrAppraisalTemplateScore, Model model) {
		model.addAttribute("hrAppraisalTemplateScore", hrAppraisalTemplateScore);
		return "modules/hr/hrAppraisalTemplateScoreForm";
	}

	@RequiresPermissions("hr:hrAppraisalTemplateScore:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalTemplateScore hrAppraisalTemplateScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalTemplateScore)){
			return form(hrAppraisalTemplateScore, model);
		}
		hrAppraisalTemplateScoreService.save(hrAppraisalTemplateScore);
		addMessage(redirectAttributes, "保存模板评分成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplateScore/?repage";
	}
	
	//@RequiresPermissions("hr:hrAppraisalTemplateScore:edit")
	@RequestMapping(value = "delete")
	//@ResponseBody
	public void delete(HrAppraisalTemplateScore hrAppraisalTemplateScore, RedirectAttributes redirectAttributes) {
		hrAppraisalTemplateScoreService.delete(hrAppraisalTemplateScore);
		//addMessage(redirectAttributes, "删除模板评分成功");
		//return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalTemplateScore/?repage";
	}

}