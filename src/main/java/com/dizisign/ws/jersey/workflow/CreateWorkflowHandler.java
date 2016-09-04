package com.dizisign.ws.jersey.workflow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dizisign.model.pdf.PDFDocument;
import com.dizisign.model.pdf.PDFWorkflowDocument;
import com.dizisign.model.user.DiziUser;
import com.dizisign.model.workflow.Workflow;
import com.dizisign.model.workflow.WorkflowEntry;
import com.dizisign.model.workflow.WorkflowStatus;
import com.dizisign.util.ServiceContext;
import com.dizisign.ws.jersey.RESTResponse;
import com.dizisign.ws.request.ParticipantEntry;
import com.dizisign.ws.request.WorkflowParticipantsRequest;
import com.dizisign.ws.util.JsonUtil;
import com.dizisign.ws.util.RequestLogger;
import com.dizisign.ws.util.ResponseStatus;
import com.dizisign.ws.util.ResponseUtil;
import com.dizisign.ws.util.SessionConstants;


@Path("workflow")
public class CreateWorkflowHandler {
	
	
	@POST
	@Path("/saveParticipants")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String saveParticipants(
			  @FormParam("participants") String participants,
			  @Context HttpServletRequest servletRequest){
		RequestLogger.debug("participants request:"+participants);
		String jsonResponse =  null;
		RESTResponse response =  null;

		Object temp = servletRequest.getSession().getAttribute(SessionConstants.USER_ID);
		RequestLogger.debug("userID in Session:"+temp);
		Long userID = temp!=null?(Long)temp:null;

		if (userID != null){
			temp = servletRequest.getSession().getAttribute(SessionConstants.WORKFLOW_DOC);
			RequestLogger.debug("PDFDocument in Session:"+temp);
			PDFDocument doc = temp!=null?(PDFDocument)temp:null;
			if (doc!=null && doc.getId()!=null && doc.getId()>0) {
				temp = servletRequest.getSession().getAttribute(SessionConstants.WORKFLOW_ID);
				Long workflowID = temp!=null?(Long)temp:null;
				
				if (workflowID!=null){
					Workflow workflow = ServiceContext.getWorkflowService().fetch(workflowID);
					PDFWorkflowDocument workflowDoc = ServiceContext.getPDFWorkflowService().save(workflowID, doc);
					workflow.setPdfWorkflowDocumentID(workflowDoc.getId());
					ServiceContext.getWorkflowService().update(workflow);
				}else{
					Workflow workflow = new Workflow();
					workflow.setOwnerUserID(userID);
					workflow.setStatus(WorkflowStatus.PENDING_FILLED);
					workflow.setStartDate(new Date()); //TODO
					RequestLogger.debug("converting from json");
					ParticipantEntry[] entries = (ParticipantEntry[])JsonUtil.convertFromJson(participants, ParticipantEntry[].class);
					RequestLogger.debug("request object:"+entries);
					if (entries!=null && entries.length>0){
						//List<ParticipantEntry> entries = request.getParticipants();
						WorkflowEntry currentWorkflowEntry = null;
						for(ParticipantEntry entry:entries){
							WorkflowEntry workflowEntry = new WorkflowEntry();
							workflowEntry.setParticipantEmail(entry.getEmail());
							workflowEntry.setStatus(1);
							workflowEntry.setValidityInDays(0);//TODO
							if (currentWorkflowEntry == null){
								currentWorkflowEntry = workflowEntry;
								workflow.setStartEntry(workflowEntry);
							}else{
								currentWorkflowEntry.setNextEntry(workflowEntry);
								currentWorkflowEntry=workflowEntry;
							}
						}
						workflow = ServiceContext.getWorkflowService().create(workflow);
						PDFWorkflowDocument workflowDoc = ServiceContext.getPDFWorkflowService().save(workflow.getId(), doc);
						workflow.setPdfWorkflowDocumentID(workflowDoc.getId());
						ServiceContext.getWorkflowService().update(workflow);
						servletRequest.getSession().setAttribute(SessionConstants.WORKFLOW_ID,workflow.getId());
						response =  ResponseUtil.prepareRESTResponse(ResponseStatus.success, workflow.getId(), null);						
					}else{
						response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Parcipants not added.");
					}
				}
				
			} else {
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Document not uploaded.");
			}  
		}else{
			response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "User not authenticated");
		}
		RequestLogger.debug("response:"+response);
		jsonResponse =  JsonUtil.convertToJson(response);
		RequestLogger.debug("saveParticipants-responseJSON:"+jsonResponse);
		return jsonResponse;
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(  
			@FormDataParam("file") InputStream uploadedInputStream,  
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@Context HttpServletRequest servletRequest) {  

		RequestLogger.debug("uploadFile:"+fileDetail.getFileName()+",type:"+fileDetail.getType());
		String jsonResponse =  null;
		RESTResponse response =  null;
		
		Object temp = servletRequest.getSession().getAttribute(SessionConstants.USER_ID);
		RequestLogger.debug("userID in Session:"+temp);
		Long userID = temp!=null?(Long)temp:null;
		
		if (userID != null){
			
			if (fileDetail.getFileName().endsWith(".pdf")){
				PDFDocument doc = ServiceContext.getPDFService().save(fileDetail.getFileName(), userID, uploadedInputStream);
				if (doc!=null && doc.getId()!=null && doc.getId()>0) {
					servletRequest.getSession().setAttribute(SessionConstants.WORKFLOW_DOC,doc);
					servletRequest.getSession().removeAttribute(SessionConstants.WORKFLOW_ID);
					RequestLogger.debug("resetting workflow in session");
					/*
				temp = servletRequest.getSession().getAttribute(SessionConstants.WORKFLOW_ID);
				Long workflowID = temp!=null?(Long)temp:null;
				if (workflowID!=null){
					Workflow workflow = ServiceContext.getWorkflowService().fetch(workflowID);
					PDFWorkflowDocument workflowDoc = ServiceContext.getPDFWorkflowService().save(workflowID, doc);
					workflow.setPdfWorkflowDocumentID(workflowDoc.getId());
					ServiceContext.getWorkflowService().update(workflow);
				}else{
					Workflow workflow = new Workflow();
					PDFWorkflowDocument workflowDoc = ServiceContext.getPDFWorkflowService().save(workflow.getId(), doc);
					workflow.setPdfWorkflowDocumentID(workflowDoc.getId());
					workflow = ServiceContext.getWorkflowService().create(workflow);
					servletRequest.getSession().setAttribute(SessionConstants.WORKFLOW_ID,workflow.getId());
				}
					 */
					response =  ResponseUtil.prepareRESTResponse(ResponseStatus.success, null, null);
				} else {
					response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Problem in uploading file");
				}
			}else{
				response =  ResponseUtil.prepareRESTResponse(ResponseStatus.failed, null, "Please upload only PDF file");
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
