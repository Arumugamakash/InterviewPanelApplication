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
		Credentials credentials = new Credentials();
		credentials.setUserName(name);
		credentials.setPassword(password);
		InterViewPanelDatabase.getInstance().addInterviewerCredentials(credentials);
		InterViewPanelDatabase.getInstance().addInterViewerDetails(interviewer);
	}

	public void showSelectedCandidates() {
		List<Candidates> selectCandidates = InterViewPanelDatabase.getInstance().showSelectedCandidate();
		for (Candidates candidates : selectCandidates) {
			if (candidates.getRating() >= 3.5) {
				System.out.println("Selected candidateDetails is:" + candidates.getCandidateName());
			} else {
				System.out.println("No Selected Candidates");
			}
		}
	}
//	public void selectedCandidate() {
//		List<Candidates>candidateList=InterViewPanelDatabase.getInstance().showCandidateDetails();
//		for (Candidates candidates : candidateList) {
//			if(candidates.getRating()>3.5) {
//				List<Candidates>selectedCandidate=InterViewPanelDatabase.getInstance().showCandidateDetails();
//				InterViewPanelDatabase.getInstance().addSelectedCandidates(selectedCandidate);
//			}
//		}
//		
//	}
//	public void showSelectedCandidates() {
//		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
//		for (Candidates candidates : candidateList) {
//			if (candidates.getRating() == 0.0) {
//				System.out.println("akasj");
////				new CompanySetupView().onSetupComplete(null);
//			} else if (candidates.getRating() >= 3.5) {
//				InterViewPanelDatabase.getInstance().addSelectedCandidates(candidates);
//				System.out.println("Selected candidateDetails is:" + candidates.getCandidateName());
////				new CompanySetupView().onSetupComplete(null);
//			} else {
//				System.out.println("No selected candidates...");
////				new CompanySetupView().onSetupComplete(null);
//			}
//
//		}
//			if (candidates1.getRating() == 0.0) {
//				System.out.println("akasj");
//				new CompanySetupView().onSetupComplete(null);
//			} else if (candidates.getRating() >= 3.5) {
//				interviewerManageModel.showSelectedCandidates(candidates);
//				System.out.println("Selected candidateDetails is:" + candidates.getCandidateName());
//				new CompanySetupView().onSetupComplete(null);
//			} 
////				else {
////				System.out.println("No selected candidates...");
////				new CompanySetupView().onSetupComplete(null);
////			}
//		}
//	}
//		InterViewPanelDatabase.getInstance().addSelectedCandidates(candidates);
//		
}

//	public boolean checkInterviewer(String name) {
//		List<Interviewer> interviwerList = InterViewPanelDatabase.getInstance().showInterViewersDetails();
//		for (Interviewer interviewer : interviwerList) {
//			if (interviewer.getInterviewerName().equals(name)) {
//				if( interviewer.getStatus().equals("free")) {
//					interviewer.setStatus("Allocated");
//					return true;
//				}
//				else {
//					interviewerManageView.showAlart("Interviewer "+name+" is not Free");
//				}	
//			}
//			else {
//				interviewerManageView.showAlart("Invalid Interviewer");
//			}
//		}
//		return false;
//	}
//
//	public boolean checkCandidate(String name) {
//		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
//		for (Candidates candidates : candidateList) {
//			if (candidates.getCandidateName().equals(name)) {
//				return true;
//			}
//		}
//		return false;
//	}

//	public void mappingCandidateDetails(String interviewerName, String CandidateName) {
//		Map<String, String>mapInterviewerToCandidate=new HashMap();
//		mapInterviewerToCandidate.put(interviewerName,CandidateName);
//		InterViewPanelDatabase.getInstance().addMapingCanditatesDetails(mapInterviewerToCandidate);
//	}
//	public void showMappingDetails() {
//		List<Map<String, String>>mapCandidates=InterViewPanelDatabase.getInstance().showMappingCandidatesDetails();
//		int i=1;
//		for (Map<String, String> map : mapCandidates) {
//			for (Map.Entry m:map.entrySet()) {
//				System.out.println(i+++"."+m.getKey()+"  - "+m.getValue());
//			}
//		}
//	}
