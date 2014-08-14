package com.magazyn.model;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database extends Thread {
	
	private static Database instance = new Database();
	
	private Connection conn;
	
	private Database() {
		try {
			this.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void connect() throws Exception {
        System.out.println("MyThread - START "+Thread.currentThread().getName());

		if (conn != null)
			return;

		try {
            Thread.sleep(1000);
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = String.format("jdbc:mysql://localhost:%d/firma_magazyn",3306);

		conn = DriverManager.getConnection(url, "root", "");
        System.out.println("MyThread - END "+Thread.currentThread().getName());

	}
	
	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
		conn = null;
	}
	
}
