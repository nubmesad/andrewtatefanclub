package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewSuccessfulBids extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSuccessfulBids frame = new ViewSuccessfulBids();
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
	public ViewSuccessfulBids() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Successful Bids");
		lblNewLabel.setBounds(306, 24, 277, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select paper to review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(61, 61, 703, 140);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(27, 21, 654, 98);
		panel.add(comboBox);
		
		JTextArea txtrEnterYourReview = new JTextArea();
		txtrEnterYourReview.setText("Enter your review here....");
		txtrEnterYourReview.setBounds(61, 296, 703, 111);
		contentPane.add(txtrEnterYourReview);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(266, 227, 146, 37);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Select your rating: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(61, 223, 195, 38);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit Review");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(446, 442, 137, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(193, 442, 137, 37);
		contentPane.add(btnNewButton_1);

	}
}
