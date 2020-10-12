package gui;
import code.*;
import code.Deck.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.BorderLayout;

/*
 * Creates the Draw Phase GUI window
 * @author Alec Willette
 * 
 * */

public class drawPhase {

	private JFrame frmFortressDefense;

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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drawPhase window = new drawPhase();
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
	public drawPhase() {
		initialize();
	}
	
	code.Game turn = new code.Game();//initializes decks
	code.Hand hand = new code.Hand();//initializes the player's hand

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
					System.exit(0);
				}
				lblTimer.setText(Integer.toString(i));
				i--;
			}
		});
		tm.start();
		
		
		JLabel lblSeconds = new JLabel("Seconds left");
		lblSeconds.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		lblSeconds.setBounds(454, 76, 152, 41);
		frmFortressDefense.getContentPane().add(lblSeconds);
		
		JLabel lblName = new JLabel("Player_Name's Turn");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBorder(UIManager.getBorder("InternalFrame.border"));
		lblName.setForeground(Color.RED);
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(848, 11, 216, 79);
		frmFortressDefense.getContentPane().add(lblName);
		
		JLabel lblBar = new JLabel("HEALTHPOINTS: 19");
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBar.setBounds(380, 114, 254, 46);
		frmFortressDefense.getContentPane().add(lblBar);
		
		JLabel lblRoundNum = new JLabel("ROUND 5/8");
		lblRoundNum.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblRoundNum.setBounds(10, 216, 195, 90);
		frmFortressDefense.getContentPane().add(lblRoundNum);
		
		//Initialize Attack card images
		Image axeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/axe.png")).getImage();
		Image battleAxeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/battleAxe.png")).getImage();
		Image crossbowImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/crossbow.png")).getImage();
		Image maceImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/mace.png")).getImage();
		Image stickImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/stick.png")).getImage();
		Image swordImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/sword.png")).getImage();
				
		//Initialize Defense card images
		Image barbedWireImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/barbedWire.png")).getImage();
		Image ironDoorImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/ironDoor.png")).getImage();
		Image reinforcedGateImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/reinforcedGate.png")).getImage();
		Image steelChainsImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/steelChains.png")).getImage();
		Image stoneWallImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/stoneWall.png")).getImage();
		Image woodenWallImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/woodenWall.png")).getImage();
				
		//Initialize Damage card images
		Image earthquakeImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/earthquake.png")).getImage();
		Image floodImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/flood.png")).getImage();
		Image thunderstormImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/thunderstorm.png")).getImage();
		Image tornadoImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/tornado.png")).getImage();
				
		//Initialize Special card images
		Image archerTowerImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/archerTower.png")).getImage();
		Image scoutImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/scout.png")).getImage();
		Image tradeImg = new ImageIcon(this.getClass().getResource("Images/specialIMG/trade.png")).getImage();
		
		JButton btnCard1 = new JButton("");
		btnCard1.setVisible(false);
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> " + hand.Select(0).card_name + " card selected </html>");
				lblCard1.setVisible(true);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		curBtn = btnCard1;//initialize to first btn
		
		JButton btnCard2 = new JButton("");
		btnCard2.setVisible(false);
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> BATTLE AXE card selected </html>");
				lblCard2.setVisible(true);
				lblCard1.setVisible(false);
				lblCard3.setVisible(false);
				lblCard4.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard3 = new JButton("");
		btnCard3.setVisible(false);
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> REINFORCED GATE card selected </html>");
				lblCard3.setVisible(true);
				lblCard2.setVisible(false);
				lblCard1.setVisible(false);
				lblCard4.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard4 = new JButton("");
		btnCard4.setVisible(false);
		btnCard4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgBox.setText("<html> SCOUT card selected </html>");
				lblCard4.setVisible(true);
				lblCard2.setVisible(false);
				lblCard3.setVisible(false);
				lblCard1.setVisible(false);
				lblSelected.setText("");
			}
		});
		
		JButton btnCard5 = new JButton("");
		btnCard5.setVisible(false);
		//btnCard5.setBounds(534, 22, 119, 176);
		//cardPanel.add(btnCard5);
		
		JButton btnCard6 = new JButton("");
		btnCard6.setVisible(false);
		//btnCard6.setBounds(666, 22, 119, 176);
		//cardPanel.add(btnCard6);
		
		JButton btnCard7 = new JButton("");
		btnCard7.setVisible(false);
		//btnCard7.setBounds(796, 22, 119, 176);
		//cardPanel.add(btnCard7);
		
		JButton btnCard8 = new JButton("");
		btnCard8.setVisible(false);
		//btnCard8.setBounds(925, 22, 119, 176);
		//cardPanel.add(btnCard8);
		
		JButton btnAttack = new JButton("");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		Image attackImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/attackCard.png")).getImage();
		btnAttack.setIcon(new ImageIcon(attackImg));
		btnAttack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAttack.setBounds(250, 209, 234, 280);
		frmFortressDefense.getContentPane().add(btnAttack);
		
		JButton btnDefense = new JButton("");
		btnDefense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		Image defenseImg = new ImageIcon(this.getClass().getResource("Images/defenseIMG/defenseCard.png")).getImage();
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
					lblSelected.setText("<html> Discard AXE selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard AXE </html>");
					discard = true;
				}
				else if(lblCard2.isVisible())
				{
					lblSelected.setText("<html> Discard BATTLE AXE selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard BATTLE AXE </html>");
					discard = true;
				}
				else if(lblCard3.isVisible())
				{
					lblSelected.setText("<html> Discard REINFORCED GATE selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard REINFORCED GATE </html>");
					discard = true;
				}
				else if(lblCard4.isVisible())
				{
					lblSelected.setText("<html> Discard SCOUT selected </html>");
					lblMsgBox.setText("<html> Click GO! to discard SCOUT </html>");
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
					System.exit(0);
				}
				else if(lblSelected.getText() == "<html> Attack Deck selected </html>")
				{
					hand.Draw(turn.AttackDeck);//draws card and adds it to hand
					numA++;
					
					if(hand.Select(hand.Size()-1).card_name == "AXE")
					{
						curBtn.setIcon(new ImageIcon(axeImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "BATTLE AXE")
					{
						curBtn.setIcon(new ImageIcon(battleAxeImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "CROSSBOW")
					{
						curBtn.setIcon(new ImageIcon(crossbowImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "MACE")
					{
						curBtn.setIcon(new ImageIcon(maceImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "STICK")
					{
						curBtn.setIcon(new ImageIcon(stickImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "SWORD")
					{
						curBtn.setIcon(new ImageIcon(swordImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "ARCHER TOWER")
					{
						curBtn.setIcon(new ImageIcon(archerTowerImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "SCOUT")
					{
						curBtn.setIcon(new ImageIcon(scoutImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "TRADE")
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
					
					/*if(hand.Size() == 1)	//enables the btn for new card
					{
						if(hand.Select(hand.Size()-1).card_name == "AXE")
						{
							btnCard1.setIcon(new ImageIcon(axeImg));
							btnCard1.setBounds(10, 24, 119, 176);
							cardPanel.add(btnCard1);
							btnCard1.setVisible(true);
						}
						else if(hand.Select(hand.Size()-1).card_name == "BATTLE AXE")
						{
							btnCard1.setIcon(new ImageIcon(battleAxeImg));
							btnCard1.setBounds(10, 24, 119, 176);
							cardPanel.add(btnCard1);
							btnCard1.setVisible(true);
						}
						else if(hand.Select(hand.Size()-1).card_name == "CROSSBOW")
						{
							btnCard1.setIcon(new ImageIcon(crossbowImg));
							btnCard1.setBounds(10, 24, 119, 176);
							cardPanel.add(btnCard1);
							btnCard1.setVisible(true);
						}
						else if(hand.Select(hand.Size()-1).card_name == "MACE")
						{
							btnCard1.setIcon(new ImageIcon(maceImg));
							btnCard1.setBounds(10, 24, 119, 176);
							cardPanel.add(btnCard1);
							btnCard1.setVisible(true);
						}
						
					}
					else if(hand.Size() == 2)
					{
						btnCard2.setVisible(true);
					}
					else if(hand.Size() == 3)
					{
						btnCard3.setVisible(true);
					}
					else if(hand.Size() == 4)
					{
						btnCard4.setVisible(true);
					}
					else if(hand.Size() == 5)
					{
						btnCard5.setVisible(true);
					}
					else if(hand.Size() == 6)
					{
						btnCard6.setVisible(true);
					}
					else if(hand.Size() == 7)
					{
						btnCard7.setVisible(true);
					}
					else if(hand.Size() == 8)
					{
						btnCard8.setVisible(true);
					}
					*/
					
					/*
					final JOptionPane optionPane = new JOptionPane("<html> " + hand.Select(hand.Size()-1).card_name + " card acquired! </html>", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

					final JDialog dialog = new JDialog();
					dialog.setTitle("Message");
					dialog.setModal(true);

					dialog.setContentPane(optionPane);

					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.pack();
					dialog.setVisible(true);
					*/
					
					JOptionPane.showMessageDialog(null, "<html> " + hand.Select(hand.Size()-1).card_name + " card acquired! </html>");
				}
				else if(lblSelected.getText() == "<html> Defense Deck selected </html>")
				{
					hand.Draw(turn.DefenseDeck);
					numD++;
					
					if(hand.Select(hand.Size()-1).card_name == "EARTHQUAKE")
					{
						curBtn.setIcon(new ImageIcon(earthquakeImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "FLOOD")
					{
						curBtn.setIcon(new ImageIcon(floodImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "THUNDERSTORM")
					{
						curBtn.setIcon(new ImageIcon(thunderstormImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "TORNADO")
					{
						curBtn.setIcon(new ImageIcon(tornadoImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "BARBED WIRE")
					{
						curBtn.setIcon(new ImageIcon(barbedWireImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "IRON DOOR")
					{
						curBtn.setIcon(new ImageIcon(ironDoorImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "REINFORCED GATE")
					{
						curBtn.setIcon(new ImageIcon(reinforcedGateImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "STEEL CHAINS")
					{
						curBtn.setIcon(new ImageIcon(steelChainsImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "STONE WALL")
					{
						curBtn.setIcon(new ImageIcon(stoneWallImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "WOODEN WALL")
					{
						curBtn.setIcon(new ImageIcon(woodenWallImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "ARCHER TOWER")
					{
						curBtn.setIcon(new ImageIcon(archerTowerImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "SCOUT")
					{
						curBtn.setIcon(new ImageIcon(scoutImg));
					}
					else if(hand.Select(hand.Size()-1).card_name == "TRADE")
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
					
					/*
					if(hand.Size() == 1)	//enables the btn for new card
					{
						btnCard1.setVisible(true);
					}
					else if(hand.Size() == 2)
					{
						btnCard2.setVisible(true);
					}
					else if(hand.Size() == 3)
					{
						btnCard3.setVisible(true);
					}
					else if(hand.Size() == 4)
					{
						btnCard4.setVisible(true);
					}
					else if(hand.Size() == 5)
					{
						btnCard5.setVisible(true);
					}
					else if(hand.Size() == 6)
					{
						btnCard6.setVisible(true);
					}
					else if(hand.Size() == 7)
					{
						btnCard7.setVisible(true);
					}
					else if(hand.Size() == 8)
					{
						btnCard8.setVisible(true);
					}
					*/
					/*
					final JOptionPane optionPane = new JOptionPane("<html> " + hand.Select(hand.Size()-1).card_name + " card acquired! </html>", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

					final JDialog dialog = new JDialog();
					dialog.setTitle("Message");
					dialog.setModal(true);

					dialog.setContentPane(optionPane);

					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.pack();
					dialog.setVisible(true);
					*/
					
					JOptionPane.showMessageDialog(null, "<html> " + hand.Select(hand.Size()-1).card_name + " card acquired! </html>");
				}
				else if(discard)
				{
					if(lblCard1.isVisible())
					{
						lblCard1.setVisible(false);
						btnCard1.setVisible(false);
						JOptionPane.showMessageDialog(null, "<html> The AXE card has been discarded </html>");
						System.exit(0);
					}
					else if(lblCard2.isVisible())
					{
						lblCard2.setVisible(false);
						btnCard2.setVisible(false);
						JOptionPane.showMessageDialog(null, "<html> The BATTLE AXE card has been discarded </html>");
						System.exit(0);
					}
					else if(lblCard3.isVisible())
					{
						lblCard3.setVisible(false);
						btnCard3.setVisible(false);
						JOptionPane.showMessageDialog(null, "<html> The REINFORCED GATE card has been discarded </html>");
						System.exit(0);
					}
					else if(lblCard4.isVisible())
					{
						lblCard4.setVisible(false);
						btnCard4.setVisible(false);
						JOptionPane.showMessageDialog(null, "<html> The SCOUT card has been discarded </html>");
						System.exit(0);
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
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		healthBar.setForeground(new Color(50, 205, 50));
		healthBar.setMaximum(40);
		healthBar.setBackground(Color.DARK_GRAY);
		healthBar.setValue(19);
		healthBar.setBounds(250, 152, 508, 46);
		frmFortressDefense.getContentPane().add(healthBar);
		
		
		
		
		
		
		
		
		
		
	}
}
