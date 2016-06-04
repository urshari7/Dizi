package com.dizisign.ws.jersey;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dizisign.model.user.ContactInfo;
import com.dizisign.model.user.DiziUser;
import com.dizisign.model.user.PersonalDetails;
import com.dizisign.model.user.UserRole;
import com.dizisign.service.login.AuthUtils;
import com.dizisign.util.ServiceContext;
import com.dizisign.ws.util.JsonUtil;
import com.dizisign.ws.util.RequestLogger;
import com.dizisign.ws.util.ResponseStatus;
import com.dizisign.ws.util.ResponseUtil;

@Path("signup")
public class RegisterAccountHandler {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String doRegister(@BeanParam User user,
		      @Context HttpServletRequest servletRequest){
		String jsonResponse =  null;
		try{
			
			System.err.println("In the registration block");
			System.err.println(user.toString());
			RequestLogger.debug("register :"+user);
			String passwordHash = AuthUtils.getPasswordHash(user.getPassword());
			
			DiziUser diziUser = new DiziUser();
			ContactInfo contactInfo =new ContactInfo();
			contactInfo.setAddress(user.getAddress());
			contactInfo.setAddress2(user.getAddress2());
			contactInfo.setCountry(user.getCountry());
			contactInfo.setFax("");
			contactInfo.setMobile(user.getMobile());
			contactInfo.setPhone("");
			contactInfo.setState(user.getProvince());
			
			diziUser.setContactInfo(contactInfo );
			diziUser.setEmail(user.getEmail());
			diziUser.setPasswordHash(passwordHash);
			
			PersonalDetails personalDetails = new PersonalDetails();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
//			personalDetails.setDob(sdf.parse(user.getDob()));
			personalDetails.setFirstName(user.getFirstName());
			personalDetails.setGender(user.getGender());
			personalDetails.setLastName(user.getLastName());
			personalDetails.setMiddleName("");
			personalDetails.setSsn("");
			personalDetails.setTitle("MR");
			diziUser.setPersonalDetails(personalDetails);
			
			ServiceContext.getRegisterAccountService().register(diziUser);
			
			
//			diziUser.setRole(UserRole);
//			diziUser.setStatus("ACTIVE");s
			diziUser.setVerified(false);
			/*RequestLogger.debug("doLoginn:"+email+":"+password);
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
			RequestLogger.debug("doLoginn:"+jsonResponse);*/
		}catch(Throwable e){
			e.printStackTrace();
			RequestLogger.error("Login Failed:", e);
		}
		return jsonResponse;
	}
	
}
