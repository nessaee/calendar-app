package datatype;

public abstract class Node {
	private String label;
	private Integer cID;
	private Integer pID;
	
	public Node() {
		this.label = "unknown";
		this.cID = 0;
		this.pID = 0;
	}
	public Node(String name, Integer ID, Integer parentID){
		this.label = name;
		this.cID = ID;
		this.pID = parentID;
	}
	public String getName() {
		return label;
	}
	public void setName(String name) {
		this.label = name;
	}
	public Integer getID() {
		return cID;
	}
	public void setID(Integer iD) {
		cID = iD;
	}
	public Integer getParentID() {
		return pID;
	}
	public void setParentID(Integer parentID) {
		this.pID = parentID;
	}
	
}

