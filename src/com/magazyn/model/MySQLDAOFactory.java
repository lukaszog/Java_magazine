package com.magazyn.model;

public class MySQLDAOFactory extends DAOFactory {

 	public CategoryDAO getCategoryDAO() {
		return new MySQLCategoryDAO();
	}

	
}
