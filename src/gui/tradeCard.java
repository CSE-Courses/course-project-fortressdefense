package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import code.Hand;
import code.Player;
import code.Socket.Client;
import code.Socket.Server;

import javax.swing.JButton;

public class tradeCard {

	public static JFrame frmFortressDefense;
	
	private JFrame mainFrame;
	private Server gameServer; // null is game is not host
	private Client client; // null if game is host
	private Hand hand;
	private Player selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tradeCard window = new tradeCard(frmFortressDefense, null, null);
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
	public tradeCard(JFrame mainFrame, Server gameServer, Client client) {
		this.mainFrame = mainFrame;
		this.client = client;
		this.gameServer = gameServer;
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
		lblTrade.setBounds(335, 11, 276, 60);
		frmFortressDefense.getContentPane().add(lblTrade);
		
		JLabel lblAct = new JLabel("ACTIVATED");
		lblAct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAct.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAct.setBounds(335, 62, 276, 45);
		frmFortressDefense.getContentPane().add(lblAct);
		
		JLabel lblChoose = new JLabel("CHOOSE A PLAYER:");
		lblChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoose.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblChoose.setBounds(105, 155, 332, 45);
		frmFortressDefense.getContentPane().add(lblChoose);
		
		JButton btnP1 = new JButton("Player 1");
		btnP1.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnP1.setBounds(148, 211, 244, 45);
		frmFortressDefense.getContentPane().add(btnP1);
		
		JButton btnP2 = new JButton("Player 2");
		btnP2.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnP2.setBounds(148, 267, 244, 45);
		frmFortressDefense.getContentPane().add(btnP2);
		
		JButton btnP3 = new JButton("Player 3");
		btnP3.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnP3.setBounds(148, 323, 244, 45);
		frmFortressDefense.getContentPane().add(btnP3);
		
		JButton btnP4 = new JButton("Player 4");
		btnP4.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnP4.setBounds(148, 379, 244, 45);
		frmFortressDefense.getContentPane().add(btnP4);
		
		JButton btnP5 = new JButton("Player 5");
		btnP5.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnP5.setBounds(148, 435, 244, 45);
		frmFortressDefense.getContentPane().add(btnP5);
		
		JButton btnGO = new JButton("GO!");
		btnGO.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
		btnGO.setBackground(new Color(0, 128, 0));
		btnGO.setBounds(568, 211, 251, 134);
		frmFortressDefense.getContentPane().add(btnGO);
		frmFortressDefense.setBounds(100, 100, 1000, 600);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
