package com.dizisign.ws.request;

public class ParticipantEntry {
       private int order;
       private String email;
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ParticipantEntry [order=" + order + ", email=" + email + "]";
	}
       
       
}
