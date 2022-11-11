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

import Controller.AuthorController;
import Controller.ReviewerController;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReviewerHome extends JFrame {

	private JPanel contentPane;
	private JLabel workloadLabel;


	public ReviewerHome(String username, String password) {
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
		panel.setBorder(new TitledBorder(null, "Un-Reviewed Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(364, 67, 456, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		String[] workNo = {"1","2","3","4","5","6","7","8","9","10"};
        JComboBox workLoadCombo = new JComboBox(workNo);
        workLoadCombo.setBounds(166, 39, 47, 22);
        contentPane.add(workLoadCombo);
		
		JList list = new JList();
		list.setBounds(10, 25, 436, 335);
		panel.add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Bidding", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 67, 328, 121);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 36, 122, 14);
		panel_1.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(8, 57, 61, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnNo.setBounds(69, 57, 61, 23);
		panel_1.add(rdbtnNo);
		
		JButton btnNewButton_1 = new JButton("Submit Bid");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(8, 87, 122, 23);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Current Bids", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(22, 199, 328, 297);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(10, 23, 308, 229);
		panel_2.add(list_1);
		
		JButton btnNewButton_2 = new JButton("Delete Bids");
		btnNewButton_2.setBounds(10, 263, 91, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("View Allocated Papers");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerViewAllocatedPapers rap = new ReviewerViewAllocatedPapers();
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
		btnNewButton_2_1_1.setBounds(364, 449, 111, 23);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("All Reviews");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewerCommentPage cr = new ReviewerCommentPage();
				cr.setVisible(true);
			}
		});
		btnNewButton_2_1_1_1.setBounds(595, 449, 111, 23);
		contentPane.add(btnNewButton_2_1_1_1);
		
		JLabel workloadLabel = new JLabel("");
		workloadLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		workloadLabel.setBounds(305, 44, 206, 17);
		contentPane.add(workloadLabel);
		
		JLabel lblNewLabel = new JLabel("Max no. of workload:");
		lblNewLabel.setBounds(29, 42, 146, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));

		
		JButton btnNewButton_3 = new JButton("Set");
		btnNewButton_3.addActionListener(new ActionListener() {
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
		btnNewButton_3.setBounds(223, 39, 69, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2_1_1_1_1 = new JButton("View Bids");
		btnNewButton_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1_1_1_1.setBounds(480, 449, 111, 23);
		contentPane.add(btnNewButton_2_1_1_1_1);
		
		JButton btnNewButton_2_1_1_1_2 = new JButton("Logout");
		btnNewButton_2_1_1_1_2.setBounds(709, 449, 111, 23);
		contentPane.add(btnNewButton_2_1_1_1_2);
	
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 839, 542);
		this.setVisible(true);
	}
	
}
