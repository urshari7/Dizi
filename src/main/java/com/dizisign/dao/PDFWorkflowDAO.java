package com.dizisign.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dizisign.model.pdf.PDFWorkflowDocument;

@Repository
public interface PDFWorkflowDAO  extends CrudRepository<PDFWorkflowDocument,Long>{

}
