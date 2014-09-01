package com.magazyn.view;

/**
 * @author Lukasz
 *  * This class is used to store data corresponding to the table

 */
public class CompanyEvent{
	
	private String name;
	private String address;
	private String table;
	private String action;
	private int id;

    /**
     *
     * @param name
     * @param table
     * @param address
     * @param id
     * @param action
     */
	public CompanyEvent(String name, String table, String address, int id, String action){
		super();
		this.name = name;
		this.table = table;
		this.id = id;
		this.action = action;
		this.address = address;
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
     * @param address
     */
	public void setAddress(String address) {
		this.address = address;
	}

    /**
     *
     * @return
     */
	public String getTable() {
		return table;
	}

    /**
     *
     * @param table
     */
	public void setTable(String table) {
		this.table = table;
	}

    /**
     *
     * @return
     */
	public String getAction() {
		return action;
	}

    /**
     *
     * @param action
     */
	public void setAction(String action) {
		this.action = action;
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
	
	
	
}
