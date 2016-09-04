package com.dizisign.service.pdf.store;

import java.io.InputStream;

public abstract class PDFStore {

	public abstract String save(String fileName,Long userID,InputStream uploadedFileStream) throws Exception;

}
