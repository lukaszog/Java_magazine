package com.magazyn.model;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Lukasz
 */
/*
 * * One DAO class person table or view
 */

public interface TableDAO {

    /**
     *
     * @return
     * @throws SQLException
     */
	public List<Category> getCategory() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
	public List<Company> getCompany() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
	public List<Client> getClient() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
	public List<Order> getOrder() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
	public List<Item> getItem() throws SQLException;
	
}
