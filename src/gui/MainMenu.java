package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class MainMenu {
	
	public MainMenu() {
		runGUI();
	}

	public JLabel createDisplayImage(String file) {
	    JLabel label = new JLabel();
	    java.net.URL imgURL = this.getClass().getResource(file);
	    
	    if (imgURL == null) {
	      throw new IllegalArgumentException("Couldn't find file: " + file);
	    }
	    
	    ImageIcon cardImage = new ImageIcon(imgURL);    
	    label.setIcon(cardImage);
	    Dimension d = new Dimension(cardImage.getIconWidth() + 10, cardImage.getIconHeight() + 10);
	    label.setSize(d);
	    label.setPreferredSize(d);
	    label.setMaximumSize(d);
	    label.setMinimumSize(d);
	    return label;
	}
	
	public void runGUI() {
		JFrame frame = new JFrame("FORTRESS DEFENSE");
		
		Color c1 = new Color(153, 102, 0);
		//Color c2 = new Color(0, 0, 153);
		
	    JPanel panel = new JPanel();
	    
	    JLabel logo = createDisplayImage("/fd/FD.png");
	    logo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(logo);
	    panel.add(Box.createVerticalStrut(100));
	    
	    JButton cgb = new JButton("Create Game");
	    cgb.setAlignmentX(Component.CENTER_ALIGNMENT);
	    cgb.setSize(new Dimension(150, 75));
	    cgb.setBackground(c1);
	    panel.add(cgb);
	    panel.add(Box.createVerticalStrut(25));
	    
	    JButton jgb = new JButton("Join Game");
	    jgb.setAlignmentX(Component.CENTER_ALIGNMENT);
	    jgb.setBackground(c1);
	    panel.add(jgb);
	    panel.add(Box.createVerticalStrut(25));
	    
	    jgb.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
	    });
	    
	    JButton rules = new JButton("Rules");
	    rules.setAlignmentX(Component.CENTER_ALIGNMENT);
	    rules.setBackground(c1);
	    panel.add(rules);
	    panel.add(Box.createVerticalStrut(25));
	    
	    rules.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rulesScreen();
			}
	    });
		jgb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Join_Game jg = new Join_Game();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	    
	    JButton draw = new JButton("Draw Phase");
	    draw.setAlignmentX(Component.CENTER_ALIGNMENT);
	    draw.setBackground(c1);
	    panel.add(draw);
	    panel.add(Box.createVerticalStrut(25));
	    
	    draw.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//drawPhase dp = new drawPhase();
			}
	    });
	    
	    JButton attack = new JButton("Attack Phase");
	    attack.setAlignmentX(Component.CENTER_ALIGNMENT);
	    attack.setBackground(c1);
	    panel.add(attack);
	    panel.add(Box.createVerticalStrut(25));
	    
	    attack.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				attackPhase ap = new attackPhase();
			}
	    });
	    
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    frame.getContentPane().add(panel);
	    
		frame.setSize(1920, 1020);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void rulesScreen() {
		JFrame frame = new JFrame("Rules");
		
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea(100,100);
		
	    textArea.setText("\t\t\t\t\t\tFORTRESS DEFENSE\n\n"
	    		+ "Objective: Build up health points by drawing health point cards in order to strengthen your fortress. "
	    		+ "Build up an arsenal by drawing attack cards. "
	    		+ "Use the attack cards to destroy all other fortresses.\n\n\n" 
	    		+ "There are 2 phases of the game, the draw phase and the attack phase.\n\n"
	    		+ "The Draw Phase: \n This phase lasts 8 rounds. During which, each player chooses to draw a card from one of the two decks (Attack or Defense),\n"
	    		+ "discard a card in their hand, or pass (do nothing).\n"
	    		+ " After the last player’s turn on the 8th round, all players display all their cards that have: (Show at the start of the attack round). Max 4 from each deck (attack/health).\n\n\n"
	    		+ "The Attack Phase: \n This phase lasts until every player uses all playable cards in their hand, or until there is only 1 player left with positive health points for their fortress.\n"
	    		+ " During this phase a player can choose to play an attack or special card, or pass (do nothing).\n"
	    		+ " If more than 1 player is left, the Draw phase starts again for another 5 rounds.\n"
	    		+ " During this second draw phase however, any health point cards picked up are immediately discarded and the player’s turn ends.\n"
	    		+ " This way, no player’s fortress increases or decreases in health points.\n"
	    		+ " After conclusion of the second Draw phase, the second Attack phase commences.\n"
	    		+ " If by the end of the third attack phase there are more than 1 player left, the player with the greatest health points wins.\n"
	    		+ " If there is a tie between players by the end of the game, those players must fight to the death in mortal combat in real life (NOT REALLY!!!), those players must take turns drawing from the deck."
	    		);
	    
	    Font font = new Font("Times New Roman", Font.BOLD, 20);
		textArea.setFont(font);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.BLUE);
		
	    panel.add(textArea);
		frame.getContentPane().add(panel);
		
		frame.setSize(1920, 1020);
		frame.setVisible(true);
	}
}
