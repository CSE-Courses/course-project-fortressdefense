package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import code.card_class.*;

/**
 * Attack Deck for Fortress Defense
 * @author Andrew Jank
 */
public class AttackDeck implements IDeck {

	/**
	 * Data Structure for Attack Deck
	 * @author Andrew Jank
	 */
	private Stack<Card> stack;
	
	/*
	 * Constants representing number of cards in attack deck
	 */
	
	private final int numBattleAxe = 5;
	private final int numAxe = 5;
	private final int numSword = 5;
	private final int numStick = 5;
	private final int numMace = 5;
	private final int numCrossBow = 5;
	private final int numArchers = 5;
	private final int numScout = 5;
	private final int numTrade = 5;
	
	/**
	 * Creates new empty Attack Deck
	 * @author Andrew Jank
	 */
	public AttackDeck() {
		stack = new Stack<Card>();
	}

	@Override
	public void Initialize() {
		stack.addAll(AddBattleAxe(numBattleAxe));
		stack.addAll(AddAxe(numAxe));
		stack.addAll(AddSword(numSword));
		stack.addAll(AddStick(numStick));
		stack.addAll(AddMace(numMace));
		stack.addAll(AddCrossbow(numCrossBow));
		stack.addAll(AddArchers(numArchers));
		stack.addAll(AddScout(numScout));
		stack.addAll(AddTrade(numTrade));
		
		Shuffle();
	}

	@Override
	public int Size() {
		return stack.size();
	}

	@Override
	public void Add(Card card) {
		if (card.getType().equals("ATTACK") || card.getType().equals("SPECIAL")) {
			stack.push(card);
		}
	}

	@Override
	public void Clear() {
		stack = new Stack<Card>();
	}

	@Override
	public Card Draw() {
		if (Size() == 0) {
			return null;
		}
		return stack.pop();
	}

	@Override
	public void Shuffle() {
		Collections.shuffle(stack);
	}
	
	private Collection<Card> AddTrade(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("TRADE"));
		}
		return cards;
	}

	private Collection<Card> AddScout(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("SCOUT"));
		}
		return cards;
	}

	private Collection<Card> AddArchers(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("ARCHER TOWER"));
		}
		return cards;
	}

	private Collection<Card> AddCrossbow(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("CROSSBOW"));
		}
		return cards;
	}

	private Collection<Card> AddMace(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("MACE"));
		}
		return cards;
	}

	private Collection<Card> AddStick(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("STICK"));
		}
		return cards;
	}

	private Collection<Card> AddSword(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("SWORD"));
		}
		return cards;
	}

	private Collection<Card> AddAxe(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("AXE"));
		}
		return cards;
	}

	private Collection<Card> AddBattleAxe(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("BATTLE AXE"));
		}
		return cards;
	}

}
