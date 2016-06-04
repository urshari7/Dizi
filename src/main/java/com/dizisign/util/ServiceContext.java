package com.dizisign.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dizisign.service.login.LoginService;
import com.dizisign.service.login.RegisterAccountService;

public class ServiceContext {

	private static ServiceContext context = new ServiceContext();
	private ApplicationContext springContext = null;
	
	private ServiceContext(){
		this.springContext = new ClassPathXmlApplicationContext("applicationContext_dizisign.xml");
	}
	
	private static ServiceContext getContext(){
		return context;
	}
	
	public ApplicationContext getSpringContext() {
		return springContext;
	}

	public static LoginService getLoginService(){
		return (LoginService) getContext().getSpringContext().getBean("loginService");
	}
	
	public static RegisterAccountService getRegisterAccountService(){
		return (RegisterAccountService) getContext().getSpringContext().getBean("registerAccountService");
	}
}
