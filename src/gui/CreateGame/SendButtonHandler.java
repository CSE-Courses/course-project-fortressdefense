package gui.CreateGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.*;

import code.Game;
import code.ServerModel;
import code.Player;
import gui.drawPhase;

public class SendButtonHandler implements ActionListener {

	private JTextField _message;
	private JTextArea _chat;
	private String _host;
	
	public SendButtonHandler(JTextField message, JTextArea chat, String host) {
		_message = message;
		_chat = chat;
		_host = host;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = _message.getText();
        if (!input.equals("")) {
            Time time = new Time(System.currentTimeMillis());
            _chat.setText(_chat.getText() + time + "\n" + _host +": " + input + "\n\n");
            _message.setText("");
        }

	}

}
