package com.magazyn.model;

/**
 * @author Łukasz
 */
public class Company {
	
	private int id;
	private String name;
	private String address;

    /**
     *
     * @param id
     * @param name
     * @param address
     *
     */
	public Company(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

    /**
     *
     * @return
     */
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
	public String getAddress() {
		return address;
	}


    /**
     *
     * @return
     */
	public String toString() {
	        return this.name;
    }
	

}
