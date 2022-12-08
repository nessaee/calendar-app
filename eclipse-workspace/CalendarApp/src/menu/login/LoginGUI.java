package menu.login;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import db.setup.DB;
import menu.main.MainGUI;

public class LoginGUI extends JFrame {
	
	private JPanel panel;
	private JButton registerButton;
	private JButton loginButton;
	private JButton exitButton;
	private DB db;
	
	// LoginGUI Constructor that creates the Login Menu and calls BuildGUI to build it
	public LoginGUI(DB db) {
		super("Login Menu");
		this.db = db;
		
		setSize(400, 150);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(Color.white);
		add(new JLabel("<HTML><center><b>Welcome to the ECE373 Calendar Project</b>"
						+ "<BR>Select Register if you would like to create a new account<BR>Select Login if you would"
						+ " like to login to an existing account<BR>Select Exit to exit the program</center><HTML>"));
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setVisible(true);
	}
	
	
	// Method to build the GUI for the Login Menu
	// Creates Register, Login, and Exit buttons
	public void buildGUI() {
		panel = new JPanel();
		panel.setBounds(600, 600, 600, 600);
		panel.setBackground(Color.white);

		registerButton = new JButton("Register");
		registerButton.setBounds(500, 1000, 80, 30);
		registerButton.setBackground(Color.orange);
		registerButton.addActionListener(new ActionPerformed());
		
		loginButton = new JButton("Login");
		loginButton.setBounds(1000, 1000, 80, 30);
		loginButton.setBackground(Color.green);
		loginButton.addActionListener(new ActionPerformed());
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(1500, 1000, 80, 30);
		exitButton.setBackground(Color.red);
		exitButton.addActionListener(new ActionPerformed());
		
		panel.add(registerButton);
		panel.add(loginButton);
		panel.add(exitButton);
		
		add(panel);
	}
	
	// ActionPerformed class to implement button actions
	public class ActionPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) (e.getSource());		// Gets user input if button is pressed
			if(source.equals(registerButton)) {		// Calls handleRegister if Register is pressed
				handleRegister();
			}
			else if(source.equals(loginButton)) {	// Calls handleLogin if Login is pressed
				handleLogin();
			}
			else if(source.equals(exitButton)) {	// Calls handleExit if Exit is pressed
				handleExit();
			}
		}
		
		// FIXME If user presses close, then the program displays an error message
		/* This method handles the Register button. If it is pressed User input will be taken for
		 * a username, password, and confirmPassword. It will perform error checking on the input
		 * and if no input errors are found, a User will be created and added to the database
		 * */
		private void handleRegister() {
			// Creating Panel Components
			JTextField username = new JTextField(5);
			JPasswordField password = new JPasswordField(5);
			password.setEchoChar('*');
			JPasswordField passwordConfirm = new JPasswordField(5);
			passwordConfirm.setEchoChar('*');
			
			// Getting Panel Inputs and Displaying Panel
			JPanel panelR = new JPanel(new GridLayout(3, 1));
			panelR.add(new JLabel("Enter Username: "));
			panelR.add(username);
			panelR.add(Box.createHorizontalStrut(20));
			panelR.add(new JLabel("Enter Password: "));
			panelR.add(password);
			panelR.add(Box.createHorizontalStrut(20));
			panelR.add(new JLabel("Confirm Password: "));
			panelR.add(passwordConfirm);
			panelR.add(Box.createHorizontalStrut(20));
			JOptionPane.showMessageDialog(null, panelR);
			// Getting Panel Inputs as Strings
			String u = username.getText();
			String p = new String(password.getPassword());
			String pc = new String(passwordConfirm.getPassword());
			
			// Creating the new User based on given information
			String result = createUser(u, p, pc);
			JOptionPane.showMessageDialog(null, result, "Register Menu", JOptionPane.PLAIN_MESSAGE);
		}
		
		/* Helper method to handle the Register button. It takes in user input and performs error checking
		 * to determine if the input is valid. If it is, a Login is created and the User is added to the
		 * database
		 *  */
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
		
		/* Helper method to add User to the database */
		private void addUserToDatabase(Login login) {
			ArrayList<Object> rowData = new ArrayList<>();
			rowData.add(login.getUserID());
			rowData.add(login.getUsername());
			rowData.add(login.getPassword());
			
			db.saveRow("Users", rowData);
		}
		
		//FIXME Add back-end interaction with database
		/* This method handles the Login button. If it is pressed then user input is received and
		 * verified. If it is valid then the User is logged in and moves to the Main Menu 
		 * */
		private void handleLogin() {
			// Creating Panel Components
			JTextField username = new JTextField(5);
			JPasswordField password = new JPasswordField(5);
			password.setEchoChar('*');
			
			// Getting Panel Inputs and Displaying Panel
			JPanel panelL = new JPanel(new GridLayout(2, 1));
			panelL.add(new JLabel("Enter Username: "));
			panelL.add(username);
			panelL.add(Box.createHorizontalStrut(20));
			panelL.add(new JLabel("Enter Password: "));
			panelL.add(password);
			panelL.add(Box.createHorizontalStrut(20));
			JOptionPane.showMessageDialog(null, panelL);
			// Getting Panel Inputs as Strings
			String u = username.getText();
			String p = new String(password.getPassword());
			
			if(checkInput(u, p)) {		// Checking if User input is valid
				Login login = new Login(db, "Login", u, p);		// Checking User Login information
				if(login.getUserID() == -1) {
					JOptionPane.showMessageDialog(null, "Username/Password does not exist", "Login Menu", JOptionPane.PLAIN_MESSAGE);
				}
				else { 
					dispose();
					MainGUI mainMenu = new MainGUI(db, login.getUserID(), u);

				}
			}
		}
		
		private boolean checkInput(String u, String p) {
			if(u.equals("") || p.equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid Input: Input cannot be empty/blank", "Login Menu", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		/* This method handles the Exit button. If it is pressed then the database is closed
		 * and the program is ended.
		 * */
		private void handleExit() {
			db.close();
			System.exit(0);
		}
	}
	
	
}
