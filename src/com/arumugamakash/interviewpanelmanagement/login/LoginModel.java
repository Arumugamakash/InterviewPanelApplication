package com.arumugamakash.interviewpanelmanagement.login;

import java.util.List;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;

public class LoginModel {
	public LoginView loginView;
	private Credentials credentials;

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
	public void validateAdmin(String name, String password) {
		if (isValidAdminName(name) && isValidPassword(password)) {
			loginView.loginSuccess("---------Login Successful-----------");
		} else {
			if (!InterViewPanelDatabase.getInstance().viewInterviewerCredentials().isEmpty()) {
				List<Credentials> credentialsList = InterViewPanelDatabase.getInstance().viewInterviewerCredentials();
				for (Credentials credentials : credentialsList) {
					if (credentials.getUserName().equals(name) && credentials.getPassword().equals(password)) {
						loginView.interviewerLoginSuccess(credentials.getUserName());
					}
				}
			} else {
				loginView.showAlart("\t\tInvalid Username or Password");
				loginView.startLogin();
			}
		}
	}

	private boolean isValidAdminName(String userName) {
		return userName.equals(InterViewPanelDatabase.getInstance().getCredentials().getUserName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(InterViewPanelDatabase.getInstance().getCredentials().getPassword());
	}

	public void readDatas() {
		InterViewPanelDatabase.getInstance().readAllDatas();
	}

}
