package com.arumugamakash.interviewpanelmanagement.interviewermanagement;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.arumugamakash.interviewpanelmanagement.companysetup.CompanySetupView;
import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;

public class InterviewerManageView {
	Scanner sc = new Scanner(System.in);
	private InterviewerManageModel interviewerManageModel;
	private Interviewer interviewer;

	public InterviewerManageView() {
		interviewerManageModel = new InterviewerManageModel(this);
		interviewer = new Interviewer();
	}

	public void getInterviewersDetails() {
		System.out.println("How many InterViewer in our company");
		int youWant = sc.nextInt();// 3
		sc.nextLine();
		for (int i = 0; i < youWant; i++) {
			System.out.println("Enter the Interviewer Name");
			String name = sc.nextLine();
			System.out.println("Enter the Interviewer Email Id");
			String email = sc.nextLine();
			System.out.println("Enter the Password");
			String password = sc.nextLine();
			// System.out.println("Enter the Status of the InterViewer");
			String status = "free";
			interviewerManageModel.setInterviewerDetails(name, interviewer.getInterviewId(), email, password, status);
		}
	}

	public void showInterviewersDetails() {
		List<Interviewer> interviewersdetails = InterViewPanelDatabase.getInstance().showInterViewersDetails();
		for (Interviewer interviewer : interviewersdetails) {
			System.out.println(
					"InterViewer Name:" + interviewer.getInterviewerName() + "  -  " + interviewer.getStatus());
		}
	}

//	public void mapCandidateInterviewer() {
////		System.out.println("Enter the InterviewerName");
////		String interviewerName=sc.nextLine();
////		if(interviewerManageModel.checkInterviewer(interviewerName)!=null) {
////			Interviewer interviewer=interviewerManageModel.checkInterviewer(interviewerName);
////			System.out.println("Enter the CandidateId");
////			int candidateId=sc.nextInt();
////			if(interviewerManageModel.checkCandidate(candidateId)) {
////				interviewer.setStatus("Allocated");
////			}
////		}
//		System.out.println("Enter the CandidateName");
//		String candidateName = sc.nextLine();
//		if (interviewerManageModel.checkCandidate(candidateName)) {
//			System.out.println("Enter the InterviewerName");
//			String interviewerName = sc.nextLine();
//			if (interviewerManageModel.checkInterviewer(interviewerName)) {
//				interviewerManageModel.mappingCandidateDetails(interviewerName,candidateName);
//			}
//		}
//	}
	public void showAlart(String string) {
		System.out.println(string);
	}

//	public void selectedCandidate() {
//		interviewerManageModel.selectedCandidate();
//		List<Candidates> selectedCandidate = InterViewPanelDatabase.getInstance().showSelectedCandidate();
//		System.out.println("\nSelected Candidates\n");
//		for (Candidates candidates : selectedCandidate) {
//
//			System.out.println(candidates.getCandidateName());
//		}
//	}
//	public void selectedCandidate() {
//		List<Candidates> candidateList = InterViewPanelDatabase.getInstance().showCandidateDetails();
//		for (Candidates candidates : candidateList) {
//			if (candidates.getRating() == 0.0) {
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
	public void selectedcandidate() {
		interviewerManageModel.showSelectedCandidates();
	}

}
