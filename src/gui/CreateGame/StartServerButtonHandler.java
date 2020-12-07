package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executor;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import code.FX_Handler;
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
	private JFrame mainFrame;
	private JPanel mainPanel;
	
	public StartServerButtonHandler(ServerModel model, JButton startButton, JButton endButton,JTextField textField, JSpinner spinner, 
			JTextField textField_1, JComboBox<String> choice, Executor executor, Executor tcpServer, JPanel panel, JTextArea chatBox, JFrame mainFrame, JPanel mainPanel) {
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
		this.mainFrame = mainFrame;
		this.mainPanel = mainPanel;
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
			
			server = new Server(GameConstants.tcpPort, serverModel, chat, mainFrame, mainPanel);
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
