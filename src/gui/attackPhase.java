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
		
		JLabel p2 = new JLabel("PLAYER 2");
		p2.setForeground(c2);
		p2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp2 = new JLabel("Health Points : 10");
		hp2.setForeground(c2);
		hp2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel p1 = new JLabel("PLAYER 1");
		p1.setForeground(c2);
		p1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp1 = new JLabel("Health Points : 12");
		hp1.setForeground(c2);
		hp1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lpanel.add(exit);
		lpanel.add(Box.createVerticalStrut(200));
		lpanel.add(p2);
		lpanel.add(Box.createVerticalStrut(20));
		lpanel.add(hp2);
		lpanel.add(Box.createVerticalStrut(200));
		lpanel.add(p1);
		lpanel.add(Box.createVerticalStrut(20));
		lpanel.add(hp1);
		lpanel.add(Box.createVerticalStrut(20));
		
		JLabel p3 = new JLabel("PLAYER 3");
		p3.setForeground(c2);
		p3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp3 = new JLabel("Health Points : 5");
		hp3.setForeground(c2);
		hp3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel p4 = new JLabel("PLAYER 4");
		p4.setForeground(c2);
		p4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		p4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel hp4 = new JLabel("Health Points : 2");
		hp4.setForeground(c2);
		hp4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		hp4.setHorizontalAlignment(SwingConstants.CENTER);
		
		rpanel.add(Box.createVerticalStrut(200));
		rpanel.add(p3);
		rpanel.add(Box.createVerticalStrut(20));
		rpanel.add(hp3);
		rpanel.add(Box.createVerticalStrut(200));
		rpanel.add(p4);
		rpanel.add(Box.createVerticalStrut(20));
		rpanel.add(hp4);
		
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
		
		tpanel.add(attack);
		tpanel.add(Box.createVerticalStrut(20));
		tpanel.add(time);
		tpanel.add(Box.createVerticalStrut(100));
		tpanel.add(hp);
		tpanel.add(Box.createVerticalStrut(100));
		
		JButton pass = new JButton("PASS");
		pass.setBackground(c1);
		pass.setForeground(c2);
		pass.setFont(new Font("Arial", Font.BOLD, 25));
		pass.setBounds(1000, 300, 300, 100);
		tpanel.add(pass);
		tpanel.add(Box.createVerticalStrut(50));
		
		JButton go = new JButton("GO!");
		go.setBackground(c1);
		go.setForeground(c2);
		go.setFont(new Font("Arial", Font.BOLD, 25));
		go.setBounds(850, 500, 300, 100);
		tpanel.add(go);
		
		JButton card1 = new JButton("");
		Image axeImg = new ImageIcon(this.getClass().getResource("Images/axe.png")).getImage();
		card1.setIcon(new ImageIcon(axeImg));
		card1.setBackground(c1);
		spanel.add(card1);
		
		JButton card2 = new JButton("");
		card2.setIcon(new ImageIcon(axeImg));
		card2.setBackground(c1);
		spanel.add(card2);
		
		JButton card3 = new JButton("");
		Image baxeImg = new ImageIcon(this.getClass().getResource("Images/battleAxe.png")).getImage();
		card3.setIcon(new ImageIcon(baxeImg));
		card3.setBackground(c1);
		spanel.add(card3);
		
		JButton card4 = new JButton("");
		card4.setIcon(new ImageIcon(baxeImg));
		card4.setBackground(c1);
		spanel.add(card4);
		
		JButton card5 = new JButton("");
		Image gate = new ImageIcon(this.getClass().getResource("Images/reinforcedGate.png")).getImage();
		card5.setIcon(new ImageIcon(gate));
		card5.setBackground(c1);
		spanel.add(card5);
		
		JButton card6 = new JButton("");
		card6.setIcon(new ImageIcon(gate));
		card6.setBackground(c1);
		spanel.add(card6);
		
		JButton card7 = new JButton("");
		Image scout = new ImageIcon(this.getClass().getResource("Images/scout.png")).getImage();
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
		frame.setSize(1920, 1020);
		frame.setVisible(true);
	}

}