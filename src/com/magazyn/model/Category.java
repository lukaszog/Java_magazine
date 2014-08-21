package com.magazyn.model;

public class Category {
	
	private int id;
	private String name;
	private String table;
 	
	public Category(int id, String name)
	{
		this.name = name;
		this.id  = id;
 	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
 	public String toString() {
		return name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}

}
