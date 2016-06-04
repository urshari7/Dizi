package com.dizisign.model.pdf;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class PDFWorkflowDocument{

	@Id
    @GeneratedValue
    private Long id;
	private Long workflowID;
	
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private PDFDocument pdfDocument;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkflowID() {
		return workflowID;
	}
	public void setWorkflowID(Long workflowID) {
		this.workflowID = workflowID;
	}
	public PDFDocument getPdfDocument() {
		return pdfDocument;
	}
	public void setPdfDocument(PDFDocument pdfDocument) {
		this.pdfDocument = pdfDocument;
	}
	@Override
	public String toString() {
		return "PDFWorkflowDocument [id=" + id + ", workflowID=" + workflowID
				+ ", pdfDocument=" + pdfDocument + "]";
	}
	
	
}
