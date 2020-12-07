package gui.CreateGame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JTextField;

import code.AccessType;
import code.FX_Handler;
import code.ServerModel;

public class AccessComboHandler implements ItemListener {

	private ServerModel serverModel;
	private JTextField passTextField;
	
	public AccessComboHandler(ServerModel model, JTextField passField) {
		serverModel = model;
		passTextField = passField;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		//add FX
		FX_Handler button = new FX_Handler();
		try {
			button.misc_fx("button");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			ex.printStackTrace();
		}
		if (e.getStateChange() == ItemEvent.SELECTED) {
			serverModel.SetAccessType((String)e.getItem());
			if (serverModel.GetAccessType() == AccessType.Public) {
				passTextField.setEnabled(false);
				passTextField.setText("");
				serverModel.SetPassword("");
			}
			else if (serverModel.GetAccessType() == AccessType.Private) {
				passTextField.setEnabled(true);
			}
		}
		
	}

}
