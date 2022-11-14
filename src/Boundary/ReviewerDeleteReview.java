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
import java.awt.event.MouseAdapter;

public class ReviewerDeleteReview extends JFrame {

	private JPanel contentPane;
	private String paperTitle;
	private ResultSet getContent;
	private ResultSet getAuthor;
	private ResultSet getpId;
	private ResultSet getReviews;
	private String paperId;
	private String rating;
	private JTable reviewTable;
	private ResultSet result;

	/**
	 * Create the frame.
	 */
	public ReviewerDeleteReview(String username, String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ReviewerController rc = new ReviewerController();
	
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 93, 431, 273);
		contentPane.add(panel);
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
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 35, 411, 228);
		panel.add(scrollPane);
		
		reviewTable = new JTable();
		scrollPane.setViewportView(reviewTable);
		ResultSet rId = rc.validateIDRetrieve(username);
		try {
			if(rId.next()) {
				String reviewerId = rId.getString("userId");
				result = rc.retrieveReviewedPapers(reviewerId);
				onSuccessViewReviews(result);
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
					StringBuilder builder = new StringBuilder();
					try {
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
			}
		});
		JButton btnNewButton = new JButton("Delete Review");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getpId = rc.validatePaperIDRetrieve(paperTitle);
					if(getpId.next()) {
						paperId = getpId.getString(1);
					}
					else {
						JOptionPane.showMessageDialog(null, "Cant get paper Id.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					if(rc.validateDeleteReview(paperId) && rc.validateUpdateStatus2(paperId)) {
						JOptionPane.showMessageDialog(null, "Delete successful.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						ResultSet rId = rc.validateIDRetrieve(username);
						if(rId.next()) {
							String reviewerId = rId.getString("userId");
							result = rc.retrieveReviewedPapers(reviewerId);
							onSuccessViewReviews(result);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(265, 373, 164, 21);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(20, 373, 72, 21);
		contentPane.add(btnBack);
	}
	private void onSuccessViewReviews(ResultSet result) {
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
