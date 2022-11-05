package Boundary;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.UserLoginController;

import javax.swing.JScrollPane;
import Entity.User;
import Controller.AdminController;

public class AdminHome extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField pwField;
	private JTextField searchUserField;
	private JTable accTable;
	public String name;
	private JTextField userIDField;


	public AdminHome(String username, String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Homepage");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel.setBounds(324, 29, 239, 48);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 98, 406, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 22, 123, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(10, 59, 123, 36);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Account Role : ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(10, 133, 175, 36);
		panel.add(lblNewLabel_1_1_1);
		
		userField = new JTextField();
		userField.setBounds(198, 28, 175, 31);
		panel.add(userField);
		userField.setColumns(10);
		
		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(198, 65, 175, 31);
		panel.add(pwField);
		
		String[] accountType = {"Author","Reviewer","Conference Chair","Admin"};
		JComboBox accRoleDDL = new JComboBox(accountType);
		accRoleDDL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		accRoleDDL.setSelectedIndex(3);
		accRoleDDL.setBounds(198, 143, 175, 22);
		panel.add(accRoleDDL);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerBtn.setBounds(39, 195, 94, 46);
		registerBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				try {
					String username = userField.getText();
					String password = pwField.getText();
					String email = userIDField.getText();
					String accType = String.valueOf(accRoleDDL.getSelectedItem());

					if(validateUI(username, password)) {
						AdminController admincontroller = new AdminController();
						if(admincontroller.validateRegister(username, password, accType)) {
							JOptionPane.showMessageDialog(null, "Account Registered!", "SUCESS", JOptionPane.INFORMATION_MESSAGE);	
						}
						else {
					    JOptionPane.showMessageDialog(null, "Missing Field", "ERROR", JOptionPane.ERROR_MESSAGE);					
                        }
				}
					else {
						JOptionPane.showMessageDialog(null, "Missing Field", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
				}		
			}
		});
		panel.add(registerBtn);
		
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userIDField.setText(null);
				userField.setText(null);
				pwField.setText(null);
			}
		});
		clearBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		clearBtn.setBounds(279, 195, 94, 46);
		panel.add(clearBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

						AdminController ac = new AdminController();
						String userID = userIDField.getText();
						String username = userField.getText();
						String password = pwField.getText();
						String accountType = String.valueOf(accRoleDDL.getSelectedItem());
						
						if(validateUpdateUI(userID)) {
							if(ac.validateUpdate(userID,username,password,accountType)) {
								onSuccessUpdate();
							}
							else {
								onFailure();
							}
						}
						else {
							onFailure();
						}
					}
					
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Update Failed", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateBtn.setBounds(160, 195, 94, 46);
		panel.add(updateBtn);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("User ID:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_2.setBounds(10, 95, 123, 36);
		panel.add(lblNewLabel_1_1_2);
		
		userIDField = new JTextField();
		userIDField.setColumns(10);
		userIDField.setBounds(198, 102, 175, 31);
		panel.add(userIDField);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(21, 375, 406, 110);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username : ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(10, 39, 123, 36);
		panel_1.add(lblNewLabel_1_2);
		
		searchUserField = new JTextField();
		searchUserField.setColumns(10);
		searchUserField.setBounds(132, 45, 175, 31);
		panel_1.add(searchUserField);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = searchUserField.getText();
				try {
					if(validateUI(username)) {
						AdminController ai = new AdminController();
						ResultSet result = ai.validateRetrieve(username);
						
						if(result != null)  
						{
							onSuccessSearch(result);
						}
						else
						{
						    JOptionPane.showMessageDialog(null, "No records found.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					else 
					{
					    JOptionPane.showMessageDialog(null, "No records found.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
				}			
				
			}
		});
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		searchBtn.setBounds(317, 44, 79, 31);
		panel_1.add(searchBtn);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 115, 342, 347);
		contentPane.add(scrollPane);
		accTable = new JTable();
		scrollPane.setViewportView(accTable);
		AdminController ai = new AdminController();
		ResultSet result = ai.retrieveUserTable();
		onSuccessView(result);
		
		
		JButton Logout = new JButton("Logout");
		Logout.setBounds(709, 473, 91, 23);
		contentPane.add(Logout);
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
					LoginHome lh = new LoginHome();
					lh.setVisible(true);
					
				}
			});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(437, 98, 363, 372);
		contentPane.add(panel_2);
		
		JButton ViewUsers = new JButton("View Users");
		ViewUsers.setBounds(457, 472, 91, 23);
		contentPane.add(ViewUsers);
		ViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminController ai = new AdminController();
					ResultSet result = ai.retrieveUserTable();
					if(result != null) {
						onSuccessView(result);
					}
					else 
					{
					    JOptionPane.showMessageDialog(null, "No records found.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
				}	
				}
				
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 822, 542);
		this.setVisible(true);
	}
	
	private boolean validateUI(String username) {
		return (username != null && username.length()>0);
	}
	
	private void onSuccessView(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("userId");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("accountType");

		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("userId"), result.getString("username"), result.getString("password"), result.getString("accountType")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		accTable.setModel(model);
	}
	
	private void onSuccessSearch(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("userId");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("accountType");

		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("userId"), result.getString("username"), result.getString("password"), result.getString("accountType")});
				userField.setText(result.getString("username"));
				pwField.setText(result.getString("password"));
				userIDField.setText(result.getString("userId"));
				userIDField.setEditable(false);
				/*accRoleDDL.setSelectedItem(("accountType"));*/
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		accTable.setModel(model);
	}

	private void onSuccessUpdate() {
		JOptionPane.showMessageDialog(null, "Information updated successfully", "Information updated successfully", JOptionPane.WARNING_MESSAGE);
	}
	
	
	private void onFailure() {
		JOptionPane.showMessageDialog(null, "No record found", "No record found", JOptionPane.WARNING_MESSAGE);
	}
	private boolean validateUpdateUI(String userID) {
		return (userID != null && userID.length()>0);
	}
	private boolean validateUI(String userID, String password) {
		return (userID != null && userID.length()>0 && password != null && password.length()>0);
	}
}
