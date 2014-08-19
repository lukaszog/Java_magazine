package com.magazyn.view;

public class CategoryEvent {

	private String name;
	private String table;
	private String action;
	private int id;
 	
	public CategoryEvent(String name, String table, int id, String action){
		super();
		this.name = name;
		this.table = table;
		this.id = id;
		this.action = action;
 	}	
	public String getName() {
		return name;
	}
	public String getAction(){
		return action;
	}
	public void setAction(String action){
		this.action = action;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
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
