package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateGameBackButtonHandler implements ActionListener {

	private JPanel create;
	private JPanel main;
	private StartServerButtonHandler btnHandler;
	
	public CreateGameBackButtonHandler(JPanel createPanel, JPanel mainPanel, StartServerButtonHandler handler) {
		create = createPanel;
		main = mainPanel;
		btnHandler = handler;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.setVisible(true);
		create.setVisible(false);
		btnHandler.getUDPServer().close();
	}

}
