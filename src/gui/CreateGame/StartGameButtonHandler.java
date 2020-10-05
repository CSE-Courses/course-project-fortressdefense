package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.ServerModel;
import gui.drawPhase;

public class StartGameButtonHandler implements ActionListener {

	private JPanel create;
	private JFrame mainFrame;
	private ServerModel serverModel;
	
	public StartGameButtonHandler(JPanel createPanel, JFrame mainFrame, ServerModel model) {
		create = createPanel;
		this.mainFrame = mainFrame;
		serverModel = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (serverModel.GetCurrentPlayers() >= 2) {
			this.mainFrame.add(new drawPhase().GetPanel());
			create.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(mainFrame, "Two (2) Players needed to start a game.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		}

	}

}
