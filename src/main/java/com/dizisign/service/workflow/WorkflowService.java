package com.dizisign.service.workflow;

import com.dizisign.model.workflow.Workflow;

public interface WorkflowService {

	public Workflow create(Workflow workflow);
	
	public Workflow fetch(Long workflowID);
	
	public void update(Workflow workflow);
}
