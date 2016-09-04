package com.dizisign.ws.request;

import java.util.List;

public class WorkflowParticipantsRequest {
	private List<ParticipantEntry> participants;

	public List<ParticipantEntry> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantEntry> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return "WorkflowParticipantsRequest [participants=" + participants
				+ "]";
	}
	
	
}
