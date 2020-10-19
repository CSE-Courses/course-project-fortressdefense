package code.Deck;
import code.card_class.*;
import java.util.*;
import javafx.util.Pair;

/**
 * Player Class for Fortress Defense
 * @author Mohan Vellayan
 */

public class Player {

	public String PlayerName;
	
	private boolean ready;
	
    Map<String, Pair> cardType=Map.ofEntries(
            Map.entry("BATTLE AXE",new Pair("ATTACK", 8)),
            Map.entry("AXE", new Pair("ATTACK", 3)),
            Map.entry("SWORD", new Pair("ATTACK", 4)),
            Map.entry("STICK", new Pair("ATTACK", 1)),
            Map.entry("MACE", new Pair("ATTACK", 5)),
            Map.entry("CROSSBOW", new Pair("ATTACK", 10)),
            Map.entry("STONE WALL", new Pair("DEFENSE", 5)),
            Map.entry("BARBED WIRE", new Pair("DEFENSE", 8)),
            Map.entry("REINFORCED GATE", new Pair("DEFENSE", 14)),
            Map.entry("WOODEN WALL", new Pair("DEFENSE", 2)),
            Map.entry("IRON DOOR", new Pair("DEFENSE", 10)),
            Map.entry("STEEL CHAINS", new Pair("DEFENSE", 7)),
            Map.entry("THUNDERSTORM", new Pair("DEFENSE", -4)),
            Map.entry("EARTHQUAKE", new Pair("DEFENSE", -8)),
            Map.entry("TORNADO", new Pair("DEFENSE", -6)),
            Map.entry("FLOOD",new Pair("DEFENSE", -3)),
            Map.entry("ARCHER TOWER", new Pair("SPECIAL", 0)),
            Map.entry("TRADE",new Pair("SPECIAL", 0)),
            Map.entry("SCOUT", new Pair("SPECIAL", 0))
    );

    // why is Deck used, see Hand class. Deck contains all cards, Hand contains cards for each player
    public AttackDeck attack=new AttackDeck();
    public DefenseDeck defense=new DefenseDeck();
    public int points=10;
    public Player(String playerName){
        cardType=Map.ofEntries(
                Map.entry("BATTLE AXE",new Pair("ATTACK", 8)),
                Map.entry("AXE", new Pair("ATTACK", 3)),
                Map.entry("SWORD", new Pair("ATTACK", 4)),
                Map.entry("STICK", new Pair("ATTACK", 1)),
                Map.entry("MACE", new Pair("ATTACK", 5)),
                Map.entry("CROSSBOW", new Pair("ATTACK", 10)),
                Map.entry("STONE WALL", new Pair("DEFENSE", 5)),
                Map.entry("BARBED WIRE", new Pair("DEFENSE", 8)),
                Map.entry("REINFORCED GATE", new Pair("DEFENSE", 14)),
                Map.entry("WOODEN WALL", new Pair("DEFENSE", 2)),
                Map.entry("IRON DOOR", new Pair("DEFENSE", 10)),
                Map.entry("STEEL CHAINS", new Pair("DEFENSE", 7)),
                Map.entry("THUNDERSTORM", new Pair("DEFENSE", -4)),
                Map.entry("EARTHQUAKE", new Pair("DEFENSE", -8)),
                Map.entry("TORNADO", new Pair("DEFENSE", -6)),
                Map.entry("FLOOD",new Pair("DEFENSE", -3)),
                Map.entry("ARCHER TOWER", new Pair("SPECIAL", 0)),
                Map.entry("TRADE",new Pair("SPECIAL", 0)),
                Map.entry("SCOUT", new Pair("SPECIAL", 0))
        );
        
        this.PlayerName = playerName;
    }
    
    public Player() {
        cardType=Map.ofEntries(
                Map.entry("BATTLE AXE",new Pair("ATTACK", 8)),
                Map.entry("AXE", new Pair("ATTACK", 3)),
                Map.entry("SWORD", new Pair("ATTACK", 4)),
                Map.entry("STICK", new Pair("ATTACK", 1)),
                Map.entry("MACE", new Pair("ATTACK", 5)),
                Map.entry("CROSSBOW", new Pair("ATTACK", 10)),
                Map.entry("STONE WALL", new Pair("DEFENSE", 5)),
                Map.entry("BARBED WIRE", new Pair("DEFENSE", 8)),
                Map.entry("REINFORCED GATE", new Pair("DEFENSE", 14)),
                Map.entry("WOODEN WALL", new Pair("DEFENSE", 2)),
                Map.entry("IRON DOOR", new Pair("DEFENSE", 10)),
                Map.entry("STEEL CHAINS", new Pair("DEFENSE", 7)),
                Map.entry("THUNDERSTORM", new Pair("DEFENSE", -4)),
                Map.entry("EARTHQUAKE", new Pair("DEFENSE", -8)),
                Map.entry("TORNADO", new Pair("DEFENSE", -6)),
                Map.entry("FLOOD",new Pair("DEFENSE", -3)),
                Map.entry("ARCHER TOWER", new Pair("SPECIAL", 0)),
                Map.entry("TRADE",new Pair("SPECIAL", 0)),
                Map.entry("SCOUT", new Pair("SPECIAL", 0))
        );
    }

    public void PickingCard(Card card){
        if((card.type.equals("ATTACK"))||(card.type.equals("SPECIAL"))){
            attack.AddCard(card,1);
        }
        else {
            defense.AddCard(card, 1);
        }
    }

    public int AttackDeckSize(){
        return attack.Size();
    }

    public int DefenseDeckSize(){
        return defense.Size();
    }

    public void GettingAttacked(Card card){
        if(card.type=="ATTACK"){
            points-=(int)cardType.get(card.card_name).getValue();
        }
    }

    public void useAttackCard(Card card,Player p){
        if((card.type=="ATTACK")&&attack.contains(card)){
            p.points-=(int)cardType.get(card.card_name).getValue();
            attack.remove(card);
        }
    }

    public void useDefenseCard(Card card){
        if(!(card.type=="DEFENSE")&&defense.contains(card)){
            points+=(int)cardType.get(card.card_name).getValue();
            defense.remove(card);
        }
    }

    public void useArcherTower(Card card,Player p){
        if(!attack.contains(card)){
            throw new IllegalArgumentException("Card is not there in deck");

        }
        p.points-=1;
    }

    public Pair<AttackDeck,DefenseDeck> useScout(Card card,Player p){
        if(!attack.contains(card)&&!defense.contains(card)){
            throw new IllegalArgumentException("Card is not there in deck");
        }
        attack.remove(card);
        return new Pair<AttackDeck,DefenseDeck>(p.attack,p.defense);
    }

    public void useTrade(Card card,Card otherCard,Player p2){
        if(!attack.contains(card)&&!defense.contains(card)){
            throw new IllegalArgumentException("Card is not present with you.");
        }
        if((otherCard.type=="ATTACK")||otherCard.type=="SPECIAL"){
            p2.attack.stack.remove(card);
            attack.AddCard(otherCard,1);
        }
        else{
            p2.defense.stack.remove(card);
            defense.AddCard(otherCard,1);
        }
        if(attack.contains(card)){
            attack.remove(card);
            p2.attack.AddCard(card,1);
        }
        else{
            defense.remove(card);
            p2.defense.AddCard(card,1);
        }
    }
    
    public boolean getReady() {
    	return ready;
    }
    
    public void setReady(boolean isReady) {
    	ready = isReady;
    }

}
