package com.magazyn.view;

public class CreateUserEvent {
	
	private String name;
	private String password;
	
	public CreateUserEvent(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
