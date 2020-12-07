package code;

import java.io.Serializable;
import java.util.ArrayList;

import code.card_class.*;
import code.Deck.*;

public class Hand implements Serializable {

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
		if (Size() == 0 || index < 0 || index >= Size()) {
			return null;
		}
		
		return cards.remove(index);
	}
	
	public Card Select(int index) {
		if (Size() == 0 || index < 0 || index >= Size()) {
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
	
	public boolean Contains(Card card) {
		return cards.contains(card);
	}
	
	/**
	 * Used for trading only, adds card to hand
	 * @param card traded from another
	 * @author Andrew Jank
	 */
	public void Add(Card card) {
		cards.add(card);
	}
	
	/**
	 * Used for trading only, removes card from hand
	 * @param card to be traded to another
	 * @author Andrew Jank
	 */
	public void Remove(Card card) {
		cards.remove(card);
	}
	
	public int getNumAttack() {
		return numAttackDraw;
	}
	
	public int getNumDefense() {
		return numDefenseDraw;
	}
	
	public void incNumAttack() {
		numAttackDraw += 1;
	}
	
	public void incNumDefense() {
		numDefenseDraw += 1;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
}
