package com.arumugamakash.interviewpanelmanagement.interviewermanagement;

import java.util.Iterator;
import java.util.List;

import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;

public class InterviewerManageModel {
	private InterviewerManageView interviewerManageView;

	public InterviewerManageModel(InterviewerManageView interviewerManageView) {
		this.interviewerManageView = interviewerManageView;
	}

	// ADD INTERVIEWERS
	public void setInterviewerDetails(String name, int id, String email, String password, String status) {
		Interviewer interviewer = new Interviewer();
		interviewer.setInterviewerName(name);
		interviewer.setInterviewId(id);
		interviewer.setMailId(email);
		interviewer.setPassword(password);
		interviewer.setStatus(status);

		if (InterViewPanelDatabase.getInstance().addInterViewerDetails(interviewer)) {
			Credentials credentials = new Credentials();
			credentials.setUserName(name);
			credentials.setPassword(password);
			InterViewPanelDatabase.getInstance().addInterviewerCredentials(credentials);
			interviewerManageView.onInterviewerAdded();
		} else {
			interviewerManageView.onInterviewerExist();
		}
	}

	// VIEW INTERVIEWERS
	public void showInterviewersDetails() {
		List<Interviewer> interviewersdetails = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		if (interviewersdetails.size() != 0) {
			System.out.printf("%-20s %-15s", "Interviewer Name", "Status");
			System.out.println();
			for (Interviewer interviewer : interviewersdetails) {
				System.out.printf("%-20s %-15s", interviewer.getInterviewerName(), interviewer.getStatus());
				System.out.println();
			}
		} else {
			interviewerManageView.showAlart("\nNo interviewers added\n");
		}
	}

	public void availableInterviewers() {
		int i = 1;
		List<Interviewer> interviewersList = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		System.out.println("\nAvailable Interviewers\n");
		for (Interviewer Interviewers : interviewersList) {
			if (Interviewers.getStatus().equals("free")) {
				System.out.print("   " + i++ + " " + Interviewers.getInterviewerName()+"  ");
			}
		}
		if (i == 1) {
			interviewerManageView.showAlart("\nNo Availbale Interviewers");
		}
	}
}
