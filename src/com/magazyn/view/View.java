package com.magazyn.view;

import java.awt.Checkbox;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.magazyn.controller.Controller;
import com.magazyn.model.Company;
import com.magazyn.model.Database;
import com.magazyn.model.Item;
import com.magazyn.model.Model;
import com.magazyn.model.Category;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class View extends JFrame implements ActionListener, CategoryListener,
		CompanyListener, ClientsListener, ItemsListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private AppListener appListener;
	private JTextField custNameTxt;
	private JButton closeBtn;
	private JButton savingsBtn;
	private CreateCategoryListener categoryListener;
	private CreateCompanyListener companyListener;
	private CreateItemListener itemListener;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel card1 = new JPanel();
	private JPanel card2 = new JPanel();
	private JPanel card3 = new JPanel();
	private JPanel card4, card5, card6 = new JPanel();
	private JTable jTable1 = new JTable();
	private JTable companyTable = new JTable();
	private JTable itemTable = new JTable();
	private JScrollPane itemScroll = new JScrollPane();
	private JScrollPane companyScroll = new JScrollPane();
	private JScrollPane scroll = new JScrollPane();
	private DefaultTableModel tablemodel = new DefaultTableModel();
	private DefaultTableModel companymodel = new DefaultTableModel();
	private DefaultTableModel itemmodel = new DefaultTableModel();
	private JComboBox<Category> categoryBox = new JComboBox<Category>();
	private JComboBox<Company> companyBox = new JComboBox<Company>();
	private JPanel controls = new JPanel();
	private JPanel buttons = new JPanel();
	private JButton newrow = new JButton();
	private JButton print = new JButton();
	private JButton deletebutton = new JButton("Usuñ");
	private List<Category> people;
	private List<Company> company;
	private List<Item> item;
	private int item_box_flag = 0;
	private int box_flag_item = 0;
	private int box_flag_category = 0;
	private int box_flag_company;
	private int newrow_flag = 0;

	int categoryflag = 0, companyflag = 0, clientflag = 0, itemflag = 0;
	private int id_category;
	private int id_company;
	private int id;

	public View() throws HeadlessException {

		super("Magazyn");
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void initializeView() {

		addComponentToPane(getContentPane());
		pack();
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		addWindowListener(new WindowAdapter() {

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

			public void windowClosing(WindowEvent e) {
				fileCloseEvent();
				System.out.println("Zamknieto polaczenie ziomek");
				try {
					Database.getInstance().disconnect();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(View.this,
							"Nie mo¿na zakonczyæ po³¹czenia z baza danych",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

	private void fireOpenEvent() {
		// TODO Auto-generated method stub
		if (appListener != null) {
			appListener.onOpen();
		}

	}

	private void fileCloseEvent() {
		// TODO Auto-generated method stub
		if (appListener != null) {
			appListener.onClose();
		}
	}

	public void addComponentToPane(Container pane) {

		tabbedPane.addTab("Lista kategorii", card1);
		tabbedPane.addTab("Lista firm", card2);
		tabbedPane.addTab("Produkty", card3);
		tabbedPane.addTab("Zamówienia", card4);
		tabbedPane.addTab("Klienci", card5);
		tabbedPane.addTab("Informacje", card6);

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				// TODO Auto-generated method stub
				int paneindex = tabbedPane.getSelectedIndex();

				switch (paneindex) {
				case 0:
					System.out.println("zero");
					categoryflag = 0;
					break;
				case 1:
					if (companyflag == 0) {
						companyShow();
						companyflag = 1;
					}
					break;
				case 2:
					System.out.println("Case 2");
					if (itemflag == 0) {
						itemShow();
						itemflag = 1;
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

	public void setAppListener(AppListener appListener) {
		this.appListener = appListener;
	}

	public void showError(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void clientsShow() {
		// TODO Auto-generated method stub

	}

	public void tableEdit(final JTable table) {

		System.out.println("Jestem w table edit");
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (table.getCellEditor() != null) {

					int col = table.getSelectedColumn();
					id = (int) table.getValueAt(table.getSelectedRow(), 0);

					if (col != 0) {
						if (table == jTable1) {
							String value = (String) table.getValueAt(
									table.getSelectedRow(),
									table.getSelectedColumn());
							fireCategoryEvent(new CategoryEvent(value,
									"kategorie", id, "update"));
						} else if (table == companyTable) {
							String value = (String) table.getValueAt(
									table.getSelectedRow(), 1);
							String value2 = (String) table.getValueAt(
									table.getSelectedRow(), 2);

							fireCompanyEvent(new CompanyEvent(value, "firmy",
									value2, id, "update"));
						} else if (table == itemTable) {

							System.out.println(id);

							if (box_flag_category == 1) {
								String name = (String) table.getValueAt(
										table.getSelectedRow(), 1);
								System.out.println("edycja item linijka 254");

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

	public void deleteAction(final JTable Jb, final String table) {

		deletebutton.setEnabled(false);
		Jb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deletebutton.setEnabled(true);
			}
		});

		deletebutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selRow = (Integer) Jb.getValueAt(Jb.getSelectedRow(), 0);
				if (selRow >= 0) {
					System.out.println(selRow);
					if (table == "kategorie") {
						fireDeleteEvent(new CompanyEvent(null, "kategorie",
								null, selRow, ""), "kategorie");
					} else if (table == "firmy") {
						fireDeleteEvent(new CompanyEvent(null, "firmy", null,
								selRow, ""), "firmy");
					} else if (table == "produkty") {
						System.out.println("Produkty: " + selRow);
						fireDeleteEvent(new CompanyEvent(null, "produkty",
								null, selRow, ""), "produkty");
					}
				}

			}
		});
	}

	public void loadData() {

		tablemodel.setRowCount(0);
		people = model.getPeople();

		System.out.println("jestem w load kategorie");

		for (Category person : people) {
			tablemodel
					.addRow(new Object[] { person.getId(), person.getName() });
		}
		people.clear();

	}

	public void loadCompany() {
		// TODO Auto-generated method stub

		System.out.println("Jestem w load Company");
		companymodel.setRowCount(0);
		company = model.getCompany();

		for (Company comp : company) {
			companymodel.addRow(new Object[] { comp.getId(), comp.getName(),
					comp.getAddress() });
			companyBox.addItem(new Company(comp.getId(), comp.getName(), comp
					.getAddress()));

			// System.out.println(comp.getAddress());
		}
		company.clear();

	}

	public void loadItem() {

		itemmodel.setRowCount(0);
		item = model.getItem();
		people = model.getPeople();

		for (Category person : people) {
			categoryBox.addItem(new Category(person.getId(), person.getName()));
			System.out.println("dodaje kategorie do boxa");
		}
		people.clear();
		for (Item ite : item) {
			itemmodel.addRow(new Object[] { ite.getId(), ite.getName(),
					ite.getCategory(), ite.getCompany() });
			System.out.println("dodaje itemy do boxa");
		}
		item.clear();
	}

	@Override
	public void itemShow() {
		// TODO Auto-generated method stub

		appListener.getCompany();
		appListener.getItem();
		System.out.println("itemShow!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		itemTable = new JTable(itemmodel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					JComponent jc = (JComponent) c;
					if (column != 0) {
						// jc.setToolTipText("Edytuj: "
						// + getValueAt(row, column).toString());
					}
				}
				return c;
			}
		};

		itemScroll = new JScrollPane(itemTable);

		itemmodel.addColumn("ID");
		itemmodel.addColumn("Nazwa");
		itemmodel.addColumn("Kategoria");
		itemmodel.addColumn("Firma");
		itemTable.setRowHeight(20);
		TableColumnModel tcm = itemTable.getColumnModel();

		TableColumn categoryColumn = itemTable.getColumnModel().getColumn(2);
		TableColumn companyColumn = itemTable.getColumnModel().getColumn(3);

		categoryColumn.setCellEditor(new DefaultCellEditor(categoryBox));
		companyColumn.setCellEditor(new DefaultCellEditor(companyBox));

		tcm.getColumn(0).setMaxWidth(50);
		itemTable.getTableHeader().setFont(new Font("Arial", 0, 15));

		tableEdit(itemTable); // edycja tabeli

		categoryBox.addItemListener(new ItemListener() {
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

						int rowid = (int) itemTable.getValueAt(
								itemTable.getSelectedRow(), 0);

						id_category = selected_category.getId();
						System.out.println("Id kategorii:" + id_category
								+ "Id wiersza: " + id);

						fireItemEvent(new ItemsEvent(rowid, "produkty", null,
								null, null, id_category, id_company, "update"),
								"box_category");

					}
					box_flag_category++;
				}
			}
		});

		companyBox.addItemListener(new ItemListener() {

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

						int rowid = (int) itemTable.getValueAt(
								itemTable.getSelectedRow(), 0);

						id_company = selected_company.getId();
						System.out.println("Id kategorii:" + id_category
								+ "Id wiersza: " + id);

						fireItemEvent(new ItemsEvent(rowid, "produkty", null,
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
		deletebutton = new JButton("Usuñ");
		deleteAction(itemTable, "produkty");

		newrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final JFrame bankTeller = new JFrame("Dodaj now¹ firmê");
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
					@Override
					public void actionPerformed(ActionEvent e) {
						bankTeller.dispose();
						newrow_flag = 0;
					}
				});

				categoryBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent event) {

						JComboBox comboBox = (JComboBox) event.getSource();
						Object item = event.getItem();
						if (event.getStateChange() == ItemEvent.SELECTED
								&& box_flag_category > 0) {
							System.out.println(item.toString()
									+ " selected!!!!!!!!!!!!");
							Category selected_category = (Category) categoryBox
									.getSelectedItem();
							id_category = selected_category.getId();
							System.out.println("Id kategorii:" + id_category);
						}
					}
				});

				companyBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent event) {

						JComboBox comboBox = (JComboBox) event.getSource();
						Object item = event.getItem();
						if (event.getStateChange() == ItemEvent.SELECTED
								&& box_flag_company > 0) {
							System.out.println(item.toString()
									+ " selected!!!!!!!!!!!!");
							Company selected_company = (Company) companyBox
									.getSelectedItem();
							id_company = selected_company.getId();
							System.out.println("Id firmy:" + id_company);
						}
					}
				});

				savingsBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						String name = custNameTxt.getText();
						String address = "";

						if (!name.isEmpty() && !address.isEmpty()) {
							JOptionPane.showMessageDialog(View.this, "Dodano",
									"Dodano", JOptionPane.INFORMATION_MESSAGE);
							System.out.println(name + ": " + address);
							fireCompanyEvent(new CompanyEvent(name, "firmy",
									address, 0, "add"));
						} else {
							JOptionPane.showMessageDialog(View.this,
									"Uzupe³nij pole nazwa", "Uzupe³nij pole",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});

			}
		});

		buttons.add(newrow);
		buttons.add(deletebutton);
		buttons.add(print);
		buttons.setBorder(new TitledBorder("Zarz¹dzaj"));

		controls.add(buttons, BorderLayout.NORTH);

		card3.add(itemScroll);
		card3.add(controls);

	}

	@Override
	public void companyShow() {
		// TODO Auto-generated method stub
		System.out.println("elo");
		appListener.getCompany();

		companyTable = new JTable(companymodel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					JComponent jc = (JComponent) c;
					if (column != 0) {
						// jc.setToolTipText("Edytuj: "
						// + getValueAt(row, column).toString());
					}
				}
				return c;
			}
		};

		companyScroll = new JScrollPane(companyTable);
		companymodel.addColumn("ID");
		companymodel.addColumn("Nazwa firmy");
		companymodel.addColumn("Adres");
		companyTable.setRowHeight(20);
		TableColumnModel tcm = companyTable.getColumnModel();
		tcm.getColumn(0).setMaxWidth(50);
		companyTable.getTableHeader().setFont(new Font("Arial", 0, 15));

		tableEdit(companyTable); // edycja tabeli

		controls = new JPanel(new BorderLayout(5, 5));
		buttons = new JPanel(new GridLayout(0, 1, 4, 4));
		newrow = new JButton("Dodaj");
		print = new JButton("Drukuj");
		deletebutton = new JButton("Usuñ");
		deleteAction(companyTable, "firmy");

		newrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final JFrame bankTeller = new JFrame("Dodaj now¹ firmê");
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

				savingsBtn = new JButton("Dodaj");
				c.gridx = 1;
				c.gridy = 3;
				c.insets = new Insets(5, 5, 5, 5);
				acctInfo.add(savingsBtn, c);

				bankTeller.setVisible(true);

				closeBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						bankTeller.dispose();
					}
				});

				savingsBtn.addActionListener(new ActionListener() {

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
									"Uzupe³nij pole nazwa", "Uzupe³nij pole",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
		});

		buttons.add(newrow);
		buttons.add(deletebutton);
		buttons.add(print);
		buttons.setBorder(new TitledBorder("Zarz¹dzaj"));

		controls.add(buttons, BorderLayout.NORTH);

		card2.add(companyScroll);
		card2.add(controls);
	}

	@Override
	public void categoryShow(int refresh) {
		// TODO Auto-generated method stub
		appListener.getCategory();
		jTable1 = new JTable(tablemodel) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					JComponent jc = (JComponent) c;
					if (column != 0) {
						jc.setToolTipText("Edytuj: "
								+ getValueAt(row, column).toString());
					}
				}
				return c;
			}
		};
		scroll = new JScrollPane(jTable1);

		TableColumnModel tcm = jTable1.getColumnModel();
		tablemodel.addColumn("ID");
		tablemodel.addColumn("Nazwa");

		jTable1.setRowHeight(20);
		jTable1.getColumn("ID").setCellEditor(null);
		jTable1.getTableHeader().setFont(new Font("Arial", 0, 15));
		tcm.getColumn(0).setMaxWidth(50);

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
			deletebutton = new JButton("Usuñ");

			tableEdit(jTable1); // edycja tabeli

			print.addActionListener(new ActionListener() {
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

				@Override
				public void actionPerformed(ActionEvent e) {

					final JFrame bankTeller = new JFrame("Dodaj now¹ kategorie");
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

					savingsBtn = new JButton("Dodaj");
					c.gridx = 1;
					c.gridy = 3;
					c.insets = new Insets(5, 5, 5, 5);
					acctInfo.add(savingsBtn, c);

					bankTeller.setVisible(true);

					closeBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							bankTeller.dispose();
						}
					});

					savingsBtn.addActionListener(new ActionListener() {

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
										"Uzupe³nij pole nazwa",
										"Uzupe³nij pole",
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
			buttons.setBorder(new TitledBorder("Zarz¹dzaj"));

			controls.add(buttons, BorderLayout.NORTH);

			card1.add(scroll);
			card1.add(controls);
		}
	}

	public void setCategoryListener(CreateCategoryListener categoryListener) {
		this.categoryListener = categoryListener;
	}

	public void setCompanyListener(CreateCompanyListener companyListener) {
		this.companyListener = companyListener;
	}

	public void setItemListener(CreateItemListener itemListener) {
		// TODO Auto-generated method stub
		this.itemListener = itemListener;

	}

	public void fireCategoryEvent(CategoryEvent event) {
		if (categoryListener != null) {
			categoryListener.addCategory(event);
		}
	}

	public void fireDeleteEvent(CompanyEvent event, String table) {
		appListener.deleteRow(event, table);
	}

	public void fireCompanyEvent(CompanyEvent event) {
		if (companyListener != null) {
			companyListener.addCompany(event);
		}
	}

	public void fireItemEvent(ItemsEvent event, String type) {
		if (itemListener != null) {
			itemListener.addItem(event, type);
		}
	}

}
