package menu.main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import db.setup.DB;
import menu.calendar.CalendarGUI;
import menu.edit.EditGUI;

public class MainGUI extends JFrame {
	private JPanel panel;
	private JButton editButton;
	private JButton displayButton;
	private DB db;
	private int userID;
	
	public MainGUI(DB db, int userID) {
		super("Main Menu");
		this.db = db;
		this.userID = userID;
		
		setSize(500, 300);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(Color.white);
		add(new JLabel("<HTML><center>Welcome to the Main Menu" + "<BR>Please select Edit to edit your calendar, or"
						+ " Display to display your calendar</center><HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		panel = new JPanel();
		panel.setBounds(600, 600, 600, 600);
		panel.setBackground(Color.white);
		
		editButton = new JButton("Edit");
		editButton.setBounds(500, 1000, 80, 30);
		editButton.setBackground(Color.red);
		editButton.addActionListener(new ActionPerformed());
		
		displayButton = new JButton("Display");
		displayButton.setBounds(1000, 1000, 80, 30);
		displayButton.setBackground(Color.green);
		displayButton.addActionListener(new ActionPerformed());
		
		panel.add(editButton);
		panel.add(displayButton);
		
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
		}
		
		private void handleEdit() {
			EditGUI edit = new EditGUI();
		}
		
		private void handleDisplay() {
			CalendarGUI calendar = new CalendarGUI();
		}
	}
}
