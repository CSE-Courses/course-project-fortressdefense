package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import code.*;
import code.Deck.*;

public class DeckTests {
	
	private final int attackInitSize = 9;
	private final int defenseInitSize = 13;
	@Test
	public void TestAttackDeckEmpty(){
		IDeck deck = new AttackDeck();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckEmpty(){
		IDeck deck = new DefenseDeck();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseAddCard(){
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Defense;
		deck.Add(card);
		assertEquals(1, deck.Size());
		deck.Add(card);
		assertEquals(2, deck.Size());
		deck.Add(card);
		assertEquals(3, deck.Size());
	}
	
	@Test
	public void TestAttackAddCard(){
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Attack;
		deck.Add(card);
		assertEquals(1, deck.Size());
		deck.Add(card);
		assertEquals(2, deck.Size());
		deck.Add(card);
		assertEquals(3, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckClear(){
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Defense;
		deck.Add(card);
		deck.Add(card);
		deck.Add(card);
		deck.Clear();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckClear(){
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Attack;
		deck.Add(card);
		deck.Add(card);
		deck.Add(card);
		deck.Clear();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckDraw(){
		IDeck deck = new DefenseDeck();
		Card card1 = new Card();
		card1.Type = CardType.Defense;
		Card card2 = new Card();
		card2.Type = CardType.Defense;
		Card card3 = new Card();
		card3.Type = CardType.Defense;
		deck.Add(card1);
		deck.Add(card2);
		deck.Add(card3);
		
		assertEquals(card3, deck.Draw());
		assertEquals(2, deck.Size());
		
		assertEquals(card2, deck.Draw());
		assertEquals(1, deck.Size());
		
		assertEquals(card1, deck.Draw());
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckDraw(){
		IDeck deck = new AttackDeck();
		Card card1 = new Card();
		card1.Type = CardType.Attack;
		Card card2 = new Card();
		card2.Type = CardType.Attack;
		Card card3 = new Card();
		card3.Type = CardType.Attack;
		deck.Add(card1);
		deck.Add(card2);
		deck.Add(card3);

		assertEquals(card3, deck.Draw());
		assertEquals(2, deck.Size());
		
		assertEquals(card2, deck.Draw());
		assertEquals(1, deck.Size());
		
		assertEquals(card1, deck.Draw());
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckDrawOnEmpty() {
		IDeck deck = new AttackDeck();
		assertEquals(0, deck.Size());
		assertNull(deck.Draw());
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckDrawOnEmpty() {
		IDeck deck = new DefenseDeck();
		assertEquals(0, deck.Size());
		assertNull(deck.Draw());
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckShuffle() {
		IDeck deck = new AttackDeck();
		for (int i = 0; i < 100; i++) {
			Card card = new Card();
			card.Name = Integer.toString(i);
			card.Type = CardType.Attack;
			deck.Add(card);
		}
		
		deck.Shuffle();
		double avg = 0;
		for (int i = 0; i < 5; i++) {
			avg += Double.parseDouble(((Card)deck.Draw()).Name);
		}
		
		avg /= 5;
		assertNotEquals(2, avg);
	}
	
	@Test
	public void TestDefenseDeckShuffle() {
		IDeck deck = new DefenseDeck();
		for (int i = 0; i < 100; i++) {
			Card card = new Card();
			card.Name = Integer.toString(i);
			card.Type = CardType.Defense;
			deck.Add(card);
		}
		
		deck.Shuffle();
		double avg = 0;
		for (int i = 0; i < 5; i++) {
			avg += Double.parseDouble(((Card)deck.Draw()).Name);
		}
		
		avg /= 5;
		assertNotEquals(2, avg, 0.001);
	}
	
	@Test
	public void TestAttackDeckInitialize() {
		IDeck deck = new AttackDeck();
		deck.Initialize();
		assertEquals(attackInitSize, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckInitialize() {
		IDeck deck = new DefenseDeck();
		deck.Initialize();
		assertEquals(defenseInitSize, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddSpecial() {
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Special;
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddAttack() {
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Attack;
		deck.Add(card);
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddWeather() {
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Weather;
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddDefense() {
		IDeck deck = new DefenseDeck();
		Card card = new Card();
		card.Type = CardType.Defense;
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddSpecial() {
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Special;
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddAttack() {
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Attack;
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddWeather() {
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Weather;
		deck.Add(card);
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddDefense() {
		IDeck deck = new AttackDeck();
		Card card = new Card();
		card.Type = CardType.Defense;
		deck.Add(card);
		assertEquals(0, deck.Size());
	}
}
