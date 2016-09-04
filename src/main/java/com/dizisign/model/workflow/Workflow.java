package com.dizisign.model.workflow;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Workflow {

	@Id
    @GeneratedValue
    private Long id;
	private Long ownerUserID;
	private WorkflowStatus status;
	private Date startDate;
	private Long pdfWorkflowDocumentID;
    @OneToOne(cascade=CascadeType.ALL)
	private WorkflowEntry startEntry;
    @OneToOne(cascade=CascadeType.ALL)
	private WorkflowEntry currentyEntry;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOwnerUserID() {
		return ownerUserID;
	}
	public void setOwnerUserID(Long ownerUserID) {
		this.ownerUserID = ownerUserID;
	}
	public WorkflowStatus getStatus() {
		return status;
	}
	public void setStatus(WorkflowStatus status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Long getPdfWorkflowDocumentID() {
		return pdfWorkflowDocumentID;
	}
	public void setPdfWorkflowDocumentID(Long pdfWorkflowDocumentID) {
		this.pdfWorkflowDocumentID = pdfWorkflowDocumentID;
	}
	public WorkflowEntry getStartEntry() {
		return startEntry;
	}
	public void setStartEntry(WorkflowEntry startEntry) {
		this.startEntry = startEntry;
	}
	public WorkflowEntry getCurrentyEntry() {
		return currentyEntry;
	}
	public void setCurrentyEntry(WorkflowEntry currentyEntry) {
		this.currentyEntry = currentyEntry;
	}
	@Override
	public String toString() {
		return "Workflow [id=" + id + ", ownerUserID=" + ownerUserID
				+ ", status=" + status + ", startDate=" + startDate
				+ ", pdfWorkflowDocumentID=" + pdfWorkflowDocumentID
				+ ", startEntry=" + startEntry + ", currentyEntry="
				+ currentyEntry + "]";
	}
	
	
}
