package menu.main;
import menu.edit.EditController;
import menu.calendar.CalendarController;

import java.util.Scanner;

import db.setup.DB;

public class MainController {
	private EditController editController;
	private CalendarController calendarController;
	private User user;
	private Scanner input;
	public MainController(Scanner input) {
		this.input = input;
	}
	
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
