package menu.calendar;

import java.util.Properties;

import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CalendarGUI {
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	
	public CalendarGUI() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		f.setVisible(true);
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,null);
		f.add(datePicker);
	}
	
}
