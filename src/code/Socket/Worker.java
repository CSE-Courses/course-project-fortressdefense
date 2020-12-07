package code.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.UUID;

import code.*;
import code.RSA.PublicKey;
import code.card_class.Card;
import code.card_class.CardType;
import code.card_class.SpecialCard;

public class Worker extends Thread{
	private final Socket clientSocket;
    private final Server server;
    private OutputStream outputStream;
    private String username;
    private Player player;
    private String thisTurn;

    public Worker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    /**
     * Runs client session
     */
    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles client to server commands
     * @throws IOException
     * @throws InterruptedException
     * @author Hoahua Feng, Andrew Jank
     */
    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader command_from_client = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = command_from_client.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0) {
                switch (Command.valueOf(tokens[0])) {
                	case Leave:
                        handleLeave(tokens);
                        break;
                	case Join:
                		handleJoin(tokens);
                		break;
                	case Ready:
                		handleReady(tokens);
                		break;
                	case Message:
                		handleMessage(tokens);
                		break;
	                case Password:
	                	handlePassword(tokens, command_from_client);
	                	break;
	                case PublicKey:
	                	handleKey();
 	                	break;
	                case Draw:
	                	handleDraw(tokens);
	                	break;
	                case Discard:
	                	handleDiscard(tokens);
	                	break;
                    case SwitchTurn:    //#97
                        handleSwitchTurn();
                        break;
                    case GetTurn:
                        handelGetTurn();
                        break;
                    case UseAttack:
                    	handleAttack(tokens);
                    	break;
                	default:
                		break;
                }
            }
        }
        clientSocket.close();
    }

    /**
     * #97 turn for draw phase
     */
    private void handleSwitchTurn(){
    	server.nextTurn();
    }

    private void handelGetTurn(){
        thisTurn = server.getTurn();
        String toClientCmd = Command.GetTurn + " " + thisTurn + "\n";
        send(toClientCmd);
    }
    
    private void handleDraw(String[] tokens) {
    	if (tokens.length > 1) {
    		switch (CardType.valueOf(tokens[1])) {
				case Attack:
					player.getHand().Draw(server.getModel().getGame().AttackDeck);
					Card card = player.getHand().Select(player.getHand().Size() - 1);
					String message = Command.Draw.toString() + " " + card.getCard_name().toString() + " " + card.getType() + " " + card.getDamage() + " " + card.getID() + "\n";
					this.send(message);
					break;
				case Defense:
					player.getHand().Draw(server.getModel().getGame().DefenseDeck);
					card = player.getHand().Select(player.getHand().Size() - 1);
					message = Command.Draw.toString() + " " + card.getCard_name().toString() + " " + card.getType() + " " + card.getDamage() + " " + card.getID() + "\n";
					this.send(message);
					break;
				default:
					break;
    		}
    	}
	}

    
    private void handleDiscard(String[] tokens) {
		for (int i = 0; i < player.getHand().Size(); i++) {
			if (player.getHand().Select(i).getID().equals(UUID.fromString(tokens[1]))){
				player.getHand().Remove(player.getHand().Select(i));
				break;
			}
		}
    }
    
    private Card findCard(String[] tokens) {
    	for (int i = 0; i < player.getHand().Size(); i++) {
			if (player.getHand().Select(i).getID().equals(UUID.fromString(tokens[1]))){
				return player.getHand().Select(i);
			}
		}
    	return null;
    }
    
    private Worker findWorker(String[] tokens) {
    	for (int i = 0; i < server.getWorkerList().size(); i++) {
			if (server.getWorkerList().get(i).getUsername().equals(tokens[2])){
				return server.getWorkerList().get(i);
			}
		}
    	return null;
	}
    
    private void handleAttack(String[] tokens) {
    	if (tokens.length > 1) {
    		Card card = findCard(tokens);
    		Worker worker = findWorker(tokens);
    		player.getHand().Remove(card);
    		if (worker == null && tokens[2].equals(server.getPlayerName())) {
        		switch (card.getType()) {
    				case Attack:
    					player.useAttackCard(card, server.getModel().getPlayers().get(0));
       					if(server.getModel().getPlayers().get(0).getHasArcherTower() > 0) {
    						String message = Command.UseAttack.toString() + " " + this.getPlayer().points + "\n";
    						this.send(message);
    					}
    					break;
    				case Defense:
    					player.useDefenseCard(card);
    					break;
    				case Special:
    					switch((SpecialCard) card.getCard_name()) {
    						case Archer_Tower:
    							player.useArcherTower();
    							break;
//    						case Trade:
//    							player.useTrade(card, wantedCard, oppoPlayer);
//    							message = Command.UseAttack.toString() + " " + card.getCard_name().toString() + " " + card.getType() + " " + card.getDamage() + "\n";
//    							this.send(message);
//    							break;
    						case Scout:
    							Hand oppHand = player.useScout(server.getModel().getPlayers().get(0));
    							String message = Command.Scout.toString();
    							for(Card oppCard : oppHand.getCards()) {
    								message += " " + oppCard.getCard_name().toString() + " " + oppCard.getType() + " " + oppCard.getDamage();
    							}
    							message += "\n";
    							this.send(message);
    							break;
    						default:
    							break;
    					}
    					break;
    				default:
    					break;
        		}
    		}else {
        		switch (card.getType()) {
    				case Attack:
    					player.useAttackCard(card, worker.getPlayer());
    					if(worker.getPlayer().getHasArcherTower() > 0) {
    						String message = Command.UseAttack.toString() + " " + this.getPlayer().points + "\n";
    						this.send(message);
    					}
    					String message = Command.UseAttack.toString() + " " + worker.getPlayer().points + "\n";
    					worker.send(message);
    					break;
    				case Defense:
    					player.useDefenseCard(card);
    					break;
    				case Special:
    					switch((SpecialCard) card.getCard_name()) {
    						case Archer_Tower:
    							player.useArcherTower();
    							break;
//    						case Trade:
//    							player.useTrade(card, wantedCard, oppoPlayer);
//    							message = Command.UseAttack.toString() + " " + card.getCard_name().toString() + " " + card.getType() + " " + card.getDamage() + "\n";
//    							this.send(message);
//    							break;
    						case Scout:
    							Hand oppHand = player.useScout(worker.getPlayer());
    							message = Command.Scout.toString();
    							for(Card oppCard : oppHand.getCards()) {
    								message += " " + oppCard.getCard_name().toString() + " " + oppCard.getType() + " " + oppCard.getDamage();
    							}
    							message += "\n";
    							this.send(message);
    							break;
    						default:
    							break;
    					}
    					break;
    				default:
    					break;
        		}
    		}
    	}
    }

	public Player getPlayer() {
		return player;
	}

	private void handleKey() {
		PublicKey key = server.getRSA().getPublicKey();
		/*
		System.out.println(new BigInteger(key.getE()).toString());
		System.out.println(new BigInteger(key.getN()).toString());
		System.out.println(new String(key.getN()));
		*/
		String message = Command.PublicKey.toString() + " " + new String(key.getE()) + " " + new String(key.getN()) + "\n";
		this.username = "";
		this.send(message);
	}

	private void handlePassword(String[] tokens, BufferedReader in) {
		if (tokens.length > 1) {
			try {
				while (in.ready()) {
					tokens[1] += "\r\n" + in.readLine();
				}
				String password = new String(server.getRSA().decrypt(tokens[1].getBytes()));
				String message = Command.Password + " " + server.getModel().GetPassword().equals(password) + "\n";
				this.send(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
     * Creates message to send to client about room
     * @return
     */
    private String createMessage() {

        try {
        	Socket ip = new Socket();
			ip.connect(new InetSocketAddress("google.com", 80));
			String message = server.getModel().GetHostName() + "/" + ip.getLocalAddress().getHostAddress() + "/" + server.getModel().GetCurrentPlayers() + 
					"/" + server.getModel().GetMaxPlayers() + "/" + server.getModel().GetAccessType();
			for (int i = 0; i < server.getModel().getPlayers().size(); i++) {
				message += "/" + server.getModel().getPlayers().get(i).PlayerName + "/" + server.getModel().getPlayers().get(i).getReady();
			}
			
			return message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "";
    }

	private void handleMessage(String[] tokens) {
        try {
            ArrayList<Worker> workerList = server.getWorkerList();

            // send other online users current user's status
            String message = String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n";
            for(Worker worker : workerList) {
                if (worker.getUsername() != null) {
                	worker.send(Command.Message.toString() + " " + message);
                }
            }
            
            server.getChat().setText(server.getChat().getText() + new Time(System.currentTimeMillis()) + "\n" + message + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleReady(String[] tokens) {
		player.setReady(!player.getReady());
		server.getModel().UpdatePlayerTextFields();
		
        // send other online users current user's status
        for(Worker worker : server.getWorkerList()) {
            if (worker.getUsername() != null) {
                if (!this.username.equals(worker.getUsername())) {
                    worker.send(Command.Ready.toString() + " " + this.getUsername() + "\n");
                }
            }
        }
	}

	private void handleLeave(String[] tokens) {
        try {
            server.removeWorker(this);
            server.getModel().getPlayers().remove(player);
            server.getModel().UpdatePlayerTextFields();
            ArrayList<Worker> workerList = server.getWorkerList();

            // send other online users current user's status
            String message = this.getUsername() + " has left the game.\n";
            for(Worker worker : workerList) {
                if (worker.getUsername() != null) {
                    if (!this.username.equals(worker.getUsername())) {
                    	worker.send(Command.Leave.toString() + " " + message);
                    }
                }
            }
            
            for(Worker worker : server.getWorkerList()) {
                if (worker.getUsername() != null) {
                    if (!this.username.equals(worker.getUsername())) {
                    	worker.send(Command.Refresh.toString() + " " + createMessage() + "\n");
                    }
                }
            }
            
            server.getChat().setText(server.getChat().getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + message + "\n");
            
			clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    private void handleJoin(String[] tokens) {
    	if (tokens.length >= 2) {
            this.username = String.join(" ", tokens).replaceAll(tokens[0], "").trim();
            player = new Player(this.username);
            server.getModel().getPlayers().add(player);
            server.getModel().UpdatePlayerTextFields();
            ArrayList<Worker> workerList = server.getWorkerList();

            // send current user all other online logins
            String message = this.getUsername() + " has joined the game.\n";
            for(Worker worker : workerList) {
                if (worker.getUsername() != null) {
                    if (!this.username.equals(worker.getUsername())) {
                        worker.send(Command.Join.toString() + " " + message);
                    }
                }
            }
            
            for(Worker worker : server.getWorkerList()) {
                if (worker.getUsername() != null) {
                    if (!this.username.equals(worker.getUsername())) {
                    	worker.send(Command.Refresh.toString() + " " + createMessage() + "\n");
                    }
                }
            }       

            this.send(Command.Refresh.toString() + " " + createMessage() + "\n");
            
            server.getChat().setText(server.getChat().getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + message + "\n");
        }
}
	
    public int getHealth()
    {
    	return player.points;
    }

    //before is private, change to public #97
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sends message to client from server
	 * @param msg
	 * @author Hoahua Feng, Andrew Jank
	 */
    public void send(String msg) {
        if (this.username != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
