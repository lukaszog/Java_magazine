package com.magazyn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Model {
	
	private List<Category> people = new Vector<Category>();
	
	public List<Category> getPeople() {
		return new ArrayList<Category>(people);
	}
	
	public void load() throws Exception {
		people.clear();
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		CategoryDAO personDAO = factory.getCategoryDAO();
		people.addAll(personDAO.getCategory());
	}
	
}
