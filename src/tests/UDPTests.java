package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.*;
import code.ServerModel;
import code.Socket.*;


public class UDPTests {
	private FindGame client;
	private BroadcastGame server;
	
	@Before
    public void setup(){
		Player player = new Player("host");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		ServerModel model = new ServerModel(players);
		model.SetHostName("test game");
        server = new BroadcastGame(GameConstants.port, model);
        
        server.start();
        client = new FindGame();
    }
 
    @Test
    public void clientRecieves() {
        String echo = client.sendEcho("whoami");
        assertEquals(server.createMessage(), echo);
        echo = client.sendEcho("server is working");
        assertEquals("server is working", echo);
    }
 
    @After
    public void tearDown() {
        server.close();
        client.close();
    }
    
}
