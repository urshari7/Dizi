package com.dizisign.ws.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dizisign.util.ServiceContext;

@Path("login")
public class LoginHandler {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String method1() {
        return "LoginSuccessText";
    }

	@GET
    @Produces(MediaType.TEXT_HTML)
    public String method2() {
		boolean loginResponse = ServiceContext.getLoginService().login("abc@abc.com", "abcd");
		System.out.println("LoginResponse::"+loginResponse);
        return "LoginSuccessHTML:"+loginResponse;
    }

}
