package code.Socket;

import code.Command;
import code.GamePhase;
import code.Hand;
import code.RSA;
import code.RSA.PublicKey;
import code.card_class.*;
import gui.Join_Game;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client {
    private String name;
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;
    private Join_Game joinGame;
    private JTextArea chat;
    private String roomName;
    private Hand hand;
    private String currentTurn;
    private int currentRound;
    private Hand oppHand; 
    private HashMap<String, String> playerData = new HashMap<String, String>();
    
    
    public Client(String serverName, int serverPort, Join_Game joinGame, JTextArea chat, String name) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.joinGame = joinGame;
        this.chat = chat;
        this.name = name;
        this.hand = new Hand();
    }
    
    /**
     * Connects to server
     * @return whether server connection is valid
     * @author Hoahua Feng, Andrew Jank
     */
    public Boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            startMessageReader();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Joins game
     * @param playerName
     * @author Hoahua Feng, Andrew Jank
     */
    public void join(String playerName) {
        try {
            String cmd = Command.Join.toString() + " " + playerName + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Toggles player ready
     * @param playerName
     * @author Hoahua Feng, Andrew Jank
     */
    public void ready() {
        try {
            String cmd = Command.Ready.toString() + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Leaves game
     * @author Hoahua Feng, Andrew Jank
     */
    public void leave() {
        try {
            String cmd = Command.Leave.toString() + "\n";
			serverOut.write(cmd.getBytes());
			chat.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Closes client connection
     * @author Hoahua Feng, Andrew Jank
     */
    public void close() {
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    public void message(String playerName, String msg) {
		try {
			String cmd = Command.Message.toString() + " " + playerName + ": " + msg + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void draw(CardType type) {
    	try {
    		String cmd = Command.Draw.toString() + " " + type.toString() + "\n";
    		serverOut.write(cmd.getBytes());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void dicard(UUID id) {
    	try {
    		String cmd = Command.Discard.toString() + " " + id.toString() + "\n";
    		serverOut.write(cmd.getBytes());
    		for (int i = 0; i < hand.Size(); i++) {
    			if (hand.Select(i).getID().equals(id)){
    				hand.Remove(hand.Select(i));
    				break;
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    //#97 switch turn
	public void switchTurn(){
    	try {
			String cmd = Command.SwitchTurn.toString() + "\n";
			serverOut.write(cmd.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play(UUID id, String oppPlayer){
    	try {
			String cmd = Command.UseAttack.toString() + " " + id.toString() + " " + oppPlayer + "\n";
			serverOut.write(cmd.getBytes());
			
			// Kludge update player data for UI
			if (!oppPlayer.equals(name)) {
				int dmg = 0;
				for (Card card : this.getHand().getCards()) {
					if (card.getID().equals(id)) {
						dmg = card.getDamage();
					}
				}
				
				playerData.replace(oppPlayer, Integer.toString(Integer.parseInt(playerData.get(oppPlayer)) - dmg));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void playOpponent(UUID id) {
//		
//	}
    
    /**
     * Thread that reads all messages from server
     * @author Hoahua Feng, Andrew Jank
     */
    private void startMessageReader() {
        Thread t = new Thread() {
            @Override
            public void run() {
                readMessageLoop();
            }
        };
        t.start();
    }
    
    private int health;
    /**
     * reads all messages from server
     * @author Hoahua Feng, Andrew Jank
     */
    private void readMessageLoop() {
        try {
            String line;
            while ((line = bufferedIn.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens != null && tokens.length > 0) {
                    switch(Command.valueOf(tokens[0])) {
		                case Shutdown:
		                	JOptionPane.showMessageDialog(joinGame.getPanel(), "Connection to server lost!", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		                	joinGame.getBackButton().doClick();
		                	break;
		                case Refresh:
		                	joinGame.refreshTCP(String.join(" ", tokens).replaceAll(tokens[0], "").trim());
		                	joinGame.refresh_room_detail();
		                	break;
		                case Ready:
		                	if (joinGame.getRoom().getPlayer_status().get(String.join(" ", tokens).replaceAll(tokens[0], "").trim()).equals("Ready")){
			                	joinGame.getRoom().my_status(String.join(" ", tokens).replaceAll(tokens[0], "").trim(), 'c');
		                	}else {
		                		joinGame.getRoom().my_status(String.join(" ", tokens).replaceAll(tokens[0], "").trim(), 'r');
		                	}
		                	joinGame.refresh_room_detail();
		                	break;
		                case Join:
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + 
		                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
		                	break;
		                case Leave:
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + 
				                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
		                	break;
		                case Message:
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + 
				                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
		                	break;

		                case Start:
		                	health = Integer.parseInt(tokens[1]);
		                	String temp_turn = tokens[2];
		                	currentTurn = temp_turn;
		                	currentRound = Integer.parseInt(tokens[3]);
							if(temp_turn.equals(name)){
								joinGame.startDrawPhase();
							}
							else {
								joinGame.waitForDrawPhase();
							}
		                	break;
						case GetTurn:
							currentTurn = tokens[1];
		                	currentRound = Integer.parseInt(tokens[2]);
		                	
		                	Iterator itr = playerData.entrySet().iterator();
		                	while (itr.hasNext()) {
		                		Entry entry = (Entry)itr.next();
		                		if (Integer.parseInt((String)entry.getValue()) <= 0) {
		                			itr.remove();
		                		}
		                	}
		                	
		                	switch (GamePhase.valueOf(tokens[3])) {
			                	case Draw:
									if(name.equals(tokens[1])) {
										joinGame.startDrawPhase();
									}
									else {
										joinGame.waitForDrawPhase();
									}
			                		break;
			                	case Attack:
			                		joinGame.startAttackPhase();
			                		break;
		                	}
	
							break;

		                case PublicKey:
		                	String password = (String)JOptionPane.showInputDialog(null, "Enter Password: ", "Fortress Defense", JOptionPane.PLAIN_MESSAGE);
		                	String keyN = String.join(" ", tokens).replaceAll(tokens[0], "").replaceAll(tokens[1], "").substring(2);
		                	while (bufferedIn.ready()) {
		                		line = bufferedIn.readLine();
		                		keyN += "\r\n" + line;
		                	}
		                	/*
		                	System.out.println(new BigInteger(tokens[1].getBytes()).toString());
		            		System.out.println(new BigInteger(keyN.getBytes()).toString());
		            		System.out.println(keyN);
		            		*/
		            		byte[] test = new RSA().encrypt(password.getBytes(), new PublicKey(tokens[1].getBytes(), keyN.getBytes()));
		        			String encryption = new String(test);
		        			/*System.out.println(test);
		        			System.out.println(encryption);*/
		        			try {
		        				String cmd = Command.Password.toString() +  " " + encryption + "\n";
		        				serverOut.write(cmd.getBytes());
		        			} catch (IOException e) {
		        				// TODO Auto-generated catch block
		        				e.printStackTrace();
		        			}
		                	break;
		                case Password:
		                	if (tokens.length > 1) {
		                		if (Boolean.valueOf(tokens[1])) {
	                                this.joinGame.getGL().get(this.roomName).join(this.name);
	                                this.joinGame.setRoomName(this.roomName);
	                                this.joinGame.getFeedback().setText("You Entered " + this.roomName);
	                                this.join(this.name);
		                		}else {
			                		JOptionPane.showMessageDialog(null, "Invalid password.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		                			this.close();
		                		}
		                		
		                	}else {
		                		JOptionPane.showMessageDialog(null, "Invalid password.", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		                	}
		                	break;
		                case Draw:
		                	if (tokens.length > 4) {
		                		ICardEnum type;
		                		try {
		                			type = AttackCard.valueOf(tokens[1]);
		                		} catch (IllegalArgumentException e) {
		                       		try {
			                			type = DefenseCard.valueOf(tokens[1]);
			                		} catch (IllegalArgumentException e1) {
			                			type = SpecialCard.valueOf(tokens[1]);
			                		}
		                		}
		                		
		                		Card card = new Card(type, CardType.valueOf(tokens[2]), Integer.parseInt(tokens[3]));
		                		card.setID(UUID.fromString(tokens[4]));
		                		hand.Add(card);
		                	}
		                	break;
		                case StartAttackPhase:
		                	playerData = new HashMap<String, String>();
		                	for (int i = 3; i < tokens.length; i+=2) {
		                		if (tokens[i].equals(this.name)) {
		                			health = Integer.parseInt(tokens[i+1]);
		                		}
		                		playerData.put(tokens[i], tokens[i+1]);
		                	}
		                	temp_turn = tokens[1];
		                	currentTurn = temp_turn;
		                	currentRound = Integer.parseInt(tokens[2]);
							joinGame.startAttackPhase();
		                	break;
		                case StartDrawPhase:
		                	health = Integer.parseInt(tokens[1]);
		                	temp_turn = tokens[2];
		                	currentTurn = temp_turn;
		                	currentRound = Integer.parseInt(tokens[3]);
		                	hand.EndDrawPhase();
		                	if (currentTurn.equals(name)) {
								joinGame.startDrawPhase();
							}
							else {
								joinGame.waitForDrawPhase();
							}
		                	break;
		                case GameOver:
		                	if (tokens.length == 1) {
		                		// draw
		                		joinGame.winner("", name);
		                	}else {
		                		joinGame.winner(tokens[1], name);
		                	}
		                	break;
		                case UseAttack:
		                	health = Integer.parseInt(tokens[1]);
		                	break;
		                case Scout:
		                	for (int i = 1; i < tokens.length; i+=3) {
		                		ICardEnum type;
		                		try {
		                			type = AttackCard.valueOf(tokens[i]);
		                		} catch (IllegalArgumentException e) {
		                       		try {
			                			type = DefenseCard.valueOf(tokens[i]);
			                		} catch (IllegalArgumentException e1) {
			                			type = SpecialCard.valueOf(tokens[i]);
			                		}
		                		}
		                		oppHand.Add(new Card(type, CardType.valueOf(tokens[i+1]), Integer.parseInt(tokens[i+2])));
		                	}
		                	break;
                    	default:
                    		break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int getHealth()
    {
    	return health;
    }
    
    public JTextArea getChat() {
    	return chat;
    }
    
    public void setChat(JTextArea chatBox) {
    	chat = chatBox;
    }
    
    public String getName() {
    	return name;
    }

	public void getPublicKey(String roomName) {
		try {
			this.roomName = roomName;
			String cmd = Command.PublicKey.toString() + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public Hand getHand() {
		return hand;
	}

	public Object obtainTurn(){
    	if(currentTurn != null){
    		return currentTurn;
		}
		return null;
	}
	
	public int getRound() {
		return currentRound;
	}
	
	public HashMap<String, String> getPlayerData() {
		return playerData;
	}

	public Hand getOppHand() {
		return oppHand;
	}

	public void setOppHand(Hand oppHand) {
		this.oppHand = oppHand;
	}
	
	public Join_Game getJoinGame() {
		return this.joinGame;
	}
}
