package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.Game;
import code.ServerModel;
import code.Deck.Player;
import gui.drawPhase;

public class StartGameButtonHandler implements ActionListener {

	private JPanel create;
	private JFrame mainFrame;
	private ServerModel serverModel;
	private Game game;
	
	public StartGameButtonHandler(JPanel createPanel, JFrame mainFrame, ServerModel model, Game curGame) {
		create = createPanel;
		this.mainFrame = mainFrame;
		serverModel = model;
		game = curGame;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < serverModel.GetMaxPlayers(); i++) {
			if (!game.PlayerList.get(i).getReady()) {
				JOptionPane.showMessageDialog(mainFrame, "A game can only be started when all players are ready.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (serverModel.GetCurrentPlayers() >= 2) {
			this.mainFrame.add(new drawPhase(game).GetPanel());
			create.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(mainFrame, "Two (2) Players needed to start a game.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		}

	}

}
