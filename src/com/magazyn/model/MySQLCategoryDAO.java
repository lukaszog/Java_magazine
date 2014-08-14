package com.magazyn.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoryDAO implements CategoryDAO{

 	public List<Category> getCategory() throws SQLException
	{
		List<Category> cat = new ArrayList<Category>();
		
		Connection conn = Database.getInstance().getConnection();
		
		System.out.println(conn);
		
		String sql = "select id, name from kategorie";
		Statement selectStatement =  conn.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next())
		{
			int id = results.getInt("id");
			String name = results.getString("name");
 
			Category category = new Category(id, name);
			cat.add(category);
		}
		
		results.close();
		selectStatement.close();
		
		return cat;
		
	}
}
