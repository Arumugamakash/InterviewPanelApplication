package com.arumugamakash.interviewpanelmanagement.interviewermanagement;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;
import com.arumugamakash.interviewpanelmanagement.model.Validation;

public class InterviewerManageView {
	Scanner sc = new Scanner(System.in);
	private InterviewerManageModel interviewerManageModel;
	private Interviewer interviewer;
	private Validation validate;
	public InterviewerManageView() {
		interviewerManageModel = new InterviewerManageModel(this);
		interviewer = new Interviewer();
		validate = new Validation();
	}

	public void getInterviewersDetails() {
		try {
		System.out.println("How many InterViewer in our company");
		int youWant = sc.nextInt();// 3
		sc.nextLine();
			for (int i = 0; i < youWant; i++) {
				System.out.println("Enter the Interviewer Name");
				String name = sc.nextLine();
				System.out.println("Enter the Interviewer Email Id");
				String email = sc.nextLine();
				if (validate.validateEmail(email)) {
					System.out.println("Enter the Password");
					String password = sc.nextLine();
					String status = "free";
					interviewerManageModel.setInterviewerDetails(name, interviewer.getInterviewId(), email, password,
							status);
				}
				
			}
		} catch (Exception e) {
			System.out.println("Intput missMatch...TryAgain");
			getInterviewersDetails();
		}
	}

	public void showInterviewersDetails() {
		List<Interviewer> interviewersdetails = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		if (interviewersdetails.size() == 0) {
			System.out.println("No interviews added");
		} else {
			for (Interviewer interviewer : interviewersdetails) {
				System.out.println(
						"InterViewer Name:" + interviewer.getInterviewerName() + "  -  " + interviewer.getStatus());
			}
		}
	}

	public void showAlart(String string) {
		System.out.println(string);
	}

	public void onInterviewerAdded() {
		System.out.println("\n------- Interviwer added successfully ------- \n");
	}

	public void onInterviewerExist() {
		System.out.println("\n------- Interviwer already Exist ------- \n");

	}
}
