package com.magazyn.model;

public class Order {
	
	private int id;
	private String date;
	private int id_client;
	private int id_item;
	private String client_name;
	private String client_lastname;
	private String product;
	
	public Order(int id, String date, int id_client, int id_item,
			String client_name, String client_lastname, String product) {
		this.id = id;
		this.date = date;
		this.id_client = id_client;
		this.id_item = id_item;
		this.client_name = client_name;
		this.client_lastname = client_lastname;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_lastname() {
		return client_lastname;
	}

	public void setClient_lastname(String client_lastname) {
		this.client_lastname = client_lastname;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	
	
	
	

}
