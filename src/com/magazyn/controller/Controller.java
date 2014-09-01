package com.magazyn.controller;

import com.magazyn.model.DAOFactory;
import com.magazyn.model.Database;
import com.magazyn.model.Model;
import com.magazyn.view.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller implements CreateCategoryListener,
		CreateCompanyListener,CreateItemListener, AppListener, CreateOrderListener {

	private View view;
	private Model model;

    /**
     *
     * @param view
     * @param model
     * @throws SQLException
     * @throws Exception
     */
    public Controller(View view, Model model) throws SQLException, Exception {

		this.view = view;
		this.model = model;

		view.setModel(model);
		view.setAppListener(this);
		view.initializeView();

	}

    /**
     *
     * @param event CategoryEvent argument
     */
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

    /**
     *
     * @param event CompanyEvent argument
     * @param table table name
     */
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
			if (table.equals("kategorie")) {
				model.load(view);
			} else if (table.equals("firmy")) {
				model.loadCompany(view);
			} else if(table.equals("produkty")){
				model.loadItem(view);
			}else if(table.equals("zamowienia")){
                model.loadOrder(view);
            }else if(table.equals("klienci")){
                model.loadClient(view);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     *
     * @param event OrderEvent argument
     */
    @Override
    public void addOrder(OrderEvent event) {

        Map<Object, Object> valueMap = new HashMap<Object, Object>();
        DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
        String table = event.getTable();
         int realisation = event.getValue();
        String field = "realizacja";

        valueMap.put(field, realisation);

        try {
            if (event.getAction().equals("add")) {
                factory.question("insert", valueMap, table, 0);
            } else if (event.getAction().equals("update")) {
                factory.question("update", valueMap, table, event.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            model.loadOrder(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event ItemsEvent argument
     * @param type query type
     */
	@Override
	public void addItem(ItemsEvent event, String type) {
		// TODO Auto-generated method stub
		Map<Object, Object> valueMap = new HashMap<Object, Object>();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		String table = event.getTable();
		String value = event.getName();
		if(type.equals("normal")){
		String field = "nazwa";
		String field2 ="id_firmy";
		String field3 = "id_kategoria";
		
		valueMap.put(field, value);
		valueMap.put(field2, event.getIdCompany());
		valueMap.put(field3, event.getIdCategory());

		}else if(type.equals("box")){
			String field1 = "id_kategoria";
			String field2 = "id_firmy";
			
			valueMap.put(field1, event.getIdCategory());
			valueMap.put(field2,event.getIdCompany());
			
		}else if(type.equals("box_category")){
			String field1 = "id_kategoria";
			valueMap.put(field1, event.getIdCategory());
		}else if(type.equals("box_company")){
			String field1 = "id_firmy";
			valueMap.put(field1, event.getIdCompany());
		}
	
		try {
			if (event.getAction().equals("add")) {
				factory.question("insert", valueMap, table, 0);
			} else if (event.getAction().equals("update")) {
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

    /**
     *
     * @param event CompanyEvent argument
     */
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

    /**
     * This method load categoryies from database using model
     *
     */
	public void getCategory() {

		try {
			model.load(view);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * This method load companies from database using model
     */
	public void getCompany() {

		try {
			model.loadCompany(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * This method load orders from database using model
     */
	public void getOrder() {

		try {
			model.loadOrder(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * This method load items from database using model
     */
	public void getItem() {

		try {
			model.loadItem(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * This method load clients from database using model
     *
     */
	public void getClient() {

		try {
			model.loadClient(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Start connection
     */
	public void onOpen() {
		try {
			Database.getInstance().connect();
		} catch (Exception e) {
			view.showError("Cannot connect to database.");
		}
	}

    /**
     * Close connection
     */
	public void onClose() {
		Database.getInstance().disconnect();
	}


}
