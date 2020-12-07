package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import code.FX_Handler;
import code.Game;
import code.ServerModel;
import code.Player;
import gui.drawPhase;
import gui.drawPhaseOtherPlayer;

public class StartGameButtonHandler implements ActionListener {

	private JPanel create;
	private JFrame mainFrame;
	private StartServerButtonHandler startButtonHandler;
	
	public StartGameButtonHandler(JPanel createPanel, JFrame mainFrame, StartServerButtonHandler btnHandler) {
		create = createPanel;
		this.mainFrame = mainFrame;
		this.startButtonHandler = btnHandler;
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
		for (int i = 0; i < startButtonHandler.getModel().getPlayers().size(); i++) {
			if (!startButtonHandler.getModel().getPlayers().get(i).getReady()) {
				JOptionPane.showMessageDialog(mainFrame, "A game can only be started when all players are ready.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (startButtonHandler.getModel().GetCurrentPlayers() == startButtonHandler.getModel().GetMaxPlayers()) {
			startButtonHandler.getTCPServer().start();
			create.setVisible(false);
		}else {
			int dif = startButtonHandler.getModel().GetMaxPlayers() - startButtonHandler.getModel().GetCurrentPlayers();
			JOptionPane.showMessageDialog(mainFrame, dif + " Players needed to start a game.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		}

	}

}
