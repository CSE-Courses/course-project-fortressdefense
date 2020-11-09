package code.Socket.Game_Phase;

import code.Player;

import java.util.ArrayList;


public class data_pack implements java.io.Serializable {
    private final ArrayList<Player> player_list = new ArrayList<>();
    private final ArrayList<String> player_name_list = new ArrayList<>();
    private String turn = new String("");
    private String used_card_name = new String("");
    private String message = "";
    private String phase = "";
    private String chat_msg = "";
    public boolean new_msg = false;
    private int round = 1;
    private int room_size;

    public void send_chat_msg(String msg){
        chat_msg = msg;
        new_msg = true;
    }

    public String receive_chat_msg(){
        return chat_msg;
    }

    public String getPhase(){
        return phase;
    }

    public void setRoom_size(int n){
        room_size = n;
    }

    public int getRoom_size(){
        return room_size;
    }

    public void add_player(Player player){
        if (!player_name_list.contains(player.PlayerName)){
            player_name_list.add(player.PlayerName);
            player_list.add(player);
        }
        else{
            System.out.println("[Server] Player already joined");
        }
    }

    public void del_player(Player player){
        if(player_name_list.contains(player.PlayerName)){
            player_name_list.remove(player.PlayerName);
            player_list.remove(player);
        }
        else {
            System.out.println("[Server] Player does not in this game");
        }
    }

    public ArrayList<Player> getPlayer_list(){
        return player_list;
    }

    public void next_turn(){
        if (turn.equals("")) {
            turn = player_name_list.get(0);
        }
        else{
            int current = player_name_list.indexOf(turn);
            if (current + 1 == player_name_list.size()){
                turn = player_name_list.get(0);
                round += 1;
            }
            else {
                turn = player_name_list.get(current + 1);
            }
        }
    }

    public String getTurn(){
        return turn;
    }

    public int getRound(){return round;}

    public void card_used(String name){
        used_card_name = name;
    }

    public String getUsed_card_name(){
        return used_card_name;
    }

    public void write_message(String m){
        message = m;
        new_msg = false;
    }

    public String getMessage(){
        return message;
    }
}
