package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.Join_Game;

/**
 * Author :Haohua Feng (Eddie)
 *
 * create a new room: room_info room = new room_info()
 *                    room.create( Host's name, room size, room name )
 *                                  ^String        ^Int     ^String
 *
 * join in existed room: room.join(String player's name)
 *
 * left room: room.left(String player's name)
 *
 * current_size() returns the number of players in this room
 *
 * my_status(status): change my status between "Ready" and "Waiting".
 *                    parameter = 'r' -> Ready      parameter = not 'r' -> Waiting
 */

public class room_info {
    public String room_name;
    private List<String> players = new ArrayList<>();
    private HashMap<String, String> player_status = new HashMap<>();
    public String host;
    public Integer limit;
    public String room_status = "";
    private String address;
    private String password;
    private AccessType access;

    public void create(String name, Integer lim, String rn){
        host = name;
        limit = lim;
        room_name = rn;
        join(name);
        room_status = "Waiting";
    }

    public void join(String name){
        if(limit > current_size()) {
            if (!check(name)) {
                players.add(name);
                player_status.put(name, "Waiting");
            }
        }
    }

    public void left(String name){
        if(check(name)){
            players.remove(name);
            player_status.remove(name);
        }
    }

    public int current_size(){
        return players.size();
    }

    public void my_status(String name, Character status){
        if (status.equals('r')){
            player_status.replace(name, "Ready");
        }
        else{
            player_status.replace(name, "Waiting");
        }
    }

    public String room_detail(){
        String n = room_name;
        if (room_name.length() < 20){
            n = room_name;
            double space = (20 - room_name.length()) *2.4;
            int s = (int) space;
            for (int i = 0; i < s; i++){
                n += " ";
            }
        }
        else if(room_name.length() > 20){
            for(int i=0; i < 18; i++){
                n += n.charAt(i);
            }
            n += "..";
        }
        return n + "         " + current_size() + " / " + limit + "             " + room_status;
    }

    public HashMap<String,String> getPlayer_status(){
        return player_status;
    }

    public room_info send_update(){
        return this;
    }

    private Boolean check(String name){
        for (String player : players){
            if (player.equals(name)){
                return true;
            }
        }
        return false;
    }
    
    /*
     * arg0 = game name
     * arg1 = server ip address
     * arg2 = current players
     * arg3 = max players
     * arg4 = access
     * arg5 = p1
     * arg6 = p2 and so on
     */
	public void parseMessage(String sendEcho, Join_Game joinGame) {
		String[] args = sendEcho.split("/");
		if (args.length > 1) {
			this.address = args[1];
			this.access = AccessType.valueOf(args[4]);
			this.create(args[5], Integer.parseInt(args[3]), args[0]);
			for (int i = 6; i < args.length; i++) {
				this.join(args[i]);
				if (args[i].equals(joinGame.getName())) {
					if (!joinGame.getButtonToggled()) {
						this.my_status(args[i], 'r');
					}
				}
			}
		}
		
	}
	
	public String getAddress() {
		return address;
	}
	
	public AccessType getType() {
		return access;
	}
	
	public String getPassword() {
		return password;
	}
	
    /*
     * arg0 = game name
     * arg1 = server ip address
     * arg2 = current players
     * arg3 = max players
     * arg4 = access
     * arg5 = p1
     * arg6 = p1 ready (always set to true, but waiting because they have to start game)
     * arg7 = p2
     * arg8 = p2 ready
     */
	public void parseMessageTCP(String roomMessage, Join_Game join_Game) {
		// TODO Auto-generated method stub
		String[] args = roomMessage.split("/");
		if (args.length > 1) {
			this.address = args[1];
			this.access = AccessType.valueOf(args[4]);
			this.create(args[5], Integer.parseInt(args[3]), args[0]);
			for (int i = 7; i < args.length; i++) {
				this.join(args[i]);
				i++;
				if (Boolean.parseBoolean(args[i])) {
					this.my_status(args[i-1], 'r');
				}
			}
		}
	}
}
