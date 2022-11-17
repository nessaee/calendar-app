package menu.calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import datatype.*;
import db.setup.DB;
import db.setup.Edit;
import menu.main.User;

public class CalendarController {
	private User user;
	private DB db;
	private Scanner input;
	public CalendarController(User u, DB db, Scanner input) {
		this.user = u;
		this.db = db;
		this.input = input;
	}
	
	public void menu() {
		Scanner input = new Scanner(System.in);;
		while(true) {
			System.out.println("Welcome to the Calendar!");
			this.printCalendar();
			System.out.println("Enter 'Exit' to return to Main Menu:");
			String option = input.nextLine();
			if(option.equals("Exit")) {
				break;
			}
			else {
				System.out.println("Invalid input, please try again");
			}
		}
		
	}
	public void printCalendar() {
		System.out.println("----------------------------SETS---------------------------");
		for(Set s : user.getCalendar().getSetList()) {
			System.out.println(s);
		}
		System.out.println("-------------------------CATEGORIES-----------------------");
		for(Category c : user.getCalendar().getCategoryList()) {
			System.out.println(c);
		}
		System.out.println("---------------------------EVENTS-------------------------");
		for(Event e : user.getCalendar().getEventList()) {
			System.out.println(e);
		}
	}
}
