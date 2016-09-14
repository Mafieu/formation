package com.EPF.CLI.menu.choice;

import java.util.Comparator;

import com.EPF.CLI.dao.ComputerDao;
import com.EPF.CLI.model.Computer;

public class List implements Choice{
	
	private ComputerDao computerDao = ComputerDao.INSTANCE;
	
	public List() {
		
	}
	
	@Override
	public void showInMenu() {
		System.out.println("List Computers");
	}

	@Override
	public void action() {
		try {
			java.util.List<Computer> computers = computerDao.getAll();
			computers.sort(new Comparator<Computer>() {
				@Override
				public int compare(Computer c1, Computer c2) {
					return c1.getId().compareTo(c2.getId());
				}
			});
			for(Computer computer : computers) {
				System.out.println(computer.toString());
			}
		} catch (Exception e) {
			System.err.println("Problem recovering all computers");
		}
	}

}
