package com.thinkgem.jeesite.modules.hr.web;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.modules.hr.entity.HrAppraisalPoint;

public class XX {

	String exameeid;
	public List<HrAppraisalPoint> hrAppraisalPoints=new ArrayList<HrAppraisalPoint>();

	public List<HrAppraisalPoint> getHrAppraisalPoints() {
		return hrAppraisalPoints;
	}

	public void setHrAppraisalPoints(List<HrAppraisalPoint> hrAppraisalPoints) {
		this.hrAppraisalPoints = hrAppraisalPoints;
	}

	public String getExameeid() {
		return exameeid;
	}

	public void setExameeid(String exameeid) {
		this.exameeid = exameeid;
	}
	
}
