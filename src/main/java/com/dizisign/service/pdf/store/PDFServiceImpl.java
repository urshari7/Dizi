package com.dizisign.service.pdf.store;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.PdfDAO;
import com.dizisign.model.pdf.PDFDocument;
import com.dizisign.service.pdf.util.PDFParser;
import com.dizisign.util.ServiceLogger;

public class PDFServiceImpl implements PDFService{
	
	private PDFStore store;
	
	@Autowired
	private PdfDAO pdfDAO;
	
	private PDFParser pdfParser;

	public PDFStore getStore() {
		return store;
	}

	public void setStore(PDFStore store) {
		this.store = store;
	}

	public PdfDAO getPdfDAO() {
		return pdfDAO;
	}

	public void setPdfDAO(PdfDAO pdfDAO) {
		this.pdfDAO = pdfDAO;
	}
	
	public PDFParser getPdfParser() {
		return pdfParser;
	}

	public void setPdfParser(PDFParser pdfParser) {
		this.pdfParser = pdfParser;
	}

	@Override
	public PDFDocument save(String fileName,Long userID,InputStream uploadedFileStream) {
		ServiceLogger.debug(fileName+":"+userID);
		try{
			String fileLocation = getStore().save(fileName, userID, uploadedFileStream);
			ServiceLogger.debug("fileLocation:"+fileLocation);
			int numberOfPages = pdfParser.storePDFAsImage(fileLocation);
			ServiceLogger.debug("numberOfPages:"+numberOfPages);
			PDFDocument doc = new PDFDocument();
			doc.setName(fileName);
			doc.setPath(fileLocation);
			doc.setNumberOfPages((short)numberOfPages);
			doc.setOwnerUserID(userID);
			doc.setUploadedDate(new Date());
			PDFDocument savedDoc = getPdfDAO().save(doc);
			ServiceLogger.debug(savedDoc.toString());
			return savedDoc;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	

}
