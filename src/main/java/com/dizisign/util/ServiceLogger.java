package com.dizisign.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLogger {

	private static Logger logger = LoggerFactory.getLogger("SERVICE_LOGGER");
    
	public static void debug(String data){
		logger.debug(data);
	}
	
	public static void error(String msg, Throwable exception){
		logger.error(msg, exception);
	}
}
