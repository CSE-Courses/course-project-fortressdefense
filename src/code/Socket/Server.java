package code.Socket;

import code.*;
import code.Socket.Game_Phase.data_pack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private ArrayList<Worker> clientList = new ArrayList<Worker>();
    private data_pack Data = new data_pack();
    private ServerModel model;
    private Boolean ongoing;
    private ServerSocket serverSocket;

    private final int serverPort;

    public Server(int serverPort, ServerModel model) {
        this.serverPort = serverPort;
        this.model = model;
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
}