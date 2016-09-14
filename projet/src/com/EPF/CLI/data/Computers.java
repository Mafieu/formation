package com.EPF.CLI.data;

import java.util.ArrayList;

import com.EPF.CLI.model.Computer;

public enum Computers {
	INSTANCE;
	
	private Companies companies = Companies.INSTANCE;
	private ArrayList<Computer>computers;
	
	private Computers() {
		this.computers = new ArrayList<>();
		/*this.computers.add(new Computer("Lenovo G70", new Date(), new Date(), this.companies.get(1L)));
		this.computers.add(new Computer("iTruc", new Date(), new Date(), this.companies.get(2L)));
		this.computers.add(new Computer("Dell 6549", new Date(), new Date(), this.companies.get(3L)));
		this.computers.add(new Computer("iTruc revolution", new Date(), new Date(), this.companies.get(2L)));*/
	}
	
	public Computer get(Integer i) {
		return this.computers.get(i-1);
	}
	
	public ArrayList<Computer> getAll() {
		return this.computers;
	}

	public void add(Computer computer) {
		this.computers.add(computer);
	}
}
