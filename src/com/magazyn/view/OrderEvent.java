package com.magazyn.view;

/**
 * @author Lukasz
 * This class is used to store data corresponding to the table
 */
public class OrderEvent {

    private int id;
    private String table;
    private int value;
    private String action;

    /**
     *
     * @param id
     * @param table
     * @param value
     * @param action
     */
    public OrderEvent(int id, String table, int value, String action) {
        this.id = id;
        this.table = table;
        this.value = value;
        this.action = action;
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
     * @return
     */
    public int getValue() {
        return value;
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {

        return id;
    }
}
