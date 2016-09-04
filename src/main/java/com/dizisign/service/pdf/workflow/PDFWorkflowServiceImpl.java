package com.dizisign.service.pdf.workflow;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.PDFWorkflowDAO;
import com.dizisign.model.pdf.PDFDocument;
import com.dizisign.model.pdf.PDFWorkflowDocument;
import com.dizisign.util.ServiceLogger;

public class PDFWorkflowServiceImpl implements PDFWorkflowService{
	
	@Autowired
	private PDFWorkflowDAO pdfWorkflowDAO;
	
	public PDFWorkflowDAO getPdfWorkflowDAO() {
		return pdfWorkflowDAO;
	}

	public void setPdfWorkflowDAO(PDFWorkflowDAO pdfWorkflowDAO) {
		this.pdfWorkflowDAO = pdfWorkflowDAO;
	}

	@Override
	public PDFWorkflowDocument save(Long workflowID, PDFDocument doc) {
		ServiceLogger.debug(workflowID+":"+doc.toString());
		PDFWorkflowDocument wDoc = new PDFWorkflowDocument();
		wDoc.setPdfDocument(doc);
		wDoc.setWorkflowID(workflowID);
		wDoc = pdfWorkflowDAO.save(wDoc);
		ServiceLogger.debug(wDoc.toString());
		return wDoc;
	}

}
