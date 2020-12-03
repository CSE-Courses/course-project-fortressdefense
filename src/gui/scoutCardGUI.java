package gui;

import code.*;
import code.Socket.Client;
import code.Socket.Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class scoutCardGUI {

	public static JFrame frmFortressDefense;
	
	public static String[] turns;//for testing 
	
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
					scoutCardGUI window = new scoutCardGUI(frmFortressDefense, null, null, turns);
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
	public scoutCardGUI(JFrame mainFrame, Server gameServer, Client client, String[] turns) {
		this.mainFrame = mainFrame;
		this.client = client;
		this.gameServer = gameServer;
		this.turns = turns;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFortressDefense = new JFrame();
		frmFortressDefense.getContentPane().setBackground(new Color(147, 112, 219));
		frmFortressDefense.setBounds(100, 100, 1000, 600);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFortressDefense.getContentPane().setLayout(null);
		
		JLabel lblDrawPhase = new JLabel("SCOUT CARD");
		lblDrawPhase.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblDrawPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawPhase.setBounds(337, 11, 276, 60);
		frmFortressDefense.getContentPane().add(lblDrawPhase);
		
		JLabel lblScout = new JLabel("ACTIVATED");
		lblScout.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblScout.setHorizontalAlignment(SwingConstants.CENTER);
		lblScout.setBounds(337, 60, 276, 45);
		frmFortressDefense.getContentPane().add(lblScout);
		
		JLabel lblChoose = new JLabel("CHOOSE A PLAYER:");
		lblChoose.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoose.setBounds(133, 149, 332, 45);
		frmFortressDefense.getContentPane().add(lblChoose);
		
		JButton btnPlayer1 = new JButton("Player 1");
		btnPlayer1.setVisible(false);
		btnPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set player selection:
				//selected = gameServer.getModel().getPlayers().get(0);
				//hand = selected.getHand();
				//viewHand();
			}
		});
		btnPlayer1.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnPlayer1.setBounds(171, 205, 260, 45);
		frmFortressDefense.getContentPane().add(btnPlayer1);
		
		JButton btnPlayer2 = new JButton("Player 2");
		btnPlayer2.setVisible(false);
		btnPlayer2.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnPlayer2.setBounds(171, 271, 260, 45);
		frmFortressDefense.getContentPane().add(btnPlayer2);
		
		JButton btnPlayer3 = new JButton("Player 3");
		btnPlayer3.setVisible(false);
		btnPlayer3.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnPlayer3.setBounds(171, 332, 260, 45);
		frmFortressDefense.getContentPane().add(btnPlayer3);
		
		JButton btnPlayer4 = new JButton("Player 4");
		btnPlayer4.setVisible(false);
		btnPlayer4.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnPlayer4.setBounds(171, 396, 260, 45);
		frmFortressDefense.getContentPane().add(btnPlayer4);
		
		JButton btnPlayer5 = new JButton("Player 5");
		btnPlayer5.setVisible(false);
		btnPlayer5.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btnPlayer5.setBounds(171, 460, 260, 45);
		frmFortressDefense.getContentPane().add(btnPlayer5);
		
		//initialize all player buttons:
				JButton curBtn = btnPlayer1;
		for(int i = 1; i < turns.length; i++)
		{
					//curBtn.setText(gameServer.getModel().getPlayers().get(i).PlayerName);
					curBtn.setText(turns[i]);
					curBtn.setVisible(true);
					if(i == 1)
					{
						curBtn = btnPlayer2;
					}
					else if(i == 2)
					{
						curBtn = btnPlayer3;
					}
					else if(i == 3)
					{
						curBtn = btnPlayer4;
					}
					else if(i == 4)
					{
						curBtn = btnPlayer5;
					}
		}
		
		/*
		//initialize all player buttons:
		for(int i = 1; i < turns.length; i++)
		{
			if(client != null)
			{
				if(client.getName() != gameServer.getModel().getPlayers().get(i).PlayerName)
				{
					//curBtn.setText(gameServer.getModel().getPlayers().get(i).PlayerName);
					curBtn.setText(turns[i]);
					curBtn.setVisible(true);
				}
			}
			else if(gameServer != null)
			{
				
			}
			curBtn.setText(gameServer.getModel().getPlayers().get(i).PlayerName);
			curBtn.setVisible(true);
			
			if(i == 0)
			{
				curBtn = btnPlayer2;
			}
			else if(i == 1)
			{
				curBtn = btnPlayer3;
			}
			else if(i == 2)
			{
				curBtn = btnPlayer4;
			}
			else
			{
				curBtn = btnPlayer5;
			}
		}*/
		
		JButton btnGO = new JButton("GO!");
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewHand();
			}
		});
		btnGO.setBackground(new Color(0, 128, 0));
		btnGO.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
		btnGO.setBounds(572, 224, 251, 134);
		frmFortressDefense.getContentPane().add(btnGO);
	}
	public void viewHand()
	{
		mainFrame.getContentPane().add(new scoutCardsDisplay(mainFrame, gameServer, client, hand).GetPanel());
		GetPanel().setVisible(false);
	}
	
	public JPanel GetPanel() {
		return (JPanel) frmFortressDefense.getContentPane();
	}
}
