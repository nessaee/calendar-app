package db.setup;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		List<Object> rowData = new ArrayList<Object>();
		rowData.add(1);
		rowData.add("user");
		rowData.add("password");
		DB db = new DB("Data.db");
        db.addRow("Users", rowData);
        db.viewTable("Users");

        db.close();
	}

}
