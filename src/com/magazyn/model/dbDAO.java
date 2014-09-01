package com.magazyn.model;

import com.magazyn.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

/**
 * @author Lukasz
 * Load data from database and put into List using SwingWorker
 */

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

    /**
     *
     * @param job
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     *
     * @param model
     */
    public void setItself(Model model) {
        this.model = model;
    }

    /**
     *
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     *
     * @param table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Void doInBackground() throws Exception {
        // TODO Auto-generated method stub
        if (table.equals("kategorie")) {
            if (job == Job.SELECT) {
                categories = getCategory();
            }
        }
         if (table.equals("firmy")) {
            companies = getCompany();
        }
        if (table.equals("produkty")) {
            items = getItem();
        }
        if (table.equals("zamowienia")) {
            orders = getOrder();
        }
        if(table.equals("klienci")){
            clients = getClient();
        }
        return null;
    }

    /**
     * SwingWorker method
     */
    @Override
    protected void done() {

        if (table.equals("kategorie")) {

            if (job == Job.SELECT) {
                model.setPeople(categories);
                view.loadData();
            }
        }
        if (table.equals("firmy")) {
            model.setCompany(companies);
            view.loadCompany();
        }
        if (table.equals("produkty")) {
            model.setItem(items);
            view.loadItem();
        }
        if (table.equals("zamowienia")) {
            model.setOrder(orders);
            view.loadOrder();

        }
        if (table.equals("klienci")) {
            model.setClient(clients);
            view.loadClient();
        }
    }

    /**
     *
     * @return comp
     * @throws SQLException
     */
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

    /**
     *
     * @return cat
     * @throws SQLException
     */
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

    /**
     *
     * @return cli
     * @throws SQLException
     */
    @Override
    public List<Client> getClient() throws SQLException {

        List<Client> cli = new ArrayList<Client>();
        Connection conn = Database.getInstance().getConnection();

        String sql = "select * from klienci";
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);

        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("imie");
            String lastname = results.getString("nazwisko");
            String addres = results.getString("adres");
            Client client = new Client(id, name,lastname, addres);
            cli.add(client);

        }
        results.close();
        selectStatement.close();

        return cli;

    }

    /**
     *
     * @return ord
     * @throws SQLException
     */
    @Override
    public List<Order> getOrder() throws SQLException {
        // TODO Auto-generated method stub
        List<Order> ord = new ArrayList<Order>();
        Connection conn = Database.getInstance().getConnection();

        String sql = "select Z.id,Z.data,Z.id_klienta,Z.id_produktu,Z.realizacja,"
                + "P.nazwa,K.imie,K.nazwisko,K.adres"
                + " from zamowienia Z"
                + " join klienci K on Z.id_klienta=K.id"
                + " join produkty P on Z.id_produktu=P.id;";

        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);

        while (results.next()) {
            int id = results.getInt("Z.id");
            String date = results.getString("Z.data");
            int id_client = results.getInt("Z.id_klienta");
            int id_item = results.getInt("Z.id_produktu");
            String client_name = results.getString("K.imie");
            String last_name = results.getString("K.nazwisko");
            String product = results.getString("P.nazwa");
            String address = results.getString("K.adres");
            int done = results.getInt("Z.realizacja");

            System.out.println("dodalem");
            Order order = new Order(id, date, id_client, id_item, client_name,
                    last_name, product, address, done);
            ord.add(order);

        }
        results.close();
        selectStatement.close();

        return ord;
    }

    /**
     *
     * @return ite
     * @throws SQLException
     */
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
