package com.dizisign.ws.jersey.workflow;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dizisign.model.pdf.PDFDocument;
import com.dizisign.util.ServiceContext;
import com.dizisign.ws.jersey.RESTResponse;
import com.dizisign.ws.util.JsonUtil;
import com.dizisign.ws.util.RequestLogger;
import com.dizisign.ws.util.ResponseStatus;
import com.dizisign.ws.util.ResponseUtil;
import com.dizisign.ws.util.SessionConstants;

@Path("addFields")
public class AddFieldsHandler {

	@GET
	@Path("/getFileInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFileInfo(@Context HttpServletRequest servletRequest) {  

		String jsonResponse =  null;
		RESTResponse response =  null;
		
		Object temp = servletRequest.getSession().getAttribute(SessionConstants.USER_ID);
		RequestLogger.debug("userID in Session:"+temp);
		Long userID = temp!=null?(Long)temp:null;
		
		if (userID != null){
			PDFDocument doc = (PDFDocument) servletRequest.getSession().getAttribute(SessionConstants.WORKFLOW_DOC);
			if (doc!=null && doc.getId()!=null && doc.getId()>0) {
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.success, doc, null);
			} else {
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Problem in uploading file");
			}
		}else{
			response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "User not authenticated");
		}
		RequestLogger.debug("response:"+response);
		jsonResponse =  JsonUtil.convertToJson(response);
		RequestLogger.debug("uploadFile:"+jsonResponse);
		return jsonResponse;
	}  
}
