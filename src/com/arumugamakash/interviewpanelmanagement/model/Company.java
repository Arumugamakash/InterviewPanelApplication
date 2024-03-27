package com.arumugamakash.interviewpanelmanagement.model;

public class Company {
	private String companyName;
	private String companyMailId;
	private long contact;
	private String companyAddress;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyMailId() {
		return companyMailId;
	}

	public void setCompanyMailId(String companyMailId) {
		this.companyMailId = companyMailId;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
