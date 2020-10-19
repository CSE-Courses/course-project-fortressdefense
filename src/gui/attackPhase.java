package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class attackPhase {
	
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
		Image pc = new ImageIcon(this.getClass().getResource("/fd/my_character1.png")).getImage();
		playerIcon.setIcon(new ImageIcon(pc));

		JButton playerIcon1 = new JButton("");
		Image pc1 = new ImageIcon(this.getClass().getResource("/fd/my_character2.png")).getImage();
		playerIcon1.setIcon(new ImageIcon(pc1));

		JButton playerIcon2 = new JButton("");
		Image pc2 = new ImageIcon(this.getClass().getResource("/fd/my_character3.png")).getImage();
		playerIcon2.setIcon(new ImageIcon(pc2));

		JButton playerIcon3 = new JButton("");
		Image pc3 = new ImageIcon(this.getClass().getResource("/fd/my_character4.png")).getImage();
		playerIcon3.setIcon(new ImageIcon(pc3));

		JButton playerIcon4 = new JButton("");
		Image pc4 = new ImageIcon(this.getClass().getResource("/fd/my_character5.png")).getImage();
		playerIcon4.setIcon(new ImageIcon(pc4));

		JLabel p2 = new JLabel("PLAYER 2");
		p2.setForeground(c2);
		p2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp2 = new JLabel("Health Points : 10");
		hp2.setForeground(c2);
		hp2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp2.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar hb2 = new JProgressBar();
		hb2.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb2.setForeground(new Color(50, 205, 50));
		hb2.setMaximum(20);
		hb2.setBackground(Color.DARK_GRAY);
		hb2.setValue(10);
		
		JLabel p1 = new JLabel("PLAYER 1");
		p1.setForeground(c2);
		p1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp1 = new JLabel("Health Points : 12");
		hp1.setForeground(c2);
		hp1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp1.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar hb1 = new JProgressBar();
		hb1.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb1.setForeground(new Color(50, 205, 50));
		hb1.setMaximum(20);
		hb1.setBackground(Color.DARK_GRAY);
		hb1.setValue(12);

		lpanel.add(exit);
		lpanel.add(Box.createVerticalStrut(70));
		lpanel.add(playerIcon1);
		lpanel.add(p2);
		lpanel.add(hp2);
		lpanel.add(hb2);
		lpanel.add(Box.createVerticalStrut(80));
		lpanel.add(playerIcon);
		lpanel.add(p1);
		lpanel.add(hp1);
		lpanel.add(hb1);


		JLabel p3 = new JLabel("PLAYER 3");
		p3.setForeground(c2);
		p3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp3 = new JLabel("Health Points : 5");
		hp3.setForeground(c2);
		hp3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp3.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar hb3 = new JProgressBar();
		hb3.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb3.setForeground(new Color(50, 205, 50));
		hb3.setMaximum(20);
		hb3.setBackground(Color.DARK_GRAY);
		hb3.setValue(5);
		
		JLabel p4 = new JLabel("PLAYER 4");
		p4.setForeground(c2);
		p4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp4 = new JLabel("Health Points : 2");
		hp4.setForeground(c2);
		hp4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp4.setHorizontalAlignment(SwingConstants.CENTER);

		JProgressBar hb4 = new JProgressBar();
		hb4.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		hb4.setForeground(new Color(50, 205, 50));
		hb4.setMaximum(20);
		hb4.setBackground(Color.DARK_GRAY);
		hb4.setValue(2);

		rpanel.add(Box.createVerticalStrut(120));
		rpanel.add(playerIcon4);
		rpanel.add(p3);
		rpanel.add(hp3);
		rpanel.add(hb3);
		rpanel.add(Box.createVerticalStrut(80));
		rpanel.add(playerIcon3);
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
		tpanel.add(Box.createVerticalStrut(20));
		tpanel.add(Box.createHorizontalStrut(500));
		tpanel.add(playerIcon2);
		tpanel.add(time);
		tpanel.add(hp);
		tpanel.add(hb);
		
		JButton card1 = new JButton("");
		Image axeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/axe.png")).getImage();
		card1.setIcon(new ImageIcon(axeImg));
		card1.setBackground(c1);
		spanel.add(card1);

		JButton card2 = new JButton("");
		card2.setIcon(new ImageIcon(axeImg));
		card2.setBackground(c1);
		spanel.add(card2);
		
		JButton card3 = new JButton("");
		Image baxeImg = new ImageIcon(this.getClass().getResource("Images/attackIMG/battleAxe.png")).getImage();
		card3.setIcon(new ImageIcon(baxeImg));
		card3.setBackground(c1);
		spanel.add(card3);
		
		JButton card4 = new JButton("");
		card4.setIcon(new ImageIcon(baxeImg));
		card4.setBackground(c1);
		spanel.add(card4);
		
		JButton card5 = new JButton("");
		Image gate = new ImageIcon(this.getClass().getResource("Images/defenseIMG/reinforcedGate.png")).getImage();
		card5.setIcon(new ImageIcon(gate));
		card5.setBackground(c1);
		spanel.add(card5);
		
		JButton card6 = new JButton("");
		card6.setIcon(new ImageIcon(gate));
		card6.setBackground(c1);
		spanel.add(card6);
		
		JButton card7 = new JButton("");
		Image scout = new ImageIcon(this.getClass().getResource("Images/specialIMG/scout.png")).getImage();
		card7.setIcon(new ImageIcon(scout));
		card7.setBackground(c1);
		spanel.add(card7);
		
		JButton card8 = new JButton("");
		card8.setIcon(new ImageIcon(scout));
		card8.setBackground(c1);
		spanel.add(card8);
		
		frame.getContentPane().add(tpanel, BorderLayout.CENTER);
		frame.getContentPane().add(spanel, BorderLayout.SOUTH);
		frame.getContentPane().add(rpanel, BorderLayout.EAST);
		frame.getContentPane().add(lpanel, BorderLayout.WEST);
    
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height - 50);
		frame.setVisible(true);
	}

}