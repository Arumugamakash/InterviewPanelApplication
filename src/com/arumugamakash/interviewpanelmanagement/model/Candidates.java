package com.arumugamakash.interviewpanelmanagement.model;

public class Candidates {
	private String candidateName;
	private int candidateId;
	private static int nextId = 1;
	private String mailId;
	private long candidatePhoneNumber;
	private String skill;
	private String location;
	private int candidateNo;
	private boolean allocate=false;
	private double rating;

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = nextId++;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public long getCandidatePhoneNumber() {
		return candidatePhoneNumber;
	}

	public void setCandidatePhoneNumber(long candidatePhoneNumber) {
		this.candidatePhoneNumber = candidatePhoneNumber;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCandidateNo() {
		return candidateNo;
	}

	public void setCandidateNo(int candidateNo) {
		this.candidateNo = candidateNo;
	}

	public boolean getAllocate() {
		return allocate;
	}

	public void setAllocate(boolean allocate) {
		this.allocate = allocate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
