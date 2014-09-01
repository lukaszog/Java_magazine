package com.magazyn.view;

import com.magazyn.model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.List;


/**
 * @author Lukasz Ogan
 * @see com.magazyn.view.CategoryListener
 * @see com.magazyn.view.ClientsListener
 * @see com.magazyn.view.CompanyListener
 * @see com.magazyn.view.CompanyListener
 * @see com.magazyn.view.ItemsListener
 * @see com.magazyn.view.OrderListener
 *
 *Class View.java view package * is responsible for generating views for each tab in the application
 *class communicates with the controller (Controller.java controller package)
 *The controller is designed to extract information from the model, the target information go to the view
 *
 *
 */

public class View extends JFrame implements CategoryListener,
        CompanyListener, ClientsListener, ItemsListener, OrderListener {


    private static final long serialVersionUID = 1L;
    private Model model; /** Model instance */
    private AppListener appListener; /** Instance to main app listener */
    private JTextField custNameTxt; /** TexField in GridBagLayout */
    private JButton closeBtn; /** Close button */
    private JButton savingsBtn; /** Save button */
    private CreateCategoryListener categoryListener; /** Listener creating new category */
    private CreateCompanyListener companyListener; /** Listener creating new company */
    private CreateOrderListener orderListener; /** Listener creating new order*/
    private CreateItemListener itemListener; /** Item Listener */
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel card1 = new JPanel(); /** Creating card for CardLayout */
    private JPanel card2 = new JPanel();
    private JPanel card3 = new JPanel();
    private JPanel card4 = new JPanel();
    private JPanel card5 = new JPanel();
    private JPanel card6 = new JPanel(); /** Creating cards - end */
    private JTable jTable1 = new JTable();
    private JTable companyTable = new JTable();
    private JTable itemTable = new JTable();
    private JTable clientTable = new JTable();
    private JTable orderTable = new JTable();
    private JScrollPane itemScroll = new JScrollPane();
    private JScrollPane companyScroll = new JScrollPane();
    private JScrollPane clientScroll = new JScrollPane();
    private JScrollPane orderScroll = new JScrollPane();
    private JScrollPane scroll = new JScrollPane();
    private DefaultTableModel tablemodel = new DefaultTableModel();
    private DefaultTableModel companymodel = new DefaultTableModel();
    private DefaultTableModel clientmodel = new DefaultTableModel();
    private DefaultTableModel itemmodel = new DefaultTableModel();
    private DefaultTableModel ordermodel = new DefaultTableModel();
    private JComboBox<Category> categoryBox = new JComboBox<Category>();
    private JComboBox<Company> companyBox = new JComboBox<Company>();
    private JPanel controls = new JPanel();
    private JPanel buttons = new JPanel();
    private JButton newrow = new JButton();
    private JButton print = new JButton();
    private JButton deletebutton = new JButton("Usuń");
    private JButton acceptbutton  = new JButton("Akceptuj");
    private JButton cancelbutton = new JButton("Anuluj");
    private List<Category> people;
    private List<Company> company;
    private List<Client> client;
    private List<Item> item;
    private List<Order> order;
    private int item_box_flag = 0;
    private int box_flag_item = 0;
    private int box_flag_category = 0;
    private int box_flag_company;
    private int newrow_flag = 0;

    int categoryflag = 0, companyflag = 0, clientflag = 0, itemflag = 0,
            orderflag = 0;
    private int id_category;
    private int id_company;
    private int id;

    /**
     *
     * @throws HeadlessException
     */
    public View() throws HeadlessException {

        super("Magazyn");
    }

    /**
     *
     * @param model this method setting model
     *
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * This method initialize view and prepare GUI
     *
     */
    public void initializeView() {

        addComponentToPane(getContentPane());
        pack();
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {

            /**
             *
             * @param e windowOpened argument
             */
            public void windowOpened(WindowEvent e) {
                fireOpenEvent();
                try {
                    Database.getInstance().connect();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(View.this,
                            "Nie mozna sie polaczyc z baza danych", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    e1.printStackTrace();
                }
            }

            /**
             *
             * @param e windowClosing argument
             */
            public void windowClosing(WindowEvent e) {
                fileCloseEvent();
                System.out.println("Zamknieto polaczenie ziomek");
                try {
                    Database.getInstance().disconnect();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(View.this,
                            "Nie można zakonczyć połączenia z baza danych",
                            "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    /**
     * Open application status
     *
     */
    private void fireOpenEvent() {
        // TODO Auto-generated method stub
        if (appListener != null) {
            appListener.onOpen();
        }

    }

    /**
     * Close aplication status
     */
    private void fileCloseEvent() {
        // TODO Auto-generated method stub
        if (appListener != null) {
            appListener.onClose();
        }
    }

    /**
     *
     * @param pane addComponentToPane argument
     */
    public void addComponentToPane(Container pane) {

        tabbedPane.addTab("Lista kategorii", card1);
        tabbedPane.addTab("Lista firm", card2);
        tabbedPane.addTab("Produkty", card3);
        tabbedPane.addTab("Zamówienia", card4);
        tabbedPane.addTab("Klienci", card5);

        tabbedPane.addChangeListener(new ChangeListener() {

            /**
             *
             * @param event stateChanged argument
             */
            @Override
            public void stateChanged(ChangeEvent event) {
                // TODO Auto-generated method stub
                int paneindex = tabbedPane.getSelectedIndex();

                switch (paneindex) {
                    case 0:
                        categoryflag = 0;
                        break;
                    case 1:
                        if (companyflag == 0) {
                            companyShow();
                            companyflag = 1;
                        }
                        break;
                    case 2:
                        if (itemflag == 0) {
                            itemShow();
                            itemflag = 1;
                        }
                        break;
                    case 3:
                        if (orderflag == 0) {
                            orderShow();
                            orderflag = 1;
                        }
                        break;
                    case 4:
                        if(clientflag==0){
                            clientsShow();
                            clientflag = 1;
                        }
                        break;

                }
            }

        });

        if (categoryflag == 0) {
            categoryShow(0);
        }
        // clientsShow();

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     *
     * @param appListener argument for setAppListener method
     */
    public void setAppListener(AppListener appListener) {
        this.appListener = appListener;
    }

    /**
     *
     * @param string argument for showError method
     */
    public void showError(String string) {
        // TODO Auto-generated method stub
        System.out.println(string);

    }
    /**
     *
     * @param table argument for tableEdit to set JTable to editing
     */
    public void tableEdit(final JTable table) {

        System.out.println("Jestem w table edit");
        table.getModel().addTableModelListener(new TableModelListener() {
            /**
             *
             * @param e tableChanged agument
             */
            @Override
            public void tableChanged(TableModelEvent e) {
                // TODO Auto-generated method stub
                if (table.getCellEditor() != null) {

                    int col = table.getSelectedColumn();
                    id =  Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString());

                    if (col != 0) {
                        if (table == jTable1) {
                            String value = (String) table.getValueAt(
                                    table.getSelectedRow(),
                                    table.getSelectedColumn());
                            fireCategoryEvent(new CategoryEvent(value,
                                    "kategorie", id, "update"));
                        } else if (table == companyTable) {
                            String value = (String) table.getValueAt(
                                    table.getSelectedRow(), 2);
                            String value2 = (String) table.getValueAt(
                                    table.getSelectedRow(), 3);

                            fireCompanyEvent(new CompanyEvent(value, "firmy",
                                    value2, id, "update"));
                        } else if (table == itemTable) {

                            System.out.println(id);

                            System.out.println(box_flag_category);
                            if (box_flag_category == 1) {
                                String name = (String) table.getValueAt(
                                        table.getSelectedRow(), 2);

                                Category selected_category = (Category) categoryBox
                                        .getSelectedItem();
                                Company selected_copmany = (Company) companyBox
                                        .getSelectedItem();

                                id_category = selected_category.getId();
                                id_company = selected_copmany.getId();

                                //System.out.println("edycja item linijka 254:  " + dd);

                                fireItemEvent(new ItemsEvent(id, "produkty",
                                        null, null, name, id_category,
                                        id_company, "update"), "normal");
                            }
                        }
                    }
                }
            }
        });
    }


    /**
     *
     * @param Jb JTable name
     * @param table table name
     */
    public void deleteAction(final JTable Jb, final String table) {

        deletebutton.setEnabled(false);
        Jb.addMouseListener(new MouseAdapter() {
            /**
             *
             * @param e mouse Clicked argument
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deletebutton.setEnabled(true);
            }
        });

        deletebutton.addActionListener(new ActionListener() {
            /**
             *
             * @param e actionPerformed argument
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = (Integer) Jb.getValueAt(Jb.getSelectedRow(), 1);
                if (selRow >= 0) {
                    System.out.println(selRow);
                    if (table.equals("kategorie")) {
                        fireDeleteEvent(new CompanyEvent(null, "kategorie",
                                null, selRow, ""), "kategorie");
                        deletebutton.setEnabled(false);
                    } else if (table.equals("firmy")) {
                        fireDeleteEvent(new CompanyEvent(null, "firmy", null,
                                selRow, ""), "firmy");
                        deletebutton.setEnabled(false);
                    } else if (table.equals("produkty")) {
                        System.out.println("Produkty: " + selRow);
                        fireDeleteEvent(new CompanyEvent(null, "produkty",
                                null, selRow, ""), "produkty");
                        deletebutton.setEnabled(false);
                    }else if(table.equals("zamowienia")){
                        fireDeleteEvent(new CompanyEvent(null,"zamowienia",null,selRow,""),"zamowienia");
                        deletebutton.setEnabled(false);
                    }else if (table.equals("klienci")) {
                        fireDeleteEvent(new CompanyEvent(null, "klienci", null, selRow, ""), "klienci");
                        deletebutton.setEnabled(false);
                    }

                }

            }
        });

    }

    /**
     * Thos method load data from model - category table
     *
     *
     */
    public void loadData() {

        tablemodel.setRowCount(0);
        people = model.getPeople();

        int lp=1;

        for (Category person : people) {
            tablemodel
                    .addRow(new Object[] {lp, person.getId(), person.getName() });
            lp++;
        }
        people.clear();

    }

    /**
     * This method load data from model - client table
     *
     */
    public void loadClient() {

        clientmodel.setRowCount(0);
        client = model.getClient();

        int lp = 1;


        for (Client cli : client) {
            clientmodel.addRow(new Object[] {lp,  cli.getId(), cli.getName(),cli.getLastname(),cli.getAddress() });
            lp++;
        }
        client.clear();

    }

    /**
     * This method load data from model - company table
     *
     */
    public void loadCompany() {
        // TODO Auto-generated method stub


        companymodel.setRowCount(0);
        company = model.getCompany();

        int lp=1;

        for (Company comp : company) {
            companymodel.addRow(new Object[] {lp, comp.getId(), comp.getName(),
                    comp.getAddress() });
            companyBox.addItem(new Company(comp.getId(), comp.getName(), comp
                    .getAddress()));
            lp++;
        }
        company.clear();

    }

    /**
     * This method load data from model - order table
     *
     */
    public void loadOrder() {

        ordermodel.setRowCount(0);
        order = model.getOrder();
        String done = "";
        int lp=1;

        for (Order ord : order) {

            if (ord.getDone() == 1) {
                done = "Zrealizowano";
            } else {
                done = "Brak realizacji";
            }
            ordermodel.addRow(new Object[] {lp, ord.getId(), ord.getDate(), ord.getClient_name(),
                    ord.getClient_lastname(), ord.getAddress(),
                    ord.getProduct(), done });
            lp++;
        }
        order.clear();

    }

    /**
     * This method load data from model - item table
     *
     */
    public void loadItem() {

        itemmodel.setRowCount(0);
        item = model.getItem();
        people = model.getPeople();
        int lp=1;

        for (Category person : people) {
            categoryBox.addItem(new Category(person.getId(), person.getName()));
            // System.out.println("dodaje kategorie do boxa");

        }
        people.clear();
        for (Item ite : item) {
            itemmodel.addRow(new Object[] {lp, ite.getId(), ite.getName(),
                    ite.getCategory(), ite.getCompany() });
            //System.out.println("dodaje itemy do boxa");
            lp++;
        }
        item.clear();
    }

    /**
     * This method present clients in JTable
     *
     */
    public void clientsShow() {

        appListener.getClient();


        clientTable = new JTable(clientmodel){

            /**
             *
             * @param row numer of row in JTable
             * @param column numer of column in JTable
             * @return
             */
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 ) {
                    return false;
                } else {
                    return true;
                }
            }

            public Component prepareRenderer(TableCellRenderer renderer,
                                             int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row,
                        Index_col);
                String value = (String) getValueAt(Index_row, Index_col).toString();

                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(new Color(240,248,255));
                } else {
                    comp.setBackground(new Color(198,226,255));
                }

                return comp;
            }
        };


        clientScroll = new JScrollPane(clientTable);

        clientScroll.setPreferredSize(new Dimension(820,600));

        clientmodel.addColumn("Lp.");
        clientmodel.addColumn("ID");
        clientmodel.addColumn("Imie");
        clientmodel.addColumn("Nazwisko");
        clientmodel.addColumn("Adres");

        clientTable.setRowHeight(20);
        TableColumnModel tcm = clientTable.getColumnModel();
        tcm.getColumn(1).setMaxWidth(50);
        tcm.getColumn(0).setMaxWidth(50);
        clientTable.getTableHeader().setFont(new Font("Arial", 0, 15));

        tableEdit(clientTable); // edycja tabeli

        System.out.print("elooooooo");

        controls = new JPanel(new BorderLayout(5, 5));
        buttons = new JPanel(new GridLayout(0, 1, 4, 4));

        print = new JButton("Drukuj");
        deletebutton = new JButton("Usuń");
        deleteAction(clientTable, "klienci");


        clientTable.addMouseListener(new MouseAdapter() {
            /**
             *
             * @param e mouseClicked argument
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                acceptbutton.setEnabled(true);
            }
        });

        buttons.add(deletebutton);
        buttons.add(print);
        buttons.setBorder(new TitledBorder("Zarządzaj"));

        controls.add(buttons, BorderLayout.NORTH);

        card5.add(clientScroll);
        card5.add(controls);



    }

    /**
     * This method present orders in JTable
     *
     */
    public void orderShow() {

        appListener.getOrder();


        orderTable = new JTable(ordermodel) {

            private static final long serialVersionUID = 1L;

            /**
             *
             * @param row numer of row in JTable
             * @param column numer of column in JTable
             * @return
             */
            public boolean isCellEditable(int row, int column) {
                if (column < 8) {
                    return false;
                } else {
                    return true;
                }
            }

            /**
             *
             * @param renderer TableCellRenderer
             * @param Index_row index_row number
             * @param Index_col index_col number
             * @return return Componenet
             */
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row,
                        Index_col);
                String value = (String) getValueAt(Index_row, Index_col).toString();

                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(new Color(240,248,255));
                } else {
                    comp.setBackground(new Color(198,226,255));
                }
                if(value.equals("Brak realizacji")){
                    comp.setBackground(Color.red);
                }else if(value.equals("Zrealizowano")){
                    comp.setBackground(Color.GREEN);
                }

                return comp;
            }

        };

        orderScroll = new JScrollPane(orderTable);
        orderScroll.setPreferredSize(new Dimension(820,600));
        ordermodel.addColumn("Lp.");
        ordermodel.addColumn("ID");
        ordermodel.addColumn("Data");
        ordermodel.addColumn("Imie");
        ordermodel.addColumn("Nazwisko");
        ordermodel.addColumn("Adres");
        ordermodel.addColumn("Produkt");
        ordermodel.addColumn("Realizacja");

        orderTable.setRowHeight(20);
        TableColumnModel tcm = orderTable.getColumnModel();
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(50);

        orderTable.getTableHeader().setFont(new Font("Arial", 0, 15));

        tableEdit(orderTable); // edycja tabeli

        controls = new JPanel(new BorderLayout(5, 5));
        buttons = new JPanel(new GridLayout(0, 1, 4, 4));
        print = new JButton("Drukuj");
        acceptbutton = new JButton("Akceptuj");
        deletebutton = new JButton("Usuń");
        cancelbutton = new JButton("Anuluj");

        deleteAction(orderTable, "zamowienia");


        acceptbutton.setEnabled(false);
        cancelbutton.setEnabled(false);
        orderTable.addMouseListener(new MouseAdapter() {
            /**
             *
             * @param e mouseClicked argument
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                acceptbutton.setEnabled(true);
                cancelbutton.setEnabled(true);
            }
        });

        cancelbutton.addActionListener(new ActionListener() {
            /**
             *
             * @param e actionPerformed argument
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = (Integer) orderTable.getValueAt(orderTable.getSelectedRow(), 0);
                if (selRow >= 0) {
                    System.out.println(selRow);
                    fireOrderEvent(new OrderEvent(selRow, "zamowienia",
                            0, "update"));

                }

            }
        });
        acceptbutton.addActionListener(new ActionListener() {
            /**
             *
             * @param e actionPerformed argument
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = (Integer) orderTable.getValueAt(orderTable.getSelectedRow(), 0);
                if (selRow >= 0) {
                    System.out.println(selRow);
                    fireOrderEvent(new OrderEvent(selRow, "zamowienia",
                            1, "update"));

                }

            }
        });



        buttons.add(acceptbutton);
        buttons.add(cancelbutton);
        buttons.add(deletebutton);
        buttons.add(print);
        buttons.setBorder(new TitledBorder("Zarządzaj"));
        controls.add(buttons, BorderLayout.NORTH);

        card4.add(orderScroll);
        card4.add(controls);

    }

    /**
     * This method present items in JTable
     *
     */
    @Override
    public void itemShow() {
        // TODO Auto-generated method stub

        appListener.getCompany();
        appListener.getItem();
        itemTable = new JTable(itemmodel) {

            private static final long serialVersionUID = 1L;

            /**
             *
             * @param row row number
             * @param column column number
             * @return
             */
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    return true;
                }
            }

            /**
             *
             * @param renderer TableCellRenderer
             * @param Index_row index_row number
             * @param Index_col index_col number
             * @return return Componenet
             */
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row,
                        Index_col);
                // even index, selected or not selected
                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(new Color(240,248,255));
                } else {
                    comp.setBackground(new Color(198,226,255));
                }
                JComponent jc = (JComponent) comp;
                if (Index_col >1 ) {
                    jc.setToolTipText("Edytuj: "
                            + getValueAt(Index_row, Index_col).toString());
                }

                return comp;
            }
        };

        itemScroll = new JScrollPane(itemTable);
        itemScroll.setPreferredSize(new Dimension(820,600));
        itemmodel.addColumn("Lp.");
        itemmodel.addColumn("ID");
        itemmodel.addColumn("Nazwa");
        itemmodel.addColumn("Kategoria");
        itemmodel.addColumn("Firma");
        itemTable.setRowHeight(20);
        TableColumnModel tcm = itemTable.getColumnModel();

        TableColumn categoryColumn = itemTable.getColumnModel().getColumn(3);
        TableColumn companyColumn = itemTable.getColumnModel().getColumn(4);


        categoryColumn.setCellEditor(new DefaultCellEditor(categoryBox));
        companyColumn.setCellEditor(new DefaultCellEditor(companyBox));

        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(50);
        itemTable.getTableHeader().setFont(new Font("Arial", 0, 15));

        tableEdit(itemTable); // edycja tabeli

        categoryBox.addItemListener(new ItemListener() {
            /**
             *
             * @param event itemStateChanged argument
             */
            public void itemStateChanged(ItemEvent event) {
                if (newrow_flag == 0) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object item = event.getItem();
                    if (event.getStateChange() == ItemEvent.SELECTED
                            && box_flag_category > 0) {
                        System.out.println(item.toString()
                                + " selected!!!!!!!!!!!!");
                        Category selected_category = (Category) categoryBox
                                .getSelectedItem();

                        int idd = Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 1).toString());


                        id_category = selected_category.getId();
                        System.out.println("Id kategorii:" + id_category
                                + "Id wiersza: " + idd);

                        fireItemEvent(new ItemsEvent(idd, "produkty", null,
                                        null, null, id_category, id_company, "update"),
                                "box_category");


                    }
                    box_flag_category++;
                }
            }
        });

        companyBox.addItemListener(new ItemListener() {

            /**
             *
             * @param event itemStateChanged argument
             */
            public void itemStateChanged(ItemEvent event) {
                if (newrow_flag == 0) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    Object item = event.getItem();
                    if (event.getStateChange() == ItemEvent.SELECTED
                            && box_flag_company > 0) {
                        System.out.println(item.toString()
                                + " selected!!!!!!!!!!!!");
                        Company selected_company = (Company) companyBox
                                .getSelectedItem();

                        int idd = Integer.parseInt(itemTable.getValueAt(itemTable.getSelectedRow(), 1).toString());


                        id_company = selected_company.getId();
                        System.out.println("Id kategorii:" + id_category
                                + "Id wiersza: " + idd);

                        fireItemEvent(new ItemsEvent(idd, "produkty", null,
                                        null, null, id_category, id_company, "update"),
                                "box_company");

                    }
                    box_flag_company++;
                }
            }
        });
        controls = new JPanel(new BorderLayout(5, 5));
        buttons = new JPanel(new GridLayout(0, 1, 4, 4));
        newrow = new JButton("Dodaj");
        print = new JButton("Drukuj");
        deletebutton = new JButton("Usuń");
        deleteAction(itemTable, "produkty");

        newrow.addActionListener(new ActionListener() {

            /**
             *
             * @param e actionPerformed argument
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFrame bankTeller = new JFrame("Dodaj nowy produkt");
                bankTeller.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                bankTeller.getRootPane().setWindowDecorationStyle(
                        JRootPane.NONE);

                newrow_flag = 1;
                bankTeller.setSize(500, 280);
                bankTeller.setLocationRelativeTo(null);
                bankTeller.setResizable(false);
                bankTeller.setLayout(new GridBagLayout());

                bankTeller.setBackground(Color.gray);
                GridBagConstraints c = new GridBagConstraints();

                JPanel acctInfo = new JPanel(new GridBagLayout());
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 2;
                c.gridheight = 1;
                c.insets = new Insets(5, 5, 5, 5);
                bankTeller.add(acctInfo, c);
                c.gridwidth = 1;

                JLabel custNameLbl = new JLabel("Nazwa produktu");
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(0, 0, 0, 0);
                acctInfo.add(custNameLbl, c);
                c.weightx = 1.;

                JLabel custCategoryLbl = new JLabel("Kategoria");
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(0, 0, 0, 0);
                acctInfo.add(custCategoryLbl, c);
                c.weightx = 1.;

                JLabel custCompanyLbl = new JLabel("Firma");
                c.gridx = 0;
                c.gridy = 2;
                c.insets = new Insets(0, 0, 0, 0);
                acctInfo.add(custCompanyLbl, c);
                c.weightx = 1.;

                c.fill = GridBagConstraints.HORIZONTAL;
                custNameTxt = new JTextField("", 1000);
                c.gridx = 1;
                c.gridy = 0;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(custNameTxt, c);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = 1;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(categoryBox, c);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = 2;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(companyBox, c);

                closeBtn = new JButton("Anuluj");
                c.gridx = 0;
                c.gridy = 3;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(closeBtn, c);

                savingsBtn = new JButton("Dodaj");
                c.gridx = 1;
                c.gridy = 3;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(savingsBtn, c);

                bankTeller.setVisible(true);

                closeBtn.addActionListener(new ActionListener() {
                    /**
                     *
                     * @param e actionPerformed argument
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bankTeller.dispose();
                        newrow_flag = 0;
                    }
                });

                final int[] cmp;
                cmp = new int[1];
                final int[] cmpa;
                cmpa = new int[1];
                categoryBox.addItemListener(new ItemListener() {
                    /**
                     *
                     * @param event itemStateChanged argument
                     */
                    public void itemStateChanged(ItemEvent event) {

                        JComboBox comboBox = (JComboBox) event.getSource();
                        Object item = event.getItem();
                        if (event.getStateChange() == ItemEvent.SELECTED
                                && box_flag_category > 0) {
                             Category selected_category = (Category) categoryBox
                                    .getSelectedItem();
                            id_category = selected_category.getId();
                            System.out.println("Id kategorii:" + id_category);
                            cmp[0]=1;
                        }
                    }
                });
                 companyBox.addItemListener(new ItemListener() {

                    /**
                     *
                     * @param event itemStateChanged argument
                     */
                    public void itemStateChanged(ItemEvent event) {

                        JComboBox comboBox = (JComboBox) event.getSource();
                        Object item = event.getItem();
                        if (event.getStateChange() == ItemEvent.SELECTED
                                && box_flag_company > 0) {
                            Company selected_company = (Company) companyBox
                                    .getSelectedItem();
                            id_company = selected_company.getId();
                            System.out.println("Id firmy:" + id_company);
                            cmpa[0]=1;
                        }
                    }
                });

                savingsBtn.addActionListener(new ActionListener() {
                    /**
                     *
                     * @param e actionPerformed argument
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String name = custNameTxt.getText();

                        if (!name.isEmpty() && cmp[0]>0 && cmpa[0]>0  ) {
                            JOptionPane.showMessageDialog(View.this, "Dodano",
                                    "Dodano", JOptionPane.INFORMATION_MESSAGE);
                            fireItemEvent(new ItemsEvent(0, "produkty", "", "",
                                            name, id_category, id_company, "add"),
                                    "normal");
                            System.out.println("dodaje");
                            id_company = 0;
                            id_category = 0;

                        } else {
                            JOptionPane.showMessageDialog(View.this,
                                    "Uzupenij pola", "Uzupełnij pola",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

            }
        });

        buttons.add(newrow);
        buttons.add(deletebutton);
        buttons.add(print);
        buttons.setBorder(new TitledBorder("Zarządzaj"));

        controls.add(buttons, BorderLayout.NORTH);

        card3.add(itemScroll);
        card3.add(controls);

    }

    /**
     * This method present companyies in JTable
     *
     */
    @Override
    public void companyShow() {
        // TODO Auto-generated method stub
        appListener.getCompany();

        companyTable = new JTable(companymodel) {

            private static final long serialVersionUID = 1L;

            /**
             *
             * @param row row number
             * @param column column number
             * @return true or false
             */
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    return true;
                }
            }

            /**
             *
             * @param renderer TableCellRenderer
             * @param Index_row index_row number
             * @param Index_col index_col number
             * @return return Compononet
             */
            public Component prepareRenderer(TableCellRenderer renderer,
                                             int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row,
                        Index_col);
                // even index, selected or not selected
                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(new Color(240,248,255));
                } else {
                    comp.setBackground(new Color(198,226,255));
                }
                JComponent jc = (JComponent) comp;
                if (Index_col >1 ) {
                    jc.setToolTipText("Edytuj: "
                            + getValueAt(Index_row, Index_col));
                }
                return comp;
            }
        };

        companyScroll = new JScrollPane(companyTable);
        companyScroll.setPreferredSize(new Dimension(820,600));

        companymodel.addColumn("Lp.");
        companymodel.addColumn("ID");
        companymodel.addColumn("Nazwa firmy");
        companymodel.addColumn("Adres");
        companyTable.setRowHeight(20);
        TableColumnModel tcm = companyTable.getColumnModel();
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(50);
        companyTable.getTableHeader().setFont(new Font("Arial", 0, 15));

        tableEdit(companyTable); // edycja tabeli

        controls = new JPanel(new BorderLayout(5, 5));
        buttons = new JPanel(new GridLayout(0, 1, 4, 4));
        newrow = new JButton("Dodaj");
        print = new JButton("Drukuj");
        deletebutton = new JButton("Usuń");
        deleteAction(companyTable, "firmy");

        newrow.addActionListener(new ActionListener() {

            /**
             *
             * @param e actionPerformed argument
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFrame newpopup = new JFrame("Dodaj nową firmę");
                newpopup.setSize(500, 280);
                newpopup.setLocationRelativeTo(null);
                newpopup.setResizable(false);
                newpopup.setLayout(new GridBagLayout());

                newpopup.setBackground(Color.gray);
                GridBagConstraints c = new GridBagConstraints();

                JPanel acctInfo = new JPanel(new GridBagLayout());
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 2;
                c.gridheight = 1;
                c.insets = new Insets(5, 5, 5, 5);
                newpopup.add(acctInfo, c);
                c.gridwidth = 1;

                JLabel custNameLbl = new JLabel("Nazwa firmy");
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(0, 0, 0, 0);
                acctInfo.add(custNameLbl, c);
                c.weightx = 1.;

                JLabel custAddressLbl = new JLabel("Adres firmy");
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(0, 0, 0, 0);
                acctInfo.add(custAddressLbl, c);
                c.weightx = 1.;

                c.fill = GridBagConstraints.HORIZONTAL;
                custNameTxt = new JTextField("", 1000);
                c.gridx = 1;
                c.gridy = 0;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(custNameTxt, c);

                c.fill = GridBagConstraints.HORIZONTAL;
                final JTextField custAddressTxt = new JTextField("");
                c.gridx = 1;
                c.gridy = 1;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(custAddressTxt, c);

                closeBtn = new JButton("Anuluj");
                c.gridx = 0;
                c.gridy = 3;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(closeBtn, c);

                savingsBtn = new JButton("Dodaj nową firmę");
                c.gridx = 1;
                c.gridy = 3;
                c.insets = new Insets(5, 5, 5, 5);
                acctInfo.add(savingsBtn, c);

                newpopup.setVisible(true);

                closeBtn.addActionListener(new ActionListener() {
                    /**
                     *
                     * @param e actionPerformed argument
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newpopup.dispose();
                    }
                });

                savingsBtn.addActionListener(new ActionListener() {

                    /**
                     *
                     * @param e actionPerformed argument
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String name = custNameTxt.getText();
                        String address = custAddressTxt.getText();

                        if (!name.isEmpty() && !address.isEmpty()) {
                            JOptionPane.showMessageDialog(View.this, "Dodano",
                                    "Dodano", JOptionPane.INFORMATION_MESSAGE);

                            System.out.println(name + ": " + address);

                            fireCompanyEvent(new CompanyEvent(name, "firmy",
                                    address, 0, "add"));

                        } else {
                            JOptionPane.showMessageDialog(View.this,
                                    "Uzupełnij pole", "Uzupełnij pole",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
            }
        });

        buttons.add(newrow);
        buttons.add(deletebutton);
        buttons.add(print);
        buttons.setBorder(new TitledBorder("Zarządzaj"));

        controls.add(buttons, BorderLayout.NORTH);

        card2.add(companyScroll);
        card2.add(controls);
    }

    /**
     *
     * @param refresh
     */
    @Override
    public void categoryShow(int refresh) {
        // TODO Auto-generated method stub
        appListener.getCategory();
        jTable1 = new JTable(tablemodel) {

            private static final long serialVersionUID = 1L;

            /**
             *
             * @param row row number
             * @param column column number
             * @return return true or false
             */
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    return true;
                }
            }



            /**
             *
             * @param renderer TableCellRenderer
             * @param Index_row index_row number
             * @param Index_col index_col number
             * @return return Component
             */
            public Component prepareRenderer
            (TableCellRenderer renderer,int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                 if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                     comp.setBackground(new Color(240,248,255));
                 } else {
                     comp.setBackground(new Color(198,226,255));
                 }
                JComponent jc = (JComponent) comp;
                if (Index_col >1 ) {
                    jc.setToolTipText("Edytuj: "
                            + getValueAt(Index_row, Index_col).toString());
                }
                return comp;
            }
        };
        scroll = new JScrollPane(jTable1);
        scroll.setPreferredSize(new Dimension(820,600));

        TableColumnModel tcm = jTable1.getColumnModel();
        tablemodel.addColumn("Lp.");
        tablemodel.addColumn("ID");
        tablemodel.addColumn("Nazwa");

        jTable1.setRowHeight(20);
        jTable1.getColumn("ID").setCellEditor(null);
        jTable1.getTableHeader().setFont(new Font("Arial", 0, 15));
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(50);

        int flag;
        flag = 1;
        if (refresh == 1) {

        } else {
            if (flag == 1) {
                // loadData();
                flag++;
            }

            controls = new JPanel(new BorderLayout(5, 5));
            buttons = new JPanel(new GridLayout(0, 1, 4, 4));
            newrow = new JButton("Dodaj");
            print = new JButton("Drukuj");
            deletebutton = new JButton("Usuń");

            tableEdit(jTable1); // edycja tabeli

            print.addActionListener(new ActionListener() {
                /**
                 *
                 * @param arg0 actionPerformed argument
                 */
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    try {
                        jTable1.print();
                    } catch (PrinterException pe) {
                        System.err.println("Blad przy drukowaniu");
                    }
                }
            });
            newrow.addActionListener(new ActionListener() {

                /**
                 *
                 * @param e actionPerformed argument
                 */
                @Override
                public void actionPerformed(ActionEvent e) {

                    final JFrame newpopup = new JFrame("Dodaj nową kategorie");
                    newpopup.setSize(500, 280);
                    newpopup.setLocationRelativeTo(null);
                    newpopup.setResizable(false);
                    newpopup.setLayout(new GridBagLayout());

                    newpopup.setBackground(Color.gray);
                    GridBagConstraints c = new GridBagConstraints();

                    JPanel acctInfo = new JPanel(new GridBagLayout());
                    c.gridx = 0;
                    c.gridy = 0;
                    c.gridwidth = 2;
                    c.gridheight = 1;
                    c.insets = new Insets(5, 5, 5, 5);
                    newpopup.add(acctInfo, c);
                    c.gridwidth = 1;

                    JLabel custNameLbl = new JLabel("Nazwa kategorii");
                    c.gridx = 0;
                    c.gridy = 0;
                    c.insets = new Insets(0, 0, 0, 0);
                    acctInfo.add(custNameLbl, c);
                    c.weightx = 1.;

                    c.fill = GridBagConstraints.HORIZONTAL;
                    custNameTxt = new JTextField("", 1000);
                    c.gridx = 1;
                    c.gridy = 0;
                    c.insets = new Insets(5, 5, 5, 5);
                    acctInfo.add(custNameTxt, c);

                    closeBtn = new JButton("Anuluj");
                    c.gridx = 0;
                    c.gridy = 3;
                    c.insets = new Insets(5, 5, 5, 5);
                    acctInfo.add(closeBtn, c);

                    savingsBtn = new JButton("Dodaj nową kategorie");
                    c.gridx = 1;
                    c.gridy = 3;
                    c.insets = new Insets(5, 5, 5, 5);
                    acctInfo.add(savingsBtn, c);

                    newpopup.setVisible(true);

                    closeBtn.addActionListener(new ActionListener() {
                        /**
                         *
                         * @param e actionPerformed argument
                         */
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            newpopup.dispose();
                        }
                    });

                    savingsBtn.addActionListener(new ActionListener() {
                        /**
                         *
                         * @param e actionPerformed argument
                         */
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String name = custNameTxt.getText();
                            if (!name.isEmpty()) {
                                JOptionPane.showMessageDialog(View.this,
                                        "Dodano", "Dodano",
                                        JOptionPane.INFORMATION_MESSAGE);

                                fireCategoryEvent(new CategoryEvent(name,
                                        "kategorie", 0, "add"));
                            } else {
                                JOptionPane.showMessageDialog(View.this,
                                        "Uzupełnij pole",
                                        "Uzupełnij pole",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                }
            });

            deleteAction(jTable1, "kategorie");

            buttons.add(newrow);
            buttons.add(deletebutton);
            buttons.add(print);
            buttons.setBorder(new TitledBorder("Zarządzaj"));

            controls.add(buttons, BorderLayout.NORTH);

            card1.add(scroll);
            card1.add(controls);
        }
    }

    /**
     *
     * @param categoryListener seting categoryListener
     */
    public void setCategoryListener(CreateCategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

    /**
     *
     * @param companyListener setting companyListener
     */
    public void setCompanyListener(CreateCompanyListener companyListener) {
        this.companyListener = companyListener;
    }

    /**
     *
     * @param itemListener setting setItemListener
     */
    public void setItemListener(CreateItemListener itemListener) {
        // TODO Auto-generated method stub
        this.itemListener = itemListener;

    }

    /**
     *
     * @param orderListener setting orderListener
     */
    public void setOrderListener(CreateOrderListener orderListener){
        this.orderListener = orderListener;
    }

    /**
     *
     * @param event setting fireCategoryEvent
     */
    public void fireCategoryEvent(CategoryEvent event) {
        if (categoryListener != null) {
            categoryListener.addCategory(event);
        }
    }

    /**
     *
     * @param event argument
     * @param table argument
     */
    public void fireDeleteEvent(CompanyEvent event, String table) {
        appListener.deleteRow(event, table);
    }

    /**
     *
     * @param event argument
     */
    public void fireCompanyEvent(CompanyEvent event) {
        if (companyListener != null) {
            companyListener.addCompany(event);
        }
    }

    /**
     *
     * @param event argument
     * @param type argument
     */
    public void fireItemEvent(ItemsEvent event, String type) {
        if (itemListener != null) {
            itemListener.addItem(event, type);
        }
    }

    /**
     *
     * @param event argument
     */
    public void fireOrderEvent(OrderEvent event){
        if(orderListener != null){
            orderListener.addOrder(event);
        }
    }

}
