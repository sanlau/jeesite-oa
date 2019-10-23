/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.hr.web;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalAvgPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalManager;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;
import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalTemplateScore;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalAvgPointService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalManagerService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalPointService;
import com.thinkgem.jeesite.modules.hr.service.HrAppraisalTemplateScoreService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import net.sf.cglib.core.CollectionUtils;

/**
 * 考核评分Controller
 * @author lbx
 * @version 2019-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrAppraisalPoint")
public class HrAppraisalPointController extends BaseController {

	@Autowired
	private HrAppraisalManagerService hrAppraisalManagerService;
	@Autowired
	private HrAppraisalPointService hrAppraisalPointService;
	@Autowired
	private HrAppraisalTemplateScoreService hrAppraisalTemplateScoreService;
	@Autowired
	private HrAppraisalAvgPointService hrAppraisalAvgPointService;
	@Autowired
	private UserDao userdao;
	
	@ModelAttribute
	public HrAppraisalPoint get(@RequestParam(required=false) String id) {
		HrAppraisalPoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = hrAppraisalPointService.get(id);
		}
		if (entity == null){
			entity = new HrAppraisalPoint();
		}
		return entity;
	}
	
	@RequiresPermissions("hr:hrAppraisalPoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrAppraisalPoint hrAppraisalPoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HrAppraisalPoint> page = hrAppraisalPointService.findPage(new Page<HrAppraisalPoint>(request, response), hrAppraisalPoint); 
		
		HrAppraisalManager hrAppraisalManager=new HrAppraisalManager();
		List<HrAppraisalManager> hrAppraisalManagerList;
		String userId =   UserUtils.getUser().getId();
		hrAppraisalManagerList=hrAppraisalManagerService.getHrAppraisalManagerList(userId);
		
		Iterator<HrAppraisalManager> iterator = hrAppraisalManagerList.iterator();
        while (iterator.hasNext()) {
            HrAppraisalManager temp = iterator.next();
            if ("1".equals(temp.getStatus())) {
                iterator.remove();
            }

        }
 /*
		for(HrAppraisalManager temp:hrAppraisalManagerList){
		    if(temp.getStatus().equals("1"))
		    	hrAppraisalManagerList.remove(temp);
		}
		*/
		hrAppraisalManager = hrAppraisalManagerService.getAll(hrAppraisalManager.getId());
		model.addAttribute("hrAppraisalManagerList", hrAppraisalManagerList);
		model.addAttribute("page", page);
		return "modules/hr/hrAppraisalPointList";
	}

	@RequiresPermissions("hr:hrAppraisalPoint:view")
	@RequestMapping(value = "form")
	public String form(HrAppraisalPoint hrAppraisalPoint, Model model) {
		HrAppraisalManager hrAppraisalManager=new HrAppraisalManager();
		List<HrAppraisalManager> hrAppraisalManagerList;
		String userId =   UserUtils.getUser().getId();
		hrAppraisalManagerList=hrAppraisalManagerService.getHrAppraisalManagerList(userId);
		
		hrAppraisalManager = hrAppraisalManagerService.getAll(hrAppraisalManager.getId());
		
		model.addAttribute("hrAppraisalManager", hrAppraisalManager);
		model.addAttribute("hrAppraisalPoint", hrAppraisalPoint);
		return "modules/hr/hrAppraisalPointForm";
	}

	@RequiresPermissions("hr:hrAppraisalPoint:view")
	@RequestMapping(value = "score")
	public String score(HrAppraisalPoint hrAppraisalPoint, Model model) {
		HrAppraisalManager hrAppraisalManager=new HrAppraisalManager();
		hrAppraisalManager = hrAppraisalManagerService.get(hrAppraisalPoint.getId());
		
		HrAppraisalManager hrAppraisalManagerAll=new HrAppraisalManager();
		hrAppraisalManagerAll = hrAppraisalManagerService.getAll(hrAppraisalPoint.getId());
		String ExamineeUserNameList=hrAppraisalManagerAll.getExamineeUserIdName();
		List<String> ExamineeUserName = Arrays.asList(ExamineeUserNameList.split(","));
		hrAppraisalManagerAll.getExamineeUserId();
		
		List<User> userList;
		
		
		String ids = hrAppraisalManagerAll.getExamineeUserId();
		Set<String> findids=hrAppraisalPointService.findId(hrAppraisalManagerAll.getId());
		Set<String> fullids=Sets.newHashSet(Splitter.on(",").splitToList(ids));
		Set<String> result=Sets.difference(fullids, findids);
		Joiner.on(",").join(result);
		if(!result.isEmpty())
		{
			userList=userdao.findUserName(Joiner.on(",").join(result));
			model.addAttribute("userList", userList);
//			userList=userdao.findUserName(hrAppraisalManagerAll.getExamineeUserId());
		}
		else
		{
			hrAppraisalManagerService.updateStatus(hrAppraisalManagerAll.getId(),"1");
			return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalPoint";
		}
		hrAppraisalManagerAll.getAppraisalTemplateId();
		List<HrAppraisalTemplateScore> hrAppraisalTemplateScoreList;
		hrAppraisalTemplateScoreList=hrAppraisalTemplateScoreService.getTemplateid(hrAppraisalManagerAll.getAppraisalTemplateId());
		
		
		model.addAttribute("hrAppraisalPoint", hrAppraisalManager);
		model.addAttribute("hrAppraisalTemplateScoreList", hrAppraisalTemplateScoreList);
		return "modules/hr/hrAppraisalPointScore";
	}
	
	@RequiresPermissions("hr:hrAppraisalPoint:edit")
	@RequestMapping(value = "save")
	public String save(HrAppraisalPoint hrAppraisalPoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, hrAppraisalPoint)){
			return form(hrAppraisalPoint, model);
		}
		hrAppraisalPointService.save(hrAppraisalPoint);
		addMessage(redirectAttributes, "保存考核评分成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalPoint/?repage";
	}
	
	
	@RequiresPermissions("hr:hrAppraisalPoint:edit")
	@RequestMapping(value = "submit")
	public String submit(XX x, Model model, RedirectAttributes redirectAttributes) {
		String userId =   UserUtils.getUser().getId();
		String appraisalManagerId = null;
		String examineeUserId = null;
		int sumPoint=0;
		int index=0;
		float avgPoint=0;
		for (HrAppraisalPoint hrAppraisalPoint: x.getHrAppraisalPoints()) {
			hrAppraisalPoint.setExaminerUserId(userId);
			hrAppraisalPoint.setExamineeUserId(x.getExameeid());
			hrAppraisalPointService.save(hrAppraisalPoint);
			
			appraisalManagerId=hrAppraisalPoint.getAppraisalManagerId();
			examineeUserId=hrAppraisalPoint.getExamineeUserId();
			sumPoint+=Integer.parseInt(hrAppraisalPoint.getPoint());
			index++;
		}
		avgPoint=sumPoint/index;
		HrAppraisalAvgPoint hrAppraisalAvgPoint=new HrAppraisalAvgPoint();
		hrAppraisalAvgPoint.setAppraisalManagerId(appraisalManagerId);
		hrAppraisalAvgPoint.setAvgPoint(String.valueOf(avgPoint));
		hrAppraisalAvgPoint.setExamineeUserId(examineeUserId);
		hrAppraisalAvgPointService.save(hrAppraisalAvgPoint);
		sumPoint=0;
		index=0;
		String redict1="redirect:"+Global.getAdminPath()+"/hr/hrAppraisalPoint/score?id=";
		addMessage(redirectAttributes, "保存考核评分成功");
		return redict1+appraisalManagerId;
	}
	
	@RequiresPermissions("hr:hrAppraisalPoint:edit")
	@RequestMapping(value = "delete")
	public String delete(HrAppraisalPoint hrAppraisalPoint, RedirectAttributes redirectAttributes) {
		hrAppraisalPointService.delete(hrAppraisalPoint);
		addMessage(redirectAttributes, "删除考核评分成功");
		return "redirect:"+Global.getAdminPath()+"/hr/hrAppraisalPoint/?repage";
	}

}
