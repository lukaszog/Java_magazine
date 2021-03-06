package com.magazyn.model;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Lukasz
 *
 * Connect do database
 *
 */
public class Database {

    /**
     *
     */
	
	private static Database instance = new Database(); /** get instance */
	
	private Connection conn;

    /**
     *
     */
	private Database() {
		try {
			this.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     *
     * @return instance
     */
	public static Database getInstance() {
		return instance;
	}

    /**
     *
     * @return conn
     */
	public Connection getConnection() {
		return conn;
	}

    /**
     * Connect
     * @throws Exception
     */
	public void connect() throws Exception {

		if (conn != null)
			return;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = String.format("jdbc:mysql://localhost:%d/firma_magazyn?useUnicode=true&characterEncoding=utf8&autoReconnect=true",3306);

		conn = DriverManager.getConnection(url, "root", "");

	}

    /**
     * Disconnect
     */
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
