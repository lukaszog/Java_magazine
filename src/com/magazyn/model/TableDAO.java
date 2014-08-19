package com.magazyn.model;

import java.sql.SQLException;
import java.util.List;
 
/*
 * * One DAO class person table or view
 */

public interface TableDAO {

	public List<Category> getCategory() throws SQLException;
	public List<Company> getCompany() throws SQLException;
	public List<Client> getClient() throws SQLException;
	public List<Order> getOrder() throws SQLException;
	public List<Item> getItems() throws SQLException;
	
}
