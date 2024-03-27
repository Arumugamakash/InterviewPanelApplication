package com.arumugamakash.interviewpanelmanagement.login;

import java.util.List;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;

public class LoginModel {
	public LoginView loginView;
	private Credentials credentials;

	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
		//credentials = new Credentials();
	}
	//------------INSERT_CREDENTIALS---------//
	public void init() {
		if (credentials == null) {
			loginView.initiateSetup();
		} else {
			loginView.startLogin();
		}

	}
	//----------CREATE AND ADD CREDENTIALS TO DATABASE-----------//
	public void createCredentials(Credentials credentials) {
		InterViewPanelDatabase.getInstance().addCredentials(credentials);
		loginView.startLogin();
	}
	
	//------------TO CHECK CREDENTIALS VALID OR INVALID---------//
	public void validateAdmin(String name, String password) {
		if(isValidAdminName(name)&&isValidPassword(password)) {
			loginView.loginSuccess("---------Login Successful-----------");
		}
		else {
			System.out.println(InterViewPanelDatabase.getInstance().viewInterviewerCredentials());
			if(!InterViewPanelDatabase.getInstance().viewInterviewerCredentials().isEmpty()) {
				List<Credentials>credentialsList=InterViewPanelDatabase.getInstance().viewInterviewerCredentials();
				for (Credentials credentials : credentialsList) {
					if (credentials.getUserName().equals(name) && credentials.getPassword().equals(password)) {
						loginView.interviewerLoginSuccess(credentials.getUserName());
					}
				}
			}
			 else {
				 System.out.println("Else 2");
					loginView.showAlart("\t\tInvalid Username or Password");
					loginView.startLogin();
				}
		}
//		if (isValidAdminName(adminName)) {
//			if (isValidPassword(password)) {
//				loginView.loginSuccess("---------Login Successful-----------");
//			} else {
//				loginView.showAlart("Invalid Password try again...");
//				loginView.init();;
//				
//			}
//		} else {
//			loginView.showAlart("Invalid UserName try again...");
//			loginView.init();
//		}
	}

	private boolean isValidAdminName(String userName) {
		return userName.equals(InterViewPanelDatabase.getInstance().getCredentials().getUserName());
	}

	private boolean isValidPassword(String password) {
		return password.equals(InterViewPanelDatabase.getInstance().getCredentials().getPassword());
	}
	

}
