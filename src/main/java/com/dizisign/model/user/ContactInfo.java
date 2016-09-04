package com.dizisign.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo {

	@Id
	@GeneratedValue
	private Long id;
	private String mobile;
	private String phone;
	private String fax;
	private String address;
	private String address2;
	private String state;
	private String country;
	/*@OneToOne(mappedBy="contactInfo")
	private DiziUser user;*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "ContactInfo [id=" + id + ", mobile=" + mobile + ", phone="
				+ phone + ", fax=" + fax + ", address=" + address
				+ ", address2=" + address2 + ", state=" + state + ", country="
				+ country + "]";
	}
	
	
}
