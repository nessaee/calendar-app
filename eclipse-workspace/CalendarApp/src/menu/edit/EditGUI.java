package menu.edit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.awt.event.*;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.table.DefaultTableModel;

import datatype.*;
import datatype.Event;
import menu.main.MainGUI.ActionPerformed;

import java.awt.*;
import java.io.*;
public class EditGUI extends JFrame{
	private EditController controller;
	private JPanel panel;
	private JMenuBar menuBar;		//the horizontal container
	private JMenu addMenu, removeMenu, viewMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	
	// File submenus
    
	//JMenuItem objects are added to JMenu objects as the drop down selections.
	private JMenuItem viewSets, viewCategories, viewEvents; 
	// Add Object
	private JMenuItem addSet, addCategory, addEvent; 
	// Remove Object
	private JMenuItem removeSet, removeCategory, removeEvent; 
	
	public EditGUI(String windowTitle, EditController EC) {
		super(windowTitle);
		this.controller = EC;
		setSize(300, 100);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JLabel("<HTML><center>Welcome to the Editor" +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildGUI();	
		setVisible(true);
	}
	
	
	public void buildGUI() {
		menuBar = new JMenuBar();
		
		/* View Menu */ 
		viewMenu = new JMenu("View");
	    viewSets = new JMenuItem ("Set");
	    viewCategories = new JMenuItem ("Category");
	    viewEvents = new JMenuItem ("Event");
	    
	    viewSets.addActionListener(new MenuListener());
	    viewCategories.addActionListener(new MenuListener());
	    viewEvents.addActionListener(new MenuListener());
	    
	    viewMenu.add(viewSets);
	    viewMenu.add(viewCategories);
	    viewMenu.add(viewEvents);
	    menuBar.add(viewMenu);
	    
		/* Add Menu */
		addMenu = new JMenu("Add");
	    addSet = new JMenuItem ("Set");
	    addCategory = new JMenuItem ("Category");
	    addEvent = new JMenuItem ("Event");
	    
	    addSet.addActionListener(new MenuListener());
	    addCategory.addActionListener(new MenuListener());
	    addEvent.addActionListener(new MenuListener());
	    
	    addMenu.add(addSet);
	    addMenu.add(addCategory);
	    addMenu.add(addEvent);
	    menuBar.add(addMenu);
	    
	    /* Remove Menu */
	    removeMenu = new JMenu("Remove");
	    removeSet = new JMenuItem ("Set");
	    removeCategory = new JMenuItem ("Category");
	    removeEvent = new JMenuItem ("Event");

	    removeSet.addActionListener(new MenuListener());
	    removeCategory.addActionListener(new MenuListener());
	    removeEvent.addActionListener(new MenuListener());

	    removeMenu.add(removeSet);
	    removeMenu.add(removeCategory);
	    removeMenu.add(removeEvent);
	    menuBar.add(removeMenu);
	    
		
	    setJMenuBar(menuBar);
	}
	
	private JFrame buildJFrame(String name, int width, int height ) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		return frame;
	}
	class ExitListener implements ActionListener{
    	ArrayList<JTextField> textFieldList;
    	ArrayList<String> data = new ArrayList<String>();
    	JFrame frame;
    	
    	public ExitListener(JFrame frame ) {
    		this.frame = frame;
    	}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.frame.dispose();
		}
	}
	class OKListener implements ActionListener{
    	ArrayList<JTextField> textFieldList;
    	ArrayList<String> data = new ArrayList<String>();
    	int option;
    
    	
    	public  OKListener(ArrayList<JTextField> textFieldList, int option) {
    		this.option = option;
    		this.textFieldList = textFieldList;
    	}
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		this.data.clear();
			String windowTitle = getErrorWindowTitle(this.option);

			if(checkEmpty(windowTitle)) return;
			
			for(JTextField textField : this.textFieldList) {
    			this.data.add(textField.getText());
    		}
			windowTitle = "Success";
			String successMessage = "";
			switch(this.option) {
			case 0: // Add Event 
				String eventName = data.get(0);
				String eventDescription = data.get(1);
				int eventDate = Integer.parseInt(data.get(2));
				int eventUrgency = Integer.parseInt(data.get(3));
				int eventParentID = Integer.parseInt(data.get(4));
				controller.addEvent(controller.createEvent(eventParentID, eventName, eventDescription, eventUrgency, eventDate));
				successMessage = "Event " + eventName + " has been added to your calendar";
				popupSuccess(windowTitle, successMessage);
				break;
			case 1: // Add Category 
				String categoryName = data.get(0);
				int categoryParentID = Integer.parseInt(data.get(1));
				controller.addCategory(controller.createCategory(categoryName, categoryParentID));
				successMessage = "Category " + categoryName + " has been added to your calendar";
				popupSuccess(windowTitle, successMessage);
				break;
			case 2: // Add Set
				String setName = data.get(0);
				controller.addSet(controller.createSet(setName));
				//System.out.println(controller.createSet(setName));
				controller.getUser().getCalendar().printCalendar();
				successMessage = "Set " + setName + "has been added to your calendar";
				//popupSuccess(windowTitle, successMessage);
				break;
				
			case 3: // Remove Set
				int setID = Integer.parseInt(data.get(0));
				controller.getDB().removeRow(setID);
				controller.getUser().getCalendar().removeSet(setID);
				break;
				 
			case 4: // Remove Category
				int categoryID = Integer.parseInt(data.get(0));
				controller.getDB().removeRow(categoryID);
				controller.getUser().getCalendar().removeCategory(categoryID);
				break;
			case 5: // Remove Event
				int eventID = Integer.parseInt(data.get(0));
				controller.getDB().removeRow(eventID);
				controller.getUser().getCalendar().removeEvent(eventID);
				break;
			case 6:
				break;
			case 7:
				break;
				
			}
			
		}
	
		
    	public void clearTextFields(ArrayList<JTextField> textFieldList) {
    		for(JTextField textField : this.textFieldList) {
    			textField.setText("");
    		}
    	}
    	
    	public void popupError(String title, String label) {
    		JOptionPane.showMessageDialog(new JFrame(), label, title, JOptionPane.ERROR_MESSAGE);
		}
    	
    	public void popupSuccess(String title, String label) {
    		JOptionPane.showMessageDialog(new JFrame(), label, title, JOptionPane.INFORMATION_MESSAGE);
    	}
    	
    	public String getErrorWindowTitle(int option) {
    		String windowTitle = "";
			switch(option) {
			case 0:
				windowTitle = "Error Creating Campus Course"; 
				break;
			case 1:
				windowTitle = "Error Creating Online Course"; 
				break;
			case 2:
				windowTitle = "Error Assigning Campus Course to Classroom"; 
				break;
			case 3:
				windowTitle = "Error Adding Department to University"; 
				break;
			case 4:
				windowTitle = "Error"; 
				break;
			case 5:
				windowTitle = "Error"; 
				break;
			}
			return windowTitle;
		}
    	
    	public boolean checkEmpty(String windowTitle) {
    		boolean emptyError = false;
			for(JTextField textField : this.textFieldList) {
				if(textField.getText().strip().equals("")) {
					emptyError = true;
					break;
				}
			}
			if(emptyError) {
				String errorMessage = "One or more text field does not contain an input, please try again.";
				popupError(windowTitle, errorMessage);
				clearTextFields(this.textFieldList);
			}
			return emptyError;		
    	}
	}
	
	public class MenuListener implements ActionListener{
		//this is the method MenuListener must implement, as it comes from the ActionListener interface.
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)(e.getSource());
			if(source.equals(addEvent)){
				handleAddEvent();
			}
			else if(source.equals(addCategory)){
				handleAddCategory();
			}
			else if(source.equals(addSet)){ 
				handleAddSet(); 
			}
			else if(source.equals(removeSet)){ 
				handleRemoveSet(); 
			}
			else if(source.equals(removeEvent)){ 
				handleRemoveEvent(); 
			}
			else if(source.equals(removeCategory)){ 
				handleRemoveCategory();
			}
			else if(source.equals(viewSets)){ 
				handleViewSets(); 
			}
			else if(source.equals(viewCategories)){
				handleViewCategories();
			}
			else if(source.equals(viewEvents)){ 
				handleViewEvents(); 
			}
		}
		
		private void handleAddEvent(){ 
			String[] labels = {
				"Name: ",
				"Description",
				"Parent ID:",
				"Date: ",
				"Urgency: "
			};
			// option 0: add event 
			createInputWindow("Add New Event", labels, 0, 300, 275);
		}
		private void handleAddCategory(){ 
			String[] labels = {"Name: ", "Parent ID"};
			// option 1: add category 
			createInputWindow("Create New Category", labels, 1, 300, 275);	
		}
		private void handleAddSet(){ 
			String[] labels = {"Name: "};
			// option 2: add set
			createInputWindow("Create New Set", labels, 2, 300, 200);
		}
		private void handleRemoveSet(){ 
			String[] labels = {"Set ID: "};
			// option 3: remove set
			createInputWindow("Remove Set", labels, 3, 300, 275);		
		}	
		private void handleRemoveEvent(){ 
			String[] labels = {"Event ID: ",};
			// option 3: remove event
			createInputWindow("Remove Event", labels, 4, 300, 200);
		}
		private void handleRemoveCategory(){ 
			String[] labels = {"Category ID: "};
			// option 5: remove category
			createInputWindow("Remove Category", labels, 5, 300, 275);	
		}
		
		public void handleViewSets() {
			String[] columnNames = {"Set ID", "Set Name"};
		    datatype.Calendar calendar = controller.getUser().getCalendar();
		    ArrayList<datatype.Set> sets = calendar.getSetList();
		    Object[][] setData = new Object[sets.size()][2];
		    int rowCount = 0;
		    for(datatype.Set s : sets) {
		    	setData[rowCount][0] = s.getID();
		    	setData[rowCount][1] = s.getLabel();
		    	rowCount += 1;
		    }
		    createDisplayWindow("View Sets", columnNames, setData);
		   
		}
		public void handleViewCategories() {
			String[] columnNames = {"Parent ID", "Category ID", "Category Name"};
			datatype.Calendar calendar = controller.getUser().getCalendar();
		    ArrayList<Category> categories = calendar.getCategoryList();
		    Object[][] categoryData = new Object[categories.size()][3];
		    int rowCount = 0;
		    for(datatype.Category c : categories) {
		    	categoryData[rowCount][0] = c.getParentID();
		    	categoryData[rowCount][1] = c.getID();
		    	categoryData[rowCount][2] = c.getLabel();
		    	rowCount += 1;
		    }
		    
		    createDisplayWindow("View Categories", columnNames, categoryData);
			
		}
		public void handleViewEvents() {
			String[] columnNames = {"Parent ID", "Event ID", "Event Name", "Description", "Date", "Urgency"};
			datatype.Calendar calendar = controller.getUser().getCalendar();
		    ArrayList<Event> events = calendar.getEventList();
		    Object[][] eventData = new Object[events.size()][6];
		    int rowCount = 0; 
		    for(Event e : events) {
		    	eventData[rowCount][0] = e.getParentID();
		    	eventData[rowCount][1] = e.getID();
		    	eventData[rowCount][2] = e.getLabel();
		    	eventData[rowCount][3] = e.getDescription();
		    	eventData[rowCount][4] = e.getDate();
		    	eventData[rowCount][5] = e.getUrgency();
		    	rowCount += 1;
		    }
		    createDisplayWindow("View Events", columnNames, eventData);
			
		}

		private void createDisplayWindow(String windowTitle, String[] columnNames, Object[][] data) {
			
			JFrame frame = buildJFrame(windowTitle, 300, 300);
			Container contentPane = frame.getContentPane();
	
			DefaultTableModel tableModel = new DefaultTableModel();
			JTable table = new JTable();
			for(String columnName : columnNames){
				tableModel.addColumn(columnName);
			}
			for(Object[] o : data) {
				tableModel.addRow(o);
			}
		    
		
		    table.setModel(tableModel);
		    
		    JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
	        contentPane.setLayout(new BorderLayout ());
	        contentPane.add(scrollPane, BorderLayout.CENTER);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		}
		private void createInputWindow(String name, String[] labels, int option, int width, int height) {
			JFrame frame = buildJFrame(name, width, height);
			JComponent panel = new JPanel();
			GroupLayout layout = new GroupLayout(panel);
			panel.setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);	
			ParallelGroup labelGroup = layout.createParallelGroup();
			ParallelGroup textGroup = layout.createParallelGroup();
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
			for(String l : labels) {
				JLabel label = new JLabel(l);
				JTextField textField = new JTextField(10);
				textFieldList.add(textField);
				labelGroup.addComponent(label);
				textGroup.addComponent(textField);
				vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(textField));
			}
			hGroup.addGroup(labelGroup);
			hGroup.addGroup(textGroup);
			layout.setHorizontalGroup(hGroup);
			layout.setVerticalGroup(vGroup);
			frame.add(panel);	
			panel = new JPanel();
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new OKListener(textFieldList, option));
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ExitListener(frame));
			panel.add(okButton);
			panel.add(cancelButton);
			frame.add(panel);
			frame.add(panel);
	        frame.setVisible(true);
		}
		
	}
}
