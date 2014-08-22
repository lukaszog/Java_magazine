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
import com.magazyn.view.CreateItemListener;
import com.magazyn.view.ItemsEvent;
import com.magazyn.view.View;

public class Controller implements CreateCategoryListener,
		CreateCompanyListener,CreateItemListener, AppListener {

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

		Map<Object, Object> valueMap = new HashMap<Object, Object>();
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
	public void deleteRow(CompanyEvent event, String table) {
		// TODO Auto-generated method stub
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		try {
			System.out.println("No hej");
			factory.delete(table, event.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (table == "kategorie") {
				model.load(view);
			} else if (table == "firmy") {
				model.loadCompany(view);
			} else if(table == "produkty"){
				model.loadItem(view);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addItem(ItemsEvent event, String type) {
		// TODO Auto-generated method stub
		Map<Object, Object> valueMap = new HashMap<Object, Object>();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		String table = event.getTable();
		String value = event.getName();
		if(type=="normal"){
		String field = "nazwa";
		valueMap.put(field, value);

		}else if(type=="box"){
			String field1 = "id_kategoria";
			String field2 = "id_firmy";
			
			valueMap.put(field1, event.getIdCategory());
			valueMap.put(field2,event.getIdCompany());
			
		}else if(type=="box_category"){
			String field1 = "id_kategoria";
			valueMap.put(field1, event.getIdCategory());
		}else if(type=="box_company"){
			String field1 = "id_firmy";
			valueMap.put(field1, event.getIdCompany());
		}
	
		try {
			if (event.getAction() == "add") {
			//	factory.question("insert", valueMap, table, 0);
			} else if (event.getAction() == "update") {
				factory.question("update", valueMap, table, event.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			model.loadItem(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void addCompany(CompanyEvent event) {

		Map<Object, Object> valueMap = new HashMap<Object, Object>();
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
			model.loadCompany(view);
			System.out.println("odswiezam");
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
