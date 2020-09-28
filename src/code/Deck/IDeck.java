package code.Deck;

import code.card_class.*;

/**
 * Interface representing deck of cards for Fortress Defense.
 * @author Andrew Jank
 */
public interface IDeck {
	
	/**
	 * Initializes Deck with correct amount of cards.
	 * @author Andrew Jank
	 */
	public void Initialize();
	
	/**
	 * Returns size of deck.
	 * @return Size of deck.
	 * @author Andrew Jank
	 */
	public int Size();
	
	/**
	 * Adds card to back of deck.
	 * @param card Card to be added
	 * @author Andrew Jank
	 */
	public void Add(Card card);
	
	/**
	 * Removes all cards from deck.
	 * @author Andrew Jank
	 */
	public void Clear();
	/**
	 * Returns top card from deck and removes it.
	 * @return Top card from deck.
	 * @author Andrew Jank
	 */
	public Card Draw();
	
	/**
	 * Shuffles deck
	 * @author Andrew Jank
	 */
	public void Shuffle();

}
