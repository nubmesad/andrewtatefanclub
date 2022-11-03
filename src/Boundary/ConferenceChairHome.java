package Boundary;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

public class ConferenceChairHome extends JFrame {

	private JPanel contentPane;
	private JTable accTable;
	private JTable table;

	
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
		
		accTable = new JTable();
		accTable.setBounds(447, 115, 342, 347);
		contentPane.add(accTable);
		
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 822, 542);
		this.setVisible(true);
	}
}
