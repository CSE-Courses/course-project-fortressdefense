package code;
import code.Deck.*;
import code.card_class.*;

import java.io.Serializable;
import java.util.*;
import javafx.util.Pair;

/**
 * Player Class for Fortress Defense
 * @author Mohan Vellayan
 */

public class Player implements Serializable {

	public String PlayerName;
	
    private Hand hand;
    
    private int hasArcherTower;
    
    public int points;
    
    private boolean isReady;
    
    public Player(String playerName){  
        this.PlayerName = playerName;
        points = 10;
        hand = new Hand();
        hasArcherTower = 0;
    }
    
    public Player() {
        points = 10;
        hand = new Hand();
        hasArcherTower = 0;
    }

    public void useAttackCard(Card card,Player p){
        if(card.getType() == CardType.Attack){
        	if (p.getHasArcherTower() > 0) {
        		this.points -= 1 * p.getHasArcherTower();
        		if (this.points < 0) {
        			this.points = 0;
        		}
        	}
            p.points-=card.getDamage();
     		if (p.points < 0) {
    			p.points = 0;
    		}
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
    	hasArcherTower+=1;
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
    
    public int getHasArcherTower() {
    	return hasArcherTower;
    }
    
    public void setReady(Boolean ready) {
    	this.isReady = ready;
    }
    
    public Boolean getReady() {
    	return isReady;
    }
}
