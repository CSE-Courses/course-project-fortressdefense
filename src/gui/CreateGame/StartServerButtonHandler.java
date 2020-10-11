package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.ServerModel;

public class StartServerButtonHandler implements ActionListener {

	private ServerModel serverModel;
	private JButton start;
	private JButton end;
	private JTextField name;
	private JSpinner numSpinner;
	private JTextField password;
	private JComboBox<String> choice;
	
	public StartServerButtonHandler(ServerModel model, JButton startButton, JButton endButton, JTextField textField, JSpinner spinner, JTextField textField_1, JComboBox<String> choice) {
		serverModel = model;
		start = startButton;
		end = endButton;
		name = textField;
		numSpinner = spinner;
		password = textField_1;
		this.choice = choice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		start.setEnabled(false);
		end.setEnabled(true);
		name.setEditable(false);
		numSpinner.setEnabled(false);
		password.setEditable(false);
		this.choice.setEnabled(false);
		//TODO: implement server broadcast
	}

}
