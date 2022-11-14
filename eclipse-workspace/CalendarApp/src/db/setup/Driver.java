package db.setup;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		DB db = new DB("Data.db");
		List<Object> rowData = new ArrayList<Object>();
		/*
		rowData.add(1);
		rowData.add("user");
		rowData.add("password");
        db.saveRow("Users", rowData);
        */
        List<Object> test1_rowData = new ArrayList<Object>();
        test1_rowData = db.loadRow("Users", 1);
        System.out.println(test1_rowData);

        db.close();
	}

}
