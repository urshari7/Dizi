package com.dizisign.service.pdf.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class DiskStore extends PDFStore{
	
	private String baseLocation;
	private String fileSeparator;
	
	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String save(String fileName,Long userID,InputStream uploadedFileStream) throws Exception{
		String fileLocation = baseLocation + userID + fileSeparator + fileName;  
		//saving file
		FileOutputStream out = null;
		try {
			File userDirectory = new File(baseLocation + userID);
			if (!userDirectory.exists()){
				boolean created = userDirectory.mkdir();
				if (!created) throw new Exception("Couldnt create directory:"+baseLocation + userID);
			}
			out = new FileOutputStream(new File(fileLocation));
			int read = 0;  
			byte[] bytes = new byte[1024];  
			while ((read = uploadedFileStream.read(bytes)) != -1) {  
				out.write(bytes, 0, read);  
			}  
			out.flush();  
			out.close();
		} catch (IOException e) {
			throw e;
		}  finally{
			if (out!=null){
				try {
					out.flush();
				} catch (IOException e) {
					throw e;
				}  
				try {
					out.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return fileLocation;
	}

}
