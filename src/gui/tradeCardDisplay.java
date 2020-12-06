package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import code.Socket.Client;
import code.Socket.Server;

import javax.swing.JButton;

public class tradeCardDisplay {

	public static JFrame frmFortressDefense;
	
	private JFrame mainFrame;
	private static Server gameServer; // null is game is not host
	private static Client client; // null if game is host
	
	Timer tm;
	int i = 15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tradeCardDisplay window = new tradeCardDisplay(frmFortressDefense, gameServer, client);
					window.frmFortressDefense.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tradeCardDisplay(JFrame mainFrame, Server gameServer, Client client) {
		this.mainFrame = mainFrame;
		this.gameServer = gameServer;
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFortressDefense = new JFrame();
		frmFortressDefense.getContentPane().setBackground(new Color(147, 112, 219));
		frmFortressDefense.getContentPane().setLayout(null);
		
		JLabel lblTrade = new JLabel("TRADE CARD");
		lblTrade.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrade.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblTrade.setBounds(196, 11, 276, 60);
		frmFortressDefense.getContentPane().add(lblTrade);
		
		JLabel lblCardAcquired = new JLabel("Card Acquired:");
		lblCardAcquired.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardAcquired.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblCardAcquired.setBounds(175, 110, 313, 44);
		frmFortressDefense.getContentPane().add(lblCardAcquired);
		
		JButton btnCard = new JButton("");
		btnCard.setBounds(250, 182, 161, 227);
		frmFortressDefense.getContentPane().add(btnCard);
		
		JLabel lblTimer = new JLabel("15");
		lblTimer.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblTimer.setBounds(547, 15, 37, 51);
		frmFortressDefense.getContentPane().add(lblTimer);
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblSeconds.setBounds(587, 19, 115, 51);
		frmFortressDefense.getContentPane().add(lblSeconds);
		frmFortressDefense.setBounds(100, 100, 721, 500);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{//end turn and exit window when timer reaches 0
				if(i == -1)
				{
					tm.stop();
					viewAttack();
					if (client != null) {
						client.switchTurn();
					}else if (gameServer != null) {
						gameServer.nextTurn();
					}
					
				}
				lblTimer.setText(Integer.toString(i));
				i--;
			}
		});
		tm.start();
	}
	
	public void viewAttack()
	{
		mainFrame.remove(GetPanel());
	}

	public JPanel GetPanel() {
		return (JPanel) frmFortressDefense.getContentPane();
	}
}
