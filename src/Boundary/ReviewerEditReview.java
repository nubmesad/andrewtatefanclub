package Boundary;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ReviewerController;

public class ReviewerEditReview extends JFrame {

	private JPanel contentPane;
	private JTable reviewTable;
	private String paperTitle;
	private ResultSet getContent;
	private ResultSet getAuthor;
	private ResultSet getpId;
	private ResultSet getReviews;
	private String paperId;
	private String rating;

	/**
	 * Create the frame.
	 */
	public ReviewerEditReview(String username,String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ReviewerController rc = new ReviewerController();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(439, 11, 372, 483);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new TitledBorder(null, "My Reviewed Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(271, 449, 91, 23);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 24, 350, 405);
		panel.add(scrollPane_1);
		
		reviewTable = new JTable();
		scrollPane_1.setViewportView(reviewTable);
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Paper Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 419, 72);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 22, 48, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Author : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 47, 87, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel titleLabel = new JLabel("New label");
		titleLabel.setBounds(64, 23, 177, 14);
		panel_1.add(titleLabel);
		
		JLabel authorLabel = new JLabel("New label");
		authorLabel.setBounds(64, 47, 164, 14);
		panel_1.add(authorLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Evaluation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 82, 419, 207);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnStrongAccept = new JRadioButton("3: Strong accept");
		rdbtnStrongAccept.setBounds(6, 18, 141, 23);
		panel_2.add(rdbtnStrongAccept);
		
		JRadioButton rdbtnAccept = new JRadioButton("2: Accept");
		rdbtnAccept.setBounds(6, 44, 141, 23);
		panel_2.add(rdbtnAccept);
		
		JRadioButton rdbtnWeakAccept = new JRadioButton("1: Weak accept");
		rdbtnWeakAccept.setBounds(6, 70, 141, 23);
		panel_2.add(rdbtnWeakAccept);
		
		JRadioButton rdbtnBorderlinePaper = new JRadioButton("0: Borderline paper");
		rdbtnBorderlinePaper.setBounds(6, 96, 141, 23);
		panel_2.add(rdbtnBorderlinePaper);
		
		JRadioButton rdbtnWeakReject = new JRadioButton("-1: Weak reject");
		rdbtnWeakReject.setBounds(6, 122, 141, 23);
		panel_2.add(rdbtnWeakReject);
		
		JRadioButton rdbtnReject = new JRadioButton("-2: Reject");
		rdbtnReject.setBounds(6, 148, 111, 23);
		panel_2.add(rdbtnReject);
		
		JRadioButton rdbtnStrongReject = new JRadioButton("-3: Strong reject");
		rdbtnStrongReject.setBounds(6, 174, 141, 23);
		panel_2.add(rdbtnStrongReject);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnStrongReject);
		group.add(rdbtnReject);
		group.add(rdbtnWeakReject);
		group.add(rdbtnBorderlinePaper);
		group.add(rdbtnWeakAccept);
		group.add(rdbtnAccept);
		group.add(rdbtnStrongAccept);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 289, 419, 205);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 21, 399, 148);
		panel_3.add(textArea);
		
		ResultSet rId = rc.validateIDRetrieve(username);
		try {
			if(rId.next()) {
				String reviewerId = rId.getString("userId");
				ResultSet result = rc.retrieveReviewedPapers(reviewerId);
				onSuccessViewBids(result);
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		reviewTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = reviewTable.rowAtPoint(evt.getPoint());
				int col = reviewTable.columnAtPoint(evt.getPoint());
				
				if(row >= 0 && col == 0) {
					paperTitle = (String) reviewTable.getModel().getValueAt(row, col);
					titleLabel.setText(paperTitle);
					getAuthor = rc.retrieveAuthor(paperTitle);
					getpId = rc.validatePaperIDRetrieve(paperTitle);
					
					StringBuilder builder = new StringBuilder();
					try {
						if(getpId.next()) {
							
							paperId = getpId.getString(1);
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Errororor", "Error", JOptionPane.WARNING_MESSAGE);
						}
						ResultSet getReviews = rc.retrieveReviews(paperId);
						if(getReviews.next()) {
							textArea.setText(getReviews.getString("review"));
							rating = getReviews.getString("rating");
							if(rating.equals("Strong Reject")) {
								rdbtnStrongReject.setSelected(true);
							}
							else if(rating.equals("Reject")) {
								rdbtnReject.setSelected(true);
							}
							else if(rating.equals("Weak Reject")) {
								rdbtnWeakReject.setSelected(true);
							}
							else if(rating.equals("Borderline paper")) {
								rdbtnBorderlinePaper.setSelected(true);
							}
							else if(rating.equals("Weak Accept")) {
								rdbtnWeakAccept.setSelected(true);
							}
							else if(rating.equals("Accept")) {
								rdbtnAccept.setSelected(true);
							}
							else if(rating.equals("Strong Accept")) {
								rdbtnStrongAccept.setSelected(true);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Errororor", "Error", JOptionPane.WARNING_MESSAGE);
						}

						if(getAuthor.next()) {

							builder.append(getAuthor.getString(1));
							builder.append(", ");
						}
						authorLabel.setText(builder.toString());

						
					}
			        catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton updateBtn = new JButton("Update Review");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnStrongReject.isSelected()) {
					rating = "Strong Reject";
				}
				else if(rdbtnReject.isSelected()) {
					rating = "Reject";
				}
				else if(rdbtnWeakReject.isSelected()) {
					rating = "Weak Reject";
				}
				else if(rdbtnBorderlinePaper.isSelected()) {
					rating = "Borderline paper";
				}
				else if(rdbtnWeakAccept.isSelected()) {
					rating = "Weak Accept";
				}
				else if(rdbtnAccept.isSelected()) {
					rating = "Accept";
				}
				else if(rdbtnStrongAccept.isSelected()) {
					rating = "Strong Accept";
				}
				try {
					String review = textArea.getText();
					if(rc.validateUpdateReview(rating, review, paperId)) {
						JOptionPane.showMessageDialog(null, "Updated Review Paper!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Update failed!", "Failed", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(Exception ex) 
		    	{
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
		    	}
				
			}
		});
		updateBtn.setBounds(10, 450, 157, 23);
		panel.add(updateBtn);
	}
	
	private void onSuccessViewBids(ResultSet result) {
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
		reviewTable.setModel(model);
	}
}
