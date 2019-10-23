/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.web;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalAvgPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalManager;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplate;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateCategory1;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalAvgPointService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalManagerService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalPointService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateScoreService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateService;
import com.thinkgem.jeesite.modules.sys.entity.Role;

/**
 * 考核管理Controller
 * @author lbx
 * @version 2018-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalManager")
public class HrAppraisalManagerController extends BaseController {

	@Autowired
	private HrAppraisalManagerService hrAppraisalManagerService;
	@Autowired
	private HrAppraisalTemplateService hrAppraisalTemplateService;
	@Autowired
	private HrAppraisalTemplateScoreService hrAppraisalTemplateScoreService;
	@Autowired
	private HrAppraisalPointService hrAppraisalPointService;
	@Autowired
	private HrAppraisalAvgPointService hrAppraisalAvgPointService;
	@ModelAttribute
	public HrAppraisalManager get(@RequestParam(required=false) String id) {
		HrAppraisalManager entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalManagerService.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalManager();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalManager hrAppraisalManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalManager> page = hrAppraisalManagerService.findPage(new Page<HrAppraisalManager>(request, response), hrAppraisalManager); 
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalManagerList";
	}

	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalManager hrAppraisalManager, Model model) {
		HrAppraisalTemplate hrAppraisalTemplate=new HrAppraisalTemplate();
		List<HrAppraisalTemplate> list=hrAppraisalTemplateService.findList(hrAppraisalTemplate);
		model.addAttribute("hrAppraisalManager", hrAppraisalManager);
		model.addAttribute("list", list);
		return "modules/hr/hrAppraisalManagerForm";
	}

	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "viewform")
	public String viewform(HrAppraisalManager hrAppraisalManager, Model model) {
		HrAppraisalTemplate hrAppraisalTemplate=new HrAppraisalTemplate();
		List<HrAppraisalTemplate> list=hrAppraisalTemplateService.findList(hrAppraisalTemplate);
	 
		HrAppraisalTemplateScore hrAppraisalTemplateScore = new HrAppraisalTemplateScore();
		hrAppraisalTemplateScore.setAppraisalTemplateId(hrAppraisalManager.getAppraisalTemplateId());
		if(hrAppraisalManager.getAppraisalTemplateId()!=null)
		{
			List<HrAppraisalTemplateScore> scoreList= hrAppraisalTemplateScoreService.findList(hrAppraisalTemplateScore);
			model.addAttribute("scoreList", scoreList);
		}
		HrAppraisalManager entity = null;
		if (StringUtils.isNotBlank(hrAppraisalManager.getId())){
			entity = hrAppraisalManagerService.getAll(hrAppraisalManager.getId());
		}
		
		model.addAttribute("hrAppraisalManager", entity);
		model.addAttribute("list", list);
		return "modules/hr/hrAppraisalManagerViewForm";
	}
	
	@RequiresPermissions("hr:hrAppraisalManager:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalManager hrAppraisalManager, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalManager)){
			return form(hrAppraisalManager, model);
		}
		hrAppraisalManagerService.save(hrAppraisalManager);
		addMessage(redirectAttributes, "保存考核管理成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalManager/?repage";
	}
	
	@RequiresPermissions("hr:hrAppraisalManager:edit")
	@RequestMapping(value = "delete")
	public String delete(HrAppraisalManager hrAppraisalManager, RedirectAttributes redirectAttributes) {
		hrAppraisalManagerService.delete(hrAppraisalManager);
		addMessage(redirectAttributes, "删除考核管理成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalManager/?repage";
	}
	
	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "scoreSum")
	public String scoresum(HrAppraisalManager hrAppraisalManager, Model model) {
		HrAppraisalTemplate hrAppraisalTemplate=new HrAppraisalTemplate();
		List<HrAppraisalTemplate> list=hrAppraisalTemplateService.findList(hrAppraisalTemplate);
	 
		HrAppraisalTemplateScore hrAppraisalTemplateScore = new HrAppraisalTemplateScore();
		hrAppraisalTemplateScore.setAppraisalTemplateId(hrAppraisalManager.getAppraisalTemplateId());
		if(hrAppraisalManager.getAppraisalTemplateId()!=null)
		{
			List<HrAppraisalTemplateScore> scoreList= hrAppraisalTemplateScoreService.findList(hrAppraisalTemplateScore);
			model.addAttribute("scoreList", scoreList);
		}
		HrAppraisalManager entity = null;
		if (StringUtils.isNotBlank(hrAppraisalManager.getId())){
			entity = hrAppraisalManagerService.getAll(hrAppraisalManager.getId());
		}
		
		model.addAttribute("hrAppraisalManager", entity);
		model.addAttribute("list", list);
		return "modules/hr/hrAppraisalManagerScoreSum";
	}
	
	
	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "summary")
	public String summary(HrAppraisalManager hrAppraisalManager, Model model) {
		List<HrAppraisalPoint> hrAppraisalPointList;
		hrAppraisalPointList=hrAppraisalPointService.findAll(hrAppraisalManager.getId());
		List<HrAppraisalAvgPoint> hrAppraisalAvgPointList;
		hrAppraisalAvgPointList=hrAppraisalAvgPointService.findAll(hrAppraisalManager.getId());
		
		model.addAttribute("hrAppraisalAvgPointList", hrAppraisalAvgPointList);
		model.addAttribute("hrAppraisalPointList", hrAppraisalPointList);
		return "modules/hr/hrAppraisalManagerSummary";
		 
	}
	
	
	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "detailpoint")
	public String detailpoint(HrAppraisalManager hrAppraisalManager, Model model) {
		List<HrAppraisalPoint> hrAppraisalPointList = null;
		hrAppraisalPointList=hrAppraisalPointService.findDetail(hrAppraisalManager.getId(),hrAppraisalManager.getExamineeUserId());
		model.addAttribute("hrAppraisalPointList", hrAppraisalPointList);
		return "modules/hr/hrAppraisalManagerDetailPoint";
		 
	}
	
	@RequiresPermissions("hr:hrAppraisalManager:view")
	@RequestMapping(value = "detailview")
	public String detailview(HrAppraisalManager hrAppraisalManager, Model model) {
		List<HrAppraisalPoint> hrAppraisalPointList = null;
		hrAppraisalPointList=hrAppraisalPointService.findDetail(hrAppraisalManager.getId(),hrAppraisalManager.getExamineeUserId());
		model.addAttribute("hrAppraisalPointList", hrAppraisalPointList);
		return "modules/hr/hrAppraisalManagerDetailView";
		 
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,String>> treeData() {
		List<Map<String, String>> mapList = Lists.newArrayList();
		Map<String,String> root=Maps.newHashMap();
		root.put("id","0");
		root.put("pId", "-1");
		root.put("name","人员列表");
		mapList.add(root);
		List<HrAppraisalManager> list=hrAppraisalManagerService.findList(new HrAppraisalManager());
		for(HrAppraisalManager h:list)
		{
			Map<String,String> map=Maps.newHashMap();
			map.put("id",h.getExamineeUserId());
			map.put("pId", "0");
			map.put("name",h.getName()+"("+h.getExamineeUserId()+")");
			mapList.add(map);
		}
		return mapList;
	}
	
	
	

}