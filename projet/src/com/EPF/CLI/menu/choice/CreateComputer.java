package com.EPF.CLI.menu.choice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.EPF.CLI.dao.CompanyDao;
import com.EPF.CLI.dao.ComputerDao;
import com.EPF.CLI.model.Company;
import com.EPF.CLI.model.Computer;

public class CreateComputer implements Choice{

	private ComputerDao computerDao = ComputerDao.INSTANCE;
	private CompanyDao companyDao = CompanyDao.INSTANCE;

	public CreateComputer() {

	}

	@Override
	public void showInMenu() {
		System.out.println("Add a computer");
	}

	@Override
	public void action() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Computer name :");
		String name = scanner.next();
		System.out.println("Introduced (nothing for null, dd/MM/yyyy):");
		Date introduced = readDate(scanner);
		System.out.println("Discontinued (nothing for null, dd/MM/yyyy):");
		Date discontinued = readDate(scanner);
		System.out.println("Company id :");
		Long companyId = scanner.nextLong();
		Company company;
		try {
			company = companyDao.get(companyId);
			try {
				computerDao.add(new Computer(0L, name, introduced, discontinued, company));
			} catch (Exception e) {
				System.err.println("Error while adding computer");
			}
		} catch (Exception e1) {
			System.err.println("Error while retrieving company");
		}
	}

	private Date readDate(Scanner scanner) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		do {
			try {
				String sDate = scanner.next();
				if(sDate.equals("")) {
					return null;
				}
				date = sdf.parse(sDate);
			} catch (ParseException e) {
				System.err.println("Wrong format (dd/MM/yyyy)");
			}
		} while(date == null);
		return date;
	}

}
