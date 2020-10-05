package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateGameBackButtonHandler implements ActionListener {

	private JPanel create;
	private JPanel main;
	
	public CreateGameBackButtonHandler(JPanel createPanel, JPanel mainPanel) {
		create = createPanel;
		main = mainPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.setVisible(true);
		create.setVisible(false);
	}

}
