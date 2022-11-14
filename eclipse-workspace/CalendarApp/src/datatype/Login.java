package datatype;

import db.setup.DB;

public class Login {
	
	private String username;
	private String password;
	
	public Login() {
		this.username = "";
		this.password = "";
	}
	
	public Login(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String u) {
		this.username = u;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}
	
	public int checkExists(DB db) {
		if(EXISTS IN db) {
			return userID;
		}
		
		return -1;
	}
}
