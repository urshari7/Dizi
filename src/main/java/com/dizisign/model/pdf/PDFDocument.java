package com.dizisign.model.pdf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PDFDocument {

	@Id
    @GeneratedValue
    private Long id;
	private String name;
	private String path;
	private String ownerEmail;
	private Date uploadedDate;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	@Override
	public String toString() {
		return "PDFDocument [id=" + id + ", name=" + name + ", path=" + path
				+ ", ownerEmail=" + ownerEmail + ", uploadedDate="
				+ uploadedDate + "]";
	}
	
}
