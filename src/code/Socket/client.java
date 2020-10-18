package code.Socket;

import code.Deck.Player;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class client {
    private static data_pack Data = new data_pack();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ObjectOutputStream output = null;
    private static ObjectInputStream input = null;
    private static InputStreamReader isr = null;
    private static OutputStreamWriter command_to_server = null;
    private static BufferedWriter client_command = null;

    private static void receive_from_server(Socket server) throws IOException, InterruptedException, ClassNotFoundException {
        command_to_server = new OutputStreamWriter(server.getOutputStream());
        client_command = new BufferedWriter(command_to_server);
        client_command.write("client_pull" + "\n");
        client_command.flush();
        TimeUnit.SECONDS.sleep(1);
        input = new ObjectInputStream(new BufferedInputStream(server.getInputStream()));
        Object in = input.readObject();
        System.out.println("[Server] Received data from Server...");
        if (in != null) {
            Data = (data_pack) in;
            for (Player value : Data.getPlayer_list()) {
                System.out.println("\t \t " + value.PlayerName + ": " + value.points + " HP");
            }
            System.out.println("\t \t Recent activity: "+Data.getMessage());
        }
    }

    private static void send_to_server(Socket server) throws IOException, InterruptedException {
        command_to_server = new OutputStreamWriter(server.getOutputStream());
        client_command = new BufferedWriter(command_to_server);
        client_command.write("client_push" + "\n");
        client_command.flush();
        TimeUnit.SECONDS.sleep(1);
        output = new ObjectOutputStream(server.getOutputStream());
        output.writeObject(Data);
        output.flush();
        System.out.println("[Server] Update and Push to Server...");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Demo\nEnter Your Name");
        String player_name = "";
        player_name= reader.readLine();
        Player player = new Player(player_name);
        System.out.println("[Client] Initiated Player: " + player_name);

        String host_address = "172.20.5.78";
        int port = 16225;
        Socket server = new Socket(host_address, port);
        System.out.println("[Client] Connected to Server" + server.getInetAddress() + ": " + port);
        receive_from_server(server);
        Data.add_player(player);
        Data.write_message("player "+player_name+" joined...");
        send_to_server(server);
        System.out.println("[Client] Player Joined");

        try(server){
            boolean ongoing = true;
            String command;
            while (ongoing){
                while (!reader.ready()){
                    Thread.sleep(4000);
                    System.out.println("\n[Client] Auto Refresh Every 5 Sec");
                    receive_from_server(server);
                }
                command = reader.readLine();


                switch (command){
                    case "pull":
                        receive_from_server(server);
                        break;
                    case "push":
                        send_to_server(server);
                        break;
                    case "quit":
                        Data.del_player(player);
                        Data.write_message("Player "+ player_name + " quited");
                        ongoing = false;
                    default:
                }

                if(command.equals("attack")) {
                    System.out.println("who?");
                    String att_name = reader.readLine();
                    System.out.println("How much damage?");
                    int damage = Integer.parseInt(reader.readLine());
                    for (Player value : Data.getPlayer_list()) {
                        if (value.PlayerName.equals(att_name)) {
                            value.points -= damage;
                            Data.write_message(player_name + " deals " + damage + " damage to " + att_name);
                        }
                    }
                    send_to_server(server);
                }
                command = null;
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
