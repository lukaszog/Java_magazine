package com.magazyn.model;

import com.magazyn.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class dbDAO extends SwingWorker<Void, Void> implements TableDAO {

	private Model model;
	private View view;
	private Job job;
	private List<Category> categories;
	private List<Company> companies;
	private List<Client> clients;
	private List<Order> orders;
	private List<Item> items;
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

	public void setTable(String table) {
		this.table = table;
	}

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		if (table == "kategorie") {
			if (job == Job.SELECT) {
				categories = getCategory();
				System.out.println("Jestem w kategorie");
			}
		}

		System.out.println(table);
		if (table == "firmy") {
			System.out.println("Jestem tutaj w company");
			companies = getCompany();
			System.out.println("za company");
		}
		if (table == "produkty") {
			items = getItem();
			System.out.println("za itemami");
		}
		return null;
	}

	@Override
	protected void done() {

		if (table == "kategorie") {

			if (job == Job.SELECT) {
				model.setPeople(categories);
				view.loadData();
			}
		}
		if (table == "firmy") {
			System.out.println("done firmy");
			model.setCompany(companies);
			view.loadCompany();
		}
		if(table=="produkty"){
			model.setItem(items);
			view.loadItem();
		}
	}

	public List<Company> getCompany() throws SQLException {
		List<Company> comp = new ArrayList<Company>();
		Connection conn = Database.getInstance().getConnection();

		String sql = "select nazwa,id,adres from firmy";
		Statement selectStatement = conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next()) {
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

	public List<Category> getCategory() throws SQLException {
		List<Category> cat = new ArrayList<Category>();
		Connection conn = Database.getInstance().getConnection();

		String sql = "select id, name from kategorie";
		Statement selectStatement = conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			Category category = new Category(id, name);
			cat.add(category);

		}
		results.close();
		selectStatement.close();

		return cat;
	}

	@Override
	public List<Client> getClient() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrder() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItem() throws SQLException {
		// TODO Auto-generated method stub
		List<Item> ite = new ArrayList<Item>();
		Connection conn = Database.getInstance().getConnection();

		String sql = "select  P.id,P.id_kategoria,P.id_firmy,P.nazwa,K.name,F.nazwa"
				+ " 	from produkty P	"
				+ "     join kategorie K on P.id_kategoria=K.id"
				+ "	    join firmy F on P.id_firmy=F.id";

		Statement selectStatement = conn.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next()) {
			int id = results.getInt("id");
			String category = results.getString("K.name");
			String company = results.getString("F.nazwa");
			String name = results.getString("P.nazwa");
			int id_category = results.getInt("P.id_kategoria");
			int id_company = results.getInt("P.id_firmy");

			Item item = new Item(id, category, company, name, id_category,
					id_company);
			ite.add(item);

		}
		results.close();
		selectStatement.close();

		return ite;
	}
}
