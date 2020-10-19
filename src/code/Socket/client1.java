package code.Socket;

import code.Deck.Player;
import org.junit.Assert;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class client1 {
    private static data_pack Data = new data_pack();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ObjectOutputStream output = null;
    private static ObjectInputStream input = null;
    private static InputStreamReader command_from_server = null;
    private static BufferedReader server_command = null;
    private static OutputStreamWriter command_to_server = null;
    private static BufferedWriter client_command = null;
    private static String player_name = "";
    private static String command = null;

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
            System.out.println("\t \t Round: " + Data.getRound() + "\tTurn: " + Data.getTurn());
            for (Player value : Data.getPlayer_list()) {
                System.out.println("\t \t " + value.PlayerName + ": " + value.points + " HP");
            }
            System.out.println("\t \t Recent activity: "+Data.getMessage());
        }
    }

    private static void send_to_server(Socket server) throws IOException, InterruptedException {
        Data.next_turn();
        command_to_server = new OutputStreamWriter(server.getOutputStream());
        client_command = new BufferedWriter(command_to_server);
        client_command.write("client_push" + "\n");
        client_command.flush();
        TimeUnit.SECONDS.sleep(1);
        output = new ObjectOutputStream(server.getOutputStream());
        Data.next_turn();
        output.writeObject(Data);
        output.flush();
        System.out.println("[Server] Update and Push to Server...");
    }

    private static void join_server (Socket server, Player player) throws IOException, InterruptedException {
        command_to_server = new OutputStreamWriter(server.getOutputStream());
        client_command = new BufferedWriter(command_to_server);
        client_command.write("player_join" + "\n");
        client_command.flush();
        TimeUnit.SECONDS.sleep(1);
        output = new ObjectOutputStream(server.getOutputStream());
        output.writeObject(player);
        output.flush();
        System.out.println("[Client] Send Player Info to Server...");
    }

    public static void main(String[] args) throws Exception {
        while(player_name.equals("")) {
            System.out.println("Demo\nEnter Your Name");
            player_name = reader.readLine();
            if(player_name.length() == 0){
                System.out.println("[Client] *Error: Name cannot be Empty");
            }
        }
        Player player = new Player(player_name);
        System.out.println("[Client] Initiated Player: " + player_name);

        System.out.println("Enter port");
        Scanner s = new Scanner(System.in);
        int port = s.nextInt();
        System.out.println("Enter Host Address");
        //"172.20.5.78"
        String host_address = reader.readLine();

        Socket server = new Socket(host_address, port);
        System.out.println("[Client] Connected to Server" + server.getInetAddress() + ": " + port);

        join_server(server, player);

        try(server){
            boolean ongoing = true;
            while (ongoing){
                if(Data.getTurn().equals(player_name)){
                    System.out.println("[Client] It is your turn. What do you want to do?"+
                            "\n\t \t 1.attack\t2.defense(Heal your self)\t3.pass");
                    int selection = s.nextInt();
                    if(selection == 1) {
                        System.out.println("\t \t Who do you want to attack?");
                        String attack_name = reader.readLine();
                        System.out.println("\t \t By how much?");
                        int damage = s.nextInt();
                        for(Player value: Data.getPlayer_list()){
                            if(value.PlayerName.equals(attack_name)){
                                value.points -= damage;
                                break;
                            }
                        }
                        Data.write_message(player_name + " deals " + damage + " damage to " + attack_name);
                    }
                    else if(selection == 2) {
                        System.out.println("\t \t By how much?");
                        int damage = s.nextInt();
                        for(Player value: Data.getPlayer_list()) {
                            if (value.PlayerName.equals(player_name)) {
                                value.points += damage;
                                break;
                            }
                        }
                        Data.write_message(player_name + "recover " + damage + " HP");
                    }
                    else{
                        System.out.println("\t \t Pass");
                        Data.write_message(player_name + "passed passed turn");
                    }
                    command = "push";
                }
                else {
                    System.out.println("[Client] Waiting for input from server");
                    command_from_server = new InputStreamReader(server.getInputStream());
                    server_command = new BufferedReader(command_from_server);
                    command = server_command.readLine();
                    System.out.println("[Server] Auto Receive from Server");
                    System.out.println("[Client] Input = " + command);
                }

                switch (command) {
                    case "pull": {
                        receive_from_server(server);
                        break;
                    }
                    case "push": {
                        if (!Data.getTurn().equals(player_name)) {
                            System.out.println("[Server] It is Player " + Data.getTurn() + "'s Turn \n\t \t Not Your Turn Yet");
                        } else {
                            Data.next_turn();
                            send_to_server(server);
                        }
                        break;
                    }
                    case "quit": {
                        Data.del_player(player);
                        Data.write_message("Player " + player_name + " quited");
                        ongoing = false;
                    }
                    default:
                }
                command = null;
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}