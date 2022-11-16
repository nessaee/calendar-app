package datatype;

import java.util.ArrayList;

public abstract class Node {
	private String label;
	private Integer currentID;
	private Integer parentID;
	
	public Node() {
		this.label = "unknown";
		this.currentID = 0;
		this.parentID = 0;
	}
	public Node(Integer parentID, Integer ID, String name){
		this.parentID = parentID;
		this.currentID = ID;
		this.label = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getID() {
		return currentID;
	}
	public void setID(Integer iD) {
		currentID = iD;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public String toString() {
		return "";
	}
	public ArrayList<Object> getRowData(){
		ArrayList<Object> rowData = new ArrayList<Object>();
		rowData.add(this.getParentID());
		rowData.add(this.getID());
		rowData.add(this.getLabel());
		return rowData;
	}
	
}

