package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import code.Hand;
import code.Player;
import code.Socket.Client;
import code.Socket.Server;
import code.card_class.AttackCard;
import code.card_class.DefenseCard;
import code.card_class.SpecialCard;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tradeCard {

	public static JFrame frmFortressDefense;
	
	private JFrame mainFrame;
	private Server gameServer; // null is game is not host
	private Client client; // null if game is host
	private Hand hand;
	private Player selected;
	private static String[] players;
	private static String playerName;
	
	private int cardIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tradeCard window = new tradeCard(frmFortressDefense, null, null, playerName);
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
	public tradeCard(JFrame mainFrame, Server gameServer, Client client, String playerName) {
		if(gameServer != null)
		{
			this.hand = gameServer.getHand();
		}
		else
		{
			try {
				mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mainFrame.setCursor(Cursor.getDefaultCursor());
			this.hand = client.getHand();
		}
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
		
		//Initialize Attack card images
		Image axeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/axe.PNG")).getImage();
		Image battleAxeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/battleAxe.PNG")).getImage();
		Image crossbowImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/crossbow.PNG")).getImage();
		Image maceImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/mace.PNG")).getImage();
		Image stickImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/stick.PNG")).getImage();
		Image swordImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/sword.PNG")).getImage();

		//Initialize Defense card images
		Image barbedWireImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/barbedWire.PNG")).getImage();
		Image ironDoorImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/ironDoor.PNG")).getImage();
		Image reinforcedGateImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/reinforcedGate.PNG")).getImage();
		Image steelChainsImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/steelChains.PNG")).getImage();
		Image stoneWallImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/stoneWall.PNG")).getImage();
		Image woodenWallImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/woodenWall.PNG")).getImage();

		//Initialize Damage card images
		Image earthquakeImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/earthquake.PNG")).getImage();
		Image floodImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/flood.PNG")).getImage();
		Image thunderstormImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/thunderstorm.PNG")).getImage();
		Image tornadoImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/tornado.PNG")).getImage();

		//Initialize Special card images
		Image archerTowerImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/archerTower.PNG")).getImage();
		Image scoutImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/scout.PNG")).getImage();
		Image tradeImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/trade.PNG")).getImage();
		
		JButton btnGO = new JButton("GO!");
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call trade func with index as arg
			}
		});
		btnGO.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 50));
		btnGO.setBackground(new Color(0, 128, 0));
		btnGO.setBounds(793, 26, 251, 134);
		frmFortressDefense.getContentPane().add(btnGO);
		
		JButton btnCard1 = new JButton("");
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 0;
				if(gameServer != null)
				{
					gameServer.trade(cardIndex, playerName);
				}
			}
		});
		btnCard1.setVisible(false);
		btnCard1.setBounds(10, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard1);
		
		JButton btnCard2 = new JButton("");
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 1;
			}
		});
		btnCard2.setVisible(false);
		btnCard2.setBounds(162, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard2);
		
		JButton btnCard3 = new JButton("");
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 2;
			}
		});
		btnCard3.setVisible(false);
		btnCard3.setBounds(314, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard3);
		
		JButton btnCard4 = new JButton("");
		btnCard4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 3;
			}
		});
		btnCard4.setVisible(false);
		btnCard4.setBounds(466, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard4);
		
		JButton btnCard5 = new JButton("");
		btnCard5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 4;
			}
		});
		btnCard5.setVisible(false);
		btnCard5.setBounds(618, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard5);
		
		JButton btnCard6 = new JButton("");
		btnCard6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 5;
			}
		});
		btnCard6.setVisible(false);
		btnCard6.setBounds(770, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard6);
		
		JButton btnCard7 = new JButton("");
		btnCard7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 6;
			}
		});
		btnCard7.setVisible(false);
		btnCard7.setBounds(922, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard7);
		
		JButton btnCard8 = new JButton("");
		btnCard8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardIndex = 7;
			}
		});
		btnCard8.setVisible(false);
		btnCard8.setBounds(1074, 198, 142, 222);
		frmFortressDefense.getContentPane().add(btnCard8);
		
		JLabel lblNewLabel = new JLabel("Select a card to give:");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
		lblNewLabel.setBounds(29, 143, 357, 44);
		frmFortressDefense.getContentPane().add(lblNewLabel);
		frmFortressDefense.setBounds(100, 100, 1242, 470);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize players hand:
		JButton curBtn = btnCard1;
		if(gameServer != null)
		{
			for(int i = 0; i < gameServer.getHand().Size(); i++)
			{
				if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Axe)
				{
					curBtn.setIcon(new ImageIcon(axeImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Battle_Axe)
				{
					curBtn.setIcon(new ImageIcon(battleAxeImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Crossbow)
				{
					curBtn.setIcon(new ImageIcon(crossbowImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Mace)
				{
					curBtn.setIcon(new ImageIcon(maceImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Stick)
				{
					curBtn.setIcon(new ImageIcon(stickImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == AttackCard.Sword)
				{
					curBtn.setIcon(new ImageIcon(swordImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == SpecialCard.Archer_Tower)
				{
					curBtn.setIcon(new ImageIcon(archerTowerImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == SpecialCard.Scout)
				{
					curBtn.setIcon(new ImageIcon(scoutImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == SpecialCard.Trade)
				{
					curBtn.setIcon(new ImageIcon(tradeImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Earthquake)
				{
					curBtn.setIcon(new ImageIcon(earthquakeImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Flood)
				{
					curBtn.setIcon(new ImageIcon(floodImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Thunderstorm)
				{
					curBtn.setIcon(new ImageIcon(thunderstormImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Tornado)
				{
					curBtn.setIcon(new ImageIcon(tornadoImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Barbed_Wire)
				{
					curBtn.setIcon(new ImageIcon(barbedWireImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Iron_Door)
				{
					curBtn.setIcon(new ImageIcon(ironDoorImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Reinforced_Gate)
				{
					curBtn.setIcon(new ImageIcon(reinforcedGateImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Steel_Chains)
				{
					curBtn.setIcon(new ImageIcon(steelChainsImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Stone_Wall)
				{
					curBtn.setIcon(new ImageIcon(stoneWallImg));
				}
				else if(gameServer.getHand().Select(i).getCard_name() == DefenseCard.Wooden_Wall)
				{
					curBtn.setIcon(new ImageIcon(woodenWallImg));
				}
				//curBtn.setBounds(curBound, 22, 119, 176);
				curBtn.setVisible(true);
				
				//curBound = curBound + 130;
				if(curBtn == btnCard1)
				{
					curBtn = btnCard2;
				}
				else if(curBtn == btnCard2)
				{
					curBtn = btnCard3;
				}
				else if(curBtn == btnCard3)
				{
					curBtn = btnCard4;
				}
				else if(curBtn == btnCard4)
				{
					curBtn = btnCard5;
				}
				else if(curBtn == btnCard5)
				{
					curBtn = btnCard6;
				}
				else if(curBtn == btnCard6)
				{
					curBtn = btnCard7;
				}
				else if(curBtn == btnCard7)
				{
					curBtn = btnCard8;
				}
			}
		}
		else
		{
			for(int i = 0; i < client.getHand().Size(); i++)
			{
				if(client.getHand().Select(i).getCard_name() == AttackCard.Axe)
				{
					curBtn.setIcon(new ImageIcon(axeImg));
				}
				else if(client.getHand().Select(i).getCard_name() == AttackCard.Battle_Axe)
				{
					curBtn.setIcon(new ImageIcon(battleAxeImg));
				}
				else if(client.getHand().Select(i).getCard_name() == AttackCard.Crossbow)
				{
					curBtn.setIcon(new ImageIcon(crossbowImg));
				}
				else if(client.getHand().Select(i).getCard_name() == AttackCard.Mace)
				{
					curBtn.setIcon(new ImageIcon(maceImg));
				}
				else if(client.getHand().Select(i).getCard_name() == AttackCard.Stick)
				{
					curBtn.setIcon(new ImageIcon(stickImg));
				}
				else if(client.getHand().Select(i).getCard_name() == AttackCard.Sword)
				{
					curBtn.setIcon(new ImageIcon(swordImg));
				}
				else if(client.getHand().Select(i).getCard_name() == SpecialCard.Archer_Tower)
				{
					curBtn.setIcon(new ImageIcon(archerTowerImg));
				}
				else if(client.getHand().Select(i).getCard_name() == SpecialCard.Scout)
				{
					curBtn.setIcon(new ImageIcon(scoutImg));
				}
				else if(client.getHand().Select(i).getCard_name() == SpecialCard.Trade)
				{
					curBtn.setIcon(new ImageIcon(tradeImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Earthquake)
				{
					curBtn.setIcon(new ImageIcon(earthquakeImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Flood)
				{
					curBtn.setIcon(new ImageIcon(floodImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Thunderstorm)
				{
					curBtn.setIcon(new ImageIcon(thunderstormImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Tornado)
				{
					curBtn.setIcon(new ImageIcon(tornadoImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Barbed_Wire)
				{
					curBtn.setIcon(new ImageIcon(barbedWireImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Iron_Door)
				{
					curBtn.setIcon(new ImageIcon(ironDoorImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Reinforced_Gate)
				{
					curBtn.setIcon(new ImageIcon(reinforcedGateImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Steel_Chains)
				{
					curBtn.setIcon(new ImageIcon(steelChainsImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Stone_Wall)
				{
					curBtn.setIcon(new ImageIcon(stoneWallImg));
				}
				else if(client.getHand().Select(i).getCard_name() == DefenseCard.Wooden_Wall)
				{
					curBtn.setIcon(new ImageIcon(woodenWallImg));
				}
				//curBtn.setBounds(curBound, 22, 119, 176);
				curBtn.setVisible(true);
				
				//curBound = curBound + 130;
				if(curBtn == btnCard1)
				{
					curBtn = btnCard2;
				}
				else if(curBtn == btnCard2)
				{
					curBtn = btnCard3;
				}
				else if(curBtn == btnCard3)
				{
					curBtn = btnCard4;
				}
				else if(curBtn == btnCard4)
				{
					curBtn = btnCard5;
				}
				else if(curBtn == btnCard5)
				{
					curBtn = btnCard6;
				}
				else if(curBtn == btnCard6)
				{
					curBtn = btnCard7;
				}
				else if(curBtn == btnCard7)
				{
					curBtn = btnCard8;
				}
			}
		}
		
		
		
	}
	public JPanel GetPanel() {
		return (JPanel) frmFortressDefense.getContentPane();
	}
}
