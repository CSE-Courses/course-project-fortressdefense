package gui.CreateGame;

import java.awt.Panel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        JSpinner spinner = (JSpinner) e.getSource();
		serverModel.SetMaxPlayers((Integer) spinner.getValue());
		for (int i = serverModel.GetMaxPlayers(); i < 7; i++) {
			if (i > serverModel.GetMaxPlayers()) {
				playerPanel.getComponent(i - 1).setVisible(false);
			}else {
				playerPanel.getComponent(i - 1).setVisible(true);
			}
		}
	}

}
