package com.dizisign.service.pdf.store;

import java.io.InputStream;

import com.dizisign.model.pdf.PDFDocument;

public interface PDFService {

	public PDFDocument save(String fileName,Long userID,InputStream uploadedFileStream);
}
