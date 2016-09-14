package com.EPF.CLI.data;

import java.util.ArrayList;

import com.EPF.CLI.model.Company;

public enum Companies {
	INSTANCE;
	
	private ArrayList<Company> companies;
	
	private Companies() {
		this.companies = new ArrayList<>();
		/*this.companies.add(new Company("Lenovo"));
		this.companies.add(new Company("Apple"));
		this.companies.add(new Company("Dell"));*/
	}
	
	public Company get(Long companyId) {
		return this.companies.get(companyId.intValue()-1);
	}
}
