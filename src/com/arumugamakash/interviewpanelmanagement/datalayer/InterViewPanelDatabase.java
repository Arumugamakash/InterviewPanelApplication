package com.arumugamakash.interviewpanelmanagement.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Company;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InterViewPanelDatabase {
	private String candidateFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\candidateList.json";
	private String interviewerFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\InterviewerList.json";
	private String mappingFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\MappingList.json";
	private String credentialsFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\credentialsList.json";
	private String companyFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\company.json";

	private Credentials credentials;
	private Company company;
	private List<Candidates> candidatesList;
	private List<Interviewer> interviewersList;
	private List<Map<String, String>> mappingCandidatesDetails;
	private List<Credentials> credentialsList;
	private List<Candidates> selectedCandidate;
	ObjectMapper obj = new ObjectMapper();

	private InterViewPanelDatabase() {
		candidatesList = new ArrayList<Candidates>();
		interviewersList = new ArrayList<Interviewer>();
		mappingCandidatesDetails = new ArrayList<Map<String, String>>();
		credentialsList = new ArrayList<Credentials>();
		selectedCandidate = new ArrayList<Candidates>();
	}

	private static InterViewPanelDatabase interViewPanelDatabase;

	public static InterViewPanelDatabase getInstance() {
		if (interViewPanelDatabase == null) {
			interViewPanelDatabase = new InterViewPanelDatabase();
		}
		return interViewPanelDatabase;
	}

//	public boolean addCandidatesDetails(Candidates candidates) {
//		return candidatesList.add(candidates);
//	}
	// -------ADD CREDENTIALS-------//
	public void addCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	// --------GET CREDENTIALS------//
	public Credentials getCredentials() {
		return credentials;
	}

	// -----------ADD COMPANY------------//
	public void addCompanyDetails(Company company) {
		this.company = company;
		try {
			File file = new File(companyFile);
			if (!file.exists()) {
				System.out.println("file");
				file.createNewFile();
			}
//			if (file.length() > 0) {
//				// verify the length of the file
//				company = obj.readValue(new File(companyFile), new TypeReference<Company>() {
//				});
////				for (Interviewer interviewers : interviewersList) {
////					if (interviewers.getMailId().equals(interviewer.getMailId()))
////						return false;
////				}
////				interviewersList.add(interviewer);
//				obj.writeValue(file, company);
//			} else {
				obj.writeValue(file, company);

//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dfgh");
		}
	
	}

	// ---------GET COMPANY-----------//
	public Company getCompany() {
		try {
			return company = obj.readValue(new File(companyFile), new TypeReference <Company>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// --------------ADD CANDIDATE-------------//
	public boolean addCandidatesDetails(Candidates candidates) {

		try {
			File file = new File(candidateFile);
			if (!file.exists()) {
				System.out.println("file");
				file.createNewFile();
			}
			if (file.length() > 0) {
				// verify the length of the file
				candidatesList = obj.readValue(new File(candidateFile), new TypeReference<List<Candidates>>() {
				});
				for (Candidates candidate : candidatesList) {
					if (candidates.getMailId().equals(candidate.getMailId()))
						return false;
				}
				candidatesList.add(candidates);
				obj.writeValue(file, candidatesList);
				return true;
			} else {
				candidatesList.add(candidates);
				obj.writeValue(file, candidatesList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dfgh");
		}
		return false;

//		boolean hasCandidates = false;
//		for (Candidates addedCandidate : candidatesList) {
//			if ((addedCandidate.getCandidateName().equals(candidates.getCandidateName())
//					&& addedCandidate.getMailId().equals(candidates.getMailId()))) {
//				hasCandidates = true;
//				break;
//			}
//		}
//		if (hasCandidates) {
//			return false;
//		} else {
//			candidatesList.add(candidates);
////			try {
////				File file = new File(
////						D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\assignBook.json");
////				if (!file.exists()) {
////					file.createNewFile();
////					obj.writeValue(file, new ArrayList<Map<String, String>>());
////				}
////				List<Map<String, String>> existingAssignBooks = obj.readValue(file, new TypeReference<List<Map<String, String>>>() {
////				});
////				existingAssignBooks.add(assign);
////				obj.writeValue(file, existingAssignBooks);
////
////				return true;
////			} catch (IOException e) {
////				e.printStackTrace();
////				return false;
////			}
//				return true;
//		}
	}

	// -----------SHOW CANDIDATE--------------//
	public List<Candidates> showCandidateDetails() {
		try {
			return candidatesList = obj.readValue(new File(candidateFile), new TypeReference<List<Candidates>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//	public List<Book> searchBook(String bookName) {
//		List<Book> searchResult = new ArrayList<Book>();
//		for (Book book : bookList) {
//			if (book.getBookName().contains(bookName)) {
//				searchResult.add(book);
//			}
//		}
//		return searchResult;
//	}

	public boolean addInterViewerDetails(Interviewer interviewer) {
		try {
			File file = new File(interviewerFile);
			if (!file.exists()) {
				System.out.println("file");
				file.createNewFile();
			}
			if (file.length() > 0) {
				// verify the length of the file
				interviewersList = obj.readValue(new File(interviewerFile), new TypeReference<List<Interviewer>>() {
				});
				for (Interviewer interviewers : interviewersList) {
					if (interviewers.getMailId().equals(interviewer.getMailId()))
						return false;
				}
				interviewersList.add(interviewer);
				obj.writeValue(file, interviewersList);
				return true;
			} else {
				interviewersList.add(interviewer);
				obj.writeValue(file, interviewersList);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dfgh");
		}
		return false;

	}
//		interviewersList.add(interviewer);
//		for (Interviewer book : interviewersList) {
//          System.out.println(book.getInterviewerName());
//		}
//		boolean hasInterviewer = false;
//		for (Interviewer addInterviewer : interviewersList) {
//			if ((addInterviewer.getInterviewerName().equals(interviewer.getInterviewerName())
//					&& addInterviewer.getMailId().equals(interviewer.getMailId()))) {
//				hasInterviewer = true;
//				break;
//			}
//		}
//		if (hasInterviewer) {
//			return false;
//		} else {
//			interviewersList.add(interviewer);
//			return true;
//		}
//	}

	public List<Interviewer> showInterViewersDetails() {
		try {
			return interviewersList = obj.readValue(new File(interviewerFile), new TypeReference<List<Interviewer>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//	public List<Credentials> showCredentialsDetails() {
//		try {
//			return credentialsList = obj.readValue(new File(interviewerFile), new TypeReference<List<Credentials>>() {
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	public void addMapingCanditatesDetails(Map<String, String> mapInterviewerToCandidate) {
		// mappingCandidatesDetails.add(mapInterviewerToCandidate);
		try {
			File file = new File(mappingFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				mappingCandidatesDetails = obj.readValue(file, new TypeReference<List<Map<String, String>>>() {
				});

				// obj.writeValue(file, new ArrayList<Map<String, String>>());
			}
//			List<Map<String, String>> mappingDetails = obj.readValue(file,
//					new TypeReference<List<Map<String, String>>>() {
//					});
			mappingCandidatesDetails.add(mapInterviewerToCandidate);
			obj.writeValue(file, mappingCandidatesDetails);

		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			File file = new File(mappingFile);
//			if (!file.exists()) {
//				System.out.println("file");
//				file.createNewFile();
//			}
//			if (file.length() > 0) {
//				// verify the length of the file
//				mappingCandidatesDetails = obj.readValue(new File(mappingFile), new TypeReference<List<Map<String , String>>>() {
//				});
//				for (Candidates candidates : candidatesList) {
//					
//				}
//				interviewersList.add(interviewer);
//				obj.writeValue(file, interviewersList);
//				return true;
//			} else {
//				interviewersList.add(interviewer);
//				obj.writeValue(file, interviewersList);
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("dfgh");
//		}
//		return false;
//		try {
//			File file = new File();
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			if (file.length() >= 0) {
//
//				obj.writeValue(file, new ArrayList<Map<String, String>>());
//			}
//			List<Map<String, String>> existingAssignBooks = obj.readValue(file,
//					new TypeReference<List<Map<String, String>>>() {
//					});
//			existingAssignBooks.add(assign);
//			obj.writeValue(file, existingAssignBooks);
//
//			return true;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	public List<Map<String, String>> showMappingCandidatesDetails() {
		try {
			return mappingCandidatesDetails = obj.readValue(new File(mappingFile),
					new TypeReference<List<Map<String, String>>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addInterviewerCredentials(Credentials credentials) {
		// credentialsList.add(credentials);
		try {
			File file = new File(credentialsFile);
			if (!file.exists()) {
				System.out.println("file");
				file.createNewFile();
			}
			if (file.length() > 0) {
				// verify the length of the file
				credentialsList = obj.readValue(new File(credentialsFile), new TypeReference<List<Credentials>>() {
				});
//
				credentialsList.add(credentials);
				obj.writeValue(file, credentialsList);
//				return true;
			} else {
				credentialsList.add(credentials);
				obj.writeValue(file, credentialsList);
//				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dfgh");
		}
	}

	public List<Credentials> viewInterviewerCredentials() {
		try {
			return credentialsList = obj.readValue(new File(credentialsFile), new TypeReference<List<Credentials>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public void addSelectedCandidates(List<Candidates> selectedCandidate) {
//		selectedCandidate.addAll(selectedCandidate);
//	}
	public List<Candidates> showSelectedCandidate() {
		return selectedCandidate;
	}

	public void addSelectedCandidates(Candidates candidates) {
		selectedCandidate.add(candidates);
	}

}
