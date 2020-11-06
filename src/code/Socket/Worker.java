package code.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;

import code.*;

public class Worker extends Thread{
	private final Socket clientSocket;
    private final Server server;
    private OutputStream outputStream;
    private String username;
    private Player player;

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
                	default:
                		break;
                }
            }
        }

        clientSocket.close();
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
            
            server.getChat().setText(server.getChat().getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + message + "\n");
        }
}
	
	private String getUsername() {
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
