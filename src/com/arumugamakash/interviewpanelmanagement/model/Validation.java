package com.arumugamakash.interviewpanelmanagement.model;

public class Validation {
	public boolean validatePhoneNumber(long phoneNumber) {
		String phoneNo = "" + phoneNumber;
		String regex = "\\d{10}";
		return phoneNo.matches(regex);
	}

	public boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$";
		return email.matches(regex);
	}

}
