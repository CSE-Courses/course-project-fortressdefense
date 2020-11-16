package gui;
import code.*;
import code.Deck.*;
import code.Socket.Client;
import code.Socket.Server;
import code.card_class.AttackCard;
import code.card_class.CardType;
import code.card_class.DefenseCard;
import code.card_class.SpecialCard;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.JPanel;

/*
 * Creates the Draw Phase GUI window during another player's turn
 * @author Alec Willette
 * 11/1/2020
 * */

public class drawPhaseOtherPlayer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	Timer tm;
	int i = 30;
	int numDots = 0;//used for waiting message
	private Client client;
	private Server gameServer;
	private Hand hand;
	private String turn;
	
	public JPanel GetPanel() {
		return (JPanel) frame.getContentPane();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drawPhaseOtherPlayer window = new drawPhaseOtherPlayer(null, null, null);
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
	public drawPhaseOtherPlayer(Server server, Client client, Hand theHand) {
		this.gameServer = server;
		this.client = client;
		this.hand = theHand;
		System.out.println(hand.Size());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1129, 765);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("EXIT GAME");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setBounds(0, 0, 152, 68);
		frame.getContentPane().add(btnExit);
		
		JLabel lblDrawPhase = new JLabel("DRAW PHASE");
		lblDrawPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawPhase.setFont(new Font("Algerian", Font.BOLD, 40));
		lblDrawPhase.setBackground(new Color(255, 140, 0));
		lblDrawPhase.setBounds(388, 0, 296, 90);
		frame.getContentPane().add(lblDrawPhase);
		
		JLabel lblTimer = new JLabel("30");
		lblTimer.setFont(new Font("Sitka Subheading", Font.PLAIN, 36));
		lblTimer.setBounds(433, 71, 49, 46);
		frame.getContentPane().add(lblTimer);
		
		JLabel lblDots = new JLabel("");
		lblDots.setFont(new Font("Lucida Bright", Font.BOLD, 45));
		lblDots.setBounds(734, 277, 82, 68);
		frame.getContentPane().add(lblDots);
		
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{//end turn and exit window when timer reaches 0
				if(i == -1)
				{
					tm.stop();
					//client.switchTurn();
					//System.exit(0);
				}
				lblTimer.setText(Integer.toString(i));
				i--;
				if(numDots == 0)//subroutine for waiting dots
				{
					lblDots.setText(".");
					numDots++;
				}
				else if(numDots == 1)
				{
					lblDots.setText("..");
					numDots++;
				}
				else if(numDots == 2)
				{
					lblDots.setText("...");
					numDots++;
				}
				else
				{
					lblDots.setText("");
					numDots = 0;
				}
			}
		});
		tm.start();
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		lblSeconds.setBounds(488, 75, 152, 41);
		frame.getContentPane().add(lblSeconds);
		
		JLabel lblBar = new JLabel("HEALTHPOINTS: 0");
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBar.setBounds(388, 128, 254, 46);
		frame.getContentPane().add(lblBar);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setMaximum(50);
		healthBar.setForeground(new Color(50, 205, 50));
		healthBar.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		healthBar.setBackground(Color.DARK_GRAY);
		healthBar.setBounds(273, 185, 508, 46);
		frame.getContentPane().add(healthBar);
		
		if(gameServer != null)
		{
			healthBar.setValue(gameServer.getModel().getPlayers().get(0).points);
			lblBar.setText("HEALTHPOINTS: " + gameServer.getModel().getPlayers().get(0).points);
		}
		else if(client != null)
		{
			healthBar.setValue(client.getHealth());
			lblBar.setText("HEALTHPOINTS: " + client.getHealth());
		}
		
		
		JLabel lblMsgBox = new JLabel("");
		lblMsgBox.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		lblMsgBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblMsgBox.setBorder(UIManager.getBorder("ProgressBar.border"));
		lblMsgBox.setBounds(10, 105, 216, 139);
		frame.getContentPane().add(lblMsgBox);
		
		JLabel lblRoundNum = new JLabel("ROUND 5/8");
		lblRoundNum.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblRoundNum.setBounds(20, 255, 195, 90);
		frame.getContentPane().add(lblRoundNum);
		
		JButton playerIcon = new JButton("");
		playerIcon.setBounds(710, 0, 100, 100);
		frame.getContentPane().add(playerIcon);
		
		JLabel lblName;
		// Kludge: server should send turn name
		if (client != null) {
			lblName = new JLabel(client.obtainTurn() + "'s Turn");
		}else if (gameServer != null) {
			lblName = new JLabel(gameServer.getTurn() + "'s Turn");
		}else {
			lblName = new JLabel("<Dynamic>'s Turn");
		}
		lblName.setForeground(Color.RED);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBorder(UIManager.getBorder("InternalFrame.border"));
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(820, 0, 500, 100);
		frame.getContentPane().add(lblName);
		
		JLabel lblWaiting = new JLabel("Waiting for other player");
		lblWaiting.setFont(new Font("Lucida Bright", Font.BOLD, 35));
		lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting.setBounds(273, 271, 468, 100);
		frame.getContentPane().add(lblWaiting);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(Color.YELLOW);
		cardPanel.setBounds(10, 453, 1093, 262);
		frame.getContentPane().add(cardPanel);
		cardPanel.setLayout(null);

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

		JButton btnCard1 = new JButton("");
		btnCard1.setVisible(false);
		btnCard1.setBounds(34, 55, 120, 166);
		cardPanel.add(btnCard1);

		JButton btnCard2 = new JButton("");
		btnCard2.setVisible(false);
		btnCard2.setBounds(164, 55, 120, 166);
		cardPanel.add(btnCard2);

		JButton btnCard3 = new JButton("");
		btnCard3.setVisible(false);
		btnCard3.setBounds(294, 55, 120, 166);
		cardPanel.add(btnCard3);

		JButton btnCard4 = new JButton("");
		btnCard4.setVisible(false);
		btnCard4.setBounds(424, 55, 120, 166);
		cardPanel.add(btnCard4);

		JButton btnCard5 = new JButton("");
		btnCard5.setVisible(false);
		btnCard5.setBounds(554, 55, 120, 166);
		cardPanel.add(btnCard5);

		JButton btnCard6 = new JButton("");
		btnCard6.setVisible(false);
		btnCard6.setBounds(684, 55, 120, 166);
		cardPanel.add(btnCard6);

		JButton btnCard7 = new JButton("");
		btnCard7.setVisible(false);
		btnCard7.setBounds(814, 55, 120, 166);
		cardPanel.add(btnCard7);

		JButton btnCard8 = new JButton("");
		btnCard8.setVisible(false);
		btnCard8.setBounds(944, 55, 120, 166);
		cardPanel.add(btnCard8);

		JButton curBtn = btnCard1;
		int curBound = 10;
		for(int i = 0; i < hand.Size(); i++)
		{
			if(hand.Select(i).getCard_name() == AttackCard.Axe)
			{
				curBtn.setIcon(new ImageIcon(axeImg));
			}
			else if(hand.Select(i).getCard_name() == AttackCard.Battle_Axe)
			{
				curBtn.setIcon(new ImageIcon(battleAxeImg));
			}
			else if(hand.Select(i).getCard_name() == AttackCard.Crossbow)
			{
				curBtn.setIcon(new ImageIcon(crossbowImg));
			}
			else if(hand.Select(i).getCard_name() == AttackCard.Mace)
			{
				curBtn.setIcon(new ImageIcon(maceImg));
			}
			else if(hand.Select(i).getCard_name() == AttackCard.Stick)
			{
				curBtn.setIcon(new ImageIcon(stickImg));
			}
			else if(hand.Select(i).getCard_name() == AttackCard.Sword)
			{
				curBtn.setIcon(new ImageIcon(swordImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Barbed_Wire)
			{
				curBtn.setIcon(new ImageIcon(barbedWireImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Earthquake)
			{
				curBtn.setIcon(new ImageIcon(earthquakeImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Flood)
			{
				curBtn.setIcon(new ImageIcon(floodImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Iron_Door)
			{
				curBtn.setIcon(new ImageIcon(ironDoorImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Reinforced_Gate)
			{
				curBtn.setIcon(new ImageIcon(reinforcedGateImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Steel_Chains)
			{
				curBtn.setIcon(new ImageIcon(steelChainsImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Stone_Wall)
			{
				curBtn.setIcon(new ImageIcon(stoneWallImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Thunderstorm)
			{
				curBtn.setIcon(new ImageIcon(thunderstormImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Tornado)
			{
				curBtn.setIcon(new ImageIcon(tornadoImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Wooden_Wall)
			{
				curBtn.setIcon(new ImageIcon(woodenWallImg));
			}
			else if(hand.Select(i).getCard_name() == SpecialCard.Archer_Tower)
			{
				curBtn.setIcon(new ImageIcon(archerTowerImg));
			}
			else if(hand.Select(i).getCard_name() == SpecialCard.Scout)
			{
				curBtn.setIcon(new ImageIcon(scoutImg));
			}
			else if(hand.Select(i).getCard_name() == SpecialCard.Trade)
			{
				curBtn.setIcon(new ImageIcon(tradeImg));
			}

			curBtn.setBounds(curBound, 22, 119, 176);
			cardPanel.add(curBtn);
			curBtn.setVisible(true);

			curBound = curBound + 130;

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
