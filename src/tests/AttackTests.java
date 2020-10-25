package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import javafx.util.Pair;
import code.card_class.*;
import code.GameConstants;
import code.Hand;
import code.Player;
import code.Deck.*;
public class AttackTests {

    Card stick = new Card(AttackCard.Stick, CardType.Attack, GameConstants.dmgStick);
    Card battleaxe = new Card(AttackCard.Battle_Axe, CardType.Attack, GameConstants.dmgBattleAxe);
    Card axe = new Card(AttackCard.Axe, CardType.Attack, GameConstants.dmgAxe);
    Card sword = new Card(AttackCard.Sword, CardType.Attack, GameConstants.dmgSword);
    Card mace = new Card(AttackCard.Mace, CardType.Attack, GameConstants.dmgMace);
    Card crossbow = new Card(AttackCard.Crossbow, CardType.Attack, GameConstants.dmgCrossbow);
    Card archertower = new Card(SpecialCard.Archer_Tower, CardType.Special, GameConstants.dmgArcher);
    Card trade = new Card(SpecialCard.Trade, CardType.Special, GameConstants.dmgTrade);
    Card scout = new Card(SpecialCard.Scout, CardType.Special, GameConstants.dmgScout);

    @Test
    public void PlayerInitialPoints() {
        Player p1 = new Player();
        assertEquals(p1.points,10);
    }

    @Test
    public void PlayerGettingAttackedByBattleAxe() {
        Player p1 = new Player();

        int previousPoints = p1.points;
        p1.useAttackCard(battleaxe, p1);
        assertEquals(p1.points, previousPoints - 8);
    }

    @Test
    public void PlayerUseAttackCardByBattleAxe() {
        Player p1 = new Player();

        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(battleaxe, p2);
        assertEquals(p2.points, previousPoints - 8);
    }

    @Test
    public void PlayerGettingAttackedByAxe() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.useAttackCard(axe, p1);
        assertEquals(p1.points, previousPoints - 3);
    }

    @Test
    public void PlayerUseAttackCardByAxe() {
        Player p1 = new Player();
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(axe, p2);
        assertEquals(p2.points, previousPoints - 3);
    }

    @Test
    public void PlayerGettingAttackedBySword() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.useAttackCard(sword, p1);
        assertEquals(p1.points, previousPoints - 4);
    }

    @Test
    public void PlayerUseAttackCardBySword() {
        Player p1 = new Player();
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(sword, p2);
        assertEquals(p2.points, previousPoints - 4);
    }

    @Test
    public void PlayerGettingAttackedByStick() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.useAttackCard(stick, p1);
        assertEquals(p1.points, previousPoints - 1);
    }

    @Test
    public void PlayerUseAttackCardByStick() {
        Player p1 = new Player();
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(stick, p2);
        assertEquals(p2.points, previousPoints - 1);
    }

    @Test
    public void PlayerGettingAttackedByMace() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.useAttackCard(mace, p1);
        assertEquals(p1.points, previousPoints - 5);
    }

    @Test
    public void PlayerUseAttackCardByMace() {
        Player p1 = new Player();
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(mace, p2);
        assertEquals(p2.points, previousPoints - 5);
    }

    @Test
    public void PlayerGettingAttackedByCrossbow() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.useAttackCard(crossbow, p1);
        assertEquals(p1.points, previousPoints - 10);
    }

    @Test
    public void PlayerUseAttackCardByCrossbow() {
        Player p1 = new Player();
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(crossbow, p2);
        assertEquals(p2.points, previousPoints - 10);
    }

    @Test
    public void PlayerUseArcherTower(){
        Player p1 = new Player();
        Player p2= new Player();
        int p2previousPoints=p2.points;
        int p1previousPoints = p1.points;
        p1.useArcherTower();
        p2.useAttackCard(crossbow, p1);
        assertEquals(p2.points, p2previousPoints-1);
        assertEquals(p1.points, p1previousPoints-10);
    }

    @Test
    public void PlayerUseScout(){
        Player p1 = new Player();
        Player p2= new Player();
        IDeck deck = new DefenseDeck();
        deck.Add(stick);
        p2.getHand().Draw(deck);
        int previousPoints=p2.points;
        Hand p2Hand = p1.useScout(p2);
        assertEquals(previousPoints, p2.points);
        assertEquals(p2.getHand(), p2Hand);
    }


    @Test
    public void PlayerUseTrade(){
    	IDeck deck = new AttackDeck();
    	deck.Add(stick);
    	deck.Add(axe);
        Player p1 = new Player();
        p1.getHand().Draw(deck); // axe
        Player p2= new Player();
        p2.getHand().Draw(deck); // stick
        p1.useTrade(axe,stick,p2);
        assertEquals(p1.getHand().Contains(axe),false);//should write contains() function in attackDeck and defenseDeck classes.
        assertEquals(p1.getHand().Contains(stick),true);
        assertEquals(p2.getHand().Contains(stick),false);
        assertEquals(p2.getHand().Contains(axe),true);
    }

}
