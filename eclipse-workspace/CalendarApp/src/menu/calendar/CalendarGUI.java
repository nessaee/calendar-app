package menu.calendar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

public class CalendarGUI extends JFrame{
	
	public CalendarGUI() {
		
	}
	/**
	 * 
	 */
	public void GUI() {
		
		JFrame frame = new JFrame("Calendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setLayout(new BorderLayout());
		
		//----------------------------------------
        UtilDateModel model = new UtilDateModel();
   
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
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		JButton select = new JButton("View Date");
		select.setBounds(EXIT_ON_CLOSE, ABORT, 100, 100);
		topPanel.add(datePicker);
		topPanel.setBounds(100, 100, 450, 450);
		middlePanel.setBounds(EXIT_ON_CLOSE, ABORT, 100, 100);
		bottomPanel.add(select);
		
		JPanel mainPanel = new JPanel();
	
		mainPanel.add(topPanel);
		//mainPanel.add(middlePanel);
		mainPanel.add(bottomPanel);
		
		
		//---------------------------------------------
//		pane.setDividerSize(0);
//		pane2.setDividerSize(0);
		frame.add(mainPanel);
		
		
		//--------------------------------------
		
		frame.setVisible(true);
	}
	
}
