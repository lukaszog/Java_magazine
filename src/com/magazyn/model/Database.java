package com.magazyn.model;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Łukasz
 *
 * Connect do database
 *
 */
public class Database extends Thread {

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
