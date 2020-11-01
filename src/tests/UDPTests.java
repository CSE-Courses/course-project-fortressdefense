package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.*;
import code.ServerModel;
import code.Socket.*;


public class UDPTests {
	private FindGame client;
	private BroadcastGame server;
	private Executor executor;
	
	@Before
    public void setup(){
		Game game = new Game();
		Player player = new Player("host");
		game.PlayerList.add(player);
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
    }
 
    @Test
    public void clientRecieves() {
        String echo = client.sendEcho("whoami");
        assertEquals(server.createMessage(), echo);
        echo = client.sendEcho("server is working");
        assertEquals("server is working", echo);
    }
    
    @Test
    public void serverStartEnd() {
        String echo = client.sendEcho("whoami");
        assertEquals(server.createMessage(), echo);
        server.close();

        Game game = new Game();
		Player player = new Player("host");
		game.PlayerList.add(player);
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        echo = client.sendEcho("whoami");
        assertEquals(server.createMessage(), echo);
    }
 
 
    @After
    public void tearDown() {
        server.close();
        client.close();
    }
    
}
