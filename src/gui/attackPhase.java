package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;

import code.Socket.*;
import code.*;

public class attackPhase {

	Timer timer;
	boolean discard = false;
	
	private boolean card1Clicked = false;
	private boolean card2Clicked = false;
	private boolean card3Clicked = false;
	private boolean card4Clicked = false;
	private boolean card5Clicked = false;
	private boolean card6Clicked = false;
	private boolean card7Clicked = false;
	private boolean card8Clicked = false;

	private int axeValue = 3;
	private int battleAxeValue = 8;
	private int crossbowValue = 10;
	private int maceValue = 5;
	private int stickValue = 1;
	private int swordValue = 4;
	private Server server;
	private Client client;
	private JFrame frame;
	private JFrame mainFrame;
	private HashMap<String, String> playerData;
	private String playerName;
	private int health;
	private String currentTurn;
	private int round;
	
	public attackPhase(JFrame mainFrame, Server server, Client client) {
		this.server = server;
		this.client = client;
		this.mainFrame = mainFrame;
		if (this.server != null) {
			playerData = this.server.getPlayerData();
			playerName = this.server.getPlayerName();
			health = this.server.getHealth();
			currentTurn = this.server.getTurn();
			round = this.server.getRound();
		}else if (this.client != null) {
			playerData = this.client.getPlayerData();
			playerName = this.client.getName();
			health = this.client.getHealth();
			currentTurn = (String)this.client.obtainTurn();
			round = this.client.getRound();
		}
		runAttackGUI();
	}

	private void runAttackGUI() {
		Color c1 = new Color(153, 102, 0);
		Color c2 = new Color(0, 0, 153);

		frame = new JFrame("FORTRESS DEFENSE");
		frame.setBackground(c1);

		JPanel tpanel = new JPanel();
		JPanel spanel = new JPanel();
		JPanel lpanel = new JPanel();
		JPanel rpanel = new JPanel();

		tpanel.setLayout(new BoxLayout(tpanel, BoxLayout.Y_AXIS));
		tpanel.setBounds(850, 0, 500, 1000);
		spanel.setLayout(new FlowLayout());
		lpanel.setLayout(new BoxLayout(lpanel, BoxLayout.Y_AXIS));
		lpanel.setBounds(0, 0, 500, 1000);
		rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
		tpanel.setBounds(1400, 0, 500, 1000);

		
		JButton playerIcon = new JButton("");
		Image pc = new ImageIcon(this.getClass().getResource("characters/my_character3.png")).getImage();
		playerIcon.setIcon(new ImageIcon(pc));
		
		JButton playerIcon1 = new JButton("");
		Image pc1 = new ImageIcon(this.getClass().getResource("characters/my_character17.png")).getImage();
		playerIcon1.setIcon(new ImageIcon(pc1));
		
		JButton playerIcon2 = new JButton("");
		Image pc2 = new ImageIcon(this.getClass().getResource("characters/my_character11.png")).getImage();
		playerIcon2.setIcon(new ImageIcon(pc2));
		
		JButton playerIcon3 = new JButton("");
		Image pc3 = new ImageIcon(this.getClass().getResource("characters/my_character14.png")).getImage();
		playerIcon3.setIcon(new ImageIcon(pc3));
		
		JButton playerIcon4 = new JButton("");
		Image pc4 = new ImageIcon(this.getClass().getResource("characters/my_character20.png")).getImage();
		playerIcon4.setIcon(new ImageIcon(pc4));
		
		JButton playerIcon5 = new JButton("");
		Image pc5 = new ImageIcon(this.getClass().getResource("characters/my_character18.png")).getImage();
		playerIcon5.setIcon(new ImageIcon(pc5));
		
		JButton playerIcon6 = new JButton("");
		Image pc6 = new ImageIcon(this.getClass().getResource("characters/my_character19.png")).getImage();
		playerIcon6.setIcon(new ImageIcon(pc6));
		
		JButton playerIcon7 = new JButton("");
		Image pc7 = new ImageIcon(this.getClass().getResource("characters/my_character12.png")).getImage();
		playerIcon7.setIcon(new ImageIcon(pc7));
		
		JButton playerIcon8 = new JButton("");
		Image pc8 = new ImageIcon(this.getClass().getResource("characters/my_character13.png")).getImage();
		playerIcon8.setIcon(new ImageIcon(pc8));
		
		JButton playerIcon11 = new JButton("");
		Image pc11 = new ImageIcon(this.getClass().getResource("characters/my_character15.png")).getImage();
		playerIcon11.setIcon(new ImageIcon(pc11));
		
		JButton playerIcon12 = new JButton("");
		Image pc12 = new ImageIcon(this.getClass().getResource("characters/my_character16.png")).getImage();
		playerIcon12.setIcon(new ImageIcon(pc12));
		
		JButton playerIcon14 = new JButton("");
		Image pc14 = new ImageIcon(this.getClass().getResource("characters/my_character21.png")).getImage();
		playerIcon14.setIcon(new ImageIcon(pc14));
		
		JButton playerIcon15 = new JButton("");
		Image pc15 = new ImageIcon(this.getClass().getResource("characters/my_character22.png")).getImage();
		playerIcon15.setIcon(new ImageIcon(pc15));
		
		JLabel p1 = new JLabel();
		JLabel hp1 = new JLabel();
		JProgressBar hb1 = new JProgressBar();
		JLabel p2 = new JLabel();
		JLabel hp2 = new JLabel();
		JProgressBar hb2 = new JProgressBar();
		JLabel p3 = new JLabel();
		JLabel hp3 = new JLabel();
		JProgressBar hb3 = new JProgressBar();
		JLabel p4 = new JLabel();
		JLabel hp4 = new JLabel();
		JProgressBar hb4 = new JProgressBar();
		int i = 1;
		for (Entry<String, String> p: playerData.entrySet()) {
			if (!p.getKey().equals(playerName)){
				if (i == 1) {
					p1.setText(p.getKey());
					p1.setForeground(c2);
					p1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					p1.setHorizontalAlignment(SwingConstants.CENTER);

					hp1.setText("Health Points : " + p.getValue());
					hp1.setForeground(c2);
					hp1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					hp1.setHorizontalAlignment(SwingConstants.CENTER);

					hb1.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
					hb1.setForeground(new Color(50, 205, 50));
					hb1.setMaximum(50);
					hb1.setBackground(Color.DARK_GRAY);
					hb1.setValue(Integer.parseInt(p.getValue()));
					
					lpanel.add(playerIcon1);
					lpanel.add(p1);
					lpanel.add(hp1);
					lpanel.add(hb1);
					i+=1;
				}
				else if (i == 2) {
					p2.setText(p.getKey());
					p2.setForeground(c2);
					p2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					p2.setHorizontalAlignment(SwingConstants.CENTER);

					hp2.setText("Health Points : " + p.getValue());
					hp2.setForeground(c2);
					hp2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					hp2.setHorizontalAlignment(SwingConstants.CENTER);

					hb2.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
					hb2.setForeground(new Color(50, 205, 50));
					hb2.setMaximum(50);
					hb2.setBackground(Color.DARK_GRAY);
					hb2.setValue(Integer.parseInt(p.getValue()));
					lpanel.add(playerIcon2);
					lpanel.add(p2);
					lpanel.add(hp2);
					lpanel.add(hb2);
					i+=1;
				}
				else if (i == 3) {
					p3.setText(p.getKey());
					p3.setForeground(c2);
					p3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					p3.setHorizontalAlignment(SwingConstants.CENTER);

					hp3.setText("Health Points : " + p.getValue());
					hp3.setForeground(c2);
					hp3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					hp3.setHorizontalAlignment(SwingConstants.CENTER);

					hb3.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
					hb3.setForeground(new Color(50, 205, 50));
					hb3.setMaximum(50);
					hb3.setBackground(Color.DARK_GRAY);
					hb3.setValue(Integer.parseInt(p.getValue()));
					
					rpanel.add(playerIcon3);
					rpanel.add(p3);
					rpanel.add(hp3);
					rpanel.add(hb3);
					i+=1;
				}
				else if (i == 4) {
					p4.setText(p.getKey());
					p4.setForeground(c2);
					p4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					p4.setHorizontalAlignment(SwingConstants.CENTER);

					hp4.setText("Health Points : " + p.getValue());
					hp4.setForeground(c2);
					hp4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					hp4.setHorizontalAlignment(SwingConstants.CENTER);

					hb4.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
					hb4.setForeground(new Color(50, 205, 50));
					hb4.setMaximum(50);
					hb4.setBackground(Color.DARK_GRAY);
					hb4.setValue(Integer.parseInt(p.getValue()));

					rpanel.add(playerIcon4);
					rpanel.add(p4);
					rpanel.add(hp4);
					rpanel.add(hb4);
					i+=1;
				}
			}
		}
		
		JLabel attack = new JLabel("ATTACK PHASE");
		attack.setForeground(c2);
		attack.setFont(new Font("Times New Roman", Font.BOLD, 50));
		attack.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTimer = new JLabel("30");
		lblTimer.setForeground(c2);
		lblTimer.setFont(new Font("Times New Roman", Font.BOLD, 75));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel time = new JLabel("Seconds Left");
		time.setForeground(c2);
		time.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		time.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel hp = new JLabel("HEALTH POINTS : " + health);
		hp.setForeground(c2);
		hp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar hb = new JProgressBar();
		hb.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb.setForeground(new Color(50, 205, 50));
		hb.setMaximum(50);
		hb.setBackground(Color.DARK_GRAY);
		hb.setValue(health);
		hb.setBounds(250, 252, 508, 46);

		JLabel turn = new JLabel("Round: " + round + "/8" + " Turn: " + currentTurn);
		turn.setForeground(c2);
		turn.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		turn.setHorizontalAlignment(SwingConstants.CENTER);
		
		tpanel.add(attack);		
		
		timer = new Timer(1000, new ActionListener() {
			int i = Integer.parseInt(lblTimer.getText());
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i == -1)
				{
					timer.stop();
					if (currentTurn.equals(playerName)) {
						if (client != null) {
							client.switchTurn();
						}else if (server != null) {
							server.nextTurn();
						}
					}
				}
				lblTimer.setText(Integer.toString(i));
				i--;
			}
		});
		timer.start();
		
		tpanel.add(lblTimer);
		tpanel.add(time);
		tpanel.add(turn);
		tpanel.add(playerIcon);
		tpanel.add(hp);
		tpanel.add(hb);

		JButton card1 = new JButton("");
		Image axeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/axe.PNG")).getImage();
		card1.setIcon(new ImageIcon(axeImg));
		card1.setBackground(c1);
		spanel.add(card1);

		JButton card2 = new JButton("");
		Image crossbow = new ImageIcon(this.getClass().getResource("Images/attackIMG/crossbow.PNG")).getImage();
		card2.setIcon(new ImageIcon(crossbow));
		card2.setBackground(c1);
		spanel.add(card2);

		JButton card3 = new JButton("");
		Image baxeImg = new ImageIcon(this.getClass().getResource("Images/damageIMG/earthquake.PNG")).getImage();
		card3.setIcon(new ImageIcon(baxeImg));
		card3.setBackground(c1);
		spanel.add(card3);

		JButton card4 = new JButton("");
		Image mace = new ImageIcon(this.getClass().getResource("Images/damageIMG/flood.PNG")).getImage();
		card4.setIcon(new ImageIcon(mace));
		card4.setBackground(c1);
		spanel.add(card4);

		JButton card5 = new JButton("");
		Image scout = new ImageIcon(this.getClass().getResource("Images/specialIMG/scout.PNG")).getImage();
		card5.setIcon(new ImageIcon(scout));
		card5.setBackground(c1);
		spanel.add(card5);

		JButton card6 = new JButton("");
		Image sword = new ImageIcon(this.getClass().getResource("Images/attackIMG/sword.PNG")).getImage();
		card6.setIcon(new ImageIcon(sword));
		card6.setBackground(c1);
		spanel.add(card6);

		JButton card7 = new JButton("");
		Image door = new ImageIcon(this.getClass().getResource("Images/defenseIMG/ironDoor.PNG")).getImage();
		card7.setIcon(new ImageIcon(door));
		card7.setBackground(c1);
		spanel.add(card7);

		JButton card8 = new JButton("");
		Image gate = new ImageIcon(this.getClass().getResource("Images/defenseIMG/reinforcedGate.PNG")).getImage();
		card8.setIcon(new ImageIcon(gate));
		card8.setBackground(c1);
		spanel.add(card8);

		card1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card1Clicked = true;
				}
			}
		});

		card2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card2Clicked = true;
				}
			}
		});

		card3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card3Clicked = true;
				}
			}
		});

		card4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card4Clicked = true;
				}
			}
		});

		card5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card5Clicked = true;
				}
			}
		});

		card6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card6Clicked = true;
				}
			}
		});

		card7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card7Clicked = true;
				}
			}
		});

		card8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentTurn.equals(playerName)) {
					card8Clicked = true;
				}
			}
		});

		playerIcon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(card1Clicked == true) {
					card1.setIcon(null);
					card1.setVisible(false);
					card1Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - axeValue;
					String text = "";
					if(healthAfterAttack <= 0) {
						text = "Health Points : " + Integer.toString(0);
					} 
					else {
						text = "Health Points : " + Integer.toString(healthAfterAttack);
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - maceValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - stickValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - swordValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb1.getValue()>=10) {
						playerIcon1.setIcon(new ImageIcon(pc1));
					}
					else if(hb1.getValue()<10 && hb1.getValue()>=5) {
						playerIcon1.setIcon(new ImageIcon(pc5));
					}
					else if(hb1.getValue()<5) {
						playerIcon1.setIcon(new ImageIcon(pc6));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
			}
		});

		playerIcon2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(card1Clicked == true) {
					card1.setIcon(null);
					card1.setVisible(false);
					card1Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - maceValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - stickValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - swordValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb2.getValue()>=10) {
						playerIcon2.setIcon(new ImageIcon(pc2));
					}
					else if(hb2.getValue()<10 && hb2.getValue()>=5) {
						playerIcon2.setIcon(new ImageIcon(pc7));
					}
					else if(hb2.getValue()<5) {
						playerIcon2.setIcon(new ImageIcon(pc8));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
			}
		});

		playerIcon3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(card1Clicked == true) {
					card1.setIcon(null);
					card1.setVisible(false);
					card1Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn"); timer.stop();
					}
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - maceValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - stickValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - swordValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb3.getValue()>=10) {
						playerIcon3.setIcon(new ImageIcon(pc3));
					}
					else if(hb3.getValue()<10 && hb3.getValue()>=5) {
						playerIcon3.setIcon(new ImageIcon(pc11));
					}
					else if(hb3.getValue()<5) {
						playerIcon3.setIcon(new ImageIcon(pc12));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
			}
		});

		playerIcon4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(card1Clicked == true) {
					card1.setIcon(null);
					card1.setVisible(false);
					card1Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");
					
					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - maceValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - stickValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - swordValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - axeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue; 
					String text = ""; 
					if(healthAfterAttack <= 0) { 
						text = "Health Points : " + Integer.toString(0);
					} 
					else { 
						text = "Health Points : " + Integer.toString(healthAfterAttack); 
					}
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
					turn.setText("Player 1 Turn");

					if(hb4.getValue()>=10) {
						playerIcon4.setIcon(new ImageIcon(pc4));
					}
					else if(hb4.getValue()<10 && hb4.getValue()>=5) {
						playerIcon4.setIcon(new ImageIcon(pc14));
					}
					else if(hb4.getValue()<5) {
						playerIcon4.setIcon(new ImageIcon(pc15));
					}
					
					if(hp1.getText().equals("Health Points : 0") && hp2.getText().equals("Health Points : 0")
							&& hp3.getText().equals("Health Points : 0") && hp4.getText().equals("Health Points : 0")) {
						showWinner(); timer.stop(); turn.setText("Your Turn");
					}
				}
			}
		});

		frame.getContentPane().add(tpanel, BorderLayout.CENTER);
		frame.getContentPane().add(spanel, BorderLayout.SOUTH);
		frame.getContentPane().add(rpanel, BorderLayout.EAST);
		frame.getContentPane().add(lpanel, BorderLayout.WEST);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height - 50);
		frame.setVisible(false);
	}
	
	public void showWinner() {
		ImageIcon icon = null;
        java.net.URL imgURL = this.getClass().getResource("Images/winner.jpg");
        if (imgURL != null) {
           icon = new ImageIcon(imgURL);
        }
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);
        
		JOptionPane optionPane = new JOptionPane("Congrats. You Won!!!", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, icon, new Object[]{}, null);
		
		JDialog dialog = new JDialog();
		dialog.setTitle("Winner");
		dialog.setModal(true);
		dialog.setContentPane(optionPane);
		
		Timer timer = new Timer(5000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		timer.setRepeats(false);
		timer.start();

		dialog.setSize(500, 500);
		dialog.setVisible(true);
	}
	
	public JPanel getPanel() {
		return (JPanel) frame.getContentPane();
	}
	
	public void viewScout()
	{
		mainFrame.getContentPane().add(new scoutCardGUI(mainFrame, server, client, playerData.keySet().toArray(new String[0])).GetPanel());
		getPanel().setVisible(false);
	}
}