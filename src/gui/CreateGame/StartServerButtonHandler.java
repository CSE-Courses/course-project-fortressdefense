package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;

import javax.swing.*;

import code.GameConstants;
import code.Player;
import code.ServerModel;
import code.Socket.BroadcastGame;
import code.Socket.Server;

public class StartServerButtonHandler implements ActionListener {

	private ServerModel serverModel;
	private JButton start;
	private JButton end;
	private JTextField name;
	private JSpinner numSpinner;
	private JTextField password;
	private JComboBox<String> choice;
	private BroadcastGame broadcast;
	private Executor executor;
	private Executor tcpServer;
	private Server server;
	private JPanel panel;
	private JTextArea chat;
	
	public StartServerButtonHandler(ServerModel model, JButton startButton, JButton endButton,JTextField textField, JSpinner spinner, 
			JTextField textField_1, JComboBox<String> choice, Executor executor, Executor tcpServer, JPanel panel, JTextArea chatBox) {
		serverModel = model;
		start = startButton;
		end = endButton;
		name = textField;
		numSpinner = spinner;
		password = textField_1;
		this.choice = choice;
		this.executor = executor;
		this.tcpServer = tcpServer;
		this.panel = panel;
		this.chat = chatBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (name.getText().contains("/") || password.getText().contains("/")){
			JOptionPane.showMessageDialog(panel, "Cannot use / in the game name or in password", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		}
		else if(name.getText().length() > 20){
			JOptionPane.showMessageDialog(panel, "Game name cannot be more than 20 character", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		}else {
			start.setEnabled(false);
			end.setEnabled(true);
			name.setEditable(false);
			numSpinner.setEnabled(false);
			password.setEditable(false);
			this.choice.setEnabled(false);
			
			server = new Server(GameConstants.tcpPort, serverModel, chat);
			tcpServer.execute(server);	
			
			// Server Broadcast
			broadcast = new BroadcastGame(GameConstants.udpPort, server.getModel());
			executor.execute(broadcast);
			

		}
			
	}
	
	public BroadcastGame getUDPServer() {
		return this.broadcast;
	}
	
	public Server getTCPServer() {
		return this.server;
	}
	
	public ServerModel getModel() {
		return this.serverModel;
	}

}
