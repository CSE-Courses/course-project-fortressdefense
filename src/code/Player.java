package code;
import code.Deck.*;
import code.card_class.*;
import java.util.*;
import javafx.util.Pair;

/**
 * Player Class for Fortress Defense
 * @author Mohan Vellayan
 */

public class Player {

	public String PlayerName;
	
    private Hand hand;
    
    private boolean hasArcherTower;
    
    public int points;
    
    public Player(String playerName){  
        this.PlayerName = playerName;
        points = 10;
        hand = new Hand();
        hasArcherTower = false;
    }
    
    public Player() {
        points = 10;
        hand = new Hand();
        hasArcherTower = false;
    }

    public void useAttackCard(Card card,Player p){
        if(card.getType() == CardType.Attack){
        	if (p.getHasArcherTower()) {
        		this.points -= 1;
        	}
            p.points-=card.getDamage();
        }
    }

    public void useDefenseCard(Card card){
        if(card.getType() == CardType.Defense){
            points+=card.getDamage();
        }
    }

    public Hand useScout(Player p){
    	return p.getHand();
    }
    
    public void useArcherTower(){
    	hasArcherTower = true;
    }
    
    public void useTrade(Card p1Card, Card p2Card, Player p2){
    	this.hand.Remove(p1Card);
    	p2.getHand().Remove(p2Card);
    	this.hand.Add(p2Card);
    	p2.getHand().Add(p1Card);
    }
    
    public Hand getHand() {
    	return hand;
    }
    
    public boolean getHasArcherTower() {
    	return hasArcherTower;
    }
}
