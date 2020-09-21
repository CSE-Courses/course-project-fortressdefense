package code;

import code.Deck.*;

public class Game {
	
	public IDeck AttackDeck;
	public IDeck DefenseDeck;
	
	public Game() {
		AttackDeck = new AttackDeck();
		AttackDeck.Initialize();
		DefenseDeck = new DefenseDeck();
		DefenseDeck.Initialize();
	}

}
