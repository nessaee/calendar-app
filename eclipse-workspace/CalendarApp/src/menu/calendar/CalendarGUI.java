package menu.calendar;

import datatype.*;
import db.*;
import db.setup.DB;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;
import menu.*
;
import menu.main.User;

public class CalendarGUI extends JFrame{
	
	private int DateSelected;
	private UtilDateModel model = new UtilDateModel();
	JFrame frame;
	User user;
	DB db;
	public CalendarGUI(User user,DB db) {
		this.frame = new JFrame("Calendar");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(400,300);
		this.frame.setLayout(new BorderLayout());
		this.frame.setLocationRelativeTo(null);
		this.user = user;
		this.db = db;
	}
	/**
	 * 
	 */
	public void CalendarPage() {
		
		//----------------------------------------
       
       
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setSize(450, 450);
		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		
		datePicker.setBounds(100, 100, 800, 800); //doesn't work
		//model.setSelected(true);
		
		datePicker.setVisible(true);
	
		
		//-------------------------------------
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		JButton select = new JButton("View Date");
		select.addMouseListener(new ViewEventsListener());
		select.setBounds(EXIT_ON_CLOSE, ABORT, 100, 100);
		topPanel.add(datePicker);
		topPanel.setBounds(100, 100, 450, 450);
		bottomPanel.add(select);
		
		JPanel mainPanel = new JPanel();
	
		mainPanel.add(topPanel);
		//mainPanel.add(middlePanel);
		mainPanel.add(bottomPanel);
		
		
		//---------------------------------------------

		frame.add(mainPanel);
		
		//--------------------------------------
		
		frame.setVisible(true);
	}
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "MM/dd/yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }
	}
	
	
	
	private class ViewEventsListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			//display new menu 
			System.out.println("Button Clicked");
			//close current page
			frame.getContentPane().removeAll();
			frame.revalidate();
			frame.repaint();
			//open next page (Month starts at 0 for some reason)
			ViewDay(new datatype.Date(model.getMonth() + 1 ,model.getDay(),model.getYear()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void ViewDay(datatype.Date D) {
		
		
		System.out.println(D);
		frame.resize(650, 300);
		
		JTable table = new JTable();
		
		String[] Columns = {"Name", "Description","Urgency","Location"};
		DefaultTableModel model = new DefaultTableModel();
		
		//-----getting numeventa and creating data array-----
		int numEvents = 0;
		for(Event e : user.getCalendar().getEventList()) {
			if (e.getDate().toInt().equals(D.toInt())) numEvents++;
		}
		Object[][] eventData = new Object[numEvents][4];
		//---------------------------------------------------
		
		//model.setColumnIdentifiers(Columns);
		//2d data array
		int rowCount = 0;
		for(Event e : user.getCalendar().getEventList()) {

			
			if (e.getDate().toInt().equals(D.toInt())) {
				//Display event title
				System.out.println(e.getLabel());
				eventData[rowCount][0] = e.getLabel();
				//Display event description
				eventData[rowCount][1] = e.getDescription();
				
				//Display Urgency event is a part of
				eventData[rowCount][2] = e.getUrgency();
				
				eventData[rowCount][3] = db.getName(e.getParentID());
				//eventData[rowCount][3] = "";
				rowCount++;
			}
		}
		
		for(String columnName : Columns){
			model.addColumn(columnName);
		}
		for(Object[] o : eventData) {
			model.addRow(o);
		}
		
		table.setModel(model);
		//------resize columns---------
		int[] columnWidths = {20,50,5,25};
		int i = 0;
		for(int width : columnWidths) {
			table.getColumnModel().getColumn(i).setPreferredWidth(width);
			i++;
		}
		table.setRowHeight(50);
		
		//-------------------------------
		
		
		table.setAutoCreateColumnsFromModel(true);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		//frame.add(table);
		frame.setVisible(true);
	}

}
