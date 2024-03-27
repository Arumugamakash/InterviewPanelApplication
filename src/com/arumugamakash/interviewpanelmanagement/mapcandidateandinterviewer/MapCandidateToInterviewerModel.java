package com.arumugamakash.interviewpanelmanagement.mapcandidateandinterviewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;

public class MapCandidateToInterviewerModel {
	Scanner sc = new Scanner(System.in);
	private MapCandidateToInterviewerView mapCandidateToInterviewerView;

	public MapCandidateToInterviewerModel(MapCandidateToInterviewerView mapCandidateToInterviewerView) {
		this.mapCandidateToInterviewerView = mapCandidateToInterviewerView;
	}

	public boolean checkInterviewer(String InterviewerName, String candidateName) {
		List<Interviewer> interviewerList = InterViewPanelDatabase.getInstance().showInterViewersDetails();
//		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
		for (Interviewer interviewer : interviewerList) {
			System.out.println(interviewer.getInterviewerName());
			if (interviewer.getInterviewerName().equals(InterviewerName)) {
				if (interviewer.getStatus().equals("free") && checkCandidateAllocate(candidateName)) {
					interviewer.setStatus("Allocated");
					return true;
				} else {
					mapCandidateToInterviewerView.showAlert("Interviewer " + InterviewerName + " is not Free");
					mapCandidateToInterviewerView.mapCandidateInterviewer();
				}
			}
		}
		// mapCandidateToInterviewerView.showAlert("Invalid InterviewerName..TryAgain");
		return false;
	}

	public boolean checkCandidateAllocate(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
		for (Candidates candidates : candidateList) {
			System.out.println(candidates.getCandidateName());
			if (candidates.getCandidateName().equals(name)) {
				candidates.setAllocate("Yes");
				return true;
			}
		}
		return false;
	}

	public boolean checkCandidate(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(name)) {
				if (candidates.getAllocate().equals("No")) {
					return true;
				} else {
					System.out.println("Candidate Already Allocated");
//					mapCandidateToInterviewerView.mapCandidateInterviewer();
					new CompanySetupView().onSetupComplete(null);
				}
			}
		}

		//mapCandidateToInterviewerView.showAlert("Invalid Candidate...try Again");
		// mapCandidateToInterviewerView.mapCandidateInterviewer();
		return false;
	}

	public void mappingCandidateDetails(String interviewerName, String CandidateName) {
		Map<String, String> mapInterviewerToCandidate = new HashMap();
		mapInterviewerToCandidate.put(interviewerName, CandidateName);
		mapCandidateToInterviewerView.showAlert("\nMapping candidate To Interviewer added successfully\n");
		InterViewPanelDatabase.getInstance().addMapingCanditatesDetails(mapInterviewerToCandidate);
//		mapCandidateToInterviewerView.mapCandidateInterviewer();
	}
	public void showMappingDetails() {
		List<Map<String, String>> mapCandidates = InterViewPanelDatabase.getInstance().showMappingCandidatesDetails();
		int i = 1;
		for (Map<String, String> map : mapCandidates) {
			for (Map.Entry m : map.entrySet()) {
				System.out.println(i++ + "." + m.getKey() + "  - " + m.getValue());
			}
		}
	}

	public String m1(String name) {
		List<Map<String, String>> mapCandidates = InterViewPanelDatabase.getInstance().showMappingCandidatesDetails();
		for (Map<String, String> map : mapCandidates) {
			Object obj = name;
			System.out.println(obj);
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}
		return null;
	}
	public void ratingCandidate(String name) {
		String candidateName = m1(name);
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
		List<Interviewer>interviewerList=InterViewPanelDatabase.getInstance().showInterViewersDetails();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(candidateName)) {
				System.out.println("provide the Rating of "+candidates.getCandidateName());
				candidates.setRating(sc.nextDouble());
				InterViewPanelDatabase.getInstance().addSelectedCandidates(candidates);
				for (Interviewer interviewer : interviewerList) {
					if(interviewer.getInterviewerName().equals(name)) {
						interviewer.setStatus("free");
					}
				}
				mapCandidateToInterviewerView.showAlert("\nRating provide Succesfully\n");
			}
		}
	}
}
