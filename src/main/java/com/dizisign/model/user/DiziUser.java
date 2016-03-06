package com.dizisign.model.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DiziUser {

	@Id
    @GeneratedValue
    private Long id;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private String status;
    private boolean verified;
    @OneToOne(fetch=FetchType.LAZY)
    private PersonalDetails personalDetails;
    @OneToOne(fetch=FetchType.LAZY)
    private ContactInfo contactInfo;
	
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash="
				+ passwordHash + ", passwordSalt=" + passwordSalt + ", status="
				+ status + ", verified=" + verified + ", personalDetails="
				+ personalDetails + ", contactInfo=" + contactInfo + "]";
	}
    
    
}
