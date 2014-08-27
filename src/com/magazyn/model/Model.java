package com.magazyn.model;

import com.magazyn.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Model {

	private List<Category> people = new Vector<Category>();
	private List<Company> company = new Vector<Company>();
	private List<Item> item = new Vector<Item>();
	private List<Order> order = new Vector<Order>();
	private List<Client> client = new Vector<Client>();

	public List<Category> getPeople() {
		System.out.println(people.size());
		return new ArrayList<Category>(people);
	}

	public List<Client> getClient() {
		return new ArrayList<Client>(client);
	}

	public List<Order> getOrder() {

		System.out.println("rozmiar" + order.size());
		
		return new ArrayList<Order>(order);
	}

	public List<Company> getCompany() {
		return new ArrayList<Company>(company);
	}

	public List<Item> getItem() {
		return new ArrayList<Item>(item);
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public void setPeople(List<Category> people) {
		this.people = people;
		// this.people.addAll(getPeople());
	}

	public void setCompany(List<Company> companies) {
		// TODO Auto-generated method stub
		this.company = companies;

	}

	public void setOrder(List<Order> orders) {
		// TODO Auto-generated method stub
		this.order = orders;

	}

	public void setClient(List<Client> clients) {
		// TODO Auto-generated method stub
		this.client = clients;

	}

	public void load(View view) throws Exception {
		people.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO personDAO = factory.getCategoryDAO();
		System.out.println("Model load working");
		((dbDAO) personDAO).setJob(Job.SELECT);
		((dbDAO) personDAO).setItself(this);
		((dbDAO) personDAO).setTable("kategorie");
		((dbDAO) personDAO).setView(view);
		((dbDAO) personDAO).execute();
		if (people == null)
			System.out.println("Categories is null");
	}

	public void loadCompany(View view) throws Exception {
		company.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO companyDAO = factory.getCategoryDAO();
		System.out.println("Model loadCompany working");
		((dbDAO) companyDAO).setJob(Job.SELECT);
		((dbDAO) companyDAO).setTable("firmy");
		((dbDAO) companyDAO).setItself(this);
		((dbDAO) companyDAO).setView(view);
		((dbDAO) companyDAO).execute();
	}

	public void loadItem(View view) throws Exception {
		item.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO itemDAO = factory.getCategoryDAO();
		System.out.println("Model loadItem working");
		((dbDAO) itemDAO).setJob(Job.SELECT);
		((dbDAO) itemDAO).setTable("produkty");
		((dbDAO) itemDAO).setItself(this);
		((dbDAO) itemDAO).setView(view);
		((dbDAO) itemDAO).execute();
	}

	public void loadOrder(View view) throws Exception {
		order.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO orderDAO = factory.getCategoryDAO();
		System.out.println("Model loadOrder working");
		((dbDAO) orderDAO).setJob(Job.SELECT);
		((dbDAO) orderDAO).setTable("zamowienia");
		((dbDAO) orderDAO).setItself(this);
		((dbDAO) orderDAO).setView(view);
		((dbDAO) orderDAO).execute();
	}

	public void loadClient(View view) throws Exception {
		client.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO itemDAO = factory.getCategoryDAO();
		System.out.println("Model loadOrder working");
		((dbDAO) itemDAO).setJob(Job.SELECT);
		((dbDAO) itemDAO).setTable("klienci");
		((dbDAO) itemDAO).setItself(this);
		((dbDAO) itemDAO).setView(view);
		((dbDAO) itemDAO).execute();

	}
}
