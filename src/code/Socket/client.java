package code.Socket;

import code.Deck.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class client {
    private static data_pack Data = new data_pack();
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        System.out.println("Demo\nEnter Your name");
        String player_name = "";
        player_name= reader.readLine();
        Player player = new Player(player_name);
        System.out.println("Init.Player : " + player_name);

        Socket socket = new Socket("172.20.6.109", 16225);
        try{
            while (true){
                String command = reader.readLine();
                if(command.equals("pull")) {
                    osw = new OutputStreamWriter(socket.getOutputStream());
                    bw = new BufferedWriter(osw);
                    bw.write("client pull" + "\n");
                    bw.flush();
                    TimeUnit.SECONDS.sleep(1);
                    input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    Object in = input.readObject();
                    System.out.println("Received data from Server...");
                    if (in != null) {
                        Data = (data_pack) in;
                        for (Player value : Data.getPlayer_list()) {
                            System.out.println(value.PlayerName + ": " + value.points + " HP");
                        }
                        System.out.println("Recent activity: "+Data.getMessage());
                    }
                }
                if(command.equals("join")){
                    Data.add_player(player);
                    Data.write_message("player "+player_name+" joined...");
                    command = "push";
                }
                if(command.equals("attack")) {
                    System.out.println("who?");
                    String att_name = reader.readLine();
                    System.out.println("How much damage?");
                    int damage = Integer.parseInt(reader.readLine());
                    for (Player value : Data.getPlayer_list()) {
                        if (value.PlayerName.equals(att_name)) {
                            value.points -= damage;
                            Data.write_message(player_name + " deals " + damage + " to " + att_name);
                        }
                    }
                    command = "push";
                }
                if(command.equals("defense")) {
                    System.out.println("How much hp need to recover?");
                    int healing = Integer.parseInt(reader.readLine());
                    for (Player value : Data.getPlayer_list()) {
                        if (value.PlayerName.equals(player_name)) {
                            value.points += healing;
                            Data.write_message(player_name + " recover HP by " + healing);
                        }
                    }
                    command = "push";
                }
                if(command.equals("push")){
                    osw = new OutputStreamWriter(socket.getOutputStream());
                    bw = new BufferedWriter(osw);
                    bw.write("client push" + "\n");
                    bw.flush();
                    TimeUnit.SECONDS.sleep(1);
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(Data);
                    output.flush();
                    System.out.println("Update and Push to Server...");
                }

                if(command.equals("Quit")){
                    Data.del_player(player);
                    command = "push";
                }
                if (command.equals("End")){
                    break;
                }
                command = null;
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
