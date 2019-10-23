package com.thinkgem.jeesite.test;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;

public class Act {
	private ProcessEngine processEngine;
	private RuntimeService runtimeService;
	TaskService taskservice;
	FormService formservice;
	HistoryService historyservice;
	
	
	@Before
	public void before()
	{
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/jeesite1?useUnicode=true&characterEncoding=utf-8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123456");
		processEngine = processEngineConfiguration.buildProcessEngine();
		runtimeService=processEngine.getRuntimeService();
		taskservice=processEngine.getTaskService();
		formservice=processEngine.getFormService();
		historyservice=processEngine.getHistoryService();
	}

	@Test
	public void deleteProcess()
	{
		String processInstanceId="cb140b74e34d4764ae6db560a51bb975";
		ProcessInstance pi=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(pi==null)
		{
			historyservice.deleteHistoricProcessInstance(processInstanceId);
		}
		else
		{
			runtimeService.deleteProcessInstance(processInstanceId, "");
			historyservice.deleteHistoricProcessInstance(processInstanceId);
		}
	}
	@Test
	public void deleteAllProcessDefinition()
	{
		List<Deployment> deployments=processEngine.getRepositoryService().createDeploymentQuery().list();
		for(Deployment deployment : deployments)
		{
			System.out.println("delete Process:"+deployment.getName()+",id:"+deployment.getId());
			processEngine.getRepositoryService().deleteDeployment(deployment.getId(), true);
		}
		
	}
}
