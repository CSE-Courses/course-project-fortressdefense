package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import code.FX_Handler;
import code.Game;
import code.ServerModel;
import code.Socket.Client;
import code.Player;
import gui.drawPhase;

public class SendButtonHandler implements ActionListener {

	private JTextField _message;
	private StartServerButtonHandler startBtnHandler;
	
	public SendButtonHandler(JTextField message, StartServerButtonHandler btnhandler) {
		_message = message;
		this.startBtnHandler = btnhandler;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//add FX
		FX_Handler button = new FX_Handler();
		try {
			button.selected_card_fx("Scout");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			ex.printStackTrace();
		}

		String input = _message.getText();
        if (!input.equals("")) {
            startBtnHandler.getTCPServer().message(input);
            _message.setText("");
        }

	}

}
