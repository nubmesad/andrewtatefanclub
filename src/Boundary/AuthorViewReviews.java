package Boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.AuthorController;
import Controller.ConferenceChairController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorViewReviews extends JFrame {

	private JPanel contentPane;
	private JTable reviewedPapersTable;
	private String choice;
	private String paperId;
	private JTable reviewTable;
	private JTextArea reviewTextArea;
	private JTextField paperTitleField;
	private JTextField reviewerField;
	private JTextField ratingField;
	private String authorId;
	private String paperTitle;
	private String rating;
	private ResultSet getReviewInfo;
	/**
	 * Create the frame.
	 */
	public AuthorViewReviews(String username,String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AuthorController ac = new AuthorController();
		JLabel lblNewLabel = new JLabel("Author View Reviews");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(235, 10, 198, 58);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Paper", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(336, 78, 317, 329);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(24, 27, 267, 279);
		panel.add(scrollPane);
		
		reviewedPapersTable = new JTable();
		scrollPane.setViewportView(reviewedPapersTable);
		
		ResultSet getuId = ac.validateIDRetrieve(username);
		try {
			if(getuId.next()) {
				authorId = getuId.getString("userId");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet getpId = ac.retrieveReviewsPA(authorId);
		try {
			if(getpId.next()) {
				paperId = getpId.getString("paperId");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet getReview = ac.retrieveReviewerReviews(paperId);
		System.out.println(paperId);
		onSuccessViewReviewedPapers(getReview);
		
		reviewedPapersTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				 int row = reviewedPapersTable.rowAtPoint(evt.getPoint());
			        int col = reviewedPapersTable.columnAtPoint(evt.getPoint());
			        
			        if (row >= 0 && col >= 0) 
			        {	
			        	paperTitle = (String) reviewedPapersTable.getModel().getValueAt(row, col);
						paperTitleField.setText(paperTitle);
			        	try 
			        	{	AuthorController ac = new AuthorController();
							getReviewInfo = ac.retrieveReviewerInformation(paperTitle);
							if(getReviewInfo.next()) {
								reviewerField.setText(getReviewInfo.getString("name"));
								ratingField.setText(getReviewInfo.getString("rating"));
								reviewTextArea.setText(getReviewInfo.getString("review"));
							}
						}
			        	catch (SQLException e) 
			        	{
							e.printStackTrace();
						}
			        
			        }
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 78, 282, 329);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Paper Selected:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 20, 94, 27);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reviewed by:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 59, 94, 27);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Rating:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(10, 96, 94, 27);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Review:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(10, 133, 60, 27);
		panel_1.add(lblNewLabel_1_3);
		
		paperTitleField = new JTextField();
		paperTitleField.setEditable(false);
		paperTitleField.setBounds(114, 25, 142, 19);
		panel_1.add(paperTitleField);
		paperTitleField.setColumns(10);
		
		reviewerField = new JTextField();
		reviewerField.setEditable(false);
		reviewerField.setBounds(114, 64, 142, 19);
		panel_1.add(reviewerField);
		reviewerField.setColumns(10);
		
		ratingField = new JTextField();
		ratingField.setEditable(false);
		ratingField.setColumns(10);
		ratingField.setBounds(114, 104, 142, 19);
		panel_1.add(ratingField);
		
		reviewTextArea = new JTextArea();
		reviewTextArea.setEditable(false);
		reviewTextArea.setBounds(20, 170, 236, 125);
		panel_1.add(reviewTextArea);
		
		JRadioButton rdbtn1 = new JRadioButton("1 Star");
		rdbtn1.setFont(new Font("Arial", Font.BOLD, 14));
		rdbtn1.setBounds(27, 423, 68, 21);
		contentPane.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("2 Star");
		rdbtn2.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn2.setBounds(97, 422, 78, 21);
		contentPane.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("3 Star");
		rdbtn3.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn3.setBounds(177, 423, 68, 21);
		contentPane.add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton("4 Star");
		rdbtn4.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn4.setBounds(266, 423, 68, 21);
		contentPane.add(rdbtn4);
		
		JRadioButton rdbtn5 = new JRadioButton("5 Star");
		rdbtn5.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn5.setBounds(336, 423, 68, 21);
		contentPane.add(rdbtn5);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtn1);
		group.add(rdbtn2);
		group.add(rdbtn3);
		group.add(rdbtn4);
		group.add(rdbtn5);
	
		
		JButton submitRating = new JButton("Submit Rating");
		submitRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn1.isSelected()) {
					rating = "1";
				}
				else if(rdbtn2.isSelected()) {
					rating = "2";
				}
				else if(rdbtn3.isSelected()) {
					rating = "3";
				}
				else if(rdbtn4.isSelected()) {
					rating = "4";
				}
				else if(rdbtn5.isSelected()) {
					rating = "5";
				}
				try {
					if(ac.validateSubmitAuthorRating(getReviewInfo.getString("reviewerId"), paperId, rating)) {
						JOptionPane.showMessageDialog(null, "Successfully added ratings!", "Ratings added successfully", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(Exception ex) 
		    	{
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
		    	}
			}
		});
		submitRating.setBounds(513, 424, 119, 21);
		contentPane.add(submitRating);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(560, 32, 68, 21);
		contentPane.add(btnNewButton);

	}
	private void onSuccessViewReviewedPapers(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("Title");
		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("title")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		reviewedPapersTable.setModel(model);
	}
}
