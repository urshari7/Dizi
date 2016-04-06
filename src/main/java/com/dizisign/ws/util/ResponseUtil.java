package com.dizisign.ws.util;

import com.dizisign.ws.jersey.RESTResponse;

public class ResponseUtil{

	public static RESTResponse prepareRESTResponse(ResponseStatus status,Object payload,String errorMessage){
		if (status == null){
			return null;
		}
		RESTResponse response = new RESTResponse();
		switch(status){
			case success:
						response.setStatus(status);
						response.setData(payload);
						response.setMessage(null);
						break;
			case failed:
						response.setStatus(status);
						response.setData(errorMessage);
						response.setMessage(null);
						break;
			case error:
						response.setStatus(status);
						response.setData(null);
						response.setMessage(errorMessage);
						break;
		}
		return response;
	}
}
