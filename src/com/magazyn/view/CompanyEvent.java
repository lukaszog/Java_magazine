package com.magazyn.view;

public class CompanyEvent{
	
	private String name;
	private String address;
	private String table;
	private String action;
	private int id;
 	
	public CompanyEvent(String name, String table, String address, int id, String action){
		super();
		this.name = name;
		this.table = table;
		this.id = id;
		this.action = action;
		this.address = address;
 	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	
	
}
