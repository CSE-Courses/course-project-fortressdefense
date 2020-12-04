package code.Socket;

import code.*;
import code.card_class.CardType;
import gui.attackPhase;
import gui.drawPhase;
import gui.drawPhaseOtherPlayer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Server implements Runnable{
    private ArrayList<Worker> clientList = new ArrayList<Worker>();
    private ServerModel model;
    private Boolean ongoing;
    private ServerSocket serverSocket;
    private JTextArea chat;
    private RSA encryption;
    private String turn;
    private JFrame mainFrame;
    private drawPhase drawPhase;
    private drawPhaseOtherPlayer waitingDraw;
    private int round;
    private attackPhase attackPhase;
    private GamePhase phase;
    private String serverPlayerName;
    private JPanel mainPanel;

    private final int serverPort;

    public Server(int serverPort, ServerModel model, JTextArea chat, JFrame mainFrame, JPanel mainPanel) {
        this.serverPort = serverPort;
        this.model = model;
        this.chat = chat;
        this.mainFrame = mainFrame;
        this.serverPlayerName = this.model.getPlayers().get(0).PlayerName;
        this.mainPanel = mainPanel;
        encryption = new RSA();
    }

    public ArrayList<Worker> getWorkerList() {
        return clientList;
    }

    /**
     * Runs server
     * @author Hoahua Feng, Andrew Jank
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(serverPort);
            ongoing = true;
            while(ongoing) {
            	if (model.GetCurrentPlayers() < model.GetMaxPlayers()) {
                    Socket clientSocket = serverSocket.accept();
                    Worker worker = new Worker(this, clientSocket);
                    clientList.add(worker);
                    worker.start();
            	}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch turn for draw phase #97
     * need modify for attack phase
     * @author Hoahua Feng
     */
    public void nextTurn(){
        if(turn == null){
            return;
        }
        else {
            int index;
            for(Player player : this.getModel().getPlayers()){
                if(player.PlayerName.equals(turn)){
                    index = this.getModel().getPlayers().indexOf(player);
                    if(index + 1 < this.getModel().getPlayers().size()){
                        index += 1;
                        turn = this.getModel().getPlayers().get(index).PlayerName;
                        for(Worker worker : this.getWorkerList()){
                            String toClientCmd = Command.GetTurn + " " + turn + " " + round + " " + phase + "\n";
                            worker.send(toClientCmd);
                        }
                        
                        if (phase == GamePhase.Draw) {
    						drawPhase.GetPanel().setVisible(false);
    					    mainFrame.remove(drawPhase.GetPanel());
    					    if (waitingDraw != null) {
                                waitingDraw.GetPanel().setVisible(false);
        					    mainFrame.remove(waitingDraw.GetPanel());
    					    }
    					    mainFrame.repaint();
    					    if (this.getModel().getPlayers().get(0).PlayerName.equals(this.serverPlayerName)) {
        					    waitingDraw = new drawPhaseOtherPlayer(mainFrame, this, null, this.getModel().getPlayers().get(0).getHand());
        					    mainFrame.add(waitingDraw.GetPanel());
    					    }else {
        					    waitingDraw = new drawPhaseOtherPlayer(mainFrame, this, null, new Hand());
        					    mainFrame.add(waitingDraw.GetPanel());
    					    }

                        }else if (phase == GamePhase.Attack) {
                            attackPhase.getPanel().setVisible(false);
                            mainFrame.remove(attackPhase.getPanel());
                            mainFrame.repaint();
    					    attackPhase = new attackPhase(mainFrame, this, null);
    					    mainFrame.add(attackPhase.getPanel());
                        }
                    }
                    else {
                    	this.round +=1;
                    	if (round > 8 && phase == GamePhase.Draw) {
                    		round = 1;
                    		phase = GamePhase.Attack;
                    		turn = this.model.getPlayers().get(0).PlayerName;
                    		String playerListAndHealth = "";
                    		for (Player p : this.getModel().getPlayers()) {
                    			playerListAndHealth += " " + p.PlayerName + " " + p.points;
                    		}
                            for(Worker worker : this.getWorkerList()){
                                String toClientCmd = Command.StartAttackPhase + " " + turn + " " + round + playerListAndHealth + "\n";
                                worker.send(toClientCmd);
                            }
                    		
    					    if (waitingDraw != null) {
                                waitingDraw.GetPanel().setVisible(false);
        					    mainFrame.remove(waitingDraw.GetPanel());
    					    }
    						drawPhase.GetPanel().setVisible(false);
    					    mainFrame.remove(drawPhase.GetPanel());
    					    mainFrame.repaint();
    					    attackPhase = new attackPhase(mainFrame, this, null);
    					    mainFrame.add(attackPhase.getPanel());
                    	} 
                    	else if (round > 8 && phase == GamePhase.Attack){
                    		round = 1;
                    		phase = GamePhase.Draw;
                    		
                     		// remove players who lost
                    		for (int i = 0; i < this.model.getPlayers().size(); i++) {
                    			if (player.points <= 0) {
                    				this.model.getPlayers().remove(i);
                    			}
                    		}
                    		
                    		if (this.getModel().getPlayers().size() == 1) {
                        		turn = this.model.getPlayers().get(0).PlayerName;
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.GameOver + " " + turn + "\n";
                                    worker.send(toClientCmd);
                                }
                                
                                attackPhase.showWinner(turn, serverPlayerName);
                                this.close(true);
                    		}
                    		else if (this.getModel().getPlayers().size() == 0){
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.GameOver + "\n";
                                    worker.send(toClientCmd);
                                }
                                
                                attackPhase.showWinner("", serverPlayerName);
                                this.close(true);
                    		}
                    		else {
                        		turn = this.model.getPlayers().get(0).PlayerName;
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.StartDrawPhase + " " + worker.getHealth() + " " + turn + " " + round + "\n";
                                    worker.send(toClientCmd);
                                }
                                
                                attackPhase.getPanel().setVisible(false);
                                mainFrame.remove(attackPhase.getPanel());
                                mainFrame.repaint();
        					    if (turn.equals(this.serverPlayerName)){
            					    drawPhase = new drawPhase(mainFrame, this, null);
            					    mainFrame.add(drawPhase.GetPanel());
        					    }else {
            					    waitingDraw = new drawPhaseOtherPlayer(mainFrame, this, null, new Hand());
            					    mainFrame.add(waitingDraw.GetPanel());
        					    }
                    		}
                    	}
                    	else if (phase == GamePhase.Attack) {
                    		// remove players who lost
                    		for (int i = 0; i < this.model.getPlayers().size(); i++) {
                    			if (this.getModel().getPlayers().get(i).points <= 0) {
                    				this.model.getPlayers().remove(i);
                    			}
                    		}
                    		
                           	turn = this.getModel().getPlayers().get(0).PlayerName;
                           	if (this.getModel().getPlayers().size() == 1) {
                        		turn = this.model.getPlayers().get(0).PlayerName;
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.GameOver + " " + turn + "\n";
                                    worker.send(toClientCmd);
                                }
                                
                                attackPhase.showWinner(turn, serverPlayerName);

                	            this.mainFrame.remove(attackPhase.getPanel());
                	            this.mainFrame.repaint();
                           		this.close(true);
                    		}
                    		else if (this.getModel().getPlayers().size() == 0){
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.GameOver + "\n";
                                    worker.send(toClientCmd);
                                }
                                
                                attackPhase.showWinner("", serverPlayerName);

                	            this.mainFrame.remove(attackPhase.getPanel());
                	            this.mainFrame.repaint();
                           		this.close(true);
                    		}else {
                                for(Worker worker : this.getWorkerList()){
                                    String toClientCmd = Command.GetTurn + " " + turn + " " + round + " " + phase + "\n";
                                    worker.send(toClientCmd);
                                }
                        		attackPhase.getPanel().setVisible(false);
                                mainFrame.remove(attackPhase.getPanel());
                                mainFrame.repaint();
        					    attackPhase = new attackPhase(mainFrame, this, null);
        					    mainFrame.add(attackPhase.getPanel());
                           	}
                    	}
                    	else if (phase == GamePhase.Draw) {
                        	turn = this.getModel().getPlayers().get(0).PlayerName;
                            for(Worker worker : this.getWorkerList()){
                                String toClientCmd = Command.GetTurn + " " + turn + " " + round + " " + phase + "\n";
                                worker.send(toClientCmd);
                            }
                            
                            if (drawPhase != null) {
        						drawPhase.GetPanel().setVisible(false);
        					    mainFrame.remove(drawPhase.GetPanel());
                            }
    					    if (waitingDraw != null) {
                                waitingDraw.GetPanel().setVisible(false);
        					    mainFrame.remove(waitingDraw.GetPanel());
    					    }
    					    mainFrame.repaint();
    					    if (turn.equals(this.serverPlayerName)){
        					    drawPhase = new drawPhase(mainFrame, this, null);
        					    mainFrame.add(drawPhase.GetPanel());
    					    }else {
        					    waitingDraw = new drawPhaseOtherPlayer(mainFrame, this, null, new Hand());
        					    mainFrame.add(waitingDraw.GetPanel());
    					    }

                    	}
                        

                    }
                    break;
                }
            }
        }
    }

    public String getTurn(){
        if(turn != null) {
            return turn;
        }
        return null;
    }

    public void removeWorker(Worker serverWorker) {
    	clientList.remove(serverWorker);
    }
    
    public ServerModel getModel() {
    	return model;
    }
    
    /**
     * Closes server connection and sends shutdown to all users
     * @author Hoahua Feng, Andrew Jank
     */
    public void close(Boolean sendToMain) {
    	try {
        	ongoing = false;
        	shutdown();
        	chat.setText("");
			serverSocket.close();
			if (sendToMain) {
	            mainPanel.setVisible(true);
	    		mainFrame.getContentPane().removeAll();
	    		mainFrame.getContentPane().add(mainPanel);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private void shutdown() {
        for(Worker worker : clientList) {
            worker.send(Command.Shutdown.toString() + "\n");
        }
	}
	
	public void message(String msg) {
		String message = this.serverPlayerName + ": " + msg + "\n";
        for(Worker worker : clientList) {
            worker.send(Command.Message.toString() + " " + message);
        }
        
        chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + message + "\n");
	}
	
	public void start() {
	    //#97 turn for draw, start draw phase, set turn to first player in client list
		round = 1;
		phase = GamePhase.Draw;
	    turn = this.getModel().getPlayers().get(0).PlayerName;
	    for(Worker worker : clientList) {
	        //Start draw phase, client[0]'s turn
		    worker.send(Command.Start.toString() + " " + worker.getHealth() + " " + turn + " " + round + "\n");
	    }
	   
	    drawPhase = new drawPhase(mainFrame, this, null);
	    this.mainFrame.add(drawPhase.GetPanel());
	}
	
	public void setChat(JTextArea chatBox) {
		this.chat = chatBox;
	}
	
	public JTextArea getChat() {
		return chat;
	}
	
	public RSA getRSA() {
		return encryption;
	}
	
	public void draw(CardType type) {
		switch (type) {
			case Attack:
				model.getPlayers().get(0).getHand().Draw(model.getGame().AttackDeck);
				break;
			case Defense:
				model.getPlayers().get(0).getHand().Draw(model.getGame().DefenseDeck);
				break;
			default:
				break;
		}
	}
	
	public void discard(UUID id) {
		for (int i = 0; i < model.getPlayers().get(0).getHand().Size(); i++) {
			if (model.getPlayers().get(0).getHand().Select(i).getID().equals(id)){
				model.getPlayers().get(0).getHand().Remove(model.getPlayers().get(0).getHand().Select(i));
				break;
			}
		}
	}
	
	public int getRound() {
		return round;
	}
	
	public String getPlayerName() {
		return this.serverPlayerName;
	}
	
	public int getHealth() {
		if (this.model.getPlayers().get(0).PlayerName.equals(this.serverPlayerName)) {
			return this.model.getPlayers().get(0).points;
		}else {
			return 0;
		}
	}
	
	public Hand getHand() {
		if (this.model.getPlayers().get(0).PlayerName.equals(this.serverPlayerName)) {
			return this.model.getPlayers().get(0).getHand();
		}else {
			return new Hand();
		}
	}
	
	public HashMap<String, String> getPlayerData() {
		HashMap<String, String> retVal = new HashMap<String, String>();
		for (Player p : this.model.getPlayers()) {
			retVal.put(p.PlayerName, Integer.toString(p.points));
		}
		
		return retVal;
	}
}