package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ConferenceChairController;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ConferenceChairViewReviewedPapers extends JFrame {

	private JPanel contentPane;
	private JTable reviewedPapersTable;
	ConferenceChairController cc = new ConferenceChairController();
	private String paperTitle;
	private JTextField paperTitleField;
	private JTextField reviewerField;
	private JTextField ratingField;
	public ConferenceChairViewReviewedPapers(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblReviewedPapers = new JLabel("Reviewed Papers");
		lblReviewedPapers.setFont(new Font("Arial", Font.BOLD, 23));
		lblReviewedPapers.setBounds(229, 11, 192, 48);
		contentPane.add(lblReviewedPapers);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Paper", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(312, 70, 349, 336);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 330, 304);
		panel_2.add(scrollPane);
		
		reviewedPapersTable = new JTable();
		scrollPane.setViewportView(reviewedPapersTable);
		
		ResultSet reviewedPapers = cc.viewAllReviewedPapers();
		onSuccessViewReviewedPapers(reviewedPapers);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(10, 70, 299, 336);
		contentPane.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Reviewed By : ");
		lblNewLabel_1_1_1_2.setBounds(10, 57, 103, 23);
		panel_2_1.add(lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Review : ");
		lblNewLabel_1_1_1.setBounds(10, 130, 66, 28);
		panel_2_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Rating :");
		lblNewLabel_1_1_1_1.setBounds(10, 91, 93, 28);
		panel_2_1.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(107, 302, 86, 23);
		panel_2_1.add(btnAccept);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setBounds(203, 302, 86, 23);
		panel_2_1.add(btnReject);
		
		paperTitleField = new JTextField();
		paperTitleField.setBounds(123, 24, 166, 23);
		panel_2_1.add(paperTitleField);
		paperTitleField.setColumns(10);
		
		reviewerField = new JTextField();
		reviewerField.setColumns(10);
		reviewerField.setBounds(123, 58, 166, 23);
		panel_2_1.add(reviewerField);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Paper Selected : ");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_2_1.setBounds(10, 23, 115, 23);
		panel_2_1.add(lblNewLabel_1_1_1_2_1);
		
		ratingField = new JTextField();
		ratingField.setColumns(10);
		ratingField.setBounds(123, 96, 166, 23);
		panel_2_1.add(ratingField);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(86, 139, 203, 150);
		panel_2_1.add(scrollPane_1);
		
		JTextArea reviewTextArea = new JTextArea();
		scrollPane_1.setViewportView(reviewTextArea);
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(583, 417, 72, 23);
		contentPane.add(btnBack);
		
		
		reviewedPapersTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = reviewedPapersTable.rowAtPoint(evt.getPoint());
		        int col = reviewedPapersTable.columnAtPoint(evt.getPoint());
		        
		        if (row >= 0 && col >= 0) 
		        {	
		        	paperTitle = (String) reviewedPapersTable.getModel().getValueAt(row, col);

		        
		        }

		    }
		});
	}
	
	private void onSuccessViewReviewedPapers(ResultSet result) {
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
		reviewedPapersTable.setModel(model);
	}
}
