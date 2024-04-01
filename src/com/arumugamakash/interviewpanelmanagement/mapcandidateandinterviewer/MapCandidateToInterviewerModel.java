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

	public boolean checkInterviewer(String interviewerName, String candidateName) {
		List<Interviewer> interviewerList = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		for (Interviewer interviewer : interviewerList) {
			if (interviewer.getInterviewerName().equals(interviewerName)) {
				if (interviewer.getStatus().equals("free") && checkCandidateAllocate(candidateName)) {
					String status = "Allocated";
					interviewer.setStatus(status);
//					InterViewPanelDatabase.getInstance().writeInterviewer();
					return true;
				} else {
					mapCandidateToInterviewerView.showAlert("Interviewer " + interviewerName + " is not Free");
					new CompanySetupView().onSetupComplete(null);
				}
			} else {
				mapCandidateToInterviewerView.showAlert("Invalid InterviewerName..TryAgain");
			}
		}
		return false;
	}

	public boolean checkCandidateAllocate(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(name)) {
				candidates.setAllocate(true);
				return true;
			}
		}
		return false;
	}

	public boolean checkCandidate(String name) {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(name)) {
				if (candidates.getCandidateName().equals(name)) {
					if (candidates.getAllocate() == false) {
						return true;
					} else {
						System.out.println("Candidate Already Allocated");
						new CompanySetupView().onSetupComplete(null);
					}
				} else {
					mapCandidateToInterviewerView.showAlert("Invalid CandidateName...try again");
					new CompanySetupView().onSetupComplete(null);
				}
			}
		}

		return false;
	}

	public void mappingCandidateDetails(String interviewerName, String CandidateName) {
		Map<String, String> mapInterviewerToCandidate = new HashMap();
		mapInterviewerToCandidate.put(interviewerName, CandidateName);
		InterViewPanelDatabase.getInstance().addMapingCanditatesDetails(mapInterviewerToCandidate);
		mapCandidateToInterviewerView.showAlert("\nMapping candidate To Interviewer added successfully\n");
	}

	public void showMappingDetails() {
		if (!InterViewPanelDatabase.getInstance().showMappingCandidatesDetails().isEmpty()) {
			List<Map<String, String>> mapCandidates = InterViewPanelDatabase.getInstance()
					.showMappingCandidatesDetails();
			int i = 1;
			for (Map<String, String> map : mapCandidates) {
				for (Map.Entry m : map.entrySet()) {
					System.out.println(i++ + "." + m.getKey() + "  - " + m.getValue());
				}
			}
		} else {
			mapCandidateToInterviewerView.showAlert("CouldNot Arrange the interview");
		}

	}

	public String m1(String name) {// get candidate
		List<Map<String, String>> mapCandidates = InterViewPanelDatabase.getInstance().showMappingCandidatesDetails();
		for (Map<String, String> map : mapCandidates) {
			if (map.containsKey(name)) {
				return map.get(name);
			}
		}
		return null;
	}
	public void ratingCandidate(String name) {
		String candidateName = m1(name);
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		List<Interviewer> interviewerList = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		for (Candidates candidates : candidateList) {
			if (candidates.getCandidateName().equals(candidateName)) {
				System.out.println("provide the Rating of " + candidates.getCandidateName());
				candidates.setRating(sc.nextDouble());
				for (Interviewer interviewer : interviewerList) {
					if (interviewer.getInterviewerName().equals(name)) {
						interviewer.setStatus("free");
						InterViewPanelDatabase.getInstance().writeInterviewer();
						InterViewPanelDatabase.getInstance().remove(name);
					}
				}
				mapCandidateToInterviewerView.showAlert("\nRating provide Succesfully\n");
			}
		}
	}

	public void showSelectedCandidates() {
		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().getAllCandidates();
		for (Candidates candidates : candidateList) {
			if (candidates.getRating() >= 3.5) {
				if (InterViewPanelDatabase.getInstance().addSelectedCandidates(candidates)) {
					mapCandidateToInterviewerView.showAlert("\nAlready Selected\n");
					InterViewPanelDatabase.getInstance().writeSelectedCanidates();
				}
				showSelectCandidate();
			} else {
				mapCandidateToInterviewerView.showAlert("No selected Candidates");
			}
		}
	}

	public void showSelectCandidate() {
		if (!InterViewPanelDatabase.getInstance().showSelectedCandidate().isEmpty()) {
			List<Candidates> selectedCandidate = InterViewPanelDatabase.getInstance().showSelectedCandidate();
			for (Candidates candidates : selectedCandidate) {
				mapCandidateToInterviewerView
						.showAlert("Selected candidateDetails is:" + candidates.getCandidateName());
			}
		} else {
			mapCandidateToInterviewerView.showAlert("No selected Candidates because... Interview not yet to start");
		}

	}

}
