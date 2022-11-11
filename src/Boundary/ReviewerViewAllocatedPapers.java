package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewerViewAllocatedPapers extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewerViewAllocatedPapers frame = new ReviewerViewAllocatedPapers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReviewerViewAllocatedPapers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "My Allocated Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(439, 11, 372, 483);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 21, 352, 417);
		panel.add(table);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(271, 449, 91, 23);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Paper Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 419, 72);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 22, 48, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Author : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 47, 87, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(64, 23, 177, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(64, 47, 164, 14);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Evaluation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 82, 419, 207);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("3: Strong accept");
		rdbtnNewRadioButton.setBounds(6, 18, 141, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnAccept = new JRadioButton("2: Accept");
		rdbtnAccept.setBounds(6, 44, 141, 23);
		panel_2.add(rdbtnAccept);
		
		JRadioButton rdbtnWeakAccept = new JRadioButton("1: Weak accept");
		rdbtnWeakAccept.setBounds(6, 70, 141, 23);
		panel_2.add(rdbtnWeakAccept);
		
		JRadioButton rdbtnBorderlinePaper = new JRadioButton("0: Borderline paper");
		rdbtnBorderlinePaper.setBounds(6, 96, 141, 23);
		panel_2.add(rdbtnBorderlinePaper);
		
		JRadioButton rdbtnWeakReject = new JRadioButton("-1: Weak reject");
		rdbtnWeakReject.setBounds(6, 122, 141, 23);
		panel_2.add(rdbtnWeakReject);
		
		JRadioButton rdbtnReject = new JRadioButton("-2: Reject");
		rdbtnReject.setBounds(6, 148, 111, 23);
		panel_2.add(rdbtnReject);
		
		JRadioButton rdbtnStrongReject = new JRadioButton("-3: Strong reject");
		rdbtnStrongReject.setBounds(6, 174, 141, 23);
		panel_2.add(rdbtnStrongReject);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 289, 419, 205);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 21, 399, 148);
		panel_3.add(textArea);
		
		JButton btnNewButton = new JButton("Submit review");
		btnNewButton.setBounds(285, 171, 124, 23);
		panel_3.add(btnNewButton);
		
		JButton btnEditReview = new JButton("Edit review");
		btnEditReview.setBounds(151, 171, 124, 23);
		panel_3.add(btnEditReview);
		
		JButton btnDeleteReview = new JButton("Delete review");
		btnDeleteReview.setBounds(10, 171, 124, 23);
		panel_3.add(btnDeleteReview);
	}
}
