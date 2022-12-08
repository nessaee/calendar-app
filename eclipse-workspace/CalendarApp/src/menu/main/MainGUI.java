package menu.main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		setSize(400, 200);
		setLayout(new FlowLayout(FlowLayout.CENTER));
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
		
		panel.add(editButton);
		panel.add(displayButton);
		panel.add(exitButton);
		
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
	}
}
