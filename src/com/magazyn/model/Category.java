package com.magazyn.model;

public class Category {
	
	private int id;
	private String name;
	private String table;

    /**
     *
     * @param id
     * @param name
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
 	public String toString() {
		return name;
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



}
