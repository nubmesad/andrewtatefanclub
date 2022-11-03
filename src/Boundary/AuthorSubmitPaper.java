package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AuthorSubmitPaper extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorSubmitPaper frame = new AuthorSubmitPaper();
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
	public AuthorSubmitPaper() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Author", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 26, 199, 140);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 25, 71, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(91, 22, 89, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name : ");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLastName.setBounds(10, 53, 71, 14);
		panel.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 50, 89, 20);
		panel.add(textField_1);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail.setBounds(10, 81, 71, 14);
		panel.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(91, 78, 89, 20);
		panel.add(textField_2);
		
		JLabel lblOrganization = new JLabel("Organization : ");
		lblOrganization.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOrganization.setBounds(10, 106, 81, 14);
		panel.add(lblOrganization);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 103, 89, 20);
		panel.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Author 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(242, 26, 199, 140);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("First Name : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 25, 71, 14);
		panel_1.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(91, 22, 89, 20);
		panel_1.add(textField_4);
		
		JLabel lblLastName_1 = new JLabel("Last Name : ");
		lblLastName_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLastName_1.setBounds(10, 53, 71, 14);
		panel_1.add(lblLastName_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(91, 50, 89, 20);
		panel_1.add(textField_5);
		
		JLabel lblEmail_1 = new JLabel("Email : ");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail_1.setBounds(10, 81, 71, 14);
		panel_1.add(lblEmail_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(91, 78, 89, 20);
		panel_1.add(textField_6);
		
		JLabel lblOrganization_1 = new JLabel("Organization : ");
		lblOrganization_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOrganization_1.setBounds(10, 106, 81, 14);
		panel_1.add(lblOrganization_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(91, 103, 89, 20);
		panel_1.add(textField_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Author 3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(463, 26, 199, 140);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("First Name : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 25, 71, 14);
		panel_2.add(lblNewLabel_2);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(91, 22, 89, 20);
		panel_2.add(textField_8);
		
		JLabel lblLastName_2 = new JLabel("Last Name : ");
		lblLastName_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLastName_2.setBounds(10, 53, 71, 14);
		panel_2.add(lblLastName_2);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(91, 50, 89, 20);
		panel_2.add(textField_9);
		
		JLabel lblEmail_2 = new JLabel("Email : ");
		lblEmail_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail_2.setBounds(10, 81, 71, 14);
		panel_2.add(lblEmail_2);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(91, 78, 89, 20);
		panel_2.add(textField_10);
		
		JLabel lblOrganization_2 = new JLabel("Organization : ");
		lblOrganization_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOrganization_2.setBounds(10, 106, 81, 14);
		panel_2.add(lblOrganization_2);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(91, 103, 89, 20);
		panel_2.add(textField_11);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Paper Submission", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 177, 642, 281);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Title : ");
		lblNewLabel_3.setBounds(10, 29, 48, 14);
		panel_3.add(lblNewLabel_3);
		
		textField_12 = new JTextField();
		textField_12.setBounds(78, 26, 554, 20);
		panel_3.add(textField_12);
		textField_12.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(78, 54, 554, 181);
		panel_3.add(textArea);
		
		JLabel lblNewLabel_4 = new JLabel("Contents : ");
		lblNewLabel_4.setBounds(10, 132, 91, 14);
		panel_3.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(541, 246, 91, 23);
		panel_3.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(576, 469, 91, 23);
		contentPane.add(btnBack);
	}
}
