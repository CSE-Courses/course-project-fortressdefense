package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;

import javax.swing.*;

import code.GameConstants;
import code.Player;
import code.ServerModel;
import code.Socket.BroadcastGame;

public class EndServerButtonHandler implements ActionListener {
	
	private ServerModel model;
	private JButton start;
	private JButton end;
	private JTextField name;
	private JSpinner numSpinner;
	private JTextField password;
	private JComboBox<String> choice;
	private StartServerButtonHandler startHandler;
	
	public EndServerButtonHandler(ServerModel model, JButton startButton, JButton endButton, 
			JTextField textField, JSpinner spinner, JTextField textField_1, JComboBox<String> choice, StartServerButtonHandler handler) {
		this.model = model;
		start = startButton;
		end = endButton;
		name = textField;
		numSpinner = spinner;
		password = textField_1;
		this.choice = choice;
		startHandler = handler;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		start.setEnabled(true);
		end.setEnabled(false);
		name.setEditable(true);
		numSpinner.setEnabled(true);
		password.setEditable(true);
		this.choice.setEnabled(true);
		
		startHandler.getUDPServer().close();
		startHandler.getTCPServer().close();
	}

}
