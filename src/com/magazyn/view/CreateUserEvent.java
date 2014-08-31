package com.magazyn.view;

/**
 *
 */
public class CreateUserEvent {
	
	private String name;
	private String password;

    /**
     *
     * @param name
     * @param password
     */
	public CreateUserEvent(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

    /**
     *
     * @return
     */
	public String getName() {
		return name;
	}

    /**
     *
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     *
     * @return
     */
	public String getPassword() {
		return password;
	}

    /**
     *
     * @param password
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
