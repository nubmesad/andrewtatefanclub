package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controller.AdminController;
import Controller.AuthorController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AuthorSubmitPaper extends JFrame {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextArea textAreaField;

	public AuthorSubmitPaper(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Paper Submission", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 642, 281);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Title : ");
		lblNewLabel_3.setBounds(10, 29, 48, 14);
		panel_3.add(lblNewLabel_3);
		
		titleField = new JTextField();
		titleField.setBounds(78, 26, 554, 20);
		panel_3.add(titleField);
		titleField.setColumns(10);
		JTextArea textAreaField = new JTextArea();
		textAreaField.setBounds(78, 54, 554, 181);
		panel_3.add(textAreaField);
		
		JLabel lblNewLabel_4 = new JLabel("Contents : ");
		lblNewLabel_4.setBounds(10, 132, 91, 14);
		panel_3.add(lblNewLabel_4);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				AuthorController ac = new AuthorController();
				String title = titleField.getText();
				String content = textAreaField.getText();
					if(validateUI(title,content)) 
						{
						if(ac.validateSubmitPaper(title, content))
						{
							JOptionPane.showMessageDialog(null, "Paper Submitted", "SUCESS", JOptionPane.INFORMATION_MESSAGE);	
						}
						else
						{
						    JOptionPane.showMessageDialog(null, "Fields cannot be empty.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
				    JOptionPane.showMessageDialog(null, "Fields cannot be empty.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
				}		
			}}
		);
		nextButton.setBounds(541, 246, 91, 23);
		panel_3.add(nextButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(450, 246, 91, 22);
		panel_3.add(btnBack);
	}
	
	private boolean validateUI(String title, String content) {
		return (title != null && title.length()>0 && content != null && content.length()>0);
	}
}


