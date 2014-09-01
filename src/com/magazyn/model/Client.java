package com.magazyn.model;

/**
 * @author Łukasz
 *
 */
public class Client {
	
	private int id;
	private String name;
	private String lastname;
	private String address;

    /**
     *
     * @param id
     * @param name
     * @param lastname
     * @param address
     */
	Client(int id, String name, String lastname, String address){
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}

    /**
     *
     * @param id
     */
	public void setId(int id) {
		this.id = id;
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
	public String getLastname() {
		return lastname;
	}

    /**
     *
     * @return
     */
	public String getAddress() {
		return address;
	}

}
