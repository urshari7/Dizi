package com.dizisign.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dizisign.model.pdf.PDFDocument;

@Repository
public interface PdfDAO  extends CrudRepository<PDFDocument,Long>{

}
