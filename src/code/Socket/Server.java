package code.Socket;

import code.*;
import code.Socket.Game_Phase.data_pack;
import code.card_class.CardType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JTextArea;

public class Server implements Runnable{
    private ArrayList<Worker> clientList = new ArrayList<Worker>();
    private data_pack Data = new data_pack();
    private ServerModel model;
    private Boolean ongoing;
    private ServerSocket serverSocket;
    private JTextArea chat;
    private RSA encryption;
    private String turn;
    private String phase;

    private final int serverPort;

    public Server(int serverPort, ServerModel model, JTextArea chat) {
        this.serverPort = serverPort;
        this.model = model;
        this.chat = chat;
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
            for(Worker worker : clientList){
                if(worker.getUsername().equals(turn)){
                    index = clientList.indexOf(worker);
                    if(index + 1 < getWorkerList().size()){
                        index += 1;
                        turn = getWorkerList().get(index).getUsername();
                    }
                    else {
                        turn = null; // end draw phase
                    }
                    break;
                }
            }
        }
        System.out.println("Current turn: " + turn);
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
    public void close() {
    	try {
        	ongoing = false;
        	shutdown();
        	chat.setText("");
			serverSocket.close();
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
		String message = model.getPlayers().get(0).PlayerName + ": " + msg + "\n";
        for(Worker worker : clientList) {
            worker.send(Command.Message.toString() + " " + message);
        }
        
        chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + message + "\n");
	}
	
	public void start() {
        //#97 turn for draw, start draw phase, set turn to first player in client list
        phase = "draw";
        turn = clientList.get(0).getUsername();
       for(Worker worker : clientList) {
           //Start draw phase, client[0]'s turn
    	   worker.send(Command.Start.toString() + " " + worker.getHealth() + " " + turn + "\n");
        }
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
				model.getPlayers().get(1).getHand().Draw(model.getGame().AttackDeck);
				break;
			case Defense:
				model.getPlayers().get(1).getHand().Draw(model.getGame().DefenseDeck);
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
}