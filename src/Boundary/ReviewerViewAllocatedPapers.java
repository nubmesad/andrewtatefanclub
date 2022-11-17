package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ReviewerController;

import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviewerViewAllocatedPapers extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String paperTitle;
	private ResultSet getContent;
	private ResultSet getAuthor;
	private String titleLabel;
	private String contentArea;
	private String rating;
	private String reviewerId;

	/**
	 * Create the frame.
	 */
	public ReviewerViewAllocatedPapers(String username,String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "My Allocated Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(439, 11, 372, 483);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 352, 150);
		panel.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		ReviewerController rc = new ReviewerController();
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(271, 449, 91, 23);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 192, 352, 247);
		panel.add(scrollPane_1);
		
		JTextArea contentArea_1 = new JTextArea();
		contentArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(contentArea_1);
		contentArea_1.setEditable(false);
		
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
		
		try {
			ResultSet rId = rc.validateIDRetrieve(username);
			if(rId.next()) {
				reviewerId = rId.getString("userId");
				ResultSet result = rc.retrieveAllocatedBids(reviewerId);
				onSuccessViewBids(result);
			}
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("Submit review");
		btnNewButton.addActionListener(new ActionListener() {
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
					ReviewerController rc = new ReviewerController();
					ResultSet getpId = rc.validatePaperIDRetrieve(titleLabel.getText());
					ResultSet rId = rc.validateIDRetrieve(username);
					
					if(getpId.next() && rId.next()) {
						if(rc.validateSubmitReview(getpId.getString(1),rating,textArea.getText(),rId.getString("userId")) && rc.validateUpdateStatus(getpId.getString(1))) {
							JOptionPane.showMessageDialog(null, "Inserted", "Review added successfully", JOptionPane.INFORMATION_MESSAGE);
							
							contentArea_1.setText(null);
							titleLabel.setText(null);
							authorLabel.setText(null);
							textArea.setText(null);
							
							ResultSet result = rc.retrieveAllocatedBids(reviewerId);
							onSuccessViewBids(result);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Error, Inserting failed", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					}
					
				}
				catch(Exception ex) 
		    	{
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
		    	}
				
			}
		});
		btnNewButton.setBounds(285, 171, 124, 23);
		panel_3.add(btnNewButton);
		
		JButton btnEditReview = new JButton("Edit review");
		btnEditReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerEditReview rer = new ReviewerEditReview(username,password);
				rer.setVisible(true);
			}
		});
		btnEditReview.setBounds(151, 171, 124, 23);
		panel_3.add(btnEditReview);
		
		JButton btnDeleteReview = new JButton("Delete review");
		btnDeleteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerDeleteReview rdr = new ReviewerDeleteReview(username,password);
				rdr.setVisible(true);
			}
		});
		btnDeleteReview.setBounds(10, 171, 124, 23);
		panel_3.add(btnDeleteReview);
		

		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				
				if(row >= 0 && col == 0) {
					paperTitle = (String) table.getModel().getValueAt(row, col);
					titleLabel.setText(paperTitle);
					
					getContent = rc.retrieveContent(paperTitle);
					getAuthor = rc.retrieveAuthor(paperTitle);
					StringBuilder builder = new StringBuilder();
					try {
						if(getContent.next()) {
							System.out.printf(getContent.getString(1));
							contentArea_1.setText(getContent.getString(1));
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Failed.", "ERROR", JOptionPane.ERROR_MESSAGE);			
						}
						while(getAuthor.next()) {
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
		table.setModel(model);
	}
}
