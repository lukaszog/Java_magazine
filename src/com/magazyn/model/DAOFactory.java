package com.magazyn.model;

public abstract class DAOFactory {
	
	public static final int MYSQL = 0;
	public static final int ORACLE = 1;
	
	public abstract CategoryDAO getCategoryDAO();
	
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
