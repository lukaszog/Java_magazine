package com.magazyn.view;

public class CategoryEvent {

	private String name;
	private String table;
	private String action;
	private int id;

    /**
     *
     * @param name
     * @param table
     * @param id
     * @param action
     */
	public CategoryEvent(String name, String table, int id, String action){
		super();
		this.name = name;
		this.table = table;
		this.id = id;
		this.action = action;
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
     * @return
     */
	public String getAction(){
		return action;
	}

    /**
     *
     * @param action
     */
	public void setAction(String action){
		this.action = action;
	}

    /**
     *
     * @return
     */
	public int getId(){
		return id;
	}

    /**
     *
     * @param id
     */
	public void setId(int id){
		this.id = id;
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
