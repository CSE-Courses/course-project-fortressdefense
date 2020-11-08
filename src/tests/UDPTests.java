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
        String echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
    
    @Test
    public void serverStartEnd() {
        String echo = client.pingServer();
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
        echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
   
    @Test
    public void clientRecievesPrivate() {
    	server.close();
        Game game = new Game();
		Player player = new Player("host");
		game.PlayerList.add(player);
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
		model.SetAccessType(AccessType.Private.toString());
		model.SetPassword("Secret");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        String echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
    
    @Test
    public void serverStartEndPrivate() {
    	server.close();
        Game game = new Game();
		Player player = new Player("host");
		game.PlayerList.add(player);
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
		model.SetAccessType(AccessType.Private.toString());
		model.SetPassword("Secret");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        String echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
        server.close();
        
        game = new Game();
		player = new Player("host");
		game.PlayerList.add(player);
		model = new ServerModel(game);
		model.SetHostName("test game");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
    
    @Test
    public void serverManyPlayers() {
    	server.close();
        Game game = new Game();
        for (int i = 1; i < 7; i++) {
        	game.PlayerList.add(new Player("p" + i));
        }
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
		model.SetAccessType(AccessType.Private.toString());
		model.SetPassword("Secret");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        String echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
    
    @Test
    public void clientRecievesPrivate2() {
    	server.close();
        Game game = new Game();
		Player player = new Player("host");
		Player p2 = new Player("p2");
		game.PlayerList.add(player);
		game.PlayerList.add(p2);
		ServerModel model = new ServerModel(game);
		model.SetHostName("test game");
		model.SetAccessType(AccessType.Private.toString());
		model.SetPassword("Secret");
        server = new BroadcastGame(GameConstants.udpPort, model);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(server);
        client = new FindGame();
        String echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
        game.PlayerList.remove(p2);
        echo = client.pingServer();
        assertEquals(server.createMessage(), echo);
    }
 
    @After
    public void tearDown() {
        server.close();
        client.close();
    }
    
}
