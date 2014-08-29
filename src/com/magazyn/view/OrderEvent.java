package com.magazyn.view;

/**
 * Created by Łukasz on 2014-08-29.
 */
public class OrderEvent {

    private int id;
    private String table;
    private int value;
    private String action;



    public OrderEvent(int id, String table, int value, String action) {
        this.id = id;
        this.table = table;
        this.value = value;
        this.action = action;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}
