package com.dizisign.ws.jersey;

import com.dizisign.ws.util.ResponseStatus;

public class RESTResponse {
	
	private ResponseStatus status;
	private Object data;
	private String message;
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "RESTResponse [status=" + status + ", data=" + data
				+ ", message=" + message + "]";
	}
	
}
