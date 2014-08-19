package com.magazyn.view;

public interface AppListener {
	
	public void onOpen();
	public void onClose();
	public void getCategory();
	public void getCompany();
	public void getOrder();
	public void getItem();
	public void getClient();
	public void deleteRow(CompanyEvent event);
	
}
