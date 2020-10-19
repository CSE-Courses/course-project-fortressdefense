package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.ServerModel;

public class EndServerButtonHandler implements ActionListener {
	
	private ServerModel model;
	private JButton start;
	private JButton end;
	private JTextField name;
	private JSpinner numSpinner;
	private JTextField password;
	private JComboBox<String> choice;
	
	public EndServerButtonHandler(ServerModel model, JButton startButton, JButton endButton, JTextField textField, JSpinner spinner, JTextField textField_1, JComboBox<String> choice) {
		this.model = model;
		start = startButton;
		end = endButton;
		name = textField;
		numSpinner = spinner;
		password = textField_1;
		this.choice = choice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		start.setEnabled(true);
		end.setEnabled(false);
		name.setEditable(true);
		numSpinner.setEnabled(true);
		password.setEditable(true);
		this.choice.setEnabled(true);
		//TODO: End server broadcast for server, all players will be removed from game
	}

}
