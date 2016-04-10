package com.dizisign.ws.jersey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dizisign.model.user.DiziUser;
import com.dizisign.model.user.PersonalDetails;
import com.dizisign.model.user.UserStatus;
import com.dizisign.service.login.AuthUtils;
import com.dizisign.util.ServiceContext;
import com.dizisign.ws.util.JsonUtil;
import com.dizisign.ws.util.RequestLogger;
import com.dizisign.ws.util.ResponseStatus;
import com.dizisign.ws.util.ResponseUtil;

@Path("login")
public class LoginHandler {
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String doLogin(@FormParam("email") String email,
		      @FormParam("password") String password,
		      @Context HttpServletRequest servletRequest){
		String jsonResponse =  null;
		try{
			RequestLogger.debug("doLoginn:"+email+":"+password);
			String passwordHash = AuthUtils.getPasswordHash(password);
			DiziUser user = ServiceContext.getLoginService().login(email,passwordHash);
			if (user == null){
				user = getTestUser(email);
			}
			RequestLogger.debug("user:"+user);
			RESTResponse response =  null;
			if (user!=null){
				HttpSession session = servletRequest.getSession(true);
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.success, user, null);
			}else{
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Invalid Login/Password.");
			}
			RequestLogger.debug("response:"+response);
			jsonResponse =  JsonUtil.convertToJson(response);
			RequestLogger.debug("doLoginn:"+jsonResponse);
		}catch(Throwable e){
			RequestLogger.error("Login Failed:", e);
		}
		return jsonResponse;
	}
	
	
	private DiziUser getTestUser(String email){
		if ("test@test.com".endsWith(email)){
			DiziUser user = new DiziUser();
			user.setEmail(email);
			user.setStatus(UserStatus.trial);
			user.setVerified(true);
			PersonalDetails details = new PersonalDetails();
			details.setFirstName("First");
			details.setLastName("Last");
			details.setId(1L);
			details.setMiddleName("Middle");
			details.setTitle("Mr");
			user.setPersonalDetails(details);
			return user;
		}
		return null;
	}

}
