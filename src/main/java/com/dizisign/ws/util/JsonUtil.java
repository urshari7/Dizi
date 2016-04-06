package com.dizisign.ws.util;

import com.google.gson.Gson;

public class JsonUtil {

	private static Gson gson = new Gson();
	
	public static String convertToJson(Object src){
		if (src == null) return null;
		return gson.toJson(src);
	}
}
