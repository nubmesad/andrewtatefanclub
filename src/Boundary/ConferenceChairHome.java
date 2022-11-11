package Boundary;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ConferenceChairController;
import Controller.ReviewerController;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ConferenceChairHome extends JFrame {

	private JPanel contentPane;
	private JTable accTable;
	private JTable table;
	private JLabel testLbl;
	private JLabel workloadLbl;

	
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
		ResultSet result = cc.viewAllCurrentBids();
		ResultSet result2 = cc.validateIDRetrieve(username);
		onSuccessViewBids(result);
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
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Allocate Papers", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 98, 401, 294);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAllocatePapers = new JButton("Allocate Papers");
		btnAllocatePapers.setBounds(208, 247, 183, 36);
		panel.add(btnAllocatePapers);
		
		
		JButton btnAutoAllocate = new JButton("Auto Allocate");
		btnAutoAllocate.setBounds(10, 247, 188, 36);
		panel.add(btnAutoAllocate);
		
		JComboBox ReviewerComboBox = new JComboBox();
		ReviewerComboBox.setBounds(10, 25, 381, 22);
		panel.add(ReviewerComboBox);
		
		table = new JTable();
		table.setBounds(10, 58, 381, 178);
		panel.add(table);
		
		JButton btnViewAllBids = new JButton("View All Bids");
		btnViewAllBids.setBounds(49, 426, 170, 36);
		contentPane.add(btnViewAllBids);
		
		JButton btnViewReviewedPapers = new JButton("View Reviewed Papers");
		btnViewReviewedPapers.setBounds(236, 426, 170, 36);
		contentPane.add(btnViewReviewedPapers);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(709, 473, 91, 23);
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
}
