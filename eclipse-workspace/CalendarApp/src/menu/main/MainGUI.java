package menu.main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datatype.User;
import db.setup.DB;
import menu.calendar.CalendarGUI;
import menu.edit.EditController;
import menu.edit.EditGUI;
import menu.login.LoginGUI;

public class MainGUI extends JFrame {
	private JPanel panel;
	private JButton editButton;
	private JButton displayButton;
	private JButton exitButton;
	private JButton infoButton;
	private DB db;
	private User user;
	private String username;
	EditController editController;
	
	public MainGUI(DB db, int userID, String un) {
		super("Main Menu");
		this.db = db;
		this.user = new User(userID, un, db);
		editController = new EditController(user, db);
		this.user.updateCalendar(db);
		this.user.getCalendar().printCalendar();
		this.username = un;
		setSize(350, 150);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setLocationRelativeTo(null);
		setBackground(Color.white);
		add(new JLabel("<HTML><center><b>Welcome to the Main Menu " + un + "</b><BR>Select Edit to edit your calendar"
						+ "<BR>Select Display to display your calendar<BR>Select Logout to exit the Login Menu</center><HTML>"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		panel = new JPanel();
		panel.setBounds(600, 600, 600, 600);
		panel.setBackground(Color.white);
		
		editButton = new JButton("Edit");
		editButton.setBounds(500, 1000, 80, 30);
		editButton.setBackground(Color.orange);
		editButton.addActionListener(new ActionPerformed());
		
		displayButton = new JButton("Display");
		displayButton.setBounds(1000, 1000, 80, 30);
		displayButton.setBackground(Color.green);
		displayButton.addActionListener(new ActionPerformed());
		
		exitButton = new JButton("Logout");
		exitButton.setBounds(1500, 1000, 80, 30);
		exitButton.setBackground(Color.red);
		exitButton.addActionListener(new ActionPerformed());
		
		infoButton = new JButton("Info");
		infoButton.setBounds(1500, 1000, 80, 30);
		infoButton.setBackground(Color.gray);
		infoButton.addActionListener(new ActionPerformed());
		
		panel.add(editButton);
		panel.add(displayButton);
		panel.add(exitButton);
		panel.add(infoButton);
		
		add(panel);
	}
	
	// ActionPerformed class to implement button actions
	public class ActionPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) (e.getSource());		// Gets user input if button is pressed
			if(source.equals(editButton)) {		// Calls handleRegister if Register is pressed
				handleEdit();
			}
			else if(source.equals(displayButton)) {	// Calls handleLogin if Login is pressed
				handleDisplay();
			}
			else if(source.equals(exitButton)) { 
				handleExit();
			}
			else if(source.equals(infoButton)) {
				handleInfo();
			}
		}
		
		private void handleEdit() {
			dispose();
			EditGUI edit = new EditGUI("Edit Menu", editController);
		
		}
		
		private void handleDisplay() {
			CalendarGUI calendar = new CalendarGUI(user,db);
			calendar.CalendarPage();
		}
		
		private void handleExit() {
			dispose();
			LoginGUI login = new LoginGUI(db);
		}
		
		private void handleInfo() {
			String infoMessage = "How to use the project: \r\n"
					+ "\r\n"
					+ "Once an account has been made, you are now ready to start adding elements to your calendar!\nTo add elements to your calendar, first click on the edit button. Now you can choose to view, add, or remove elements from your calendar.\r\n"
					+ "\r\n"
					+ "Here are the ranges for the ID’s:\r\n"
					+ "Set: 0-100\r\n"
					+ "Category: 101-500\r\n"
					+ "Event: 501-1000\r\n"
					+ "User: > 1000\r\n"
					+ "\r\n"
					+ "Set the parent ID to 0 if you do not want the user to be the parent";
			JOptionPane.showMessageDialog(null, infoMessage, "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
