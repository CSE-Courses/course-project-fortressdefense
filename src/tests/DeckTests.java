package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import code.GameConstants;
import code.Deck.*;
import code.card_class.*;


public class DeckTests {
	
	private final int attackInitSize = 45;
	private final int defenseInitSize = 65;
	
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
		Card card = new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall);
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
		Card card = new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick);
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
		Card card = new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall);
		deck.Add(card);
		deck.Add(card);
		deck.Add(card);
		deck.Clear();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckClear(){
		IDeck deck = new AttackDeck();
		Card card = new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick);
		deck.Add(card);
		deck.Add(card);
		deck.Add(card);
		deck.Clear();
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckDraw(){
		IDeck deck = new DefenseDeck();
		Card card1 = new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall);
		Card card2 = new Card(DefenseCard.Iron_Door, CardType.Defense, GameConstants.dmgDoor);
		Card card3 = new Card(DefenseCard.Steel_Chains, CardType.Defense, GameConstants.dmgChains);
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
		Card card1 = new Card(AttackCard.Mace, CardType.Attack, GameConstants.dmgMace);
		Card card2 = new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick);
		Card card3 = new Card(AttackCard.Axe, CardType.Attack, GameConstants.dmgAxe);
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
		Card card = new Card(SpecialCard.Trade, CardType.Special, GameConstants.dmgTrade);
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddAttack() {
		IDeck deck = new DefenseDeck();
		Card card = new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick);
		deck.Add(card);
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddWeather() {
		IDeck deck = new DefenseDeck();
		Card card = new Card(DefenseCard.Flood, CardType.Defense, GameConstants.dmgFlood);
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestDefenseDeckAddDefense() {
		IDeck deck = new DefenseDeck();
		Card card = new Card(DefenseCard.Steel_Chains, CardType.Defense, GameConstants.dmgChains);
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddSpecial() {
		IDeck deck = new AttackDeck();
		Card card = new Card(SpecialCard.Trade, CardType.Special, GameConstants.dmgTrade);
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddAttack() {
		IDeck deck = new AttackDeck();
		Card card = new Card(AttackCard.Mace, CardType.Attack, GameConstants.dmgMace);
		deck.Add(card);
		assertEquals(1, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddWeather() {
		IDeck deck = new AttackDeck();
		Card card = new Card(DefenseCard.Flood, CardType.Defense, GameConstants.dmgFlood);
		deck.Add(card);
		assertEquals(0, deck.Size());
	}
	
	@Test
	public void TestAttackDeckAddDefense() {
		IDeck deck = new AttackDeck();
		Card card = new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall);
		deck.Add(card);
		assertEquals(0, deck.Size());
	}


}
