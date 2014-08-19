package com.magazyn.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.magazyn.model.DAOFactory;
import com.magazyn.model.Database;
import com.magazyn.model.Model;
import com.magazyn.view.AppListener;
import com.magazyn.view.CategoryEvent;
import com.magazyn.view.CompanyEvent;
import com.magazyn.view.CreateCategoryListener;
import com.magazyn.view.CreateCompanyListener;
import com.magazyn.view.View;

public class Controller implements CreateCategoryListener,
		CreateCompanyListener, AppListener {

	private View view;
	private Model model;

	public Controller(View view, Model model) throws SQLException, Exception {

		this.view = view;
		this.model = model;

		view.setModel(model);
		view.setAppListener(this);
		view.initializeView();

	}

	public void addCategory(CategoryEvent event) {

		Map<String, String> valueMap = new HashMap<String, String>();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		String table = event.getTable();
		String value = event.getName();
		String field = "name";

		valueMap.put(field, value);

		try {
			if (event.getAction() == "add") {
				factory.question("insert", valueMap, table, 0);
			} else if (event.getAction() == "update") {
				factory.question("update", valueMap, table, event.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			model.load(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRow(CompanyEvent event) {
		// TODO Auto-generated method stub
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		try {
			System.out.println("No hej");
			factory.delete("kategorie", event.getId());
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			model.load(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCompany(CompanyEvent event) {

		Map<String, String> valueMap = new HashMap<String, String>();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		String table = event.getTable();
		String value = event.getName();
		String value2 = event.getAddress();
		String field = "nazwa";
		String field2 = "adres";

		valueMap.put(field, value);
		valueMap.put(field2, value2);

		try {
			if (event.getAction() == "add") {
				factory.question("insert", valueMap, table, 0);
			} else if (event.getAction() == "update") {
				factory.question("update", valueMap, table, event.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			model.load(view);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getCategory() {

		try {
			model.load(view);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getCompany() {

		try {
			model.loadCompany(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOrder() {

		try {
			model.loadOrder(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getItem() {

		try {
			model.loadItem(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getClient() {

		try {
			model.loadClient(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onOpen() {
		try {
			Database.getInstance().connect();
		} catch (Exception e) {
			view.showError("Cannot connect to database.");
		}
	}

	public void onClose() {
		Database.getInstance().disconnect();
	}
}
