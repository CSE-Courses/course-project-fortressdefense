package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import code.FX_Handler;
import gui.CreateGame.CreateGame;

public class CreateGameButtonHandler implements ActionListener {

	private JFrame frame;
	private JPanel main;
	
	public CreateGameButtonHandler(JFrame mainFrame, JPanel mainPanel) {
		frame = mainFrame;
		main = mainPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//add FX
		FX_Handler button = new FX_Handler();
		try {
			button.misc_fx("button");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			ex.printStackTrace();
		}

		String playerName = (String)JOptionPane.showInputDialog(frame, "Enter Fortress Name: ", "Fortress Defense", JOptionPane.PLAIN_MESSAGE);
		if (playerName != null) {
			while (playerName.equals("") || playerName.contains("/") || playerName.contains(" ")) {
				JOptionPane.showMessageDialog(main, "Fortress Name cannot be empty or contain /.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
				playerName = (String)JOptionPane.showInputDialog(frame, "Enter Fortress Name: ", "Fortress Defense", JOptionPane.PLAIN_MESSAGE);
			}
			
			CreateGame createGameScreen = new CreateGame(playerName, main, frame);
			frame.add(createGameScreen);
			main.setVisible(false);
		}
	}

}
