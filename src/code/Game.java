package code;

import java.util.ArrayList;

import code.Deck.*;

public class Game {
	
	public IDeck AttackDeck;
	public IDeck DefenseDeck;
	
	public ArrayList<Player> PlayerList;
	
	public Game() {
		AttackDeck = new AttackDeck();
		AttackDeck.Initialize();
		DefenseDeck = new DefenseDeck();
		DefenseDeck.Initialize();
		
		PlayerList = new ArrayList<Player>();
	}

}
