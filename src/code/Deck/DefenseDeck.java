package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import code.GameConstants;
import code.card_class.*;

/**
 * Defense Deck for Fortress Defense
 * @author Andrew Jank
 *
 */
public class DefenseDeck implements IDeck,java.io.Serializable {
	
	/**
	 * Data Structure for Defense Deck
	 * @author Andrew Jank
	 */
	public Stack<Card> stack;
	
	/**
	 * Creates empty Defense Deck
	 * @author Andrew Jank
	 */
	public DefenseDeck() {
		stack = new Stack<Card>();
	}

	@Override
	public void Initialize() {
		stack.addAll(AddStoneWall(GameConstants.numStoneWall));
		stack.addAll(AddWoodenWall(GameConstants.numWoodenWall));
		stack.addAll(AddBarbedWire(GameConstants.numBarbedWire));
		stack.addAll(AddGate(GameConstants.numGate));
		stack.addAll(AddDoor(GameConstants.numDoor));
		stack.addAll(AddChains(GameConstants.numChains));
		stack.addAll(AddArchers(GameConstants.numArchersDefense));
		stack.addAll(AddScout(GameConstants.numScoutDefense));
		stack.addAll(AddTrade(GameConstants.numTradeDefense));
		stack.addAll(AddStorm(GameConstants.numStorm));
		stack.addAll(AddTornado(GameConstants.numTornado));
		stack.addAll(AddFlood(GameConstants.numFlood));
		stack.addAll(AddQuake(GameConstants.numQuake));
		
		Shuffle();
	}

	@Override
	public int Size() {
		return stack.size();
	}

	@Override
	public void Add(Card card) {
		if (card.getType() == CardType.Defense || card.getType() == CardType.Special) {
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
			Initialize();
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
			cards.add(new Card(SpecialCard.Trade, CardType.Special, GameConstants.dmgTrade));
		}
		return cards;
	}

	private Collection<Card> AddScout(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(SpecialCard.Scout, CardType.Special, GameConstants.dmgScout));
		}
		return cards;
	}

	private Collection<Card> AddArchers(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(SpecialCard.Archer_Tower, CardType.Special, GameConstants.dmgArcher));
		}
		return cards;
	}
	
	private Collection<Card> AddQuake(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Earthquake, CardType.Defense, GameConstants.dmgQuake));
		}
		return cards;
	}

	private Collection<Card> AddFlood(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Flood, CardType.Defense, GameConstants.dmgFlood));
		}
		return cards;
	}

	private Collection<Card> AddTornado(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Tornado, CardType.Defense, GameConstants.dmgTornado));
		}
		return cards;
	}

	private Collection<Card> AddStorm(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Thunderstorm, CardType.Defense, GameConstants.dmgStorm));
		}
		return cards;
	}

	private Collection<Card> AddChains(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Steel_Chains, CardType.Defense, GameConstants.dmgChains));
		}
		return cards;
	}

	private Collection<Card> AddDoor(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Iron_Door, CardType.Defense, GameConstants.dmgDoor));
		}
		return cards;
	}

	private Collection<Card> AddGate(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Reinforced_Gate, CardType.Defense, GameConstants.dmgGate));
		}
		return cards;
	}

	private Collection<Card> AddBarbedWire(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Barbed_Wire, CardType.Defense, GameConstants.dmgBarbedWire));
		}
		return cards;
	}

	private Collection<Card> AddWoodenWall(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Wooden_Wall, CardType.Defense, GameConstants.dmgWoddenWall));
		}
		return cards;
	}

	private Collection<Card> AddStoneWall(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall));
		}
		return cards;
	}

}
