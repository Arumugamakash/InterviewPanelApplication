package com.arumugamakash.interviewpanelmanagement.datalayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arumugamakash.interviewpanelmanagement.model.Candidates;
import com.arumugamakash.interviewpanelmanagement.model.Company;
import com.arumugamakash.interviewpanelmanagement.model.Credentials;
import com.arumugamakash.interviewpanelmanagement.model.Interviewer;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InterViewPanelDatabase {
	private String candidateFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\candidateList.json";
	private String interviewerFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\InterviewerList.json";
	private String mappingFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\MappingList.json";
	private String credentialsFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\credentialsList.json";
	private String companyFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\company.json";
	private String selectedCandidateFile = "D:\\Zoho\\InterviewPanelManagement\\src\\com\\arumugamakash\\interviewpanelmanagement\\Json\\selectedCandidate.json";

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
				file.createNewFile();
			}
			obj.writeValue(file, company);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ---------GET COMPANY-----------//
	public Company getCompany() {
		try {
			return company = obj.readValue(new File(companyFile), new TypeReference<Company>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// --------------ADD CANDIDATE-------------//
	public boolean addCandidatesDetails(Candidates candidates) {
		if (!getAllCandidates().isEmpty()) {
			List<Candidates> candidatesList = getAllCandidates();
			for (Candidates candidates2 : candidatesList) {
				if (candidates2.getMailId().equals(candidates.getMailId())) {
					return false;
				}
			}
			candidatesList.add(candidates);
			return true;
		} else {
			return candidatesList.add(candidates);
		}
	}

	public void writeCandidate() {
		try {
			File file = new File(candidateFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, candidatesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------SHOW CANDIDATE--------------//
	public List<Candidates> getAllCandidates() {
		return candidatesList;
	}

	public void readCandidateDetails() {
		File file = new File(candidateFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				candidatesList = obj.readValue(file, new TypeReference<List<Candidates>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// ADD INTERVIEWERS
	public boolean addInterViewerDetails(Interviewer interviewer) {
		if (!showInterViewersDetails().isEmpty()) {
			List<Interviewer> interviewerList = showInterViewersDetails();
			for (Interviewer interviewer2 : interviewerList) {
				if (interviewer2.getMailId().equals(interviewer.getMailId())) {
					return false;
				}
			}
			interviewerList.add(interviewer);
			return true;
		} else {
			return interviewersList.add(interviewer);
		}

	}

	// WRITE INTERVIEWER
	public void writeInterviewer() {
		try {
			File file = new File(interviewerFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, interviewersList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SHOW INTERVIEWER
	public List<Interviewer> showInterViewersDetails() {
		return interviewersList;
	}

	// READ INTERVIEWER
	private void readInterviewerDetails() {
		File file = new File(interviewerFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				interviewersList = obj.readValue(file, new TypeReference<List<Interviewer>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// ADD MAPPING DETAILS
	public void addMapingCanditatesDetails(Map<String, String> mapInterviewerToCandidate) {
		mappingCandidatesDetails.add(mapInterviewerToCandidate);
	}

	// WRITE MAPPING DETAILS
	private void writeMappingDetails() {
		try {
			File file = new File(mappingFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, mappingCandidatesDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, String>> showMappingCandidatesDetails() {
		return mappingCandidatesDetails;
	}

	// READ MAPPING DETAILS
	private void readMappingDetails() {
		File file = new File(mappingFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				mappingCandidatesDetails = obj.readValue(file, new TypeReference<ArrayList<Map<String, String>>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// REMOVE AND UPDATE MAPPING DATAS
	public void remove(String name) {
		try {
			mappingCandidatesDetails = obj.readValue(new File(mappingFile),
					new TypeReference<List<Map<String, String>>>() {
					});
			for (int i = 0; i < mappingCandidatesDetails.size(); i++) {
				for (Map.Entry<String, String> temp : mappingCandidatesDetails.get(i).entrySet()) {
					if (temp.getKey().equals(name)) {
						FileWriter file = new FileWriter(mappingFile, false);
						mappingCandidatesDetails.remove(i);
					}
				}
			}
			writeMappingDetails();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/// ADD CREDENTIALS INTERVIEWER
	public void addInterviewerCredentials(Credentials credentials) {
		try {
			File file = new File(credentialsFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				// verify the length of the file
				credentialsList = obj.readValue(new File(credentialsFile), new TypeReference<List<Credentials>>() {
				});
				credentialsList.add(credentials);
				obj.writeValue(file, credentialsList);
			} else {
				credentialsList.add(credentials);
				obj.writeValue(file, credentialsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// VIEW INTERVIEWR CREDENTIALS
	public List<Credentials> viewInterviewerCredentials() {
		try {
			return credentialsList = obj.readValue(new File(credentialsFile), new TypeReference<List<Credentials>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ADD SELECTED CANDIDATES
	public boolean addSelectedCandidates(Candidates candidates) {
		if (!showSelectedCandidate().isEmpty()) {
			List<Candidates> selectedList = showSelectedCandidate();
			for (Candidates candidates2 : selectedList) {
				if (candidates.getCandidateName().equals(candidates2.getCandidateName())) {
					return false;
				}
			}
			selectedCandidate.add(candidates);
			return true;
		} else {
			return selectedCandidate.add(candidates);
		}

	}

	// WRITE SELECTED CANDIDARTES
	public void writeSelectedCanidates() {
		try {
			File file = new File(selectedCandidateFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			obj.writeValue(file, selectedCandidate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SHOW SELECTED CANDIDARES
	public List<Candidates> showSelectedCandidate() {
		return selectedCandidate;
	}

	// READ SELECTED CANDIDATES
	private void readSelectedCandidates() {
		File file = new File(selectedCandidateFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.length() > 0) {
			try {
				selectedCandidate = obj.readValue(file, new TypeReference<List<Candidates>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// WRITE ALL JSON FILE
	public void writeAll() {
		writeCandidate();
		writeInterviewer();
		writeSelectedCanidates();
		writeMappingDetails();
	}

	// READ ALL JSON FILE
	public void readAllDatas() {
		readCandidateDetails();
		readInterviewerDetails();
		readMappingDetails();
		readSelectedCandidates();
	}

}
