package menu.edit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.awt.event.*;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import datatype.*;
import datatype.Event;
import menu.main.MainGUI;
import menu.main.MainGUI.ActionPerformed;

import java.awt.*;
import java.io.*;
public class EditGUI extends JFrame{
	private EditController controller;
	private JPanel panel;
	private JMenuBar menuBar;		//the horizontal container
	private JMenu addMenu, removeMenu, viewMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	
	// File submenus
    
	// View Objects
	private JMenuItem viewSets, viewCategories, viewEvents, viewAll; 
	// Add Object
	private JMenuItem addSet, addCategory, addEvent; 
	// Remove Object
	private JMenuItem removeSet, removeCategory, removeEvent; 
	private JButton exitButton;
	public EditGUI(String windowTitle, EditController EC) {
		super(windowTitle);
		
		this.controller = EC;
		//this.controller.getUser().updateCalendar(this.controller.getDB());
		//this.controller.getUser().getCalendar().printCalendar();
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
	    viewAll = new JMenuItem ("All");
	    
	    viewSets.addActionListener(new MenuListener());
	    viewCategories.addActionListener(new MenuListener());
	    viewEvents.addActionListener(new MenuListener());
	    viewAll.addActionListener(new MenuListener());
	    
	    viewMenu.add(viewSets);
	    viewMenu.add(viewCategories);
	    viewMenu.add(viewEvents);
	    viewMenu.add(viewAll);
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
	  
	    exitButton = new JButton("Exit");
		exitButton.setBounds(1500, 1000, 80, 30);
		exitButton.setBackground(Color.red);
		exitButton.addActionListener(new ButtonListener(this));
		this.add(exitButton);
	}
	
	private JFrame buildJFrame(String name, int width, int height ) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		return frame;
	}
	
	class ButtonListener implements ActionListener{
		JFrame frame;
		public ButtonListener(JFrame frame) {
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) (e.getSource());	
			if(source.equals(exitButton)) {
				handleExit();
			}
		}
		public void handleExit() {
			MainGUI main = new MainGUI(controller.getDB(), controller.getUser().getUserID(), controller.getUser().getUsername());
			frame.dispose();
		}
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
    	JFrame frame;
    
    	
    	public  OKListener(JFrame frame, ArrayList<JTextField> textFieldList, int option) {
    		this.frame = frame;
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
				int eventParentID = Integer.parseInt(data.get(0));
				String eventName = data.get(1);
				String eventDescription = data.get(2);
				int eventDate = Integer.parseInt(data.get(3));
				int eventUrgency = Integer.parseInt(data.get(4));
				controller.addEvent(controller.createEvent(eventParentID, eventName, eventDescription, eventUrgency, eventDate));
				successMessage = "Event " + eventName + " has been added to your calendar";
				popupSuccess(windowTitle, successMessage);
				break;
			case 1: // Add Category 
				int categoryParentID = Integer.parseInt(data.get(0));
				String categoryName = data.get(1);
				controller.addCategory(controller.createCategory(categoryName, categoryParentID));
				successMessage = "Category " + categoryName + " has been added to your calendar";
				popupSuccess(windowTitle, successMessage);
				break;
			case 2: // Add Set
				String setName = data.get(0);
				controller.addSet(controller.createSet(setName));
				//System.out.println(controller.createSet(setName));
				//controller.getUser().getCalendar().printCalendar();
				successMessage = "Set " + setName + " has been added to your calendar";
				popupSuccess(windowTitle, successMessage);
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
			frame.dispose();
			
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
			if      (source.equals(addEvent))       handleAddEvent();		
			else if (source.equals(addCategory))    handleAddCategory();		
			else if (source.equals(addSet))         handleAddSet(); 		
			else if (source.equals(removeSet))      handleRemoveSet(); 		
			else if (source.equals(removeEvent))    handleRemoveEvent(); 		
			else if (source.equals(removeCategory)) handleRemoveCategory();		
			else if (source.equals(viewSets))       handleViewSets(); 		
			else if (source.equals(viewCategories)) handleViewCategories();			
			else if (source.equals(viewEvents))     handleViewEvents(); 		
			else if (source.equals(viewAll))        handleViewAll(); 
			
		}
		
		// option 0: add event 
		private void handleAddEvent(){ 
			String[] labels = {
				"Parent ID:",
				"Name: ",
				"Description",
				"Date: ",
				"Urgency: "
			};
			createInputWindow("Add New Event", labels, 0, 300, 275);
		}
		// option 1: add category 
		private void handleAddCategory(){ 
			String[] labels = {"Parent ID", "Name: "};
			createInputWindow("Create New Category", labels, 1, 300, 275);	
		}
		// option 2: add set
		private void handleAddSet(){ 
			String[] labels = {"Name: "};
			createInputWindow("Create New Set", labels, 2, 300, 200);
		}
		// option 3: remove set
		private void handleRemoveSet(){ 
			String[] labels = {"Set ID: "};
			createInputWindow("Remove Set", labels, 3, 300, 275);		
		}	
		// option 4: remove event
		private void handleRemoveEvent(){ 
			String[] labels = {"Event ID: "};
			createInputWindow("Remove Event", labels, 4, 300, 200);
		}
		// option 5: remove category
		private void handleRemoveCategory(){ 
			String[] labels = {"Category ID: "};
			createInputWindow("Remove Category", labels, 5, 300, 275);	
		}
		
		public void handleViewSets() {
			JFrame frame = buildJFrame("View Sets", 300, 300);
			String[] columnNames = {"Location", "Set ID", "Set Name"};
			int[] columnWidths = {40,60};
		    Object[][] setData = this.getSetTableData();
		    frame = createDisplayWindow(frame, columnNames, columnWidths, setData, 300, 300);
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		}
		public void handleViewCategories() {
			JFrame frame = buildJFrame("View Categories", 300, 300);
			String[] columnNames = {"Location", "Parent ID", "Category ID", "Category Name"};
			int[] columnWidths = {10,10,80};
			Object[][] categoryData = this.getCategoryTableData();
		    frame = createDisplayWindow(frame, columnNames, columnWidths, categoryData, 300, 300);
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		}
		public void handleViewEvents() {
			JFrame frame = buildJFrame("View Events", 400, 300);
			String[] columnNames = {"Location", "Parent ID", "Event ID", "Event Name", "Description", "Date", "Urgency"};
			int[] columnWidths = {3, 3, 34, 35, 15, 10};
		    Object[][] eventData = this.getEventTableData();
		    frame = createDisplayWindow(frame, columnNames, columnWidths, eventData, 400, 300);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
		}
		
		public void handleViewAll() {
			JFrame frame = buildJFrame("View All", 750, 300);
			frame.setLocationRelativeTo(null);
			String[] eventColumnNames = {"Location", "pID", "eID", "Event Name", "Description", "Date", "Urgency"};
			int[] eventColumnWidths = {3, 3, 34, 35, 15, 10};
			Object[][] eventData = this.getEventTableData();
			JTable eventTable = this.createTable(eventColumnNames, eventData, eventColumnWidths);
			JScrollPane eventSP = this.createScrollPane(eventTable, 400, 300);
			
			String[] categoryColumnNames = {"Location", "pID", "cID", "Category Name"};
			int[] categoryColumnWidths = {10,10,80};
			Object[][] categoryData = this.getCategoryTableData();
			JTable categoryTable = this.createTable(categoryColumnNames, categoryData, categoryColumnWidths);
			JScrollPane categorySP = this.createScrollPane(categoryTable, 200, 300);
			
			String[] setColumnNames = {"Location", "sID", "Set Name"};
			int[] setColumnWidths = {40,60};
		    Object[][] setData = this.getSetTableData();
		    JTable setTable = this.createTable(setColumnNames, setData, setColumnWidths);
		    JScrollPane setSP = this.createScrollPane(setTable, 150, 300);
		    
		    frame = this.createDisplayWindow(frame, eventSP, categorySP, setSP);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        //frame.setPreferredSize(new Dimension(frameDimension[0],frameDimension[1]));
	        frame.pack();
	        frame.setVisible(true);
		}

		private JFrame createDisplayWindow(JFrame frame, String[] columnNames, int[] columnWidths, Object[][] data, int dimensionX, int dimensionY) {	
			Container contentPane = frame.getContentPane();
			JTable table = createTable(columnNames, data, columnWidths);
			JScrollPane scrollPane = this.createScrollPane(table, dimensionX, dimensionY);
	        contentPane.setLayout(new BorderLayout ());
	        contentPane.add(scrollPane, BorderLayout.CENTER);
	        return frame;
	       
		}
		
		private JFrame createDisplayWindow(JFrame frame, JScrollPane eventSP, JScrollPane categorySP, JScrollPane setSP) {
			Container contentPane = frame.getContentPane();
	        contentPane.setLayout(new BorderLayout ());
	        contentPane.add(setSP, BorderLayout.WEST);
	        contentPane.add(categorySP, BorderLayout.CENTER);
	        contentPane.add(eventSP, BorderLayout.EAST);
	        return frame;
	       
		}
		
		
		private JScrollPane createScrollPane(JTable table,  int dimensionX, int dimensionY) {
			Dimension dimension = new Dimension(dimensionX, dimensionY);
		    JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(dimension);
			//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        return scrollPane;
	       
		}
		
		private JTable createTable(String[] columnNames, Object[][] data, int[] columnWidths) {
		
			DefaultTableModel tableModel = new DefaultTableModel();
			for(String columnName : columnNames) tableModel.addColumn(columnName);
			for(Object[] o : data) tableModel.addRow(o);
			JTable table = new JTable();
			DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
		    renderer.setHorizontalAlignment( SwingConstants.RIGHT );
		    table.setModel(tableModel);
		    //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		    int i = 0;
			for(int width : columnWidths) {
				table.getColumnModel().getColumn(i).setPreferredWidth(width);
				i++;
			}
		    return table;
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
			JButton okButton = new JButton("Save");
			okButton.addActionListener(new OKListener(frame, textFieldList, option));
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ExitListener(frame));
			panel.add(okButton);
			panel.add(cancelButton);
			frame.add(panel);
			frame.add(panel);
	        frame.setVisible(true);
		}
		public Object[][] getEventTableData() {
			ArrayList<Event> events = controller.getUser().getCalendar().getEventList();
		    Object[][] eventData = new Object[events.size()][7];
		    int rowCount = 0; 
		    for(Event e : events) {
		    	eventData[rowCount][0] = controller.getDB().getName(e.getParentID());
		    	eventData[rowCount][1] = e.getParentID();
		    	eventData[rowCount][2] = e.getID();
		    	eventData[rowCount][3] = e.getLabel();
		    	eventData[rowCount][4] = e.getDescription();
		    	eventData[rowCount][5] = e.getDate();
		    	eventData[rowCount][6] = e.getUrgency();
		    	rowCount += 1;
		    }
			return eventData;
		}
		
		public Object[][] getCategoryTableData() {
			ArrayList<Category> categories = controller.getUser().getCalendar().getCategoryList();
		    Object[][] categoryData = new Object[categories.size()][4];
		    int rowCount = 0;
		    for(datatype.Category c : categories) {
		    	categoryData[rowCount][0] = controller.getDB().getName(c.getParentID());
		    	categoryData[rowCount][1] = c.getParentID();
		    	categoryData[rowCount][2] = c.getID();
		    	categoryData[rowCount][3] = c.getLabel();
		    	rowCount += 1;
		    }
		    return categoryData;
		}
		
		public Object[][] getSetTableData() {
			ArrayList<datatype.Set> sets = controller.getUser().getCalendar().getSetList();
		    Object[][] setData = new Object[sets.size()][3];
		    int rowCount = 0;
		    
		    for(datatype.Set s : sets) {
		    	setData[rowCount][0] = controller.getUser().getUsername();
		    	setData[rowCount][1] = s.getID();
		    	setData[rowCount][2] = s.getLabel();
		    	rowCount += 1;
		    }
		    
			return setData;
		}
		
	}
}
