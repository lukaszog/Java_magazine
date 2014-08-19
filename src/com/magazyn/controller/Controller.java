package com.magazyn.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.magazyn.model.DAOFactory;
import com.magazyn.model.Database;
import com.magazyn.model.Model;
import com.magazyn.model.MySQLCategoryDAO;
import com.magazyn.model.TableDAO;
import com.magazyn.view.AppListener;
import com.magazyn.view.CreateCategoryEvent;
import com.magazyn.view.CreateCategoryListener;
import com.magazyn.view.CreateUserEvent;
import com.magazyn.view.CreateUserListener;
import com.magazyn.view.View;

public class Controller implements CreateUserListener,CreateCategoryListener, AppListener   {
	
	private View view;
	private Model model;
	
	public Controller(View view, Model model) throws SQLException, Exception {
		
		this.view = view;
		this.model = model;
		
		view.setModel(model);
		view.setAppListener(this);
		view.initializeView();

	}

 	public void userCreate(CreateUserEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Login event " + event.getName() + ", " + event.getPassword());	
	}
	
 	public void addCategory(CreateCategoryEvent event){
		
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		
		TableDAO categoryDAO = factory.getCategoryDAO();
		
		String table = event.getTable();
		String value = event.getName();
		String field = "name";		
		
		Map<String, String> valueMap = new HashMap<String, String>();

		valueMap.put(field, value);

	
		System.out.println(table);
		try {
			factory.insert(valueMap,table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Cateogry event "+ event.getName());	
		try {
			model.load(view);
			//view.loadData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 	public void getCategory(){	
 
 		try {
			model.load(view);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	public void getCompany(){
 		
 		try{
 			model.loadCompany(view);
 		}catch (Exception e){
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
