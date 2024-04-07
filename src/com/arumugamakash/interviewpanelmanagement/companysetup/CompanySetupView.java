package com.arumugamakash.interviewpanelmanagement.companysetup;

import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.candidatemanagement.CandidateSetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
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
	}

	// get the company Details
	public void init() {
		companySetupModel.startCompanySetup();
	}

	// GET THE COMPANY DETAILS
	public void setupInitialize() {
		try {
			validate = new Validation();
			System.out.println("\n--------Enter the Company Details---------\n");
			System.out.println("Enter the Company Name");
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
			System.out.println("InputMissMatch...TryAgain");
			sc.nextLine();
			setupInitialize();
		}
	}

	public void onSetupComplete(Company company) {
		try {
			while (true) {
				System.out.println(
						"\n\n\tApplication Features\n\n\t1.Add Candidate Details \n\t2.View All Candidates \n\t3.Add Interviewers \n\t4.View All Interviewers\n\t5.Schedule Interview\n\t6.View Scheduled Candidates\n\t7.Pending Candidates\n\t8.Selected Candidates \n\t9.Logout ");
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
					new InterviewerManageView().addInterviewersDetails();
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
					new CandidateSetupView().pendingCandidates();
					break;
				case 8:
					new MapCandidateToInterviewerView().showSelectCandidate();
					break;
				case 9:
					companySetupModel.writeAll();
					System.out.println("\n---Logout Successful---\n");
					return;
				default:
					System.out.println("\nPlease Enter valid choice\n");
				}
			}
		} catch (Exception e) {
			System.out.println("InputMissMatch...TRryAgain");
			sc.nextLine();
			onSetupComplete(company);

		}

	}

	public void interviewerSetUpComplete(String name) {
		try {
			while (true) {
				System.out.println(
						"\n\n 1.View Candidate Details \n 2.RatingTheCandidates \n 3.Selected Candidates Details\n 4.Logout\n ");
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
					new MapCandidateToInterviewerView().showSelectCandidate();
					break;
				case 4:
					companySetupModel.writeAll();
					System.out.println("\n---Logout Successful---\n");
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("InputMissMatch...TRryAgain");
			sc.nextLine();
			interviewerSetUpComplete(name);
		}
	}

	public void showAlart(String msg) {
		System.out.println(msg);

	}

}
