package com.magazyn.model;

import com.magazyn.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;



public class MySQLCategoryDAO extends SwingWorker<Void, Void> implements TableDAO{

	private Model model;
	private View view;
	private Job job;
	private List<Category> categories;
	private List<Company> companies;
	private String table;
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public void setItself(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setTable(String table){
		this.table = table;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		if(table=="kategorie"){
		if(job == Job.SELECT){
			categories = getCategory();
			System.out.println("Jestem w kategorie");
		}
		}
		
		System.out.println(table);
		if(table=="firmy"){
			System.out.println("Jestem tutaj w company");
			companies = getCompany();
			System.out.println("za company"); 
		}
		
		return null;
	}
	
	@Override
	protected void done() {	
		
		if(table=="kategorie"){
		
		
		if(job == Job.SELECT){
			model.setPeople(categories);
			view.loadData();
		}
		}
		if(table=="firmy"){
			System.out.println("done firmy");
			model.setCompany(companies);
			view.loadCompany();
		}
	}	
	
	public List<Company> getCompany() throws SQLException
	{
		List<Company> comp = new ArrayList<Company>();
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select nazwa,id,adres from firmy";
		Statement selectStatement = conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next())
		{
			int id = results.getInt("id");
			String name = results.getString("nazwa");
			String address = results.getString("adres");
			Company company = new Company(id, name, address);
			comp.add(company);
		}
		results.close();
		selectStatement.close();
	
		return comp;
	}	
 	public List<Category> getCategory() throws SQLException
	{
		List<Category> cat = new ArrayList<Category>();
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select id, name from kategorie";
		Statement selectStatement =  conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next())
		{
			int id = results.getInt("id");
			String name = results.getString("name");
			Category category = new Category(id, name);
			cat.add(category);
		}
		results.close();
		selectStatement.close();
		
		return cat;
	}
}
