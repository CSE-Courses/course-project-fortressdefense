package code.Socket;

import code.*;

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
    
    public void close() {
    	try {
        	ongoing = false;
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
    
    private static void send_to_client(Socket client) throws IOException {
        assert client != null;
        to_client = new ObjectOutputStream(client.getOutputStream());
        to_client.writeObject(Data);
        to_client.flush();
    }

    private static void receive_from_client(Socket client) throws IOException, ClassNotFoundException, InterruptedException {
        assert client != null;
        from_client = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        Object in = from_client.readObject();
        if (in != null) {
            Data = (data_pack) in;
        }
        to_every_client();
    } 


    private static void client_join(Socket client) throws IOException, ClassNotFoundException, InterruptedException {
        assert client != null;
        from_client = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        Object in = from_client.readObject();
        if (in != null) {
            Player player = (Player) in;
            Data.add_player(player);
            Data.write_message("Player " + player.PlayerName + " joined");
            System.out.println("[Server] Player \"" + player.PlayerName + "\" joined");
        }
        to_every_client();
    }

    private static void to_every_client() throws IOException {
        for(Socket value : Thread_list){
            if(!value.isClosed()){
                command_to_client = new OutputStreamWriter(value.getOutputStream());
                server_command = new BufferedWriter(command_to_client);
                server_command.write("pull" + "\n");
                server_command.flush();
                System.out.println("[Server] Send to client \t" + value.getInetAddress());
            }
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try (client) {
                    boolean ongoing = true;
                    while (ongoing) {
                        if(client.isClosed()){
                            Thread_list.remove(client);
                        }
                        command_from_client = new InputStreamReader(client.getInputStream());
                        client_command = new BufferedReader(command_from_client);
                        command = client_command.readLine();
                        System.out.println("[Client] "+ LocalTime.now() +
                                "\t\t" + client.getInetAddress() +"\t\t" + command);

                        if(command != null) {
                            switch (command) {
                                case "player_join":
                                    client_join(client);
                                    break;
                                case "client_pull":
                                    send_to_client(client);
                                    break;
                                case "client_push":
                                    receive_from_client(client);
                                    break;
                                case "GAME_OVER":
                                    ongoing = false;
                                    break;
                                default:
                                    System.out.println("[Client] * Error: Command Undefined");
                            }
                        }
                        command = null; //reset
                    }
                } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        assert from_client != null;
                        from_client.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        to_client.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }).start();
    }
    */
}