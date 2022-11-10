package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.JTextField;

public class AuthorAddAuthors extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField paperField;

	/**
	 * Launch the application.
	 */
	
	
	public AuthorAddAuthors(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Authors", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(21, 11, 541, 153);
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
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(152, 116, 102, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		paperField = new JTextField();
		paperField.setEditable(false);
		paperField.setBounds(135, 67, 383, 19);
		panel.add(paperField);
		paperField.setColumns(10);
		
		AuthorController ai = new AuthorController();
		ResultSet rs2 = ai.retrievePaperID();
		try 
		{
			if(rs2.next()) 
			{
					String title = rs2.getString(2);
					paperField.setText(title);
			}
		}
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox authorNameCombo = new JComboBox();
		authorNameCombo.setBounds(135, 32, 383, 22);
		panel.add(authorNameCombo);
		
		ResultSet rs = ai.retrieveAuthorName();
		try 
		{
			while(rs.next()) 
			{
			    String result = rs.getString(1);
			    String id = rs.getString(2);
			     
			    if (result != null) 
			    {
			        result = result.trim();
			    }
			    authorNameCombo.addItem(result);
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
				nameField.setText(s);			
			}
		});
		

		
		JButton addAuthorsBtn = new JButton("Add");
		addAuthorsBtn.addActionListener(new ActionListener() {
			private boolean validatePAUI(String paperId, String submittedId, String authorId) {
				return (paperId != null && paperId.length()>0 && submittedId != null && submittedId.length()>0 && authorId != null && authorId.length()>0);
			}
			public void actionPerformed(ActionEvent e) 
			{
				try {
					ResultSet resultId = ai.validateIDRetrieve(username);
					ResultSet resultAuthorId = ai.validateAuthorIDRetrieve(nameField.getText());
					ResultSet rs2 = ai.retrievePaperID();
					if(resultId.next() && resultAuthorId.next() && rs2.next()) 
					{
					String submittedId = resultId.getString("userId");
					String authorID = resultAuthorId.getString("userId");
					String paperId = rs2.getString("paperId");
					
					if(validatePAUI(paperId,submittedId,authorID)) 
					{
						System.out.printf(paperId + " " + submittedId + " " + authorID);
						if(ai.validateSubmitPaperAuthor(paperId,submittedId,authorID))
						{
							JOptionPane.showMessageDialog(null,"Authors Added", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						}
						else 
						{
							JOptionPane.showMessageDialog(null,"2r32 Field", "FAILED", JOptionPane.WARNING_MESSAGE);
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"Missing Field", "FAILED", JOptionPane.WARNING_MESSAGE);
					}
				}
				}
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}

			private boolean validateSubmitPaperAuthor(String paperId, String submittedId, String authorID) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		addAuthorsBtn.setBounds(459, 114, 59, 23);
		panel.add(addAuthorsBtn);
		

		
		
		JButton btnNewButton_1 = new JButton("Finish");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(487, 175, 75, 23);
		contentPane.add(btnNewButton_1);
	}

}
