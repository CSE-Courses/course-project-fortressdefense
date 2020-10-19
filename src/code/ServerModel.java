package code;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextField;

import code.Deck.Player;

/**
 * Model containing all Information for the Server from the UI, updated as needed in future
 * @author Andrew Jank and https://stackoverflow.com/questions/12337986/binding-of-jtext-fields-value-to-info-class
 */
public class ServerModel {
	
	private String hostGameName;
	private AccessType accessType;
	private String password;
	private Integer maxPlayers;
	private ArrayList<Player> players;
	private ArrayList<JTextField> textFields;
	
	public ServerModel(ArrayList<Player> _players) {
		hostGameName = "";
		accessType = AccessType.Public;
		password = "";
		maxPlayers = 6;
		players = _players;
	}
	
	public void SetHostName(String hostname) {
		hostGameName = hostname;
	}
	
	public void SetAccessType(String accessType) {
		if (accessType.equals("Public")) {
			this.accessType = AccessType.Public;
		}else if(accessType.equals("Private")) {
			this.accessType = AccessType.Private;
		}
	}
	
	public void SetPassword(String pass) {
		password = pass;
	}
	
	public void SetMaxPlayers(Integer num) {
		maxPlayers = num;
	}
	
	public String GetHostName() {
		return hostGameName;
	}
	
	public AccessType GetAccessType() {
		return accessType;
	}
	
	public String GetPassword() {
		return password;
	}
	
	public Integer GetMaxPlayers() {
		return maxPlayers;
	}
	
	public Integer GetCurrentPlayers() {
		return players.size();
	}
	
	public void SetPlayerTextFields(ArrayList<JTextField> _fields) {
		textFields = _fields;
		UpdatePlayerTextFields();
	}
	
    @Override
    public String toString() {
        return "ModelObject [hostGameName=" + hostGameName + ", password=" + password + ", numPlayers=" + maxPlayers + ", accessType=" + accessType + "]";
    }
    
    public void UpdatePlayerTextFields() {
    	for (int i = 0; i < players.size(); i++) {
        	if (i == 0) {
            	textFields.get(i).setText(i+1 + ") " + players.get(i).PlayerName);
            	textFields.get(i).setBackground(Color.YELLOW.darker());
        	}else {
            	textFields.get(i).setText(i+1 + ") " + players.get(i).PlayerName);
    			if (players.get(i).getReady()) {
    				textFields.get(i).setBackground(Color.GREEN.darker());
    			}else {
    				textFields.get(i).setBackground(Color.RED.darker());
    			}
        	}
    	}
    }
}
