package com.magazyn.model;

import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.magazyn.view.View;


public class MySQLDAOFactory extends DAOFactory {

 	public CategoryDAO getCategoryDAO() {
		return new MySQLCategoryDAO();
	}
 	
 	@Override
	public int insert(Map<String, String> valueMap, String table) throws SQLException {	
 	// TODO Auto-generated method stub
		
		Connection conn = Database.getInstance().getConnection();
		
		View view = new View();

		String val = "";
		String query = "";
		String question = "";
		Integer mapsize = valueMap.size();
		Integer x = 1;

		for (Map.Entry<String, String> entry : valueMap.entrySet()) {

			val += entry.getKey();
			question += "?";

			if (x < mapsize) {
				val += ", ";
				question += ", ";
			}
			mapsize--;
		}
		
		query = "insert into " + table + "(" + val + ") values ("+question+")";
		PreparedStatement add = conn.prepareStatement(query);
		
		for(Map.Entry<String, String> entry : valueMap.entrySet()){
			add.setObject(x,entry.getValue());
			x+=1;
		}
		int addtotable = add.executeUpdate();
		add.close();
		return addtotable;
	}
	 
	 

	
}
