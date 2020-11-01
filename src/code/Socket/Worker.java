package code.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
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
                	default:
                		break;
                }
            }
        }

        clientSocket.close();
    }

    private void handleReady(String[] tokens) {
    	if (tokens.length >= 2) {
    		player.setReady(!player.getReady());
    		server.getModel().UpdatePlayerTextFields();
        }
	}

	private void handleLeave(String[] tokens) {
        try {
            server.removeWorker(this);
            server.getModel().getPlayers().remove(player);
            server.getModel().UpdatePlayerTextFields();
            ArrayList<Worker> workerList = server.getWorkerList();

           
            // send other online users current user's status
            String onlineMsg = this.getUsername() + " has left the game." + "\n";
            for(Worker worker : workerList) {
                if (!this.username.equals(worker.getUsername())) {
                    worker.send(onlineMsg);
                }
            }
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
            for(Worker worker : workerList) {
                if (worker.getUsername() != null) {
                    if (!this.username.equals(worker.getUsername())) {
                        String msg2 = worker.getUsername() + " has joined the game." + "\n";
                        send(msg2);
                    }
                }
            }

            // send other online users current user's status
            String onlineMsg = this.username + " has joined the game." + "\n";
            for(Worker worker : workerList) {
                if (!this.username.equals(worker.getUsername())) {
                    worker.send(onlineMsg);
                }
            }
        }
}
	
	private String getUsername() {
		return username;
	}
	
    private void send(String msg) {
        if (this.username != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
		
}