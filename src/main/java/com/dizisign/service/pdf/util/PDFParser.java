package com.dizisign.service.pdf.util;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFParser{
	
	private String fileSeparator;
	
	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public int storePDFAsImage(String sourcePDFFilePath) throws Exception{
		try{
			File sourceFile = new File(sourcePDFFilePath);
			String destinationDirectoryPath = sourcePDFFilePath.substring(0, sourcePDFFilePath.lastIndexOf(fileSeparator));
			int numberOfPages = 0;
			File destinationFile = new File(destinationDirectoryPath);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
			}
			if (sourceFile.exists()) {
				PDDocument document = PDDocument.load(sourceFile);
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				String fileName = destinationDirectoryPath+fileSeparator+sourceFile.getName().replace(".pdf", "");             
				int pageCounter = 0;
				numberOfPages = document.getPages().getCount();
				while(pageCounter < numberOfPages) {
				    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
				    ImageIOUtil.writeImage(bim, fileName + "-" + (pageCounter++) + ".png", 300);
				}
				document.close();
			} else {
				throw new Exception(sourceFile.getName() +" File not exists");
			}
			return numberOfPages;
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}
}