package gui.CreateGame;

import java.awt.Panel;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import code.FX_Handler;
import code.ServerModel;

public class SpinnerHandler implements ChangeListener {

	private ServerModel serverModel;
	private Panel playerPanel;
	
	public SpinnerHandler(ServerModel model, Panel panel) {
		serverModel = model;
		playerPanel = panel;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		//add FX
		FX_Handler button = new FX_Handler();
		try {
			button.misc_fx("button");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
			ex.printStackTrace();
		}
        JSpinner spinner = (JSpinner) e.getSource();
		serverModel.SetMaxPlayers((Integer) spinner.getValue());
		for (int i = serverModel.GetMaxPlayers(); i < 6; i++) {
			if (i > serverModel.GetMaxPlayers()) {
				playerPanel.getComponent(i - 1).setVisible(false);
			}else {
				playerPanel.getComponent(i - 1).setVisible(true);
			}
		}
	}

}
