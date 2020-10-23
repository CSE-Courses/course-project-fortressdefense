package code.Deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import code.GameConstants;
import code.card_class.*;

/**
 * Attack Deck for Fortress Defense
 * @author Andrew Jank
 */
public class AttackDeck implements IDeck, java.io.Serializable {

	/**
	 * Data Structure for Attack Deck
	 * @author Andrew Jank
	 */
	public Stack<Card> stack;
	
	/**
	 * Creates new empty Attack Deck
	 * @author Andrew Jank
	 */
	public AttackDeck() {
		this.stack = new Stack<Card>();
	}

	@Override
	public void Initialize() {
		stack.addAll(AddBattleAxe(GameConstants.numBattleAxe));
		stack.addAll(AddAxe(GameConstants.numAxe));
		stack.addAll(AddSword(GameConstants.numSword));
		stack.addAll(AddStick(GameConstants.numStick));
		stack.addAll(AddMace(GameConstants.numMace));
		stack.addAll(AddCrossbow(GameConstants.numCrossBow));
		stack.addAll(AddArchers(GameConstants.numArchersAttack));
		stack.addAll(AddScout(GameConstants.numScoutAttack));
		stack.addAll(AddTrade(GameConstants.numTradeAttack));
		
		Shuffle();
	}

	@Override
	public int Size() {
		return stack.size();
	}

	@Override
	public void Add(Card card) {
		if (card.getType().equals(CardType.Attack) || card.getType().equals(CardType.Special)) {
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

	private Collection<Card> AddCrossbow(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Crossbow, CardType.Attack, GameConstants.dmgCrossbow));
		}
		return cards;
	}

	private Collection<Card> AddMace(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Mace, CardType.Attack, GameConstants.dmgMace));
		}
		return cards;
	}

	private Collection<Card> AddStick(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick));
		}
		return cards;
	}

	private Collection<Card> AddSword(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Sword, CardType.Attack, GameConstants.dmgSword));
		}
		return cards;
	}

	private Collection<Card> AddAxe(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Axe, CardType.Attack, GameConstants.dmgAxe));
		}
		return cards;
	}

	private Collection<Card> AddBattleAxe(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			cards.add(new Card(AttackCard.Battle_Axe, CardType.Attack, GameConstants.dmgBattleAxe));
		}
		return cards;
	}

}
