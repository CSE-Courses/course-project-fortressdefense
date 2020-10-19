package code.Socket;

import code.Deck.Player;
import java.util.ArrayList;


public class data_pack implements java.io.Serializable {
    private final ArrayList<Player> player_list = new ArrayList<>();
    private final ArrayList<String> player_name_list = new ArrayList<>();
    private String turn = new String("");
    private String used_card_name = new String("");
    private String message = "";
    private int round = 1;

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
        if (turn.equals("None")) {
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
    }

    public String getMessage(){
        return message;
    }
}
