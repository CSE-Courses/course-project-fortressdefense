package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import code.Card;
import code.CardType;
import code.ICard;

/**
 * Attack Deck for Fortress Defense
 * @author Andrew Jank
 */
public class AttackDeck implements IDeck {

	/**
	 * Data Structure for Attack Deck
	 * @author Andrew Jank
	 */
	private Stack<ICard> stack;
	
	/*
	 * Constants representing number of cards in attack deck
	 */
	
	private final int numBattleAxe = 1;
	private final int numAxe = 1;
	private final int numSword = 1;
	private final int numStick = 1;
	private final int numMace = 1;
	private final int numCrossBow = 1;
	private final int numArchers = 1;
	private final int numScout = 1;
	private final int numTrade = 1;
	
	/**
	 * Creates new empty Attack Deck
	 * @author Andrew Jank
	 */
	public AttackDeck() {
		stack = new Stack<ICard>();
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
	public void Add(ICard card) {
		if (((Card)card).Type == CardType.Attack || ((Card)card).Type == CardType.Special) {
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

	private Collection<? extends ICard> AddCrossbow(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddMace(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddStick(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddSword(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddAxe(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

	private Collection<? extends ICard> AddBattleAxe(int num) {
		ArrayList<ICard> cards = new ArrayList<ICard>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card());
		}
		return cards;
	}

}
