package gui;

import code.*;
import code.Socket.*;

import code.card_class.AttackCard;
import code.card_class.Card;
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

public class scoutCardsDisplay {

	public static JFrame frmFortressDefense;

	Timer tm;
	int i = 30;
	
	private JFrame mainFrame;
	private Server gameServer; // null is game is not host
	private Client client; // null if game is host
	private static Hand hand;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					scoutCardsDisplay window = new scoutCardsDisplay(frmFortressDefense, null, null);
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
	public scoutCardsDisplay(JFrame mainFrame, Server gameServer, Client client) {
		if(gameServer != null)
		{
			this.hand = gameServer.getOppHand();
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
			this.hand = client.getOppHand();
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
		frmFortressDefense.setBounds(100, 100, 1100, 350);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFortressDefense.getContentPane().setLayout(null);
		
		JLabel lblPlayerHand = new JLabel("Player's Hand:");
		lblPlayerHand.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblPlayerHand.setBounds(36, 1, 360, 41);
		frmFortressDefense.getContentPane().add(lblPlayerHand);
		
		JLabel lblTimer = new JLabel("30");
		lblTimer.setFont(new Font("Sitka Subheading", Font.PLAIN, 36));
		lblTimer.setBounds(803, 6, 67, 46);
		frmFortressDefense.getContentPane().add(lblTimer);
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		lblSeconds.setBounds(869, 8, 152, 41);
		frmFortressDefense.getContentPane().add(lblSeconds);
		
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
		btnCard1.setBounds(10, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard1);
		//for demo:
		btnCard1.setIcon(new ImageIcon(axeImg));
		btnCard1.setVisible(true);
		
		JButton btnCard2 = new JButton("");
		btnCard2.setVisible(false);
		btnCard2.setBounds(144, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard2);
		//for demo:
		btnCard2.setIcon(new ImageIcon(tradeImg));
		btnCard2.setVisible(true);
		
		JButton btnCard3 = new JButton("");
		btnCard3.setVisible(false);
		btnCard3.setBounds(278, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard3);
		//for demo:
		btnCard3.setIcon(new ImageIcon(crossbowImg));
		btnCard3.setVisible(true);
		
		JButton btnCard4 = new JButton("");
		btnCard4.setVisible(false);
		btnCard4.setBounds(412, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard4);
		//for demo:
		btnCard4.setIcon(new ImageIcon(steelChainsImg));
		btnCard4.setVisible(true);
		
		JButton btnCard5 = new JButton("");
		btnCard5.setVisible(false);
		btnCard5.setBounds(546, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard5);
		
		JButton btnCard6 = new JButton("");
		btnCard6.setVisible(false);
		btnCard6.setBounds(680, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard6);
		
		JButton btnCard7 = new JButton("");
		btnCard7.setVisible(false);
		btnCard7.setBounds(814, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard7);
		
		JButton btnCard8 = new JButton("");
		btnCard8.setVisible(false);
		btnCard8.setBounds(950, 110, 124, 190);
		frmFortressDefense.getContentPane().add(btnCard8);
		
		JLabel lblDrawPhase = new JLabel("SCOUT CARD");
		lblDrawPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawPhase.setFont(new Font("Stencil", Font.PLAIN, 40));
		lblDrawPhase.setBounds(369, 1, 276, 60);
		frmFortressDefense.getContentPane().add(lblDrawPhase);
		
		JLabel lblScout = new JLabel("ACTIVATED");
		lblScout.setHorizontalAlignment(SwingConstants.CENTER);
		lblScout.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblScout.setBounds(369, 54, 276, 45);
		frmFortressDefense.getContentPane().add(lblScout);
		
		//initialize players hand:
		JButton curBtn = btnCard1;
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
			else if(hand.Select(i).getCard_name() == DefenseCard.Earthquake)
			{
				curBtn.setIcon(new ImageIcon(earthquakeImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Flood)
			{
				curBtn.setIcon(new ImageIcon(floodImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Thunderstorm)
			{
				curBtn.setIcon(new ImageIcon(thunderstormImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Tornado)
			{
				curBtn.setIcon(new ImageIcon(tornadoImg));
			}
			else if(hand.Select(i).getCard_name() == DefenseCard.Barbed_Wire)
			{
				curBtn.setIcon(new ImageIcon(barbedWireImg));
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
			else if(hand.Select(i).getCard_name() == DefenseCard.Wooden_Wall)
			{
				curBtn.setIcon(new ImageIcon(woodenWallImg));
			}
			curBtn.setVisible(true);
			
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
