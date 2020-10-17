package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import code.card_class.*;
import code.GameConstants;
import code.Player;
import code.Deck.*;

public class DefenseTests {

    Card stonewall = new Card(DefenseCard.Stone_Wall, CardType.Defense, GameConstants.dmgStoneWall);
    Card woodenwall = new Card(DefenseCard.Wooden_Wall, CardType.Defense, GameConstants.dmgWoddenWall);
    Card barbedwire = new Card(DefenseCard.Barbed_Wire, CardType.Defense, GameConstants.dmgBarbedWire);
    Card reinforcedgate = new Card(DefenseCard.Reinforced_Gate, CardType.Defense, GameConstants.dmgGate);
    Card irondoor = new Card(DefenseCard.Iron_Door, CardType.Defense, GameConstants.dmgDoor);
    Card stellchains = new Card(DefenseCard.Steel_Chains, CardType.Defense, GameConstants.dmgChains);
    Card thunderstorm = new Card(DefenseCard.Thunderstorm, CardType.Defense, GameConstants.dmgStorm);
    Card tornado = new Card(DefenseCard.Tornado, CardType.Defense, GameConstants.dmgTornado);
    Card flood = new Card(DefenseCard.Flood, CardType.Defense, GameConstants.dmgFlood);
    Card earthquake = new Card(DefenseCard.Earthquake, CardType.Defense, GameConstants.dmgQuake);


    @Test
    public void PlayerUseDefenseCardStoneWall(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(stonewall);
        assertEquals(p1.points, previousPoints+5);
    }

    @Test
    public void PlayerUseDefenseCardWoodenWall(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(woodenwall);
        assertEquals(p1.points, previousPoints+2);
    }

    @Test
    public void PlayerUseDefenseCardBarbedWire(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(barbedwire);
        assertEquals(p1.points, previousPoints+8);
    }

    @Test
    public void PlayerUseDefenseCardReinforcedGate(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(reinforcedgate);
        assertEquals(p1.points, previousPoints+14);
    }

    @Test
    public void PlayerUseDefenseCardIronDoor(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(irondoor);
        assertEquals(p1.points, previousPoints+10);
    }

    @Test
    public void PlayerUseDefenseCardSteelChains(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(stellchains);
        assertEquals(p1.points, previousPoints+7);
    }

    @Test
    public void PlayerUseDefenseCardThunderStorm(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(thunderstorm);
        assertEquals(p1.points, previousPoints-4);
    }

    @Test
    public void PlayerUseDefenseCardTornado(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(tornado);
        assertEquals(p1.points, previousPoints-6);
    }

    @Test
    public void PlayerUseDefenseCardFlood(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(flood);
        assertEquals(p1.points, previousPoints-3);
    }

    @Test
    public void PlayerUseDefenseCardEarthquake(){
        Player p1 = new Player();
        int previousPoints=p1.points;
        p1.useDefenseCard(earthquake);
        assertEquals(p1.points, previousPoints-8);
    }

}
