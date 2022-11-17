package menu.calendar;
import java.util.Scanner;
import datatype.*;
import db.setup.DB;
import menu.main.User;

public class CalendarController {
	private User user;
	private DB db;
	private Scanner input;
	public CalendarController(User user, DB db, Scanner input) {
		this.user = user;
		this.db = db;
		this.input = input;
	}
	
	public void menu() {
		while(true) {
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("Welcome to your Calendar!");
			this.user.updateCalendar(this.db);
			// this.user.getCalendar().update(user.getUserID(), db);
			user.getCalendar().printCalendar();
			System.out.println("Enter 0 to return to Main Menu:");
			int option = input.nextInt();
			String buffer = input.nextLine();
			if(option == 0) {
				break;
			}
			else {
				System.out.println("Invalid input, please try again");
			}
		}
	}
	
}
