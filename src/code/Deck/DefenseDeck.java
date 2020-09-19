package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import code.Card;
import code.CardType;
import code.ICard;

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
	private Stack<ICard> stack;
	
	/*
	 * Constants representing number of cards in attack deck
	 */
	private final int numStoneWall = 1;
	private final int numWoodenWall = 1;
	private final int numBarbedWire = 1;
	private final int numGate = 1;
	private final int numDoor = 1;
	private final int numChains = 1;
	private final int numArchers = 1;
	private final int numScout = 1;
	private final int numTrade = 1;
	private final int numStorm = 1;
	private final int numTornado = 1;
	private final int numFlood = 1;
	private final int numQuake = 1;
	
	/**
	 * Creates empty Defense Deck
	 * @author Andrew Jank
	 */
	public DefenseDeck() {
		stack = new Stack<ICard>();
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
	public void Add(ICard card) {
		if (((Card)card).Type != CardType.Attack) {
			stack.push(card);
		}
	}

	@Override
	public void Clear() {
		stack = new Stack<ICard>();
		
	}

	@Override
	public ICard Draw() {
		if (Size() == 0) {
			return null;
		}
		return stack.pop();
	}

	@Override
	public void Shuffle() {
		Collections.shuffle(stack);
	}
	
	private Collection<? extends ICard> AddTrade(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddScout(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddArchers(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}
	
	private Collection<? extends ICard> AddQuake(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddFlood(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddTornado(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddStorm(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddChains(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddDoor(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddGate(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddBarbedWire(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddWoodenWall(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddStoneWall(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

}
