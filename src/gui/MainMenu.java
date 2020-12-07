package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import code.FX_Handler;

import code.Game;
import gui.CreateGame.CreateGame;

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
        Image image = cardImage.getImage();
        Image newimg = image.getScaledInstance(500, 350,  java.awt.Image.SCALE_SMOOTH);  
        cardImage = new ImageIcon(newimg);
        
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
    
		JPanel panel = new JPanel();

		JLabel logo = createDisplayImage("Images/FD.png");
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(logo);

	    JButton cgb = new JButton("Create Game");
	    cgb.setAlignmentX(Component.CENTER_ALIGNMENT);
	    cgb.setSize(new Dimension(150, 75));
	    cgb.setBackground(c1);
	    panel.add(cgb);
	    panel.add(Box.createVerticalStrut(25));
	    cgb.addActionListener(new CreateGameButtonHandler(frame, panel));
	    
	    JButton jgb = new JButton("Join Game");
	    jgb.setAlignmentX(Component.CENTER_ALIGNMENT);
	    jgb.setBackground(c1);
	    panel.add(jgb);
	    panel.add(Box.createVerticalStrut(25));
	    
	    jgb.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					FX_Handler button = new FX_Handler();
					try {
						button.misc_fx("button");
					} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
						System.out.println("FX file not found or wrong file name");
					}
					String playerName = (String)JOptionPane.showInputDialog(frame, "Enter Fortress Name: ", "Fortress Defense", JOptionPane.PLAIN_MESSAGE);
					if (playerName != null) {
						while (playerName.equals("") || playerName.contains("/") || playerName.contains(" ")) {
							JOptionPane.showMessageDialog(frame, "Fortress Name cannot be empty or contain /.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
							playerName = (String)JOptionPane.showInputDialog(frame, "Enter Fortress Name: ", "Fortress Defense", JOptionPane.PLAIN_MESSAGE);
						}
						frame.add(new Join_Game(playerName, panel, frame).getPanel());
						panel.setVisible(false);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				FX_Handler button = new FX_Handler();
				try {
					button.misc_fx("button");
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
					System.out.println("FX file not found or wrong file name");
				}
				rulesScreen();
			}
	    });
	    
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    frame.getContentPane().add(panel);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setBounds(0,0,screenSize.width, screenSize.height - 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
	}
	
	public void rulesScreen() {
		JFrame frame = new JFrame("Rules");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0,screenSize.width - 15, screenSize.height - 50);
		
	    textArea.setText("FORTRESS DEFENSE\n\n"
	    		+ "Objective: Build up health points by drawing health point cards in order to strengthen your fortress. "
	    		+ "Build up an arsenal by drawing attack cards. "
	    		+ "Use the attack cards to destroy all other fortresses.\n\n\n" 
	    		+ "There are 2 phases of the game, the draw phase and the attack phase.\n\n"
	    		+ "The Draw Phase: \n This phase lasts 8 rounds. During which, each player chooses to draw a card from one of the two decks (Attack or Defense),\n"
	    		+ "discard a card in their hand, or pass (do nothing).\n"
	    		+ " After the last player's turn on the 8th round, all players display all their cards that have: (Show at the start of the attack round). Max 4 from each deck (attack/health).\n\n\n"
	    		+ "The Attack Phase: \n This phase lasts until every player uses all playable cards in their hand, or until there is only 1 player left with positive health points for their fortress.\n"
	    		+ " During this phase a player can choose to play an attack or special card, or pass (do nothing).\n"
	    		+ " If more than 1 player is left, the Draw phase starts again for another 5 rounds.\n"
	    		+ " During this second draw phase however, any health point cards picked up are immediately discarded and the player's turn ends.\n"
	    		+ " This way, no player's fortress increases or decreases in health points.\n"
	    		+ " After conclusion of the second Draw phase, the second Attack phase commences.\n"
	    		+ " If by the end of the third attack phase there are more than 1 player left, the player with the greatest health points wins.\n"
	    		+ " If there is a tie between players by the end of the game, those players must fight to the death in mortal combat in real life (NOT REALLY!!!), those players must take turns drawing from the deck."
	    		);

		Font font = new Font("Times New Roman", Font.BOLD, 20);
		textArea.setFont(font);
		textArea.setBackground(new Color(153, 102, 0));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(textArea);
		frame.getContentPane().add(panel);

		panel.setBackground(new Color(153, 102, 0));
		frame.setBounds(0,0,screenSize.width, screenSize.height - 50);
		frame.setVisible(true);
	}
}

