package gui;
import code.*;
import code.Deck.*;
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


/**
 * Creates the Draw Phase GUI window
 * @author Alec Willette, Haohua Feng(#97 turn command)
 * 
 * */

public class drawPhase {

	public static JFrame frmFortressDefense;

	/**
	 * Launch the application.
	 */
	
	Timer tm;
	int i = 30;
	boolean discard = false;
	JButton curBtn;//tracks the current card button
	int curBound = 10;//sets the card btn position
	int numA = 0;//number of attack cards
	int numD = 0;//number of defense cards
	
	private Card selected;
	
	private JFrame mainFrame;
	private Server gameServer; // null is game is not host
	private Client client; // null if game is host
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drawPhase window = new drawPhase(frmFortressDefense, null, null);
					window.frmFortressDefense.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
			
	private Hand hand;
	/**
	 * Create the application.
	 */
	public drawPhase(JFrame mainFrame, Server gameServer, Client client) {
		this.mainFrame = mainFrame;
		this.client = client;
		this.gameServer = gameServer;
		
		if (this.gameServer != null) {
			hand = this.gameServer.getModel().getPlayers().get(0).getHand();
		}
		else if (this.client != null) {
			hand = this.client.getHand();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFortressDefense = new JFrame();
		frmFortressDefense.setForeground(new Color(255, 0, 0));
		frmFortressDefense.setTitle("FORTRESS DEFENSE");
		frmFortressDefense.setBounds(100, 100, 1090, 769);
		frmFortressDefense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFortressDefense.getContentPane().setLayout(null);
		JButton btnExit = new JButton("EXIT GAME");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.addActionListener(new ActionListener() {
			@Override
			//exit button
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(0, 0, 152, 68);
		frmFortressDefense.getContentPane().add(btnExit);
		
		JLabel lblDrawPhase = new JLabel("DRAW PHASE");
		lblDrawPhase.setBackground(new Color(255, 140, 0));
		lblDrawPhase.setFont(new Font("Algerian", Font.BOLD, 40));
		lblDrawPhase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawPhase.setBounds(354, 0, 296, 90);
		frmFortressDefense.getContentPane().add(lblDrawPhase);
		
		JLabel lblSelected = new JLabel("");
		lblSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelected.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSelected.setBounds(848, 184, 146, 58);
		frmFortressDefense.getContentPane().add(lblSelected);
		
		JLabel lblMsgBox = new JLabel("");
		lblMsgBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblMsgBox.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		lblMsgBox.setBorder(UIManager.getBorder("ProgressBar.border"));
		lblMsgBox.setBounds(10, 103, 216, 139);
		frmFortressDefense.getContentPane().add(lblMsgBox);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		cardPanel.setBackground(new Color(255, 255, 0));
		cardPanel.setBorder(UIManager.getBorder("ProgressBar.border"));
		cardPanel.setBounds(10, 500, 1054, 219);
		frmFortressDefense.getContentPane().add(cardPanel);
		cardPanel.setLayout(null);
		
		JLabel lblCard1 = new JLabel("***");
		lblCard1.setVisible(false);
		lblCard1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard1.setBounds(50, 11, 46, 14);
		cardPanel.add(lblCard1);
		
		JLabel lblCard2 = new JLabel("***");
		lblCard2.setVisible(false);
		lblCard2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard2.setBounds(178, 11, 46, 14);
		cardPanel.add(lblCard2);
		
		JLabel lblCard3 = new JLabel("***");
		lblCard3.setVisible(false);
		lblCard3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard3.setBounds(310, 11, 46, 14);
		cardPanel.add(lblCard3);
		
		JLabel lblCard4 = new JLabel("***");
		lblCard4.setVisible(false);
		lblCard4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard4.setBounds(436, 11, 46, 14);
		cardPanel.add(lblCard4);
		
		JLabel lblCard5 = new JLabel("***");
		lblCard5.setVisible(false);
		lblCard5.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard5.setBounds(562, 11, 46, 14);
		cardPanel.add(lblCard5);
		
		JLabel lblCard6 = new JLabel("***");
		lblCard6.setVisible(false);
		lblCard6.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard6.setBounds(687, 11, 46, 14);
		cardPanel.add(lblCard6);
		
		JLabel lblCard7 = new JLabel("***");
		lblCard7.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard7.setVisible(false);
		lblCard7.setBounds(810, 11, 46, 14);
		cardPanel.add(lblCard7);
		
		JLabel lblCard8 = new JLabel("***");
		lblCard8.setVisible(false);
		lblCard8.setHorizontalAlignment(SwingConstants.CENTER);
		lblCard8.setBounds(942, 11, 46, 14);
		cardPanel.add(lblCard8);
		
		
		
		
		JLabel lblTimer = new JLabel("30");
		lblTimer.setFont(new Font("Sitka Subheading", Font.PLAIN, 36));
		lblTimer.setBounds(407, 71, 67, 46);
		frmFortressDefense.getContentPane().add(lblTimer);
		//timer
		tm = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{//end turn and exit window when timer reaches 0
				if(i == -1)
				{
					tm.stop();
					if (client != null) {
						client.switchTurn();
					}else if (gameServer != null) {
						gameServer.nextTurn();
						GetPanel().setVisible(false);
					    mainFrame.remove(GetPanel());
					    mainFrame.repaint();
					    mainFrame.add(new drawPhaseOtherPlayer(mainFrame, gameServer, null, hand).GetPanel());
					}
				}
				lblTimer.setText(Integer.toString(i));
				i--;
			}
		});
		tm.start();
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		healthBar.setForeground(new Color(50, 205, 50));
		healthBar.setMaximum(50);
		healthBar.setBackground(Color.DARK_GRAY);
		healthBar.setBounds(250, 152, 508, 46);
		frmFortressDefense.getContentPane().add(healthBar);
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		lblSeconds.setBounds(454, 76, 152, 41);
		frmFortressDefense.getContentPane().add(lblSeconds);
		
		JLabel lblName = null;
		if (client != null) {
			lblName = new JLabel(client.obtainTurn() + "'s Turn");
		}else if (gameServer != null) {
			lblName = new JLabel(gameServer.getTurn() + "'s Turn");
		}
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBorder(UIManager.getBorder("InternalFrame.border"));
		lblName.setForeground(Color.RED);
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(910, 11, 500, 100);
		frmFortressDefense.getContentPane().add(lblName);
		
		JLabel lblBar = new JLabel("HEALTHPOINTS: 0");
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBar.setBounds(380, 114, 254, 46);
		frmFortressDefense.getContentPane().add(lblBar);
		
		JLabel lblRoundNum = new JLabel("ROUND 5/8");
		lblRoundNum.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblRoundNum.setBounds(10, 216, 195, 90);
		frmFortressDefense.getContentPane().add(lblRoundNum);
		
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
		
		
		
		JButton btnCard1 = new JButton("");
		btnCard1.setVisible(false);
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(0).getCard_name() + " card selected </html>");
				lblCard1.setVisible(true);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		curBtn = btnCard1;//initialize to first btn
		
		JButton btnCard2 = new JButton("");
		btnCard2.setVisible(false);
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(1).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(true);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard3 = new JButton("");
		btnCard3.setVisible(false);
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(2).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(true);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard4 = new JButton("");
		btnCard4.setVisible(false);
		btnCard4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(3).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(true);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard5 = new JButton("");
		btnCard5.setVisible(false);
		btnCard5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(4).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(true);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard6 = new JButton("");
		btnCard6.setVisible(false);
		btnCard6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(5).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(true);
				lblCard7.setVisible(false);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard7 = new JButton("");
		btnCard7.setVisible(false);
		btnCard7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(6).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(true);
				lblCard8.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard8 = new JButton("");
		btnCard8.setVisible(false);
		btnCard8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(7).getCard_name() + " card selected </html>");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblCard5.setVisible(false);
				lblCard6.setVisible(false);
				lblCard7.setVisible(false);
				lblCard8.setVisible(true);
				
				lblSelected.setText("");
			}
		});
		
		JButton btnAttack = new JButton("");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numA == 4 && numD == 4){
					attackPhase ap = new attackPhase();
				}
				if(numA == 4)
				{
					lblMsgBox.setText("<html> You can only draw a maximum of 4 Attack cards </html>");
					lblSelected.setText("");
				}
				else
				{
					lblSelected.setText("<html> Attack Deck selected </html>");
					lblMsgBox.setText("<html> Click GO! to draw a card from the Attack Deck </html>");
				}
			}
		});
		btnAttack.setBorder(UIManager.getBorder("CheckBox.border"));
		Image attackImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/attackCard.PNG")).getImage();
		btnAttack.setIcon(new ImageIcon(attackImg));
		btnAttack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAttack.setBounds(250, 209, 234, 280);
		frmFortressDefense.getContentPane().add(btnAttack);
		
		JButton btnDefense = new JButton("");
		btnDefense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numA == 4 && numD == 4){
					attackPhase ap = new attackPhase();
				}
				if(numD == 4)
				{
					lblMsgBox.setText("<html> You can only draw a maximum of 4 Defense cards </html>");
					lblSelected.setText("");
				}
				else
				{
					lblSelected.setText("<html> Defense Deck selected </html>");
					lblMsgBox.setText("<html> Click GO! to draw a card from the Defense Deck </html>");
				}
			}
		});
		btnDefense.setBorder(UIManager.getBorder("CheckBox.border"));
		Image defenseImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/defenseCard.PNG")).getImage();
		btnDefense.setIcon(new ImageIcon(defenseImg));
		btnDefense.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDefense.setBounds(524, 210, 234, 279);
		frmFortressDefense.getContentPane().add(btnDefense);
		
		JButton btnPass = new JButton("PASS");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("Click GO! to PASS");
				lblSelected.setText("PASS selected");
				lblCard1.setVisible(false);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
			}
		});
		btnPass.setBackground(new Color(139, 0, 0));
		btnPass.setFont(new Font("SimSun", Font.BOLD, 30));
		btnPass.setBounds(20, 306, 187, 68);
		frmFortressDefense.getContentPane().add(btnPass);
		
		
		
		JButton btnDiscard = new JButton("DISCARD");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblCard1.isVisible())
				{
					selected = hand.Select(0);
					lblSelected.setText("<html> Discard " + hand.Select(0).getCard_name() + " selected </html>");
					
					lblMsgBox.setText("<html> Click GO! to discard  " + hand.Select(0).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard2.isVisible())
				{
					selected = hand.Select(1);
					lblSelected.setText("<html> Discard " + hand.Select(1).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(1).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard3.isVisible())
				{
					selected = hand.Select(2);
					lblSelected.setText("<html> Discard " + hand.Select(2).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(2).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard4.isVisible())
				{
					selected = hand.Select(3);
					lblSelected.setText("<html> Discard " + hand.Select(3).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(3).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard5.isVisible())
				{
					selected = hand.Select(4);
					lblSelected.setText("<html> Discard " + hand.Select(4).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(4).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard6.isVisible())
				{
					selected = hand.Select(5);
					lblSelected.setText("<html> Discard " + hand.Select(5).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(5).getCard_name() + " </html>");
					discard = true;
				}
				else if(lblCard7.isVisible())
				{
					selected = hand.Select(6);
					lblSelected.setText("<html> Discard " + hand.Select(6).getCard_name() + " selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard " + hand.Select(6).getCard_name() + " </html>");
					discard = true;
				}
				else
				{
					discard = false;
					lblMsgBox.setText("<html> Select a card to be discarded </html>");
				}
			}
		});
		
		btnDiscard.setFont(new Font("SimSun", Font.BOLD, 30));
		btnDiscard.setBackground(new Color(30, 144, 255));
		btnDiscard.setBounds(20, 399, 187, 68);
		frmFortressDefense.getContentPane().add(btnDiscard);
		
		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblSelected.getText() == "PASS selected")
				{
					tm.stop();
					if (client != null) {
						client.switchTurn();
					}else if (gameServer != null) {
						gameServer.nextTurn();
						GetPanel().setVisible(false);
					    mainFrame.remove(GetPanel());
					    mainFrame.repaint();
					    mainFrame.add(new drawPhaseOtherPlayer(mainFrame, gameServer, null, hand).GetPanel());
					}
					//System.exit(0);
				}
				else if(lblSelected.getText() == "<html> Attack Deck selected </html>")
				{
					if (gameServer != null) {
						gameServer.draw(CardType.Attack);
						hand = gameServer.getModel().getPlayers().get(0).getHand();
					}
					else if (client != null) {
						client.draw(CardType.Attack);
						try {
							mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						mainFrame.setCursor(Cursor.getDefaultCursor());
						hand = client.getHand();
					}
					
					numA++;
					
					if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Axe)
					{
						curBtn.setIcon(new ImageIcon(axeImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Battle_Axe)
					{
						curBtn.setIcon(new ImageIcon(battleAxeImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Crossbow)
					{
						curBtn.setIcon(new ImageIcon(crossbowImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Mace)
					{
						curBtn.setIcon(new ImageIcon(maceImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Stick)
					{
						curBtn.setIcon(new ImageIcon(stickImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == AttackCard.Sword)
					{
						curBtn.setIcon(new ImageIcon(swordImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Archer_Tower)
					{
						curBtn.setIcon(new ImageIcon(archerTowerImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Scout)
					{
						curBtn.setIcon(new ImageIcon(scoutImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Trade)
					{
						curBtn.setIcon(new ImageIcon(tradeImg));
					}
					curBtn.setBounds(curBound, 22, 119, 176);
					cardPanel.add(curBtn);
					curBtn.setVisible(true);
					
					curBound = curBound + 130;
					
					if(hand.Size() == 1)
					{
						curBtn = btnCard2;
					}
					else if(hand.Size() == 2)
					{
						curBtn = btnCard3;
					}
					else if(hand.Size() == 3)
					{
						curBtn = btnCard4;
					}
					else if(hand.Size() == 4)
					{
						curBtn = btnCard5;
					}
					else if(hand.Size() == 5)
					{
						curBtn = btnCard6;
					}
					else if(hand.Size() == 6)
					{
						curBtn = btnCard7;
					}
					else if(hand.Size() == 7)
					{
						curBtn = btnCard8;
					}
					
					JOptionPane.showMessageDialog(null, "<html> " + hand.Select(hand.Size()-1).getCard_name() + " card acquired! </html>");
				}
				else if(lblSelected.getText() == "<html> Defense Deck selected </html>")
				{
					if (gameServer != null) {
						gameServer.draw(CardType.Defense);
						hand = gameServer.getModel().getPlayers().get(0).getHand();
					}
					else if (client != null) {
						client.draw(CardType.Defense);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						hand = client.getHand();
					}
					numD++;
					
					if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Earthquake)
					{
						curBtn.setIcon(new ImageIcon(earthquakeImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Flood)
					{
						curBtn.setIcon(new ImageIcon(floodImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Thunderstorm)
					{
						curBtn.setIcon(new ImageIcon(thunderstormImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Tornado)
					{
						curBtn.setIcon(new ImageIcon(tornadoImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Barbed_Wire)
					{
						curBtn.setIcon(new ImageIcon(barbedWireImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Iron_Door)
					{
						curBtn.setIcon(new ImageIcon(ironDoorImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Reinforced_Gate)
					{
						curBtn.setIcon(new ImageIcon(reinforcedGateImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Steel_Chains)
					{
						curBtn.setIcon(new ImageIcon(steelChainsImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Stone_Wall)
					{
						curBtn.setIcon(new ImageIcon(stoneWallImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == DefenseCard.Wooden_Wall)
					{
						curBtn.setIcon(new ImageIcon(woodenWallImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Archer_Tower)
					{
						curBtn.setIcon(new ImageIcon(archerTowerImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Scout)
					{
						curBtn.setIcon(new ImageIcon(scoutImg));
					}
					else if(hand.Select(hand.Size()-1).getCard_name() == SpecialCard.Trade)
					{
						curBtn.setIcon(new ImageIcon(tradeImg));
					}
					curBtn.setBounds(curBound, 22, 119, 176);
					cardPanel.add(curBtn);
					curBtn.setVisible(true);
	
					curBound = curBound + 130;
					
					if(hand.Size() == 1)
					{
						curBtn = btnCard2;
					}
					else if(hand.Size() == 2)
					{
						curBtn = btnCard3;
					}
					else if(hand.Size() == 3)
					{
						curBtn = btnCard4;
					}
					else if(hand.Size() == 4)
					{
						curBtn = btnCard5;
					}
					else if(hand.Size() == 5)
					{
						curBtn = btnCard6;
					}
					else if(hand.Size() == 6)
					{
						curBtn = btnCard7;
					}
					else if(hand.Size() == 7)
					{
						curBtn = btnCard8;
					}
					
					JOptionPane.showMessageDialog(null, "<html> " + hand.Select(hand.Size()-1).getCard_name() + " card acquired! </html>");
				}
				else if(discard)
				{	
					if (gameServer != null) {
						gameServer.discard(selected.getID());
						hand = gameServer.getModel().getPlayers().get(0).getHand();
					}
					else if (client != null) {
						client.dicard(selected.getID());
						hand = client.getHand();
					}
					
					if(lblCard1.isVisible())
					{
						lblCard1.setVisible(false);
						btnCard1.setVisible(false);
					}
					else if(lblCard2.isVisible())
					{
						lblCard2.setVisible(false);
						btnCard2.setVisible(false);
					}
					else if(lblCard3.isVisible())
					{
						lblCard3.setVisible(false);
						btnCard3.setVisible(false);
					}
					else if(lblCard4.isVisible())
					{
						lblCard4.setVisible(false);
						btnCard4.setVisible(false);
					}
					else if(lblCard5.isVisible())
					{
						lblCard5.setVisible(false);
						btnCard5.setVisible(false);
					}
					else if(lblCard6.isVisible())
					{
						lblCard6.setVisible(false);
						btnCard6.setVisible(false);
					}
					else if(lblCard7.isVisible())
					{
						lblCard7.setVisible(false);
						btnCard7.setVisible(false);
					}
				}
				lblSelected.setText("");
				lblMsgBox.setText("");
			}

		});
		btnGo.setBackground(new Color(0, 128, 0));
		btnGo.setFont(new Font("Britannic Bold", Font.PLAIN, 99));
		btnGo.setBounds(784, 253, 260, 181);
		frmFortressDefense.getContentPane().add(btnGo);
		
		/*
		Player Image
		 */
		JPanel playerPanel = new JPanel();
		playerPanel.setBackground(new Color(15, 28, 81));
		playerPanel.setBorder(UIManager.getBorder("ProgressBar.border"));
		playerPanel.setBounds(790, 11, 100, 100);
		frmFortressDefense.getContentPane().add(playerPanel);
		playerPanel.setLayout(null);
		JButton playerIcon = new JButton("");
		Image pc = new ImageIcon(this.getClass().getResource("characters/my_character.png")).getImage();
		playerIcon.setIcon(new ImageIcon(pc));
		playerIcon.setBounds(0, 0, 100, 100);
		playerPanel.add(playerIcon);
	}
	
	public JPanel GetPanel() {
		return (JPanel) frmFortressDefense.getContentPane();
	}
}
