package com.magazyn.view;

public class ItemsEvent {
	private int id;
	private String category;
	private int id_category;
	private int id_company;
	private String company;
	private String name;
	private String table;
	private String action;

    /**
     *
     * @param id
     * @param table
     * @param category
     * @param company
     * @param name
     * @param id_category
     * @param id_company
     * @param action
     */
	public ItemsEvent(int id, String table, String category, String company,
			String name, int id_category, int id_company, String action) {
		this.id = id;
		this.category = category;
		this.company = company;
		this.name = name;
		this.id_category = id_category;
		this.id_company = id_company;
		this.action = action;
		this.table = table;
	}

    /**
     *
     * @return
     */
	public int getId_category() {
		return id_category;
	}

    /**
     *
     * @param id_category
     */
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

    /**
     *
     * @return
     */
	public int getId_company() {
		return id_company;
	}

    /**
     *
     * @param id_company
     */
	public void setId_company(int id_company) {
		this.id_company = id_company;
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
     * @return
     */
	public int getIdCategory() {
		return id_category;
	}

    /**
     *
     * @return
     */
	public int getIdCompany() {
		return id_company;
	}

    /**
     *
     * @param id_category
     */
	public void setIdCategory(int id_category) {
		this.id_category = id_category;
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
}
