package com.arumugamakash.interviewpanelmanagement.login;

import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.candidatemanagement.CandidateSetupView;
import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;

public class LoginView {
	Scanner sc = new Scanner(System.in);
	private LoginModel loginModel;
	private CompanySetupView companySetupView;

	public LoginView() {
		loginModel = new LoginModel(this);
		companySetupView = new CompanySetupView();
	}

	public void init() {
		System.out.println("--------------" + InterviewPanelManagement.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "(" + InterviewPanelManagement.getInstance().getAppVersion()
				+ ")");
		try {
			while (true) {
				System.out.println("\nWelcome to Home page\n");
				System.out.println("\n1.Admin   2.Interviewer   3.Exit\n");
				System.out.println("Enter Your choice");
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					loginModel.init();
					break;
				case 2:
					loginModel.init();
					break;
				case 3:
					System.out.println("Thank you for Using this Application");
					return;
				}
			}

		} catch (

		Exception e) {
			System.out.println("Input MissMatch..try again1sdfghjk");
			sc.nextLine();
			init();
		}
	}

	// --------------SET USERADMIN CREDENTIALS--------------
	public void initiateSetup() {
		Credentials credentials = new Credentials();
		credentials.setUserName("Akash");
		credentials.setPassword("zoho");
		loginModel.createCredentials(credentials);
	}

	// ---------------------CREDENTIALS DETAILS FROM USER-------------------//
	public void startLogin() {
		System.out.println("Enter the UserName");
		String userName = sc.nextLine();
		System.out.println("Enter the Password");
		String password = sc.nextLine();
		loginModel.validateAdmin(userName, password);
	}

	public void showAlart(String string) {
		System.out.println(string);

	}

	public void loginSuccess(String string) {
		loginModel.readDatas();
		System.out.println(string);
		companySetupView.init();
	}

	public void interviewerLoginSuccess(String userName) {
		loginModel.readDatas();
		System.out.println("\n-----------Login SucessFul--------\n");
		companySetupView.interviewerSetUpComplete(userName);
	}

}
