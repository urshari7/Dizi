package com.dizisign.model.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PersonalDetails {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dob;
	private String gender;
	private String ssn;
	/*@OneToOne(mappedBy="personalDetails")
	private DiziUser user;
	*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/*public DiziUser getUser() {
		return user;
	}
	public void setUser(DiziUser user) {
		this.user = user;
	}
	*/
	@Override
	public String toString() {
		return "PersonalDetails [id=" + id + ", title=" + title
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", ssn=" + ssn + "]";
	}
	
	
}
