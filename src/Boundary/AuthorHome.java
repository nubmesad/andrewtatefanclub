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
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.AuthorController;

public class AuthorHome extends JFrame {

	private JPanel contentPane;


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
		
		JList reviewList = new JList();
		reviewList.setBounds(381, 118, 402, 316);
		contentPane.add(reviewList);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Reviews", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(361, 98, 439, 357);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Rate Review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 98, 323, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel reviewTitleLbl = new JLabel("New label");
		reviewTitleLbl.setFont(new Font("Arial", Font.BOLD, 15));
		reviewTitleLbl.setBounds(10, 33, 122, 14);
		panel_1.add(reviewTitleLbl);
		
		JRadioButton radioBtn1 = new JRadioButton("1");
		radioBtn1.setBounds(10, 54, 38, 23);
		panel_1.add(radioBtn1);
		
		JRadioButton radioBtn2 = new JRadioButton("2");
		radioBtn2.setBounds(50, 54, 38, 23);
		panel_1.add(radioBtn2);
		
		JRadioButton radioBtn3 = new JRadioButton("3");
		radioBtn3.setBounds(90, 54, 38, 23);
		panel_1.add(radioBtn3);
		
		JRadioButton radioBtn4 = new JRadioButton("4");
		radioBtn4.setBounds(130, 54, 38, 23);
		panel_1.add(radioBtn4);
		
		JRadioButton radioBtn5 = new JRadioButton("5");
		radioBtn5.setBounds(170, 54, 38, 23);
		panel_1.add(radioBtn5);
		
		JButton ratingBtn = new JButton("Submit Rating");
		ratingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
		ratingBtn.setBounds(10, 83, 129, 25);
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
}
