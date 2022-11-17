package Boundary;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Controller.ReviewerController;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewerViewBidsResult extends JFrame {

	private JPanel contentPane;
	private JTable bidsTable;


	public ReviewerViewBidsResult(String username) {
		ReviewerController rc = new ReviewerController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Bid Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 464, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 444, 353);
		panel.add(scrollPane);
		
		bidsTable = new JTable();
		scrollPane.setViewportView(bidsTable);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setBounds(383, 413, 91, 23);
		contentPane.add(backBtn);
		
		ResultSet getId = rc.validateIDRetrieve(username);
			try {
			getId.next();
			String reviewId = getId.getString(1);
			ResultSet bidsResult = rc.getBidsResult(reviewId);
			onSuccessViewBids(bidsResult);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void onSuccessViewBids(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("PaperId");
		model.addColumn("Title");
		model.addColumn("BidInfo");
		model.addColumn("BidStatus");

		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("paperId"),result.getString("title"),result.getString("bidInfo"),result.getString("bidStatus")});

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		bidsTable.setModel(model);
	}
}
