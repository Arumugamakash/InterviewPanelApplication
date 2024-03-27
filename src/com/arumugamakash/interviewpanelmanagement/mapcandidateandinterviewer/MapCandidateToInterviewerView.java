package com.arumugamakash.interviewpanelmanagement.mapcandidateandinterviewer;

import java.util.List;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;

public class MapCandidateToInterviewerView {
	Scanner sc = new Scanner(System.in);
	private MapCandidateToInterviewerModel mapCandidateToInterviewerModel;
	private Candidates candidate;
	public MapCandidateToInterviewerView() {
		mapCandidateToInterviewerModel = new MapCandidateToInterviewerModel(this);
	}

	public void mapCandidateInterviewer() {
		System.out.println("Enter the CandidateName");
		String candidateName = sc.nextLine();
		if (mapCandidateToInterviewerModel.checkCandidate(candidateName)) {
			System.out.println("Enter the InterviewerName");
			String interviewerName = sc.nextLine();
			if (mapCandidateToInterviewerModel.checkInterviewer(interviewerName, candidateName)) {
				mapCandidateToInterviewerModel.mappingCandidateDetails(interviewerName, candidateName);
			}
			else {
				System.out.println("Invalid InterviewerName...try again");
//				mapCandidateInterviewer();
			}
		}
		else {
			System.out.println("Invalid CandidateName..TryAgain");
//			mapCandidateInterviewer();
		}
	}

	public void mapViewCandidateDetails(String name) {
		String candidateName = mapCandidateToInterviewerModel.m1(name);
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(candidateName)) {
				showSearchCandidateDetails(candidateName);
			}
		}
	}

	public void showSearchCandidateDetails(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
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
}
