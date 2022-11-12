package datatype;

public abstract class Node {
	private String name;
	private String ID;
	private String parentID;
	
	public Node() {
		this.name = "unknown";
		this.ID = "unknown";
		this.parentID = "unknown";
	}
	public Node(String name, String ID, String parentID){
		this.name = name;
		this.ID = ID;
		this.parentID = parentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	
}

