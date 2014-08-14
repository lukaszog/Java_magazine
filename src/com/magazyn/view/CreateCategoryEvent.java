package com.magazyn.view;

public class CreateCategoryEvent {

	private String name;
	private String table;
 	
	public CreateCategoryEvent(String name, String table){
		super();
		this.name = name;
		this.table = table;
 	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
}
