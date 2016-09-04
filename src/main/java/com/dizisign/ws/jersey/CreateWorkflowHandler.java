package com.dizisign.ws.jersey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dizisign.ws.util.JsonUtil;
import com.dizisign.ws.util.RequestLogger;
import com.dizisign.ws.util.ResponseStatus;
import com.dizisign.ws.util.ResponseUtil;


@Path("workflow")
public class CreateWorkflowHandler {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(  
			@FormDataParam("file") InputStream uploadedInputStream,  
			@FormDataParam("file") FormDataContentDisposition fileDetail) {  

		RequestLogger.debug("uploadFile:"+fileDetail.getFileName());
		String jsonResponse =  null;
		RESTResponse response =  null;

		String fileLocation = "C://" + fileDetail.getFileName();  
		//saving file  
		try{
			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;  
			byte[] bytes = new byte[1024];  
			//out = new FileOutputStream(new File(fileLocation));  
			while ((read = uploadedInputStream.read(bytes)) != -1) {  
				out.write(bytes, 0, read);  
			}  
			out.flush();  
			out.close();
			response =  ResponseUtil.prepareRESTResponse(ResponseStatus.success, null, null);
		} catch (IOException e) {
			e.printStackTrace();
			response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Problem in uploading:"+e.getMessage());
		}  
		
		RequestLogger.debug("response:"+response);
		jsonResponse =  JsonUtil.convertToJson(response);
		RequestLogger.debug("uploadFile:"+jsonResponse);
		return jsonResponse;
	}  

}
