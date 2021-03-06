package com.magazyn.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Lukasz
 * @see com.magazyn.model.DAOFactory
 */

public class MySQLDAOFactory extends DAOFactory {

	public TableDAO getCategoryDAO() {
		return new dbDAO();
	}

	private String val = "";
	private String query = "";
	private String question = "";
	private String updateval = "";
	private int x = 1;

    /**
     *
     * Prepare query
     * @param valueMap map of values
     */
	public void prepare(Map<Object, Object> valueMap) {

		Integer mapsize = valueMap.size();
		Integer x = 1;

		for (Map.Entry<Object, Object> entry : valueMap.entrySet()) {

			val += entry.getKey();
			question += "?";

			updateval += entry.getKey() + " = ?";

			if (x < mapsize) {
				val += ", ";
				question += ", ";
				updateval += ",";
			}
			mapsize--;
		}
	}

    /**
     *
     * @param table table name
     * @param id record id
     * @return execute query
     * @throws SQLException
     */
	public int delete(String table, int id) throws SQLException{
		
		Connection conn = Database.getInstance().getConnection();
		
		query = "delete from "+table+" where id="+id;
		PreparedStatement add = conn.prepareStatement(query);
		int delete = add.executeUpdate();

        System.out.println(query);
		
		add.close();
		return delete;

	}

    /**
     *
     * Prepare query
     * @param type typ zapytania: insert lub update
     * @param valueMap Mapa z wartościami
     * @param table nazwa tabeli
     * @param id przechowanie id rekorku który ma być zmodyfikowany
     * @return zwraca wynik z executeUpdate, czyli wykonanie zapytania
     * @throws SQLException
     */
	@Override
	public int question(String type, Map<Object, Object> valueMap,
			String table, int id) throws SQLException {

		System.out.println("question");
		
		prepare(valueMap);
		Connection conn = Database.getInstance().getConnection();
		if (type.equals("insert")) {
			query = "insert into " + table + "(" + val + ") values ("
					+ question + ")";
            System.out.println(question);
		} else if (type.equals("update")) {
			query = "update " + table + " set " + updateval + " where id = "
					+ id;
		}
		
		System.out.println(query);
		PreparedStatement add = conn.prepareStatement(query);
		for (Map.Entry<Object, Object> entry : valueMap.entrySet()) {
			add.setObject(x, entry.getValue());
			x += 1;
            System.out.println(entry.getValue());
		}
		int addtotable = add.executeUpdate();
		add.close();
		return addtotable;
	}

}