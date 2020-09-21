package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu {
	JFrame frame = new JFrame("Fortress Defence");
	
	public MainMenu() {
		runGUI();
	}

	public void runGUI() {	    
	    JPanel panel1 = new JPanel();
	    JButton cgb = new JButton("Create Game");
	    panel1.add(cgb);
	    JButton jgb = new JButton("Join Game");
	    panel1.add(jgb);
	    JPanel panel3 = new JPanel();
	    JButton rules = new JButton("Rules");
	    panel3.add(rules);
	    frame.getContentPane().add(panel1, BorderLayout.NORTH);
	    frame.getContentPane().add(panel3, BorderLayout.SOUTH);

//	    ImageIcon image = new ImageIcon("/fd/FD.gif");
//	    frame.getContentPane().add(new JLabel(image));
	    
	    rules.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rulesScreen();
			}
	    });
		
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void rulesScreen() {
		frame.getContentPane().removeAll();
		frame.repaint();
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
	}
}
