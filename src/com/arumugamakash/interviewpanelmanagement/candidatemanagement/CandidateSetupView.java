package com.arumugamakash.interviewpanelmanagement.candidatemanagement;

import java.util.List;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.interviewermanagement.InterviewerManageView;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;

public class CandidateSetupView {
	Scanner sc = new Scanner(System.in);
	private CandidateSetupModel candidateSetupModel;
	private InterviewerManageView interviewerManageView;

	public CandidateSetupView() {
		candidateSetupModel = new CandidateSetupModel(this);
		interviewerManageView = new InterviewerManageView();

	}

//	public void onSetupComplete() {
//
//		// CandidateSetupView candidateSetupView = new CandidateSetupView();
//		while (true) {
//			System.out.println(
//					"\nApplication Features\n \n 1.Add Candidate Details \n 2.View Candidates \n 3.Add Interviewer Details \n 4.show Interviewer Details\n 5.Map Candidate and Interviewer \n6.ShowMappingDetails \n 8.Selected Candidates \n 9.Exit ");
//			System.out.println("\nEnter your Choice");
//			int choice = sc.nextInt();
//			sc.nextLine();
//			switch (choice) {
//			case 1:
//				getCandidateDetails();
//				break;
//			case 2:
//				showCandidates();
//				break;
//			case 3:
//				interviewerManageView.getInterviewersDetails();
//				break;
//			case 4:
//				interviewerManageView.showInterviewersDetails();
//				break;
//			case 5:
//				interviewerManageView.mapCandidateInterviewer();
//				break;
//			case 6:
//				interviewerManageView.showMappingDetails();
//				break;
////				case 6:
////					bookManagementView.updateBook();
////				case 7:
////					userView.deleteUser();
////					break;
////				case 8:
////					userView.viewUsers();
////					break;
//			case 9:
//				System.out.println(
//						"\n-- Thanks for using " + InterviewPanelManagement.getInstance().getAppName() + " --");
//				return;
//			default:
//				System.out.println("\nPlease Enter valid choice\n");
//			}
//		}
//
//	}

	public void addCandidateDetails() {
		try {
			System.out.println("Enter Candidate Name");
			String candidateName = sc.nextLine();
			System.out.println("Enter Candidate Phone Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter Candidate Mail ID");
			String mailId = sc.nextLine();
			System.out.println("Enter Candidate Skill");
			String skill = sc.nextLine();
			System.out.println("Enter Candidate Location");
			String location = sc.nextLine();
			System.out.println("Enter Candidate InterViewOrderNumber");
			int candidateNo=sc.nextInt();
			sc.nextLine();
			candidateSetupModel.setCandidateDetails(candidateName, phoneNumber, mailId, skill, location,candidateNo);
		} catch (Exception e) {
			System.out.println("Intput Miss Match Try again...");
			sc.nextLine();
			addCandidateDetails();
		}
	}

	public void showCandidates() {
		List<Candidates> candidatesdetails = InterViewPanelDatabase.getInstance().showCandidateDetails();
		for (Candidates candidates : candidatesdetails) {
			System.out.println("Candidates Name:-" + candidates.getCandidateName());
		}
	}

	public void onUserAdded() {
		System.out.println("\n------- candidate added successfully ------- \n");
		checkForAddNewCandidate();

	}

	public void onUserExist() {
		System.out.println("\n------- candidate Already Exist ------- \n");
		checkForAddNewCandidate();
	}

	private void checkForAddNewCandidate() {
		System.out.println("Do you want to add more Candidate? \nType Yes/No");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			addCandidateDetails();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding Candidate");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewCandidate();
		}
	}

}
