package com.dizisign.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dizisign.model.workflow.Workflow;

@Repository
public interface WorkflowDAO extends CrudRepository<Workflow, Long> {

}

