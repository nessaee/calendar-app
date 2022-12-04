package menu.login;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import db.setup.DB;

public class LoginGUI extends JFrame {
	
	private JPanel panel;
	private JButton registerButton;
	private JButton loginButton;
	private DB db;
	
	public LoginGUI(DB db) {
		super("Login Menu");
		this.db = db;
		
		setSize(850, 300);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.white);
		add(new JLabel("<HTML><center>Welcome to the ECE373 Calendar Project" + "<BR>This is the Login menu. "
						+ "Please select Register if you would like to create a new user, and Login if you would"
						+ " like to login to an existing account</center><HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		panel = new JPanel();
		panel.setBounds(600, 600, 600, 600);
		panel.setBackground(Color.white);
		
		
		registerButton = new JButton("Register");
		registerButton.setBounds(500, 1000, 80, 30);
		registerButton.setBackground(Color.red);
		registerButton.addActionListener(new ActionPerformed());
		
		loginButton = new JButton("Login");
		loginButton.setBounds(1000, 1000, 80, 30);
		loginButton.setBackground(Color.green);
		loginButton.addActionListener(new ActionPerformed());
		
		panel.add(registerButton);
		panel.add(loginButton);
		
		add(panel);
	}
	
	
	public class ActionPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton source = (JButton) (e.getSource());
			
			if(source.equals(registerButton)) {
				handleRegister();
			}
			else if(source.equals(loginButton)) {
				handleLogin();
			}
		}
		
		// FIXME Debug back-end interaction with database
		private void handleRegister() {
			// Creating Panel Components
			JTextField username = new JTextField(5);
			JTextField password = new JTextField(5);
			JTextField passwordConfirm = new JTextField(5);
			
			// Getting Panel Inputs and Displaying Panel
			JPanel panel = new JPanel(new GridLayout(3, 1));
			panel.add(new JLabel("Enter Username: "));
			panel.add(username);
			panel.add(Box.createHorizontalStrut(20));
			panel.add(new JLabel("Enter Password: "));
			panel.add(password);
			panel.add(Box.createHorizontalStrut(20));
			panel.add(new JLabel("Confirm Password: "));
			panel.add(passwordConfirm);
			panel.add(Box.createHorizontalStrut(20));
			JOptionPane.showMessageDialog(null, panel);
			// Getting Panel Inputs as Strings
			String u = username.getText();
			String p = password.getText();
			String pc = passwordConfirm.getText();
			
			// Creating the new User based on given information
			String result = createUser(u, p, pc);
			JOptionPane.showMessageDialog(null, result, "Register Menu", JOptionPane.PLAIN_MESSAGE);
		}
		
		private String createUser(String username, String password, String passwordConfirm) {
			// If the password and passwordConfirm do not match, the user is not created
			if(username.equals("") || password.equals("") || passwordConfirm.equals("")) {
				return "Invalid Input: Input cannot be empty/blank";
			}
			else if(!password.equals(passwordConfirm)) {
				return "Invalid Input: Password and Password Confirm do not match";
			}
			else if(db.checkUser(username,  password) == -1) {
				Login login = new Login(db, "Create", username, password);
				addUserToDatabase(login);
				return "Success! Username: " + username + ", Password: " + password + " has been added";
			}
			
			return "This user already exists, please try 'Login' instead of 'Register'";
		}
		
		private void addUserToDatabase(Login login) {
			ArrayList<Object> rowData = new ArrayList<>();
			rowData.add(login.getUserID());
			rowData.add(login.getUsername());
			rowData.add(login.getPassword());
			
			db.saveRow("Users", rowData);
		}
		
		//FIXME Add back-end interaction with database
		private void handleLogin() {
			// Creating Panel Components
			JTextField username = new JTextField(5);
			JTextField password = new JTextField(5);
			
			// Getting Panel Inputs and Displaying Panel
			JPanel panel = new JPanel(new GridLayout(2, 1));
			panel.add(new JLabel("Enter Username: "));
			panel.add(username);
			panel.add(Box.createHorizontalStrut(20));
			panel.add(new JLabel("Enter Password: "));
			panel.add(password);
			panel.add(Box.createHorizontalStrut(20));
			JOptionPane.showMessageDialog(null,  panel);
			// Getting Panel Inputs as Strings
			String u = username.getText();
			String p = password.getText();
		}
		
		
	}
	
	
}
