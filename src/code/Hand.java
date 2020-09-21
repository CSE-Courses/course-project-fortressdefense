package code;

import java.util.ArrayList;

import code.card_class.*;
import code.Deck.*;

public class Hand {

	private ArrayList<Card> cards;
	private int numAttackDraw;
	private int numDefenseDraw;
	
	public Hand() {
		cards = new ArrayList<Card>();
		numAttackDraw = 0;
		numDefenseDraw = 0;
	}
	
	public int Size() {
		return cards.size();
	}
	
	public void Draw(IDeck deck) {
		if (deck instanceof AttackDeck) {
			if (numAttackDraw < 4) {
				cards.add(deck.Draw());
				numAttackDraw+=1;
			}
		}
		else if (deck instanceof DefenseDeck) {
			if (numDefenseDraw < 4) {
				cards.add(deck.Draw());
				numDefenseDraw+=1;
			}
		}
		
	}
	
	public Card Play(int index) {
		if (Size() == 0 || index < 0 || index > Size()) {
			return null;
		}
		
		return cards.remove(index);
	}
	
	public Card Select(int index) {
		if (Size() == 0 || index < 0 || index > Size()) {
			return null;
		}
		
		return cards.get(index);
	}
	
	public void Clear() {
		cards = new ArrayList<Card>();
		EndDrawPhase();
	}
	
	public void BeginAttackPhase() {
		for(int i = 0; i < Size(); i++) {
			if (Select(i).PlayAtStart) {
				Play(i);
			}
		}
	}
	
	public void EndDrawPhase() {
		numDefenseDraw = 0;
		numAttackDraw = 0;
	}

}
