package com.dizisign.ws.util;

import com.google.gson.Gson;

public class JsonUtil {

	private static Gson gson = new Gson();
	
	public static String convertToJson(Object src){
		if (src == null) return null;
		return gson.toJson(src);
	}
	
	public static Object convertFromJson(String jsonFormat,Class<?> c){
		try{
			if (jsonFormat == null) return null;
			return gson.fromJson(jsonFormat, c);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
