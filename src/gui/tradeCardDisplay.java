package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class tradeCardDisplay {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tradeCardDisplay window = new tradeCardDisplay();
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
	public tradeCardDisplay() {
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
		lblTrade.setBounds(196, 11, 276, 60);
		frame.getContentPane().add(lblTrade);
		
		JLabel lblCardAcquired = new JLabel("Card Acquired:");
		lblCardAcquired.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardAcquired.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblCardAcquired.setBounds(175, 110, 313, 44);
		frame.getContentPane().add(lblCardAcquired);
		
		JButton btnCard = new JButton("");
		btnCard.setBounds(250, 182, 161, 227);
		frame.getContentPane().add(btnCard);
		
		JLabel lblTimer = new JLabel("15");
		lblTimer.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblTimer.setBounds(547, 15, 37, 51);
		frame.getContentPane().add(lblTimer);
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblSeconds.setBounds(587, 19, 115, 51);
		frame.getContentPane().add(lblSeconds);
		frame.setBounds(100, 100, 721, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
