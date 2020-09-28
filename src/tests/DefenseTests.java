package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import card_class.*;
import code.Deck.*;

public class DefenseTests {

    Card stonewall = new Card("STONE WALL","DEFENCE",5);
    Card woodenwall = new Card("WOODEN WALL","DEFENCE",2);
    Card barbedwire = new Card("BARBED WIRE","DEFENCE",8);
    Card reinforcedgate = new Card("REINFORCED GATE","DEFENCE",14);
    Card irondoor = new Card("IRON DOOR","DEFENCE",10);
    Card stellchains = new Card("STEEL CHAINS","DEFENCE",7);
    Card thunderstorm = new Card("THUNDERSTORM","DEFENCE",-4);
    Card tornado = new Card("TORNADO","DEFENCE",-6);
    Card flood = new Card("FLOOD","DEFENCE",-3);
    Card earthquake = new Card("EARTHQUAKE","DEFENCE",-8);


    @Test
    public void PlayerUseDefenseCardStoneWall(){
        Player p1 = new Player();
        Card card = new Card("STONE WALL");
        p1.PickingCard(stonewall);
        int previousPoints=p1.points;
        p1.useDefenseCard(stonewall);
        assertEquals(p1.points, previousPoints+5);
    }

    @Test
    public void PlayerUseDefenseCardWoodenWall(){
        Player p1 = new Player();
        Card card = new Card("WOODEN WALL");
        p1.PickingCard(woodenwall);
        int previousPoints=p1.points;
        p1.useDefenseCard(woodenwall);
        assertEquals(p1.points, previousPoints+2);
    }

    @Test
    public void PlayerUseDefenseCardBarbedWire(){
        Player p1 = new Player();
        Card card = new Card("BARBED WIRE");
        p1.PickingCard(barbedwire);
        int previousPoints=p1.points;
        p1.useDefenseCard(barbedwire);
        assertEquals(p1.points, previousPoints+8);
    }

    @Test
    public void PlayerUseDefenseCardReinforcedGate(){
        Player p1 = new Player();
        Card card = new Card("REINFORCED GATE");
        p1.PickingCard(reinforcedgate);
        int previousPoints=p1.points;
        p1.useDefenseCard(reinforcedgate);
        assertEquals(p1.points, previousPoints+14);
    }

    @Test
    public void PlayerUseDefenseCardIronDoor(){
        Player p1 = new Player();
        Card card = new Card("IRON DOOR");
        p1.PickingCard(irondoor);
        int previousPoints=p1.points;
        p1.useDefenseCard(irondoor);
        assertEquals(p1.points, previousPoints+10);
    }

    @Test
    public void PlayerUseDefenseCardSteelChains(){
        Player p1 = new Player();
        Card card = new Card("STEEL CHAINS");
        p1.PickingCard(stellchains);
        int previousPoints=p1.points;
        p1.useDefenseCard(stellchains);
        assertEquals(p1.points, previousPoints+7);
    }

    @Test
    public void PlayerUseDefenseCardThunderStorm(){
        Player p1 = new Player();
        Card card = new Card("THUNDERSTORM");
        p1.PickingCard(thunderstorm);
        int previousPoints=p1.points;
        p1.useDefenseCard(thunderstorm);
        assertEquals(p1.points, previousPoints-4);
    }

    @Test
    public void PlayerUseDefenseCardTornado(){
        Player p1 = new Player();
        Card card = new Card("TORNADO");
        p1.PickingCard(tornado);
        int previousPoints=p1.points;
        p1.useDefenseCard(tornado);
        assertEquals(p1.points, previousPoints-6);
    }

    @Test
    public void PlayerUseDefenseCardFlood(){
        Player p1 = new Player();
        Card card = new Card("FLOOD");
        p1.PickingCard(flood);
        int previousPoints=p1.points;
        p1.useDefenseCard(flood);
        assertEquals(p1.points, previousPoints-3);
    }

    @Test
    public void PlayerUseDefenseCardEarthquake(){
        Player p1 = new Player();
        Card card = new Card("EARTHQUAKE");
        p1.PickingCard(earthquake);
        int previousPoints=p1.points;
        p1.useDefenseCard(earthquake);
        assertEquals(p1.points, previousPoints-8);
    }

}
