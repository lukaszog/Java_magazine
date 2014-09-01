package com.magazyn.model;

/**
 * @author Lukasz
 * Category class, using to load data from database to objects
 */


public class Category {
	
	private int id;
	private String name;
	private String table;

    /**
     *
     * @param id category ID
     * @param name category name
     */

	public Category(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

	public int getId() {
		return id;
	}

    /**
     *
     * @param id id
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     *
     * @return name
     */
	public String getName() {
		return name;
	}

    /**
     *
     * @param name name
     */

	public void setName(String name) {
		this.name = name;
	}

    /**
     *
     * @return name
     */
 	public String toString() {
		return name;
	}

    /**
     *
     * @return name
     */
	public String getTable() {
		return table;
	}

    /**
     *
     * @param table table
     */
	public void setTable(String table) {
		this.table = table;
	}



}
