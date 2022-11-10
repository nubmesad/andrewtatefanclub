package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Controller.AuthorController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField userField;
	private JTextField npwdField;
	private JTextField emailField;

	/**
	 * Create the frame.
	 */
	public updateAccount(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update your account details");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 24, 307, 33);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 68, 352, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(167, 80, 151, 28);
		panel.add(userField);
		userField.setColumns(10);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(167, 42, 151, 27);
		panel.add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User ID : ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 40, 100, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Username : ");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 79, 160, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password : ");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 117, 151, 27);
		panel.add(lblNewLabel_1_1_1);
		
		npwdField = new JTextField();
		npwdField.setColumns(10);
		npwdField.setBounds(167, 118, 151, 28);
		panel.add(npwdField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email : ");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 155, 151, 27);
		panel.add(lblNewLabel_1_1_1_1);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(167, 155, 151, 28);
		panel.add(emailField);
		AuthorController ac = new AuthorController();
		ResultSet result = ac.validateIDRetrieve(username);
		onSuccessSearch(result);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AuthorController ac = new AuthorController();
					String username = userField.getText();
					String npassword = npwdField.getText();
					String email = emailField.getText();
					String userId = idField.getText();
					if(validatePassword(npassword)) {
						if(ac.validateUpdate(userId,username,npassword,email)) {
							onSuccessUpdate();
						}
						else {
							onFailure();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Password cannot be empty","ERROR", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Update Failed", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(219, 194, 100, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton_1.setBounds(290, 311, 91, 23);
		contentPane.add(btnNewButton_1);
	}
	private void onSuccessSearch(ResultSet result) {

		try {
			while(result != null && result.next()) {
				userField.setText(result.getString("username"));
				idField.setText(result.getString("userId"));
				emailField.setText(result.getString("email"));
				npwdField.setText(result.getString("password"));
				

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void onSuccessUpdate() {
		JOptionPane.showMessageDialog(null, "Information updated successfully", "Information updated successfully", JOptionPane.WARNING_MESSAGE);
	}
	private void onFailure() {
		JOptionPane.showMessageDialog(null, "Password does not match", "Password does not match", JOptionPane.WARNING_MESSAGE);
	}
	private boolean validatePassword(String password) {
		return (password != null && password.length()>0);
		}
}
