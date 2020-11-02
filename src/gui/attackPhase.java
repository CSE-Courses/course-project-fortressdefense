package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class attackPhase {
	
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
	
	public attackPhase() {
		runAttackGUI();
	}

	private void runAttackGUI() {
		Color c1 = new Color(153, 102, 0);
		Color c2 = new Color(0, 0, 153);
	
		JFrame frame = new JFrame("FORTRESS DEFENSE");
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
	
		JButton exit = new JButton("EXIT");
		exit.setBackground(Color.GRAY);
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		exit.setBounds(0, 0, 200, 100);		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton playerIcon = new JButton("");	
		Image pc = new ImageIcon(this.getClass().getResource("characters/my_character1.png")).getImage();	
		playerIcon.setIcon(new ImageIcon(pc));	
		
		JButton playerIcon1 = new JButton("");	
		Image pc1 = new ImageIcon(this.getClass().getResource("characters/my_character2.png")).getImage();	
		playerIcon1.setIcon(new ImageIcon(pc1));	
		
		JButton playerIcon2 = new JButton("");	
		Image pc2 = new ImageIcon(this.getClass().getResource("characters/my_character3.png")).getImage();	
		playerIcon2.setIcon(new ImageIcon(pc2));	
		
		JButton playerIcon3 = new JButton("");
		Image pc3 = new ImageIcon(this.getClass().getResource("characters/my_character4.png")).getImage();	
		playerIcon3.setIcon(new ImageIcon(pc3));	
		
		JButton playerIcon4 = new JButton("");	
		Image pc4 = new ImageIcon(this.getClass().getResource("characters/my_character5.png")).getImage();	
		playerIcon4.setIcon(new ImageIcon(pc4));
		
		JLabel p2 = new JLabel("PLAYER 2");
		p2.setForeground(c2);
		p2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp2 = new JLabel("Health Points : 40");
		hp2.setForeground(c2);
		hp2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar hb2 = new JProgressBar();
		hb2.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb2.setForeground(new Color(50, 205, 50));
		hb2.setMaximum(50);
		hb2.setBackground(Color.DARK_GRAY);
		hb2.setValue(40);
		
		JLabel p1 = new JLabel("PLAYER 1");
		p1.setForeground(c2);
		p1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp1 = new JLabel("Health Points : 42");
		hp1.setForeground(c2);
		hp1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar hb1 = new JProgressBar();
		hb1.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb1.setForeground(new Color(50, 205, 50));
		hb1.setMaximum(50);
		hb1.setBackground(Color.DARK_GRAY);
		hb1.setValue(42);
		
		lpanel.add(exit);
		lpanel.add(Box.createVerticalStrut(70));
		lpanel.add(playerIcon2);
		lpanel.add(p2);
		lpanel.add(hp2);
		lpanel.add(hb2);
		lpanel.add(Box.createVerticalStrut(80));
		lpanel.add(playerIcon1);
		lpanel.add(p1);
		lpanel.add(hp1);
		lpanel.add(hb1);
		
		JLabel p3 = new JLabel("PLAYER 3");
		p3.setForeground(c2);
		p3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp3 = new JLabel("Health Points : 35");
		hp3.setForeground(c2);
		hp3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar hb3 = new JProgressBar();
		hb3.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb3.setForeground(new Color(50, 205, 50));
		hb3.setMaximum(50);
		hb3.setBackground(Color.DARK_GRAY);
		hb3.setValue(35);
		
		JLabel p4 = new JLabel("PLAYER 4");
		p4.setForeground(c2);
		p4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp4 = new JLabel("Health Points : 32");
		hp4.setForeground(c2);
		hp4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar hb4 = new JProgressBar();
		hb4.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb4.setForeground(new Color(50, 205, 50));
		hb4.setMaximum(50);
		hb4.setBackground(Color.DARK_GRAY);
		hb4.setValue(32);
		
		rpanel.add(Box.createVerticalStrut(120));
		rpanel.add(playerIcon3);
		rpanel.add(p3);
		rpanel.add(hp3);
		rpanel.add(hb3);
		rpanel.add(Box.createVerticalStrut(80));
		rpanel.add(playerIcon4);
		rpanel.add(p4);
		rpanel.add(hp4);
		rpanel.add(hb4);
		
		JLabel attack = new JLabel("ATTACK PHASE");
		attack.setForeground(c2);
		attack.setFont(new Font("Times New Roman", Font.BOLD, 50));
		attack.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel time = new JLabel("30 Seconds Left");
		time.setForeground(c2);
		time.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp = new JLabel("HEALTH POINTS: 15");
		hp.setForeground(c2);
		hp.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp.setHorizontalAlignment(SwingConstants.CENTER);
		
		JProgressBar hb = new JProgressBar();
		hb.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb.setForeground(new Color(50, 205, 50));
		hb.setMaximum(20);
		hb.setBackground(Color.DARK_GRAY);
		hb.setValue(15);
		hb.setBounds(250, 252, 508, 46);
		
		tpanel.add(attack);
		tpanel.add(time);
		tpanel.add(Box.createVerticalStrut(20));
		tpanel.add(Box.createHorizontalStrut(500));
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
		Image baxeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/battleAxe.PNG")).getImage();
		card3.setIcon(new ImageIcon(baxeImg));
		card3.setBackground(c1);
		spanel.add(card3);
		
		JButton card4 = new JButton("");
		Image mace = new ImageIcon(this.getClass().getResource("Images/attackIMG/mace.PNG")).getImage();
		card4.setIcon(new ImageIcon(mace));
		card4.setBackground(c1);
		spanel.add(card4);
		
		JButton card5 = new JButton("");
		Image stick = new ImageIcon(this.getClass().getResource("Images/attackIMG/stick.PNG")).getImage();
		card5.setIcon(new ImageIcon(stick));
		card5.setBackground(c1);
		spanel.add(card5);
		
		JButton card6 = new JButton("");
		Image sword = new ImageIcon(this.getClass().getResource("Images/attackIMG/sword.PNG")).getImage();
		card6.setIcon(new ImageIcon(sword));
		card6.setBackground(c1);
		spanel.add(card6);
		
		JButton card7 = new JButton("");
		card7.setIcon(new ImageIcon(axeImg));
		card7.setBackground(c1);
		spanel.add(card7);
		
		JButton card8 = new JButton("");
		card8.setIcon(new ImageIcon(baxeImg));
		card8.setBackground(c1);
		spanel.add(card8);
		
		card1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card1Clicked = true;
			}
		});
		
		card2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card2Clicked = true;
			}
		});
		
		card3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card3Clicked = true;
			}
		});
		
		card4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card4Clicked = true;
			}
		});
		
		card5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card5Clicked = true;
			}
		});
		
		card6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card6Clicked = true;
			}
		});
		
		card7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card7Clicked = true;
			}
		});
		
		card8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				card8Clicked = true;
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
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - maceValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - stickValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - swordValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - axeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp1.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp1.setText(text);
					hb1.setValue(healthAfterAttack);
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
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - maceValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - stickValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - swordValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - axeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp2.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp2.setText(text);
					hb2.setValue(healthAfterAttack);
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
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - maceValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - stickValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - swordValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - axeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp3.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp3.setText(text);
					hb3.setValue(healthAfterAttack);
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
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card2Clicked == true) {
					card2.setIcon(null);
					card2.setVisible(false);
					card2Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - crossbowValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card3Clicked == true) {
					card3.setIcon(null);
					card3.setVisible(false);
					card3Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card4Clicked == true) {
					card4.setIcon(null);
					card4.setVisible(false);
					card4Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - maceValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card5Clicked == true) {
					card5.setIcon(null);
					card5.setVisible(false);
					card5Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - stickValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card6Clicked == true) {
					card6.setIcon(null);
					card6.setVisible(false);
					card6Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - swordValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card7Clicked == true) {
					card7.setIcon(null);
					card7.setVisible(false);
					card7Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - axeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
				else if(card8Clicked == true) {
					card8.setIcon(null);
					card8.setVisible(false);
					card8Clicked = false;
					int textValue = Integer.parseInt(hp4.getText().substring(16));
					int healthAfterAttack = textValue - battleAxeValue;
					String text = "Health Points : " + Integer.toString(healthAfterAttack);
					hp4.setText(text);
					hb4.setValue(healthAfterAttack);
				}
			}
		});
		
		frame.getContentPane().add(tpanel, BorderLayout.CENTER);
		frame.getContentPane().add(spanel, BorderLayout.SOUTH);
		frame.getContentPane().add(rpanel, BorderLayout.EAST);
		frame.getContentPane().add(lpanel, BorderLayout.WEST);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setBounds(0,0,screenSize.width, screenSize.height - 50);
	    frame.setVisible(true);
	}

}