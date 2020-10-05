package code;

/**
 * Model containing all Information for the Server from the UI, updated as needed in future
 * @author Andrew Jank and https://stackoverflow.com/questions/12337986/binding-of-jtext-fields-value-to-info-class
 */
public class ServerModel {
	
	private String hostGameName;
	private AccessType accessType;
	private String password;
	private Integer maxPlayers;
	private Integer currPlayers;
	
	public ServerModel() {
		hostGameName = "";
		accessType = AccessType.Public;
		password = "";
		maxPlayers = 6;
		currPlayers = 1;
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
	
	public void SetCurrentPlayers(Integer num) {
		currPlayers = num;
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
		return currPlayers;
	}
	
    @Override
    public String toString() {
        return "ModelObject [hostGameName=" + hostGameName + ", password=" + password + ", numPlayers=" + maxPlayers + ", accessType=" + accessType + "]";
    }
}
