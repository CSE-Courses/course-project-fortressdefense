package code.Socket;

import code.Deck.Player;
import java.util.ArrayList;
import java.io.Serializable;

public class data_pack implements Serializable {
    private static ArrayList<Player> player_list = new ArrayList<>();
    private static String turn = new String("None");
    private static String used_card_name = new String("None");

    public void add_player(Player player){
        player_list.add(player);
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
}
