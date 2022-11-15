package Boundary;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.AdminController;
import Controller.ConferenceChairController;
import Controller.ReviewerController;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConferenceChairHome extends JFrame {

	private JPanel contentPane;
	private JTable accTable;
	private JTable autoTable;
	private JLabel testLbl;
	private JLabel workloadLbl;
	private String user;
	private String reviewerId;
	private JComboBox ReviewerComboBox;
	private ResultSet getId;
	private ResultSet mainTable;

	
	public ConferenceChairHome(String username, String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Conference Chair Homepage");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel.setBounds(236, 29, 327, 48);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 115, 342, 347);
		contentPane.add(scrollPane);
		
		accTable = new JTable();
		scrollPane.setViewportView(accTable);
		
		JLabel testLbl = new JLabel("");
		testLbl.setBounds(735, 37, 45, 13);
		contentPane.add(testLbl);
		
		ConferenceChairController cc = new ConferenceChairController();
		mainTable = cc.viewAllCurrentBids();
		ResultSet result2 = cc.validateIDRetrieve(username);
		onSuccessViewBids(mainTable);
		try {
			while(result2.next())
			{
				testLbl.setText(result2.getString("userId"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "View Bids", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(437, 98, 363, 372);
		contentPane.add(panel_2);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manually Allocate Papers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 98, 401, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		


		JComboBox ReviewerComboBox = new JComboBox();
		ReviewerComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		ReviewerComboBox.setBounds(10, 25, 381, 22);
		panel.add(ReviewerComboBox);
		
		JButton btnViewReviewedPapers = new JButton("View Reviewed Papers");
		btnViewReviewedPapers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConferenceChairViewReviewedPapers ua = new ConferenceChairViewReviewedPapers(username);
				ua.setVisible(true);
			}
		});
		
		btnViewReviewedPapers.setBounds(26, 472, 401, 25);
		contentPane.add(btnViewReviewedPapers);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginHome lh = new LoginHome();
				lh.setVisible(true);
			}
		});
		btnLogout.setBounds(699, 473, 102, 23);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome user: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(602, 30, 123, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Preferred workload: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(26, 74, 162, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel workloadLbl = new JLabel("");
		workloadLbl.setBounds(177, 81, 45, 13);
		contentPane.add(workloadLbl);

		
		
		accTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = accTable.rowAtPoint(evt.getPoint());
		        int col = accTable.columnAtPoint(evt.getPoint());
		        
		        
		        if (row >= 0 && col >= 1) 
		        {	
		        	user = (String) accTable.getModel().getValueAt(row, col);
			        getId = cc.validateReviewerIDRetrieve(user);
			        try {
						if(getId.next())
						{
							ReviewerComboBox.removeAllItems();
							String reviewId = getId.getString("userId");
					        ResultSet getWorkload = cc.validateWorkload(reviewId);
					        ResultSet getAllocatedBids = cc.validateBidsDDL(reviewId);
					        ResultSet getAllocatedBids2 = cc.validateBidsDDL(reviewId);
							onSuccessViewSelectedUserBids(getAllocatedBids2);
							if(getWorkload.next())
							{
								workloadLbl.setText(getWorkload.getString(1));
							}
							while(getAllocatedBids.next() && getAllocatedBids != null) 
							{
								String title = getAllocatedBids.getString(1);
								ReviewerComboBox.addItem(title);
							}
						}
					} 

			        catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		        }

		    }
		});
		
		JButton btnAllocatePapers = new JButton("Allocate Paper");
		btnAllocatePapers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String selected_text = (String) ReviewerComboBox.getItemAt(ReviewerComboBox.getSelectedIndex());
				ResultSet pId = cc.getPaperId(selected_text);
				try 
				{
					String reviewId = getId.getString("userId");
			        ResultSet getPaperCount = cc.getAllocatedPapersCount(reviewId);
			        ResultSet getWorkload = cc.validateWorkload(reviewId);
			        
						if(pId.next() && getPaperCount.next() && getWorkload.next())
						{
							ReviewerComboBox.removeAllItems();
							String paperId = pId.getString("paperId");
					        int workload = Integer.parseInt(getWorkload.getString(1)); 
					        int paperCount = Integer.parseInt(getPaperCount.getString(1));
					        if(!(paperCount >= workload))
					        {
								if(cc.insertAllocation(paperId, reviewId) && cc.insertAllocationUpdateBidStatus(paperId, reviewId)) 
								{
									cc.insertAllocationUpdateBidStatusFailed(paperId);
									JOptionPane.showMessageDialog(null, "Inserted Successfully!", "SUCESS", JOptionPane.INFORMATION_MESSAGE);
									mainTable = cc.viewAllCurrentBids();
									onSuccessViewBids(mainTable);
							        ResultSet getAllocatedBids = cc.validateBidsDDL(reviewId);
							        ResultSet getAllocatedBids2 = cc.validateBidsDDL(reviewId);
									onSuccessViewSelectedUserBids(getAllocatedBids2);

									while(getAllocatedBids != null && getAllocatedBids.next()) 
									{
										ReviewerComboBox.addItem(getAllocatedBids.getString(1));
									}
								}
					        }
							else 
							{
								JOptionPane.showMessageDialog(null, "Insertion Failed, Max workload of reviewer reached.", "ERROR", JOptionPane.ERROR_MESSAGE);					
		                    }
						}
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
		});
		btnAllocatePapers.setBounds(260, 58, 131, 22);
		panel.add(btnAllocatePapers);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Auto Allocate Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 193, 401, 277);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		JButton btnAutoAllocate = new JButton("Auto Allocate");
		btnAutoAllocate.setBounds(268, 243, 123, 23);
		panel_1.add(btnAutoAllocate);
		btnAutoAllocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
		        getId = cc.validateReviewerIDRetrieve(user);
		        List<String> list=new ArrayList<String>();  
				try 
				{
					if(getId.next())
					{
						String reviewId = getId.getString("userId");
			        	ResultSet paperIdList = cc.getPaperIdList(reviewId);


				        	while(paperIdList.next())
				        	{
					        	String test = paperIdList.getString(1);
					        	list.add(test);
				        	}				        	
				        	for (int i=0; i<list.size(); i++)
				        	{
						        ResultSet getPaperCount = cc.getAllocatedPapersCount(reviewId);
						        ResultSet getWorkload = cc.validateWorkload(reviewId);
						        if(getPaperCount.next() && getWorkload.next())
								{
							        int workload = Integer.parseInt(getWorkload.getString(1)); 
							        int paperCount = Integer.parseInt(getPaperCount.getString(1));
					        		if(!(paperCount >= workload))
					        		{
						        		String paperId = list.get(i);
						        		if(cc.insertAllocation(paperId, reviewId) && cc.insertAllocationUpdateBidStatus(paperId, reviewId))
						        		{
											cc.insertAllocationUpdateBidStatusFailed(paperId);
											JOptionPane.showMessageDialog(null, "Automatically Inserted Paper: " + list.get(i) + "!", "SUCESS", JOptionPane.INFORMATION_MESSAGE);
						        		}
					        		}
					        		else
					        		{
										JOptionPane.showMessageDialog(null, "Insertion Failed for paper: " + list.get(i) + ", Max workload of reviewer reached.", "ERROR", JOptionPane.ERROR_MESSAGE);					
					        		}
								}
						    }
							ReviewerComboBox.removeAllItems();
							mainTable = cc.viewAllCurrentBids();
							onSuccessViewBids(mainTable);
					        ResultSet getAllocatedBids = cc.validateBidsDDL(reviewId);
					        ResultSet getAllocatedBids2 = cc.validateBidsDDL(reviewId);
							onSuccessViewSelectedUserBids(getAllocatedBids2);

							while(getAllocatedBids != null && getAllocatedBids.next()) 
							{
								ReviewerComboBox.addItem(getAllocatedBids.getString(1));
							}
					}	
				} 
				catch (SQLException e1) 
				{
					
					
					
					
					
					
					
					
					e1.printStackTrace();
				}

			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 18, 381, 214);
		panel_1.add(scrollPane_1);
		
		autoTable = new JTable();
		scrollPane_1.setViewportView(autoTable);
		
		
		
		JButton updateButton = new JButton("Edit Account");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccount ua = new updateAccount(username);
				ua.setVisible(true);
			}
		});
		updateButton.setBounds(573, 473, 116, 23);
		contentPane.add(updateButton);
		
		JButton notifyBtn = new JButton("Notify Author");
		notifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConferenceChairNotifyAuthors ua = new ConferenceChairNotifyAuthors(username);
				ua.setVisible(true);
			}
		});
		notifyBtn.setBounds(447, 473, 116, 23);
		contentPane.add(notifyBtn);

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 822, 542);
		this.setVisible(true);
	}
	
	private void onSuccessViewBids(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("Title");
		model.addColumn("Reviewer");
		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("title"),result.getString("name")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		accTable.setModel(model);
	}
	
	private void onSuccessViewSelectedUserBids(ResultSet result) {
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
		autoTable.setModel(model);
	}
}
