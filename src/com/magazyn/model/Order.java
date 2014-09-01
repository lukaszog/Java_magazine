package com.magazyn.model;

/**
 * @author Lukasz
 * This class is used to store data corresponding to the table
 */
public class Order {
	
	private int id;
	private String date;
	private int id_client;
	private int id_item;
	private String client_name;
	private String client_lastname;
	private String product;
	private String address;
	private int done;

    /**
     *
     * @param id
     * @param date
     * @param id_client
     * @param id_item
     * @param client_name
     * @param client_lastname
     * @param product
     * @param address
     * @param done
     */
	public Order(int id, String date, int id_client, int id_item,
			String client_name, String client_lastname, String product, String address, int done) {
		this.id = id;
		this.date = date;
		this.id_client = id_client;
		this.id_item = id_item;
		this.client_name = client_name;
		this.client_lastname = client_lastname;
		this.product = product;
		this.address = address;
		this.done = done;
	}

    /**
     *
     * @return done
     */
	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

    /**
     *
     * @return address
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
     * @return id
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
     * @return date
     */
	public String getDate() {
		return date;
	}

    /**
     *
     * @return client_name
     */
	public String getClient_name() {
		return client_name;
	}
    /**
     *
     * @return client_lastname
     */
	public String getClient_lastname() {
		return client_lastname;
	}

    /**
     *
     * @return product
     */
	public String getProduct() {
		return product;
	}

	
	
	

}
