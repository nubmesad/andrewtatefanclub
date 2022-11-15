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

import Controller.ConferenceChairController;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConferenceChairNotifyAuthors extends JFrame {

	private JPanel contentPane;
	private JTable arTable;
	private JTextField emailField;
	private String conferenceId;
	private String authorId;
	private String paperId;
	
	public ConferenceChairNotifyAuthors(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Accepted / Rejected Papers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 21, 610, 456);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 590, 269);
		panel.add(scrollPane);
		
		arTable = new JTable();
		scrollPane.setViewportView(arTable);
		
		JLabel emailLbl = new JLabel("Email: ");
		emailLbl.setFont(new Font("Arial", Font.BOLD, 15));
		emailLbl.setBounds(20, 310, 56, 14);
		panel.add(emailLbl);
		
		emailField = new JTextField();
		emailField.setEditable(false);
		emailField.setBounds(102, 308, 417, 20);
		panel.add(emailField);
		emailField.setColumns(10);
		
		
		JLabel lblMessage = new JLabel("Message: ");
		lblMessage.setFont(new Font("Arial", Font.BOLD, 15));
		lblMessage.setBounds(20, 345, 86, 14);
		panel.add(lblMessage);
		
		JTextArea messageTextArea = new JTextArea();
		messageTextArea.setBounds(102, 347, 417, 87);
		panel.add(messageTextArea);
		
		ConferenceChairController cc = new ConferenceChairController();
		ResultSet getUserId = cc.validateIDRetrieve(username);
		
		try 
		{
			getUserId.next();
			conferenceId = getUserId.getString(1);
			ResultSet paperList = cc.getPaperNotifyList(conferenceId);	
			onSuccess(paperList);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		arTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = arTable.rowAtPoint(evt.getPoint());
		        int col = arTable.columnAtPoint(evt.getPoint());
		        
		        
		        if (row >= 0 && col >= 0) 
		        {	
		        	String getId = (String) arTable.getModel().getValueAt(row, col);
		        	paperId = (String) arTable.getModel().getValueAt(row, 0);
					ResultSet emailSet = cc.getAuthorEmail(getId);
					
					try 
					{
						emailSet.next();
						emailField.setText(emailSet.getString(1));
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}

		        }

		    }
		});
		
		
		JButton notifyBtn = new JButton("Notify");
		notifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ResultSet paperList = cc.getPaperNotifyList(conferenceId);	
				try {
					paperList.next();
					authorId=paperList.getString(3);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if("".equals(messageTextArea.getText()))
				{
					JOptionPane.showMessageDialog(null, "Dont leave textarea empty la", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if (cc.sendEmail(conferenceId, authorId, messageTextArea.getText()) && cc.sendEmailStatus(paperId,conferenceId))
				{
					JOptionPane.showMessageDialog(null, "Inserted Successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					onSuccess(paperList);
				}
			}
		});
		notifyBtn.setBounds(529, 414, 71, 20);
		panel.add(notifyBtn);
	}
	
	private void onSuccess(ResultSet result) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("PaperId");
		model.addColumn("Title");
		model.addColumn("AuthorId");
		model.addColumn("Paper Status");
		try {
			while(result != null && result.next()) {
				model.addRow(new Object[] {result.getString("paperId"),result.getString("title"),result.getString("submittedId"),result.getString("paperStatus")});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}
		arTable.setModel(model);
	}
}
