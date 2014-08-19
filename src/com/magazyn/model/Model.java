package com.magazyn.model;

import com.magazyn.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Model {
	
	private List<Category> people = new Vector<Category>();
	private List<Company> company = new Vector<Company>();
	
	public List<Category> getPeople() {
		System.out.println(people.size());
		return new ArrayList<Category>(people);
	}
	
	public List<Company> getCompany(){
	//	System.out.println(company.size());
		return new ArrayList<Company>(company);
	}
	
	public void setPeople(List<Category> people) {
		this.people = people;
	//	this.people.addAll(getPeople());
	}
	public void setCompany(List<Company> companies) {
		// TODO Auto-generated method stub
		this.company = companies;
		
	}

	
	public void load(View view) throws Exception {
		people.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO personDAO = factory.getCategoryDAO();
		System.out.println("Model load working");
		((MySQLCategoryDAO)personDAO).setJob(Job.SELECT);
		((MySQLCategoryDAO)personDAO).setItself(this);
		((MySQLCategoryDAO)personDAO).setTable("kategorie");
		((MySQLCategoryDAO)personDAO).setView(view);
		((MySQLCategoryDAO)personDAO).execute();		
		if (people == null) System.out.println("Categories is null");	
	}	
	
	public void loadCompany(View view) throws Exception{
		company.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		TableDAO companyDAO = factory.getCategoryDAO();
		System.out.println("Model loadCompany working");
		((MySQLCategoryDAO)companyDAO).setJob(Job.SELECT);
		((MySQLCategoryDAO)companyDAO).setTable("firmy");
		((MySQLCategoryDAO)companyDAO).setItself(this);
		((MySQLCategoryDAO)companyDAO).setView(view);
		((MySQLCategoryDAO)companyDAO).execute();
		
	}
}
