package menu.calendar;
import java.util.Scanner;
import datatype.*;
import db.setup.DB;

/*
 * This class controls the display of the calendar. It ensures the calendar is updated, and displays it.
 */
public class CalendarController {
	
	// Declaration of fields
	private User user;
	private DB db;
	private Scanner input;
	
	// Constructor that takes in a User, database, and Scanner
	public CalendarController(User user, DB db, Scanner input) {
		this.user = user;
		this.db = db;
		this.input = input;
	}
	
	// Method to display the menu, and allow the user to select what they would like to happen. It displays the user's 
	// calendar, and loops until exit option is chosen.
	public void menu() {
		while(true) {
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("Welcome to your Calendar!");
			this.user.updateCalendar(this.db);
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
