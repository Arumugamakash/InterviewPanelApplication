package com.arumugamakash.interviewpanelmanagement.candidatemanagement;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;

public class CandidateSetupModel {
	private CandidateSetupView candidateSetupView;
	private Candidates candidates;

	public CandidateSetupModel(CandidateSetupView candidateSetupView) {
		this.candidateSetupView = candidateSetupView;
	}

	public void setCandidateDetails(String candidateName, long phoneNumber, String mailId, String skill,
			String location, int no) {
		candidates = new Candidates();
		candidates.setCandidateName(candidateName);
		candidates.setCandidatePhoneNumber(phoneNumber);
		candidates.setMailId(mailId);
		candidates.setSkill(skill);
		candidates.setLocation(location);
		candidates.setCandidateNo(no);
		if (InterViewPanelDatabase.getInstance().addCandidatesDetails(candidates)) {
			candidateSetupView.onUserAdded();
		} else {
			candidateSetupView.onUserExist();

		}
	}

}
