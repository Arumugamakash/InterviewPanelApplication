package com.arumugamakash.interviewpanelmanagement.login;

import java.util.List;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Validation;

public class LoginModel {
	public LoginView loginView;
	private Credentials credentials;
	public Validation validate;

	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	// ------------INSERT_CREDENTIALS---------//
	public void init() {
		if (credentials == null) {
			loginView.initiateSetup();
		} else {
			loginView.startLogin();
		}

	}

	// ----------CREATE AND ADD CREDENTIALS TO DATABASE-----------//
	public void createCredentials(Credentials credentials) {
		InterViewPanelDatabase.getInstance().addCredentials(credentials);
		loginView.startLogin();
	}

	// ------------TO CHECK CREDENTIALS VALID OR INVALID---------//
	public void validateUser(String name, String password) {
		readDatas();
		validate = new Validation();
		if (validate.isValidAdminName(name) && validate.isValidPassword(password)) {
			loginView.adminLoginSuccess("\n\tLogin Successful\n");
		} else if (!InterViewPanelDatabase.getInstance().viewInterviewerCredentials().isEmpty()) {

			List<Credentials> credentialsList = InterViewPanelDatabase.getInstance().viewInterviewerCredentials();
			boolean flag = true;
			for (Credentials credentials : credentialsList) {
				if (credentials.getUserName().equals(name) && credentials.getPassword().equals(password)) {
					loginView.interviewerLoginSuccess(credentials.getUserName());
					flag = false;
					break;
				}
			}
			if (flag) {
				loginView.showAlart("\n\t\tInvalid Username or Passwordd\n");
				loginView.startLogin();
			}
		} else {
			loginView.showAlart("\n\t\tInvalid Username or Password\n");
			loginView.startLogin();
		}
	}

	public void readDatas() {
		InterViewPanelDatabase.getInstance().readAllDatas();
	}

}
