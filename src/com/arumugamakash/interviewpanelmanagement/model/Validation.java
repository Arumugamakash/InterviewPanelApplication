package com.arumugamakash.interviewpanelmanagement.model;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;

public class Validation {
	public boolean isValidAdminName(String userName) {
		return userName.equals(InterViewPanelDatabase.getInstance().getCredentials().getUserName());
	}

	public boolean isValidPassword(String password) {
		return password.equals(InterViewPanelDatabase.getInstance().getCredentials().getPassword());
	}

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
