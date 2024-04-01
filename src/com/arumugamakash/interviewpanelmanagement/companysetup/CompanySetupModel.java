package com.arumugamakash.interviewpanelmanagement.companysetup;

import java.beans.IndexedPropertyDescriptor;

import com.arumugamakash.interviewpanelmanagement.datalayer.InterViewPanelDatabase;
import com.arumugamakash.interviewpanelmanagement.model.Company;

public class CompanySetupModel {
	private CompanySetupView companySetupView;
	private Company company;

	public CompanySetupModel(CompanySetupView companySetupView) {
		this.companySetupView = companySetupView;
		this.company = InterViewPanelDatabase.getInstance().getCompany();

	}

	public void startCompanySetup() {
		if (company == null) {
			companySetupView.setupInitialize();
		} else {
			companySetupView.showAlart("\n------Company Setup completed------\n");
			companySetupView.onSetupComplete(company);
		}

	}

	public void setCompanyDetails(String companyName, String mailId, long phoneNumber, String address) {
		company = new Company();
		company.setCompanyName(companyName);
		company.setCompanyMailId(mailId);
		company.setContact(phoneNumber);
		company.setCompanyAddress(address);
		createCompany(company);
	}

	private void createCompany(Company company) {
		InterViewPanelDatabase.getInstance().addCompanyDetails(company);
		companySetupView.showAlart("\n-------Company Setup completed-----\n");
		companySetupView.onSetupComplete(company);
	}

	public void writeAll() {
		InterViewPanelDatabase.getInstance().writeAll();
	}

}
