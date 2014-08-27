package com.magazyn.view;

public class ItemsEvent {
	private int id;
	private String category;
	private int id_category;
	private int id_company;
	private String company;
	private String name;
	private String table;
	private String action;

	public ItemsEvent(int id, String table, String category, String company,
			String name, int id_category, int id_company, String action) {
		this.id = id;
		this.category = category;
		this.company = company;
		this.name = name;
		this.id_category = id_category;
		this.id_company = id_company;
		this.action = action;
		this.table = table;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
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

	public int getIdCategory() {
		return id_category;
	}

	public int getIdCompany() {
		return id_company;
	}

	public void setIdCategory(int id_category) {
		this.id_category = id_category;
	}

	public void setIdCompany(int id_company) {
		this.id_company = id_company;
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
