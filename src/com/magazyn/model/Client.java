package com.magazyn.model;

public class Client {
	
	private int id;
	private String name;
	private String lastname;
	private String address;
	
	Client(int id, String name, String lastname, String address){
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
