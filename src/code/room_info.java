package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public void parseMessage(String sendEcho) {
		String[] args = sendEcho.split("/");
		if (args.length > 1) {
			this.address = args[1];
			this.create("TEST", Integer.parseInt(args[3]), args[0]);
		}
		
	}
	
	public String getAddress() {
		return address;
	}
}
