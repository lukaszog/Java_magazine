package com.magazyn.view;

import java.awt.Checkbox;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.magazyn.model.Database;
import com.magazyn.model.Model;
import com.magazyn.model.Category;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class View extends JFrame implements ActionListener, CategoryListener,
		CompanyListener, ClientsListener {

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

	JTabbedPane tabbedPane = new JTabbedPane();

	private JPanel card1 = new JPanel();
	private JPanel card2 = new JPanel();
	private JTable jTable1 = new JTable();
	private JScrollPane scroll = new JScrollPane();
	private DefaultTableModel tablemodel = new DefaultTableModel();
	private List<Category> people;

	int categoryflag = 0, companyflag = 0, clientflag = 0, itemflag = 0;

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

		tabbedPane.setPreferredSize(new Dimension(200, 30));

		tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int paneindex = tabbedPane.getSelectedIndex();

				switch (paneindex) {
				case 1:
					if (companyflag == 0) {
						companyShow();
						companyflag = 1;
					}
					break;
				}
			}
		});
		categoryShow(0);
		clientsShow();

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

	public void companyShow() {
		// TODO Auto-generated method stub
		System.out.println("elo");

	}

	public void loadData() {

		tablemodel.getDataVector().removeAllElements();
		people = model.getPeople();

		for (Category person : people) {
			tablemodel
					.addRow(new Object[] { person.getId(), person.getName() });
		}
	
		people.clear();

	}

	@Override
	public void categoryShow(int refresh) {
		// TODO Auto-generated method stub
		appListener.getCategory();
		jTable1 = new JTable(tablemodel){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){  
		         if(column==0){
		        	 return false;
		         }
		         else{
		        	 return true;
		         }		   
		     } 
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);
		        if (c instanceof JComponent) {
		            JComponent jc = (JComponent) c;
		            if(column!=0){
		            jc.setToolTipText("Edytuj: " + getValueAt(row, column).toString());
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
		jTable1.getTableHeader().setFont( new Font( "Arial" , 0, 15 ));
		tcm.getColumn(0).setMaxWidth(50);
		
		int flag;
		flag = 1;
		if (refresh == 1) {

		} else { 
				if (flag == 1) {
				loadData();
				flag++;
			}

			JPanel controls = new JPanel(new BorderLayout(5, 5));
			JPanel buttons = new JPanel(new GridLayout(0, 1, 4, 4));

			final JButton deletebutton = new JButton("Usuñ");
			JButton newrow = new JButton("Dodaj");
			JButton print = new JButton("Drukuj");

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
			jTable1.getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
					if (jTable1.getCellEditor() != null) {
						
						
						int col = jTable1.getSelectedColumn();
						if(col!=0){
						String columnname = jTable1.getColumnName(col);
						System.out.println(jTable1.getSelectedColumn());
						System.out.println("--" + columnname);
						System.out.println(jTable1.getValueAt(
								jTable1.getSelectedRow(),
								jTable1.getSelectedColumn())); // nowa wartosc
						System.out.println(jTable1.getValueAt(
								jTable1.getSelectedRow(), 0)); // id
						}
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
					// bankTeller.getContentPane().add(everything,
					// BorderLayout.CENTER);

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

								fireCategoryEvent(new CreateCategoryEvent(name,
										"kategorie"));
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
			deletebutton.setEnabled(false);
			jTable1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					deletebutton.setEnabled(true);
				}
			});
			deletebutton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int selRow = (Integer) jTable1.getValueAt(
							jTable1.getSelectedRow(), 0);

					if (selRow >= 0) {
						System.out.println(selRow);
					}
				}
			});

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

	public void fireCategoryEvent(CreateCategoryEvent event) {
		if (categoryListener != null) {
			categoryListener.addCategory(event);
		}
	}

}
