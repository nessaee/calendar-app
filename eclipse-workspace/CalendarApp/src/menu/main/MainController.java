package menu.main;
import menu.edit.EditController;
import menu.calendar.CalendarController;

import java.util.Scanner;

import db.setup.DB;

/*
 * This class controls all editing of the calendar. A user must be successfully logged in through the LoginController for this point
 * to be reached. This will display a menu, and allow the User to choose whether they would like to edit or display the calendar.
 * It then calls the appropriate controller.
 */
public class MainController {
	// Declaration of fields
	private EditController editController;
	private CalendarController calendarController;
	private User user;
	private Scanner input;
	
	// Constructor that takes in a Scanner as a parameter
	public MainController(Scanner input) {
		this.input = input;
	}
	
	// Method that displays a user menu and takes in input. It then calls the appropriate controller based on the 
	// user input.
	public void menu(int userID, DB db) {
		this.user = new User(userID, db);
		this.editController = new EditController(this.user, db, input);
		this.calendarController = new CalendarController(this.user, db, input);
		int option = -1;
		String buffer = "";
		while(true) {
			System.out.println("-------------------------------------------------------------\n");
			System.out.println("Welcome to the Main Menu!");
			System.out.println("(0) Logout\n(1) View Calendar\n(2) Edit Calendar\nEnter the option you would like to choose: ");
			option = input.nextInt();
			buffer = input.nextLine();
			switch(option) {
				case 0:
					return;
				case 1:
					this.calendarController.menu();
					break;
				case 2:
					this.editController.menu();
					break;
				default:
					System.out.println("Invalid input, please try again");
					break;
			}
			
		}
	
	}
	
	// Getters and Setters
	public EditController getEditController() {
		return editController;
	}

	public void setEditController(EditController editController) {
		this.editController = editController;
	}

	public CalendarController getCalendarController() {
		return calendarController;
	}

	public void setCalendarController(CalendarController calendarController) {
		this.calendarController = calendarController;
	}
	
}
