package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import Controller.AdminController;
import Controller.AuthorController;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AuthorAddAuthors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorAddAuthors frame = new AuthorAddAuthors();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public AuthorAddAuthors() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Authors", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(21, 11, 541, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Author Name : ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(23, 36, 102, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Paper  : ");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(23, 69, 102, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lbl1 = new JLabel("Selected Author : ");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl1.setBounds(23, 117, 119, 14);
		panel.add(lbl1);
		
		JLabel lbl2 = new JLabel("Selected Paper : ");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl2.setBounds(23, 142, 119, 14);
		panel.add(lbl2);
		
		JButton addAuthorsBtn = new JButton("Add");
		addAuthorsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		addAuthorsBtn.setBounds(459, 139, 59, 23);
		panel.add(addAuthorsBtn);
		
		JLabel nameLbl = new JLabel("");
		nameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		nameLbl.setBounds(152, 118, 286, 14);
		panel.add(nameLbl);
		JComboBox authorNameCombo = new JComboBox();
		authorNameCombo.setBounds(135, 32, 383, 22);
		panel.add(authorNameCombo);
		
		AuthorController ai = new AuthorController();
		ResultSet rs = ai.retrieveAuthorName();
		try 
		{
			while(rs.next()) 
			{
			    String result = rs.getString(1); // Retrieves the value of the designated column in the current row of this ResultSet object as a String
			    String result2 = rs.getString(2); 
			    if (result != null) 
			    {
			        result = result.trim();
			    }
			    authorNameCombo.addItem(result + " " + result2);
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		authorNameCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s = (String)authorNameCombo.getSelectedItem();
				nameLbl.setText(s);			
			}
		});
		
		
		JLabel paperLbl = new JLabel("");
		paperLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		paperLbl.setBounds(152, 143, 297, 14);
		panel.add(paperLbl);
		
		JComboBox papersCombo = new JComboBox();
		papersCombo.setBounds(135, 65, 383, 22);
		panel.add(papersCombo);
		ResultSet rs2 = ai.retrievePaperTitle();
		try {
			while(rs2.next()) {
			    String result = rs2.getString(1); // Retrieves the value of the designated column in the current row of this ResultSet object as a String
			    if (result != null) 
			    {
			        result = result.trim();
			    }
			    papersCombo.addItem(result);
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		papersCombo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = (String)papersCombo.getSelectedItem();
				paperLbl.setText(s);
			}
		});
		

		
		
		JButton btnNewButton_1 = new JButton("Finish");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(487, 198, 75, 23);
		contentPane.add(btnNewButton_1);
	}
}
