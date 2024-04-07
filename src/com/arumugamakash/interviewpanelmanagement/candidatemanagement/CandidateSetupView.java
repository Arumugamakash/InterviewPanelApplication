package com.arumugamakash.interviewpanelmanagement.candidatemanagement;

import java.util.Scanner;
import com.arumugamakash.interviewpanelmanagement.interviewermanagement.InterviewerManageView;
import com.arumugamakash.interviewpanelmanagement.model.Validation;

public class CandidateSetupView {
	Scanner sc = new Scanner(System.in);
	private CandidateSetupModel candidateSetupModel;
	private InterviewerManageView interviewerManageView;
	private Validation validate;

	public CandidateSetupView() {
		candidateSetupModel = new CandidateSetupModel(this);
		interviewerManageView = new InterviewerManageView();
		validate = new Validation();
	}

	public void addCandidateDetails() {
		try {
			System.out.println("Enter Candidate Name");
			String candidateName = sc.nextLine();
			System.out.println("Enter Candidate Phone Number");
			long phoneNumber = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter Candidate Mail ID");
			String mailId = sc.nextLine();
			if (validate.validateEmail(mailId) && validate.validatePhoneNumber(phoneNumber)) {
				System.out.println("Enter Candidate Skill");
				String skill = sc.nextLine();
				System.out.println("Enter Candidate Location");
				String location = sc.nextLine();
				candidateSetupModel.setCandidateDetails(candidateName, phoneNumber, mailId, skill, location);
			} else {
				System.out.println("Invalid Mail Id or Phone Number...try Again");
				addCandidateDetails();
			}

		} catch (Exception e) {
			System.out.println("Intput Miss Match Try again...");
			sc.nextLine();
			addCandidateDetails();
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

	public void showCandidates() {
		candidateSetupModel.showCandidates();
	}

	public void pendingCandidates() {
		candidateSetupModel.pendingCandidatesDetails();
	}

	public void alart(String msg) {
		System.out.println(msg);
	}

}
