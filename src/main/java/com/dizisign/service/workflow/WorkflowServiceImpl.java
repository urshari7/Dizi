package com.dizisign.service.workflow;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.WorkflowDAO;
import com.dizisign.model.workflow.Workflow;
import com.dizisign.util.ServiceLogger;

public class WorkflowServiceImpl implements WorkflowService{

	@Autowired
	private WorkflowDAO workflowDAO;
	
	
	public WorkflowDAO getWorkflowDAO() {
		return workflowDAO;
	}


	public void setWorkflowDAO(WorkflowDAO workflowDAO) {
		this.workflowDAO = workflowDAO;
	}


	@Override
	public Workflow create(Workflow wFlow) {
		Workflow workflow = workflowDAO.save(wFlow);
		ServiceLogger.debug(workflow.toString());
		return workflow;
	}

	@Override
	public Workflow fetch(Long workflowID){
		Workflow workflow =  workflowDAO.findOne(workflowID);
		ServiceLogger.debug(workflow.toString());
		return workflow;
	}
	
	@Override
	public void update(Workflow wFlow) {
	    workflowDAO.save(wFlow);
	}
}
