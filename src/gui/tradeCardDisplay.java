package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import code.Socket.Client;
import code.Socket.Server;
import code.card_class.AttackCard;
import code.card_class.Card;
import code.card_class.DefenseCard;
import code.card_class.SpecialCard;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class tradeCardDisplay {

	public static JFrame frmFortressDefense;
	
	private JFrame mainFrame;
	private static Server gameServer; // null is game is not host
	private static Client client; // null if game is host
	
	private static Card theCard;
	
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
		if (this.gameServer != null) {
			this.theCard = gameServer.getHand().getCards().get(gameServer.getHand().Size() - 1);
		}else if (client != null){
			this.theCard = client.getHand().getCards().get(client.getHand().Size() - 1);
		}
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

		if(theCard.getCard_name() == AttackCard.Axe)
		{
			btnCard.setIcon(new ImageIcon(axeImg));
		}
		else if(theCard.getCard_name() == AttackCard.Battle_Axe)
		{
			btnCard.setIcon(new ImageIcon(battleAxeImg));
		}
		else if(theCard.getCard_name() == AttackCard.Crossbow)
		{
			btnCard.setIcon(new ImageIcon(crossbowImg));
		}
		else if(theCard.getCard_name() == AttackCard.Mace)
		{
			btnCard.setIcon(new ImageIcon(maceImg));
		}
		else if(theCard.getCard_name() == AttackCard.Stick)
		{
			btnCard.setIcon(new ImageIcon(stickImg));
		}
		else if(theCard.getCard_name() == AttackCard.Sword)
		{
			btnCard.setIcon(new ImageIcon(swordImg));
		}
		else if(theCard.getCard_name() == SpecialCard.Archer_Tower)
		{
			btnCard.setIcon(new ImageIcon(archerTowerImg));
		}
		else if(theCard.getCard_name() == SpecialCard.Scout)
		{
			btnCard.setIcon(new ImageIcon(scoutImg));
		}
		else if(theCard.getCard_name() == SpecialCard.Trade)
		{
			btnCard.setIcon(new ImageIcon(tradeImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Earthquake)
		{
			btnCard.setIcon(new ImageIcon(earthquakeImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Flood)
		{
			btnCard.setIcon(new ImageIcon(floodImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Thunderstorm)
		{
			btnCard.setIcon(new ImageIcon(thunderstormImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Tornado)
		{
			btnCard.setIcon(new ImageIcon(tornadoImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Barbed_Wire)
		{
			btnCard.setIcon(new ImageIcon(barbedWireImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Iron_Door)
		{
			btnCard.setIcon(new ImageIcon(ironDoorImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Reinforced_Gate)
		{
			btnCard.setIcon(new ImageIcon(reinforcedGateImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Steel_Chains)
		{
			btnCard.setIcon(new ImageIcon(steelChainsImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Stone_Wall)
		{
			btnCard.setIcon(new ImageIcon(stoneWallImg));
		}
		else if(theCard.getCard_name() == DefenseCard.Wooden_Wall)
		{
			btnCard.setIcon(new ImageIcon(woodenWallImg));
		}
		
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
