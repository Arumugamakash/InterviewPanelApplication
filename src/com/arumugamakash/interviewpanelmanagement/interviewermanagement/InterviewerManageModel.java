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
}
