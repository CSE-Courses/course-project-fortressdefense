package gui.CreateGame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;

import code.AccessType;
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
		if (e.getStateChange() == ItemEvent.SELECTED) {
			serverModel.SetAccessType((String)e.getItem());
			if (serverModel.GetAccessType() == AccessType.Public) {
				passTextField.setEnabled(false);
			}
			else if (serverModel.GetAccessType() == AccessType.Private) {
				passTextField.setEnabled(true);
			}
		}
		
	}

}
