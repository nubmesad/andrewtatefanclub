package Boundary;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.AdminController;
import Controller.AuthorController;
import Controller.ReviewerController;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviewerHome extends JFrame {

	private JPanel contentPane;
	private JLabel workloadLabel;
	private JLabel testLbl;
	private JTable reviewersPaperTable;
	private String choice;
	private String paper;
	private JTable bidsTable;
	private String paperTitle;
	private String paperId;
	private ResultSet result3;
	private ResultSet result4;
	private ResultSet viewCurrentBids;
	
	public ReviewerHome(String username, String password){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReviewerHomepage = new JLabel("Reviewer Homepage");
		 
		lblReviewerHomepage.setFont(new Font("Arial", Font.BOLD, 23));
		contentPane.add(lblReviewerHomepage);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Papers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(364, 67, 456, 371);
		contentPane.add(panel);
		panel.setLayout(null);	
		
		String[] workNo = {"1","2","3","4","5","6","7","8","9","10"};
        JComboBox workLoadCombo = new JComboBox(workNo);
        workLoadCombo.setBounds(166, 39, 47, 22);
        contentPane.add(workLoadCombo);
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Bidding", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 67, 328, 121);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Selected Paper: ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 36, 131, 14);
		panel_1.add(lblNewLabel_1);

		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnYes.setBounds(8, 57, 61, 23);
		panel_1.add(rdbtnYes);
			
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnNo.setBounds(69, 57, 61, 23);
		panel_1.add(rdbtnNo);
		if(rdbtnYes.isSelected())
			choice="Yes";
			if(rdbtnNo.isSelected())
			choice="No";
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnYes);
		group.add(rdbtnNo);
		
		JLabel testLbl = new JLabel("");
		testLbl.setFont(new Font("Arial", Font.BOLD, 12));
		testLbl.setBounds(745, 16, 78, 14);
		contentPane.add(testLbl);
		
		JButton submitBtn = new JButton("Submit Bid");
		submitBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
		        if(rdbtnYes.isSelected()){
		            choice = "Yes";
		        }
		        else if(rdbtnNo.isSelected()){
		            choice = "No";
		        }
		        
		    	try 
		    	{
					ReviewerController rc = new ReviewerController();
					ResultSet result = rc.validateIDRetrieve(username);
					ResultSet result2 = rc.validatePaperIDRetrieve(paper);
					while(result != null && result2 != null && result.next() && result2.next()) 
					{
						String reviewId = result.getString("userId");
						String paperId = result2.getString("paperId");
					
						if(rc.validateSubmitBid(paperId, reviewId, choice)) 
						{
							JOptionPane.showMessageDialog(null, "Inserted", "Information updated successfully", JOptionPane.INFORMATION_MESSAGE);
							result4 = rc.viewAllCurrentBids(testLbl.getText());
							onSuccessViewBids(result4);
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
		
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submitBtn.setBounds(8, 87, 122, 23);
		panel_1.add(submitBtn);
		
		JLabel paperLbl = new JLabel("");
		paperLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		paperLbl.setBounds(117, 36, 187, 14);
		panel_1.add(paperLbl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Current Bids", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(22, 199, 328, 297);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
	
		
		JButton deleteBtn = new JButton("Delete Bids");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReviewerController rc = new ReviewerController();
					viewCurrentBids = rc.viewAllCurrentBids(testLbl.getText());
					if(rc.validateDeleteCurrentBids(paperId,testLbl.getText())) {
						JOptionPane.showMessageDialog(null, "Delete current bids successful", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						if(viewCurrentBids.next()) {
							onSuccessViewBids(viewCurrentBids);
							}
						}
					else {
						JOptionPane.showMessageDialog(null, "Delete current bids failed", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		deleteBtn.setBounds(10, 263, 119, 23);
		panel_2.add(deleteBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 308, 230);
		panel_2.add(scrollPane_1);
		bidsTable = new JTable();
		scrollPane_1.setViewportView(bidsTable);
		
		bidsTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = bidsTable.rowAtPoint(evt.getPoint());
				int col = bidsTable.columnAtPoint(evt.getPoint());
				
				if(row >= 0 && col == 0) {
					paperTitle = (String) bidsTable.getModel().getValueAt(row, col);
					ReviewerController rc = new ReviewerController();
					ResultSet getpId = rc.validatePaperIDRetrieve(paperTitle);
					try {
						if(getpId.next()) {
							paperId = getpId.getString(1);
						}
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
		});
		
		
		JButton btnNewButton_2_1 = new JButton("View Allocated Papers");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerViewAllocatedPapers rap = new ReviewerViewAllocatedPapers(username,password);
				rap.setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(364, 473, 456, 23);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Edit Account");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccount ua = new updateAccount(username);
				ua.setVisible(true);
			}
		});
		btnNewButton_2_1_1.setBounds(364, 449, 145, 23);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("All Reviews");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerCommentPage cr = new ReviewerCommentPage();
				cr.setVisible(true);
			}
		});
		btnNewButton_2_1_1_1.setBounds(509, 449, 179, 23);
		contentPane.add(btnNewButton_2_1_1_1);
		
		JLabel workloadLabel = new JLabel("");
		workloadLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		workloadLabel.setBounds(302, 44, 206, 17);
		contentPane.add(workloadLabel);
		
		JLabel lblNewLabel = new JLabel("Max no. of workload:");
		lblNewLabel.setBounds(29, 42, 146, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));

		
		JButton setBtn = new JButton("Set");
		setBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReviewerController rc = new ReviewerController();
					ResultSet result = rc.validateIDRetrieve(username);
					String workload = String.valueOf(workLoadCombo.getSelectedItem());
					while(result != null && result.next()) {
						String reviewId = result.getString("userId");
						if(rc.validateReviewWorkload(reviewId, workload)) {
							JOptionPane.showMessageDialog(null, "Workload Set", "Information updated successfully", JOptionPane.INFORMATION_MESSAGE);
							workloadLabel.setText("Current Workload: " + workload);
						}
						else {
							JOptionPane.showMessageDialog(null, "Error setting workload", "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		setBtn.setBounds(223, 39, 69, 23);
		contentPane.add(setBtn);
		
		JButton btnNewButton_2_1_1_1_2 = new JButton("Logout");
		btnNewButton_2_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginHome lh = new LoginHome();
				lh.setVisible(true);
			}
		});
		btnNewButton_2_1_1_1_2.setBounds(688, 449, 132, 23);
		contentPane.add(btnNewButton_2_1_1_1_2);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 436, 339);
		panel.add(scrollPane);
		reviewersPaperTable = new JTable();
		scrollPane.setViewportView(reviewersPaperTable);
		
		JLabel lblWelcomeUser = new JLabel("Welcome User:");
		lblWelcomeUser.setFont(new Font("Arial", Font.BOLD, 12));
		lblWelcomeUser.setBounds(644, 11, 111, 23);
		contentPane.add(lblWelcomeUser);
		


		ReviewerController rc = new ReviewerController();
		ResultSet result = rc.retrievePapers();
		onSuccessView(result);
		
		ResultSet result2 = rc.validateIDRetrieve(username);
		try {
			while(result2.next())
			{
			testLbl.setText(result2.getString("userId"));
			result3 = rc.viewAllCurrentBids(testLbl.getText());
			onSuccessViewBids(result3);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		reviewersPaperTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = reviewersPaperTable.rowAtPoint(evt.getPoint());
		        int col = reviewersPaperTable.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 1) {
		        	
		        	paper = (String) reviewersPaperTable.getModel().getValueAt(row, col);
		        	paperLbl.setText(paper);
		        }
		    }
		});
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 839, 542);
		this.setVisible(true);
	}
	
	private void onSuccessView(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("paperId");
		model.addColumn("Title");
		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("paperId"), result.getString("Title")});

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		reviewersPaperTable.setModel(model);

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
				model.addRow(new Object[] {result.getString("Title")});

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		bidsTable.setModel(model);
	}
}
