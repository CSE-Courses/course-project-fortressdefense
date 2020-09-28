package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import card_class.*;

/**
 * Defense Deck for Fortress Defense
 * @author Andrew Jank
 *
 */
public class DefenseDeck implements IDeck {
	
	/**
	 * Data Structure for Defense Deck
	 * @author Andrew Jank
	 */
	public Stack<Card> stack;
	
	/*
	 * Constants representing number of cards in attack deck
	 */
	private final int numStoneWall = 5;
	private final int numWoodenWall = 5;
	private final int numBarbedWire = 5;
	private final int numGate = 5;
	private final int numDoor = 5;
	private final int numChains = 5;
	private final int numArchers = 5;
	private final int numScout = 5;
	private final int numTrade = 5;
	private final int numStorm = 5;
	private final int numTornado = 5;
	private final int numFlood = 5;
	private final int numQuake = 5;
	
	/**
	 * Creates empty Defense Deck
	 * @author Andrew Jank
	 */
	public DefenseDeck() {
		stack = new Stack<Card>();
	}

	@Override
	public void Initialize() {
		stack.addAll(AddStoneWall(numStoneWall));
		stack.addAll(AddWoodenWall(numWoodenWall));
		stack.addAll(AddBarbedWire(numBarbedWire));
		stack.addAll(AddGate(numGate));
		stack.addAll(AddDoor(numDoor));
		stack.addAll(AddChains(numChains));
		stack.addAll(AddArchers(numArchers));
		stack.addAll(AddScout(numScout));
		stack.addAll(AddTrade(numTrade));
		stack.addAll(AddStorm(numStorm));
		stack.addAll(AddTornado(numTornado));
		stack.addAll(AddFlood(numFlood));
		stack.addAll(AddQuake(numQuake));
		
		Shuffle();
	}

	@Override
	public int Size() {
		return stack.size();
	}

	@Override
	public void Add(Card card) {
		if (card.getType() == "DEFENSE" || card.getType() == "SPECIAL") {
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
	public Stack<Card> AddCard(Card card, int num){

		if((card.type=="DEFENSE")){
			throw new IllegalArgumentException("No cards other than defense cards can be added to defense deck");
		}
		for (int i = 0; i < num; i++) {
			stack.add(card);
		}
		return stack;
	}
	public void remove(Card card){
		stack.remove(card);
	}
	public boolean contains(Card card){
		return stack.contains(card);
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
	
	private Collection<Card> AddQuake(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("EARTHQUAKE"));
		}
		return cards;
	}

	private Collection<Card> AddFlood(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("FLOOD"));
		}
		return cards;
	}

	private Collection<Card> AddTornado(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("TORNADO"));
		}
		return cards;
	}

	private Collection<Card> AddStorm(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("THUNDERSTORM"));
		}
		return cards;
	}

	private Collection<Card> AddChains(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("STEEL CHAINS"));
		}
		return cards;
	}

	private Collection<Card> AddDoor(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("IRON DOOR"));
		}
		return cards;
	}

	private Collection<Card> AddGate(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("REINFORCED GATE"));
		}
		return cards;
	}

	private Collection<Card> AddBarbedWire(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("BARBED WIRE"));
		}
		return cards;
	}

	private Collection<Card> AddWoodenWall(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("WOODEN WALL"));
		}
		return cards;
	}

	private Collection<Card> AddStoneWall(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card("STONE WALL"));
		}
		return cards;
	}

}
