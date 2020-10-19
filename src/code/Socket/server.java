package code.Socket;

import code.Deck.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class server {
    private static ArrayList<Socket> Thread_list= new ArrayList();
    private static data_pack Data = new data_pack();
    private static InputStreamReader command_from_client = null;
    private static BufferedReader client_command = null;
    private static OutputStreamWriter command_to_client = null;
    private static BufferedWriter server_command = null;
    private static ObjectInputStream from_client = null;
    private static ObjectOutputStream to_client = null;
    private static InputStreamReader reader;
    private static String command = null;

    public static void main(String[] args) throws IOException {
        int port = 16225;
        ServerSocket server = new ServerSocket(port);
        System.out.println("\n[Server] Server started at port " + port);
        while (true) {
            Socket socket = server.accept();
            Thread_list.add(socket);
            System.out.println("[Server] Client connected\t" + socket.getInetAddress());
            invoke(socket);
        }
    }

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
        for(Socket value : Thread_list){
            command_to_client = new OutputStreamWriter(value.getOutputStream());
            server_command = new BufferedWriter(command_to_client);
            server_command.write("pull" +"\n");
            server_command.flush();
            System.out.println("[Server] Send to client \t" + value.getInetAddress());
        }
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
        for(Socket value : Thread_list){
            command_to_client = new OutputStreamWriter(value.getOutputStream());
            server_command = new BufferedWriter(command_to_client);
            server_command.write("pull" +"\n");
            server_command.flush();
            System.out.println("[Server] Send to client \t" + value.getInetAddress());
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try (client) {
                    boolean ongoing = true;
                    while (ongoing) {
                        command_from_client = new InputStreamReader(client.getInputStream());
                        client_command = new BufferedReader(command_from_client);
                        command = client_command.readLine();
                        System.out.println("[Client] "+ LocalTime.now() +
                                "\t\t" + client.getInetAddress() +"\t\t" + command);

                        assert command != null;
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
}
