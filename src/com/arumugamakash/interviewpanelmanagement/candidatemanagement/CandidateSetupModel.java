package com.arumugamakash.interviewpanelmanagement.candidatemanagement;

import java.util.List;

import com.arumugamakash.interviewpanelmanagement.InterviewPanelManagement;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;

public class CandidateSetupModel {
	private CandidateSetupView candidateSetupView;
	private Candidates candidates;

	public CandidateSetupModel(CandidateSetupView candidateSetupView) {
		this.candidateSetupView = candidateSetupView;
	}

	// ADD CANDIDATES
	public void setCandidateDetails(String candidateName, long phoneNumber, String mailId, String skill,
			String location) {
		candidates = new Candidates();
		candidates.setCandidateName(candidateName);
		candidates.setCandidatePhoneNumber(phoneNumber);
		candidates.setMailId(mailId);
		candidates.setSkill(skill);
		candidates.setLocation(location);
		candidates.setCandidateId();
		if (InterViewPanelDatabase.getInstance().addCandidatesDetails(candidates)) {
			candidateSetupView.onUserAdded();
		} else {
			candidateSetupView.onUserExist();
		}
	}

	// VIEW ALL CANDIDATES
	public void showCandidates() {
		List<Candidates> candidatesdetails = InterViewPanelDatabase.getInstance().getAllCandidates();
		if (candidatesdetails.size() != 0) {
			System.out.printf("%-17s %-17s %-22s %-19s %-20s %-17s", "Candidate Name", "Candidate Id",
					"Candidate MailId", "phoneNumber", "Skill", "Address");
			System.out.println();

			for (Candidates candidates : candidatesdetails) {
				System.out.printf("%-22s %-12s %-22s %-19s %-20s %-17s", candidates.getCandidateName(),
						candidates.getCandidateId(), candidates.getMailId(), candidates.getCandidatePhoneNumber(),
						candidates.getSkill(), candidates.getLocation());
				System.out.println();
			}
		} else {
			candidateSetupView.alart("No candidates added");
		}
	}

	public void pendingCandidatesDetails() {
		int i = 1;
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		System.out.println("\nPendingCandidates\n");
		for (Candidates candidates : candidateList) {
			if (!candidates.getAllocate()) {
				System.out.print("   " + i++ + " " + candidates.getCandidateName() + "  ");
			}
		}
		if (i == 1) {
			candidateSetupView.alart("\nNo pending Candidates\n");
		}
	}

}
