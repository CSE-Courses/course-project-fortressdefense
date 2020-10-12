package code.Socket;

import code.Deck.Player;
import java.util.ArrayList;


public class data_pack implements java.io.Serializable {
    private ArrayList<Player> player_list = new ArrayList<>();
    private String turn = new String("None");
    private String used_card_name = new String("None");
    private String message = "None";

    public void add_player(Player player){
        player_list.add(player);
    }

    public void del_player(Player player){
        player_list.remove(player);
    }

    public ArrayList<Player> getPlayer_list(){
        return player_list;
    }

    public void next_turn(String name){
        turn = name;
    }

    public String getTurn(){
        return turn;
    }

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
