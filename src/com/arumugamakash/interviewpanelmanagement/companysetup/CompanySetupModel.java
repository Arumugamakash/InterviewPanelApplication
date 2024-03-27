package com.arumugamakash.interviewpanelmanagement.companysetup;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Company;

public class CompanySetupModel {
	private CompanySetupView companySetupView;
	private Company company;

	public CompanySetupModel(CompanySetupView companySetupView) {
		this.companySetupView = companySetupView;
		this.company=InterViewPanelDatabase.getInstance().getCompany();
		
	}
	public void startCompanySetup() {
		if (company == null) {
			System.out.println("1st_time");
			companySetupView.setupInitialize();
		} else {
			companySetupView.onSetupComplete(company);
		}

	}
	public void setCompanyDetails(String companyName, String mailId,long phoneNumber, String address) {
	    company=new Company();
		company.setCompanyName(companyName);
		company.setCompanyMailId(mailId);
		company.setContact(phoneNumber);
		company.setCompanyAddress(address);
		createCompany(company);
		//InterViewPanelDatabase.getInstance().addCompanyDetails(company);
		//companySetupView.onSetupComplete(company);
	}
	private void createCompany(Company company) {
		InterViewPanelDatabase.getInstance().addCompanyDetails(company);
		companySetupView.onSetupComplete(company);
	}

}
