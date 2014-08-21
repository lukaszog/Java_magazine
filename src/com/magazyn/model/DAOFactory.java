package com.magazyn.model;

import java.sql.SQLException;
import java.util.Map;

public abstract class DAOFactory {
	
	public static final int MYSQL = 0;
	public static final int ORACLE = 1;
	
	public abstract TableDAO getCategoryDAO();
	public abstract int question(String type, Map<Object, Object> valueMap, String table, int id) throws SQLException;
	public abstract int delete(String table, int id) throws SQLException;
	
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
