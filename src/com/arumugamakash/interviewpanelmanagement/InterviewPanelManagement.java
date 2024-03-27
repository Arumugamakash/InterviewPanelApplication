package com.arumugamakash.interviewpanelmanagement;

import com.arumugamakash.interviewpanelmanagement.login.LoginView;

public class InterviewPanelManagement {

	public static InterviewPanelManagement interPanel;
	private String appName = "InterviewPanelManagement2024";
	private String appVersion = "0.1.0";

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	private InterviewPanelManagement() {

	}

	public static InterviewPanelManagement getInstance() {
		if (interPanel == null) {
			interPanel = new InterviewPanelManagement();
		}
		return interPanel;
	}

	private void create() {
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public static void main(String[] args) {
		interPanel.getInstance().create();
	}
}
