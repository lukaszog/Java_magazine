package com.magazyn.model;

public class Item {
	
	private int id;
	private String category;
	private String company;
	private String name;
	
	public Item(int id, String category, String company, String name) {
		this.id = id;
		this.category = category;
		this.company = company;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
