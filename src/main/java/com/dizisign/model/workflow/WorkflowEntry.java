package com.dizisign.model.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WorkflowEntry {

	@Id
    @GeneratedValue
    private Long id;
	private String participantEmail;
	private int status;  // 1 - pending, 0 - signed
	@OneToOne(cascade=CascadeType.ALL)
	private WorkflowEntry nextEntry;
	private int validityInDays;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParticipantEmail() {
		return participantEmail;
	}
	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public WorkflowEntry getNextEntry() {
		return nextEntry;
	}
	public void setNextEntry(WorkflowEntry nextEntry) {
		this.nextEntry = nextEntry;
	}
	public int getValidityInDays() {
		return validityInDays;
	}
	public void setValidityInDays(int validityInDays) {
		this.validityInDays = validityInDays;
	}
	@Override
	public String toString() {
		return "WorkflowEntry [id=" + id + ", participantEmail="
				+ participantEmail + ", status=" + status + ", nextEntry="
				+ nextEntry + ", validityInDays=" + validityInDays + "]";
	}
	
}
