package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class tradeCardPick {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tradeCardPick window = new tradeCardPick();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tradeCardPick() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(147, 112, 219));
		frame.getContentPane().setLayout(null);
		
		JLabel lblTrade = new JLabel("TRADE CARD");
		lblTrade.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrade.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblTrade.setBounds(377, 11, 276, 60);
		frame.getContentPane().add(lblTrade);
		
		JLabel lblNewLabel = new JLabel("Select a card to give:");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 59, 357, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCard1 = new JButton("");
		btnCard1.setBounds(20, 114, 135, 206);
		frame.getContentPane().add(btnCard1);
		
		JButton btnCard2 = new JButton("");
		btnCard2.setBounds(165, 114, 135, 206);
		frame.getContentPane().add(btnCard2);
		
		JButton btnCard3 = new JButton("");
		btnCard3.setBounds(310, 114, 135, 206);
		frame.getContentPane().add(btnCard3);
		
		JButton btnCard4 = new JButton("");
		btnCard4.setBounds(455, 114, 135, 206);
		frame.getContentPane().add(btnCard4);
		
		JButton btnCard5 = new JButton("");
		btnCard5.setBounds(600, 114, 135, 206);
		frame.getContentPane().add(btnCard5);
		
		JButton btnCard6 = new JButton("");
		btnCard6.setBounds(745, 114, 135, 206);
		frame.getContentPane().add(btnCard6);
		
		JButton btnCard7 = new JButton("");
		btnCard7.setBounds(890, 114, 135, 206);
		frame.getContentPane().add(btnCard7);
		
		JButton btnCard8 = new JButton("");
		btnCard8.setBounds(1035, 114, 135, 206);
		frame.getContentPane().add(btnCard8);
		frame.setBounds(100, 100, 1200, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
