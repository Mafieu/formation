package com.EPF.CLI.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.EPF.CLI.menu.choice.Choice;
import com.EPF.CLI.menu.choice.CreateComputer;
import com.EPF.CLI.menu.choice.List;

public class Menu {

	private Map<String, Choice> choices;

	public Menu() {
		this.choices = new HashMap<>();
		this.choices.put("1", new List());
		this.choices.put("2", new CreateComputer());
	}

	public void run() {
		String input;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("---Menu---");
			for(Entry<String, Choice> choice : this.choices.entrySet()) {
				System.out.print(choice.getKey() + "- ");
				choice.getValue().showInMenu();
			}
			System.out.println("q- Quit");
			input = scanner.next();
			if(!input.equals("q")) {
				choices.get(input).action();
			}
		} while(!input.equals("q"));
		scanner.close();
	}
}
