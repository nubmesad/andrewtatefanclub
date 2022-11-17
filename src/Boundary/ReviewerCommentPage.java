package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ReviewerController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReviewerCommentPage extends JFrame {

	private JPanel contentPane;
	private String paperTitle;
	private String selectedTitle;
	private String paperId;
	private String userId;
	private JTable commentTable;


	/**
	 * Create the frame.
	 */
	public ReviewerCommentPage(String username,String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selected Paper", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(290, 63, 432, 192);
		contentPane.add(panel_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(10, 21, 412, 150);
		panel_1_1.add(textArea);
		
		JLabel lblNewLabel = new JLabel("All Reviewed Papers");
		lblNewLabel.setBounds(242, 25, 253, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Select Reviewed Paper", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(27, 63, 253, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox ReviewsComboBox = new JComboBox();
		
		ReviewsComboBox.setBounds(10, 23, 233, 22);
		panel.add(ReviewsComboBox);
		ReviewerController rc = new ReviewerController();
		ResultSet result = rc.retrieveReviewsPaperId();
		try 
		{
			while(result.next()) 
			{
				paperTitle = result.getString(1);
				 if (paperTitle != null) 
				    {
					 paperTitle = paperTitle.trim();
				    }
				 ReviewsComboBox.addItem(paperTitle);
			}
			
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ReviewsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTitle = (String)ReviewsComboBox.getSelectedItem();
				ResultSet getpId = rc.validatePaperIDRetrieve(selectedTitle);
				ResultSet getReview = rc.retrieveOtherReviews(selectedTitle);
				try {
					if(getReview.next()) {
						textArea.setText(getReview.getString("content"));
					}
					else {
						JOptionPane.showMessageDialog(null,"ERROR","Error", JOptionPane.WARNING_MESSAGE);
					}
					if(getpId.next()) {
						paperId = getpId.getString("paperId");
					}
					ResultSet getComment = rc.retrieveComments(paperId);
					onSuccessViewComments(getComment);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JTextArea txtrWriteYourComments = new JTextArea();
		txtrWriteYourComments.setText("Write your comments here...");
		txtrWriteYourComments.setBounds(38, 152, 228, 208);
		contentPane.add(txtrWriteYourComments);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "All Comments", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(289, 257, 432, 141);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 412, 110);
		panel_1.add(scrollPane);
		
		commentTable = new JTable();
		scrollPane.setViewportView(commentTable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Add Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(27, 127, 252, 271);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Comment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(dtf.format(now));  
				String comment = txtrWriteYourComments.getText();
				ResultSet getuId = rc.validateIDRetrieve(username);
				try {
					if(getuId.next()) {
						userId = getuId.getString("userId");
					}
					if(rc.validateSubmitComment(paperId, userId, comment, dtf.format(now))) {
						JOptionPane.showMessageDialog(null,"Comment Successfully Added", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						ResultSet getComment = rc.retrieveComments(paperId);
						onSuccessViewComments(getComment);
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed to add comment", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 237, 120, 23);
		panel_2.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(630, 399, 91, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
	
	private void onSuccessViewComments(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("User");
		model.addColumn("Comments");
		model.addColumn("Date");
		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("userId"),result.getString("comment"),result.getString("date")});

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		commentTable.setModel(model);
	}
}
