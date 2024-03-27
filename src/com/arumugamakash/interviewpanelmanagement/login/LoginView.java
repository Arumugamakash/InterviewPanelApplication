package com.arumugamakash.interviewpanelmanagement.login;

import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.candidatemanagement.CandidateSetupView;
import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;

public class LoginView {
	Scanner sc = new Scanner(System.in);
	private LoginModel loginModel;
	private CompanySetupView companySetupView;

	public LoginView() {
		loginModel = new LoginModel(this);
		companySetupView=new CompanySetupView();
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
		}catch (Exception e) {
			System.out.println("Input MissMatch..try again");
			sc.nextLine();
			init();
		}

	}
	//--------------SET USERADMIN CREDENTIALS--------------
	public void initiateSetup() {
		Credentials credentials = new Credentials();
		credentials.setUserName("Akash");
		credentials.setPassword("zoho");
		loginModel.createCredentials(credentials);
	}
	//---------------------CREDENTIALS DETAILS FROM USER-------------------//
	public void startLogin() {
		System.out.println("Enter the UserName");
		String userName = sc.nextLine();
		System.out.println("Enter the Password");
		String password = sc.nextLine();
		loginModel.validateAdmin(userName, password);
	}
	
//	public void loginSetup() {
//		System.out.println("Enter your Admin name");
//		String adminName = sc.nextLine();
//		System.out.println("Enter your Password");
//		String password = sc.nextLine();
//		loginModel.validateAdmin(adminName, password);
//	}

	public void showAlart(String string) {
		System.out.println(string);

	}

//	private void initCandidateSetup() {
//		System.out.println("\n Welcome to Interview Panel System");
//		CandidateSetupView candidateView = new CandidateSetupView();
//		candidateView.onSetupComplete();
//
//	}

	public void loginSuccess(String string) {
		System.out.println(string);
		companySetupView.init();
		//initCandidateSetup();
	}

	public void interviewerLoginSuccess(String userName) {
		System.out.println("\n-----------Login SucessFul--------\n");
		companySetupView.interviewerSetUpComplete(userName);
	}

}
