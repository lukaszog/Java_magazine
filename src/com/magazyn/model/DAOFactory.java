package com.magazyn.model;

import java.sql.SQLException;
import java.util.Map;

public abstract class DAOFactory {
	
	public static final int MYSQL = 0;
	public static final int ORACLE = 1;
	
	public abstract TableDAO getCategoryDAO();
	public abstract int insert(Map<String, String> valueMap, String table) throws SQLException;
	
	public static DAOFactory getFactory(int type){
		switch(type){
		case MYSQL:
			return new MySQLDAOFactory();
		case ORACLE:
			//return new OracleDAOFactory();
		default:
			return null;
		}
		
		
	}
}
