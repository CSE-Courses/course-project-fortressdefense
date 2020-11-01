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
	
	public StartServerButtonHandler(ServerModel model, JButton startButton, JButton endButton, 
			JTextField textField, JSpinner spinner, JTextField textField_1, JComboBox<String> choice, Executor executor, Executor tcpServer) {
		serverModel = model;
		start = startButton;
		end = endButton;
		name = textField;
		numSpinner = spinner;
		password = textField_1;
		this.choice = choice;
		this.executor = executor;
		this.tcpServer = tcpServer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		start.setEnabled(false);
		end.setEnabled(true);
		name.setEditable(false);
		numSpinner.setEnabled(false);
		password.setEditable(false);
		this.choice.setEnabled(false);
		
		
		// Server Broadcast
		Player host = serverModel.getPlayers().get(0);
		serverModel.getPlayers().clear();
		serverModel.getPlayers().add(host);
		broadcast = new BroadcastGame(GameConstants.udpPort, serverModel);
		executor.execute(broadcast);
		
		server = new Server(GameConstants.tcpPort, serverModel);
		tcpServer.execute(server);		
	}
	
	public BroadcastGame getUDPServer() {
		return this.broadcast;
	}
	
	public Server getTCPServer() {
		return this.server;
	}

}
