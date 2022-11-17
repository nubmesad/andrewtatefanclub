package Boundary;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.AdminController;
import Controller.AuthorController;
import javax.swing.JTextField;

public class AuthorHome extends JFrame {

	private JPanel contentPane;
	private JTable reviewTable;
	private String authorId;

	public AuthorHome(String username, String password) {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Author Homepage");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel.setBounds(323, 31, 220, 56);
		contentPane.add(lblNewLabel);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginHome lh = new LoginHome();
				lh.setVisible(true);
			}
		});
		logoutBtn.setBounds(652, 466, 146, 26);
		contentPane.add(logoutBtn);
		
		JLabel reviewTitleLbl = new JLabel("");
		reviewTitleLbl.setBounds(28, 98, 323, 34);
		contentPane.add(reviewTitleLbl);
		reviewTitleLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Email Inbox", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 98, 757, 334);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 706, 286);
		panel.add(scrollPane);
		
		reviewTable = new JTable();
		scrollPane.setViewportView(reviewTable);
		AuthorController ac = new AuthorController();
		ResultSet getuId = ac.validateIDRetrieve(username);
		try {
			if(getuId.next()) {
				authorId = getuId.getString("userId");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet getEmail = ac.retrieveEmail(authorId);
		onSuccess(getEmail);
		
		JButton updateBtn = new JButton("Update Account");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccount ua = new updateAccount(username);
				ua.setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		updateBtn.setBounds(496, 467, 146, 25);
		contentPane.add(updateBtn);
		
		JButton submitPaperBtn = new JButton("Submit Paper");
		submitPaperBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthorSubmitPaper asp = new AuthorSubmitPaper(username, password);
				asp.setVisible(true);
			}
		});
		submitPaperBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		submitPaperBtn.setBounds(184, 466, 146, 26);
		contentPane.add(submitPaperBtn);
		
		JButton viewReviews = new JButton("View Reviews");
		viewReviews.setFont(new Font("Arial", Font.PLAIN, 15));
		viewReviews.setBounds(28, 466, 146, 26);
		contentPane.add(viewReviews);
		
		viewReviews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthorViewReviews avr = new AuthorViewReviews(username,password);
				avr.setVisible(true);
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 822, 542);
		this.setVisible(true);
		
	}
	
	
	private void onSuccess(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("From Conference Chair:");
		model.addColumn("Message");

		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("name"), result.getString("message")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		reviewTable.setModel(model);
	}
	
		
	
}
