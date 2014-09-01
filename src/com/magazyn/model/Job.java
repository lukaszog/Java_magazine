package com.magazyn.model;

/**
 *
 */
public enum Job {
	
	SELECT(1, "Select"),
	INSERT(2,"Insert");
	
	private int key;
	private String value;
	
	private Job(int key, String value){
		this.key = key;
		this.value =value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
