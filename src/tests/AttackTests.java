package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import javafx.util.Pair;
import card_class.*;
import code.Deck.*;
public class AttackTests {

    Card stick = new Card("STICK","ATTACK",1);
    Card battleaxe = new Card("BATTLE AXE","ATTACK",8);
    Card axe = new Card("AXE","ATTACK",3);
    Card sword = new Card("SWORD","ATTACK",4);
    Card mace = new Card("MACE","ATTACK",5);
    Card crossbow = new Card("CROSSBOW","ATTACK",10);
    Card archertower = new Card("ARCHER TOWER","SPECIAL",0);
    Card trade = new Card("TRADE","SPECIAL",0);
    Card scout = new Card("SCOUT","SPECIAL",0);

    @Test
    public void PlayerInitialPoints() {
        Player p1 = new Player();
        assertEquals(p1.points,10);
    }

    @Test
    public void PlayerPickingCardStick() {
        Player p1 = new Player();

        p1.PickingCard(stick);
        assertEquals(p1.attack.stack.peek(), stick);
        assertEquals(p1.attack.stack.peek().type, "ATTACK");
    }


    @Test
    public void PlayerGettingAttackedByBattleAxe() {
        Player p1 = new Player();

        int previousPoints = p1.points;
        p1.GettingAttacked(battleaxe);
        assertEquals(p1.points, previousPoints - 8);
    }

    @Test
    public void PlayerUseAttackCardByBattleAxe() {
        Player p1 = new Player();

        p1.PickingCard(battleaxe);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(battleaxe, p2);
        assertEquals(p2.points, previousPoints - 8);
    }

    @Test
    public void PlayerGettingAttackedByAxe() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.GettingAttacked(axe);
        assertEquals(p1.points, previousPoints - 3);
    }

    @Test
    public void PlayerUseAttackCardByAxe() {
        Player p1 = new Player();
        p1.PickingCard(axe);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(axe, p2);
        assertEquals(p2.points, previousPoints - 3);
    }

    @Test
    public void PlayerGettingAttackedBySword() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.GettingAttacked(sword);
        assertEquals(p1.points, previousPoints - 4);
    }

    @Test
    public void PlayerUseAttackCardBySword() {
        Player p1 = new Player();
        p1.PickingCard(sword);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(sword, p2);
        assertEquals(p2.points, previousPoints - 4);
    }

    @Test
    public void PlayerGettingAttackedByStick() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.GettingAttacked(stick);
        assertEquals(p1.points, previousPoints - 1);
    }

    @Test
    public void PlayerUseAttackCardByStick() {
        Player p1 = new Player();
        p1.PickingCard(stick);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(stick, p2);
        assertEquals(p2.points, previousPoints - 1);
    }

    @Test
    public void PlayerGettingAttackedByMace() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.GettingAttacked(mace);
        assertEquals(p1.points, previousPoints - 5);
    }

    @Test
    public void PlayerUseAttackCardByMace() {
        Player p1 = new Player();
        p1.PickingCard(mace);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(mace, p2);
        assertEquals(p2.points, previousPoints - 5);
    }

    @Test
    public void PlayerGettingAttackedByCrossbow() {
        Player p1 = new Player();
        int previousPoints = p1.points;
        p1.GettingAttacked(crossbow);
        assertEquals(p1.points, previousPoints - 10);
    }

    @Test
    public void PlayerUseAttackCardByCrossbow() {
        Player p1 = new Player();
        p1.PickingCard(crossbow);
        Player p2 = new Player();
        int previousPoints = p2.points;
        p1.useAttackCard(crossbow, p2);
        assertEquals(p2.points, previousPoints - 10);
    }

    @Test
    public void PlayerUseArcherTower(){
        Player p1 = new Player();
//        Card card = new Card("ARCHER");
        p1.PickingCard(archertower);
        Player p2= new Player();
        int previousPoints=p2.points;
        p1.useArcherTower(archertower,p2);//should keep track of which player is attacking you.
        assertEquals(p2.points, previousPoints-1);
    }

    @Test
    public void PlayerUseScout(){
        Player p1 = new Player();
        p1.PickingCard(scout);
        Player p2= new Player();
        p2.PickingCard(axe);
        p2.PickingCard(stick);
        int previousPoints=p2.points;
        AttackDeck a= p2.attack;
        DefenseDeck b=p2.defense;
        Pair<AttackDeck,DefenseDeck>hi=p1.useScout(scout,p2);
        assertEquals(hi.getKey(), p2.attack);
        assertEquals(hi.getValue(), p2.defense);
    }


    @Test
    public void PlayerUseTrade(){
        Player p1 = new Player();
        p1.PickingCard(axe);
        Player p2= new Player();
        p2.PickingCard(stick);
        p2.PickingCard(scout);
        Card tradeCard=p2.attack.Draw();
        AttackDeck a=p1.attack;
        int p1size=a.Size();
        AttackDeck a1=p2.attack;
        int p2size=a1.Size();
        p1.useTrade(axe,tradeCard,p2);
        assertEquals(p1.attack.Size(), p1size);
        assertEquals(p1.attack.stack.contains(axe),false);//should write contains() function in attackDeck and defenseDeck classes.
        assertEquals(p1.attack.stack.contains(tradeCard),true);
        assertEquals(p2.attack.stack.contains(tradeCard),false);
        assertEquals(p2.attack.stack.contains(axe),true);
    }

}
