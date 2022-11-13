package datatype;

import java.util.ArrayList;

public class Set extends Node{
	private ArrayList<Category> categoryList;

	public Set() {
		categoryList = new ArrayList<Category>();
	}
	
	public void importExisting() {
		
	}
	public void addCollection(Category aCategory) {
		this.categoryList.add(aCategory);
	}
	public String toString() {
		return this.getName();
	}
}
