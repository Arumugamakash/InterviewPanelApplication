package com.arumugamakash.interviewpanelmanagement.companysetup;

import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.candidatemanagement.CandidateSetupView;
import com.arumugamakash.interviewpanelmanagement.interviewermanagement.InterviewerManageView;
import com.arumugamakash.interviewpanelmanagement.mapcandidateandinterviewer.MapCandidateToInterviewerView;
import com.arumugamakash.interviewpanelmanagement.model.Company;
import com.arumugamakash.interviewpanelmanagement.model.Validation;

public class CompanySetupView {
	Scanner sc = new Scanner(System.in);
	private CompanySetupModel companySetupModel;
	private Validation validate;

	public CompanySetupView() {
		companySetupModel = new CompanySetupModel(this);
		validate = new Validation();
	}

	public void init() {
		companySetupModel.startCompanySetup();
	}

	public void setupInitialize() {
		try {
			System.out.println("\n--------------Company Details---------------");
			System.out.println("\nEnter the Company Name");
			String companyName = sc.nextLine();
			System.out.println("Enter the Company Mail ID");
			String mailId = sc.nextLine();
			System.out.println("Enter the Company Contact Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			if (validate.validateEmail(mailId) && validate.validatePhoneNumber(phoneNumber)) {
				System.out.println("Enter the Company Address");
				String address = sc.nextLine();
				companySetupModel.setCompanyDetails(companyName, mailId, phoneNumber, address);
			} else {
				System.out.println("Invalid Mail Id or Phone Number...try Again");
				setupInitialize();
			}
		} catch (Exception e) {
			System.out.println("InputMissMatch...TRryAgain");
			sc.nextLine();
			setupInitialize();
		}
	}

	public void onSetupComplete(Company company) {
		while (true) {
			System.out.println(
					"\nApplication Features\n \n 1.Add Candidate Details \n 2.View Candidates \n 3.Add Interviewer Details \n 4.show Interviewer Details\n 5.Map Candidate and Interviewer \n 6.ShowMappingDetails \n 7.Selected Candidates \n 8.Exit ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				new CandidateSetupView().addCandidateDetails();
				break;
			case 2:
				new CandidateSetupView().showCandidates();
				break;
			case 3:
				new InterviewerManageView().getInterviewersDetails();
				break;
			case 4:
				new InterviewerManageView().showInterviewersDetails();
				break;
			case 5:
				new MapCandidateToInterviewerView().mapCandidateInterviewer();
				break;
			case 6:
				new MapCandidateToInterviewerView().showMappingDetails();
				break;
			case 7:
				new InterviewerManageView().selectedcandidate();
				break;
			case 8:
				System.out.println(
						"\n-- Thanks for using " + InterviewPanelManagement.getInstance().getAppName() + " --");
				return;
			default:
				System.out.println("\nPlease Enter valid choice\n");
			}
		}

	}

	public void interviewerSetUpComplete(String name) {
		while (true) {
			System.out.println(
					"\n\n 1.View Candidate Details \n 2.RatingTheCandidates \n 3.Selected Candidates Details\n 4.Rejected Candidate Details \n 5.Exit\n ");
			System.out.println("\nEnter your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				new MapCandidateToInterviewerView().mapViewCandidateDetails(name);
				break;
			case 2:
				new MapCandidateToInterviewerView().ratingCandidate(name);
				break;
			case 3:
				new InterviewerManageView().selectedcandidate() ;
				break;
			case 4:
				new InterviewerManageView().showInterviewersDetails();
				break;
			case 5:
				return;
			}
		}
	}

}
