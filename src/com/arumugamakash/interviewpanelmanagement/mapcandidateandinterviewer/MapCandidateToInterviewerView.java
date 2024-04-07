package com.arumugamakash.interviewpanelmanagement.mapcandidateandinterviewer;

import java.util.List;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.candidatemanagement.CandidateSetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.interviewermanagement.InterviewerManageView;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;

public class MapCandidateToInterviewerView {
	Scanner sc = new Scanner(System.in);
	private MapCandidateToInterviewerModel mapCandidateToInterviewerModel;
	private CandidateSetupView candidateSetupView;
	private InterviewerManageView interviewerManageView;

	public MapCandidateToInterviewerView() {
		mapCandidateToInterviewerModel = new MapCandidateToInterviewerModel(this);
		candidateSetupView = new CandidateSetupView();
		interviewerManageView = new InterviewerManageView();
	}

	public void mapCandidateInterviewer() {
		candidateSetupView.pendingCandidates();
		System.out.println();
		interviewerManageView.availableInterviewers();
		System.out.println();

		System.out.println("\nEnter the CandidateName");
		String candidateName = sc.nextLine();
		if (mapCandidateToInterviewerModel.checkCandidate(candidateName)) {
			System.out.println("Enter the InterviewerName");
			String interviewerName = sc.nextLine();
			if (mapCandidateToInterviewerModel.checkInterviewer(interviewerName, candidateName)) {
				mapCandidateToInterviewerModel.mappingCandidateDetails(interviewerName, candidateName);
			}
		} else {
			System.out.println("Candidate Not Found");
		}
	}

	public void mapViewCandidateDetails(String name) {
		if (mapCandidateToInterviewerModel.m1(name) != null) {
			String candidateName = mapCandidateToInterviewerModel.m1(name);
			List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
			for (Candidates candidates : candidateList) {
				if (candidates.getCandidateName().equals(candidateName)) {
					showSearchCandidateDetails(candidateName);
				}
			}
		} else {
			showAlert("Candidates not allowcated to interview");
		}

	}

	public void showSearchCandidateDetails(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s", "Candidate Name", "Candidate Id",
				"Candidate mail Id", "PhoneNumber", "Skill", "Location", "Rating");
		System.out.println();
		for (Candidates candidates : candidateList) {
			if (name.equals(candidates.getCandidateName())) {
				System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s", candidates.getCandidateName(),
						candidates.getCandidateId(), candidates.getMailId(), candidates.getCandidatePhoneNumber(),
						candidates.getSkill(), candidates.getLocation(), candidates.getRating());
				System.out.println();
			}

		}
	}

	public void showMappingDetails() {
		mapCandidateToInterviewerModel.showMappingDetails();
	}

	public void showAlert(String msg) {
		System.out.println(msg);
	}

	public void ratingCandidate(String name) {
		mapCandidateToInterviewerModel.ratingCandidate(name);
	}

	public void showSelectCandidate() {
		mapCandidateToInterviewerModel.showSelectCandidate();

	}
}
