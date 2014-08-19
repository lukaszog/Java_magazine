package com.magazyn.model;

import java.sql.SQLException;
import java.util.List;
 
/*
 * * One DAO class person table or view
 */

public interface TableDAO {

	public List<Category> getCategory() throws SQLException;
	public List<Company> getCompany() throws SQLException;
	
}
