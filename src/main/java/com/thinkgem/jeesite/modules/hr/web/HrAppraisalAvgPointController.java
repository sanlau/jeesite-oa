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
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalAvgPoint;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalAvgPointService;

/**
 * 考核平均分Controller
 * @author lbx
 * @version 2019-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalAvgPoint")
public class HrAppraisalAvgPointController extends BaseController {

	@Autowired
	private HrAppraisalAvgPointService hrAppraisalAvgPointService;
	
	@ModelAttribute
	public HrAppraisalAvgPoint get(@RequestParam(required=false) String id) {
		HrAppraisalAvgPoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalAvgPointService.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalAvgPoint();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalAvgPoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalAvgPoint hrAppraisalAvgPoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalAvgPoint> page = hrAppraisalAvgPointService.findPage(new Page<HrAppraisalAvgPoint>(request, response), hrAppraisalAvgPoint); 
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalAvgPointList";
	}

	@RequiresPermissions("hr:hrAppraisalAvgPoint:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalAvgPoint hrAppraisalAvgPoint, Model model) {
		model.addAttribute("hrAppraisalAvgPoint", hrAppraisalAvgPoint);
		return "modules/hr/hrAppraisalAvgPointForm";
	}

	@RequiresPermissions("hr:hrAppraisalAvgPoint:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalAvgPoint hrAppraisalAvgPoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalAvgPoint)){
			return form(hrAppraisalAvgPoint, model);
		}
		hrAppraisalAvgPointService.save(hrAppraisalAvgPoint);
		addMessage(redirectAttributes, "保存考核平均分成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalAvgPoint/?repage";
	}
	
	@RequiresPermissions("hr:hrAppraisalAvgPoint:edit")
	@RequestMapping(value = "delete")
	public String delete(HrAppraisalAvgPoint hrAppraisalAvgPoint, RedirectAttributes redirectAttributes) {
		hrAppraisalAvgPointService.delete(hrAppraisalAvgPoint);
		addMessage(redirectAttributes, "删除考核平均分成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalAvgPoint/?repage";
	}

}