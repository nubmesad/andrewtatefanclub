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

public class AuthorHome extends JFrame {

	private JPanel contentPane;
	private JTable accTable;

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
		logoutBtn.setBounds(709, 473, 91, 23);
		contentPane.add(logoutBtn);
		
		JLabel reviewTitleLbl = new JLabel("");
		reviewTitleLbl.setBounds(28, 98, 323, 34);
		contentPane.add(reviewTitleLbl);
		reviewTitleLbl.setFont(new Font("Arial", Font.PLAIN, 13));
		JScrollPane reviewList = new JScrollPane();
		reviewList.setBounds(381, 118, 402, 316);
		contentPane.add(reviewList);
		accTable = new JTable();
		reviewList.setViewportView(accTable);
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Reviews", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(361, 98, 439, 357);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Rate Review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 143, 323, 78);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton radioBtn1 = new JRadioButton("1");
		radioBtn1.setBounds(10, 19, 38, 23);
		panel_1.add(radioBtn1);
		
		JRadioButton radioBtn2 = new JRadioButton("2");
		radioBtn2.setBounds(50, 19, 38, 23);
		panel_1.add(radioBtn2);
		
		JRadioButton radioBtn3 = new JRadioButton("3");
		radioBtn3.setBounds(90, 19, 38, 23);
		panel_1.add(radioBtn3);
		
		JRadioButton radioBtn4 = new JRadioButton("4");
		radioBtn4.setBounds(130, 19, 38, 23);
		panel_1.add(radioBtn4);
		
		JRadioButton radioBtn5 = new JRadioButton("5");
		radioBtn5.setBounds(170, 19, 38, 23);
		panel_1.add(radioBtn5);
		
		JButton ratingBtn = new JButton("Submit Rating");
		ratingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
		ratingBtn.setBounds(20, 47, 129, 25);
		panel_1.add(ratingBtn);
		
		JButton updateBtn = new JButton("Update Account");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccount ua = new updateAccount();
				ua.setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		updateBtn.setBounds(28, 253, 158, 56);
		contentPane.add(updateBtn);
		
		JButton submitPaperBtn = new JButton("Submit Paper");
		submitPaperBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthorSubmitPaper asp = new AuthorSubmitPaper();
				asp.setVisible(true);
			}
		});
		submitPaperBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		submitPaperBtn.setBounds(196, 253, 155, 56);
		contentPane.add(submitPaperBtn);
		
		JButton CurrentReviewsBtn = new JButton("Current Reviews");
		CurrentReviewsBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		CurrentReviewsBtn.setBounds(371, 464, 146, 26);
		contentPane.add(CurrentReviewsBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(28, 86, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		
		CurrentReviewsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AuthorController ac = new AuthorController();
					ResultSet result = ac.retrieveUserTable();
					if(result != null) {
						onSuccess(result);
						reviewList.addMouseListener(new MouseAdapter() 
						{
							@Override
							public void mouseReleased(MouseEvent e) {
								int index = accTable.getSelectedRow();
								String title = accTable.getValueAt(index, 0).toString();
								
								reviewTitleLbl.setText(title);
							}
						});
					}
					else 
					{
					    JOptionPane.showMessageDialog(null, "No records found.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
				}	
				}
				
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 822, 542);
		this.setVisible(true);
		
	}
	
	

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void onSuccess(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("title");
		model.addColumn("content");

		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("title"), result.getString("content")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		accTable.setModel(model);
	}
}
