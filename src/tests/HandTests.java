package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import code.*;
import code.Deck.*;

public class HandTests {
	
	private Game game;
	
	@Before 
	public void TestInit() {
		game = new Game();
	}
	
	@Test
	public void TestHandCreate() {
		Hand hand = new Hand();
		assertEquals(0, hand.Size());
	}
	
	@Test
	public void TestHandAdd() {
		Hand hand = new Hand();
		hand.Draw(game.AttackDeck);
		assertEquals(1, hand.Size());
		hand.Draw(game.DefenseDeck);
		assertEquals(2, hand.Size());
	}
	
	@Test
	public void TestHandClear() {
		Hand hand = new Hand();
		hand.Draw(game.AttackDeck);
		assertEquals(1, hand.Size());
		hand.Draw(game.DefenseDeck);
		assertEquals(2, hand.Size());
		hand.Clear();
		assertEquals(0, hand.Size());
	}
	
	@Test
	public void TestSelectNull() {
		Hand hand = new Hand();
		assertNull(hand.Select(-1));
		assertNull(hand.Select(10));
	}
	
	@Test
	public void TestSelect() {
		Hand hand = new Hand();
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		
		ICard card = hand.Select(3);
		assertTrue(((Card)card).Type == CardType.Attack || ((Card)card).Type == CardType.Special);
		assertEquals(8, hand.Size());
		
		card = hand.Select(7);
		assertTrue(((Card)card).Type == CardType.Defense || ((Card)card).Type == CardType.Weather || ((Card)card).Type == CardType.Special);
		assertEquals(8, hand.Size());
	}
	
	@Test
	public void TestOverMaxAttack() {
		Hand hand = new Hand();
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.AttackDeck);
		assertEquals(4, hand.Size());
	}
	
	@Test
	public void TestOverMaxDefense() {
		Hand hand = new Hand();
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.DefenseDeck);
		assertEquals(4, hand.Size());
	}
	
	@Test
	public void TestOverMaxClearThenAdd() {
		Hand hand = new Hand();
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.DefenseDeck);
		assertEquals(4, hand.Size());
		hand.Clear();
		hand.Draw(game.DefenseDeck);
		assertEquals(1, hand.Size());
	}
	
	@Test
	public void TestPlayOnEmpty() {
		Hand hand = new Hand();
		assertNull(hand.Play(0));
		assertNull(hand.Play(-1));
	}
	
	@Test
	public void TestPlayNotEmpty() {
		Hand hand = new Hand();
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.AttackDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		hand.Draw(game.DefenseDeck);
		assertNotNull(hand.Play(1));
		assertEquals(7, hand.Size());
		assertNotNull(hand.Play(1));
		assertEquals(6, hand.Size());
	}
	
	@Test
	public void TestPlayStartOfTurn() {
		Hand hand = new Hand();
		
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.PlayAtStart = true;
		card.Type = CardType.Special;
		deck.Add(card);
		
		hand.Draw(deck);
		hand.BeginAttackPhase();
		assertEquals(0, hand.Size());
		
	}
	
	@Test
	public void TestPlayStartOfTurnNone() {
		Hand hand = new Hand();
		
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Name = "Archer Tower";
		card.PlayAtStart = false;
		card.Type = CardType.Attack;
		deck.Add(card);
		
		hand.Draw(deck);
		hand.BeginAttackPhase();
		assertEquals(1, hand.Size());
		
	}

}
