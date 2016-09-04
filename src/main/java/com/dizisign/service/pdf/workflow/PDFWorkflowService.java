package com.dizisign.service.pdf.workflow;

import com.dizisign.model.pdf.PDFDocument;
import com.dizisign.model.pdf.PDFWorkflowDocument;

public interface PDFWorkflowService {
	
	public PDFWorkflowDocument save(Long workflowID,PDFDocument doc);
}
