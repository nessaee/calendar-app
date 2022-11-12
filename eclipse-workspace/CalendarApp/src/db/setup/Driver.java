package db.setup;
public class Driver {

	public static void main(String[] args) {
		DB db = new DB("Data.db");
        //db.write(5, "ameer", "password");
        db.read("Users");

        db.close();
	}

}
