package code.Socket.Game_Phase;

import code.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Author Haohua Feng
 * Server combine (Code in Game_phase_chart and old version of TCP server)
 * Important change:
 *          * Receive input from players. All computations are now taking in server
 *            Then, send back to all players
 *
 *          * before: computes in player's local machine and send result to server
 *            Then send back to all players.
 *
 *
 * Not implement: Get Address and Port from UDP then use TCP connection
 * */
public class Server_Game_Phase {
    private final HashSet<Client> clients;

    private data_pack Data;

    public static void main(String[] args) {
        Server_Game_Phase serverGamePhase = new Server_Game_Phase();
        try {
            serverGamePhase.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server_Game_Phase() {
        clients = new HashSet<>();
    }

    public void start() throws IOException {
        Data = new data_pack();
        int port = 16225;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server start at " + server.getInetAddress() + ": " + port);
        while(true) {
            Socket client = server.accept();
            System.out.println(client + " joined!");
            Client c = new Client(client);
            clients.add(c);
            new Thread(c).start();
        }
    }

    private class Client implements Runnable{
        private String name;
        private DataInputStream is;
        private boolean status;
        private final Socket client;

        public Client(Socket new_client) {
            client = new_client;
            status = true;
            try {
                is = new DataInputStream(client.getInputStream());
                name = is.readUTF();
                Player player = new Player();
                player.PlayerName = name;
                Data.add_player(player);
                Data.next_turn();
                Data.write_message(name +" joined the game");
                toEveryone("player_join");
                System.out.println("Greeting to " + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(status) {
                try {
                    toEveryone(this.receive());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String receive() {
            String command = null;
            try {
                if (is != null) {
                    command = is.readUTF();
                    System.out.println(this.name + ": " + command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return command;
        }

        public void send(data_pack data) {
            try {
                ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                os.writeObject(data);
                os.flush();
                System.out.println("Send to Client " + this.name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public data_pack error(){
            data_pack error_message = new data_pack();
            error_message.send_chat_msg("[Server] Something wrong, try again");
            return error_message;
        }

        /**
         * below is the demo only for sprint grade
         * Subject to change.
         * Receive command from client do computation in compute()
         * If the pattern is correct, execute the command, otherwise treat command as user message
         * Note: Console only can get one input at once. Will not be like this for final version.
         *
         * For example: (whitespace)
         *              attack name damage
         *              defense number
         *              pass
         *              quit
         * Able to:
         *          Attack
         *          Defense
         *          pass
         *          quit
         * missing:
         *          add card to hand
         *          use card
         *          change to phase between attack phase and draw phase
         *          winner
         *          etc.
         */
        public void compute(String input) throws IOException {
            String[] command = input.trim().split("\\s+");
            switch (command[0]){
                case "attack":{
                    if (command.length == 3 && Data.getTurn().equals(name)){
                        for(Player player : Data.getPlayer_list()){
                            if (player.PlayerName.equals(command[1])){
                                player.points -= Integer.parseInt(command[2]);
                                Data.write_message(name + " deals " + command[2] + " damage to " + player.PlayerName);
                                Data.next_turn();
                            }
                            else {
                                this.send(error());
                            }
                        }
                    }
                    else {
                        this.send(error());
                    }
                    break;
                }
                case "defense":{
                    if(command.length == 2 && Data.getTurn().equals(name)){
                        for(Player player : Data.getPlayer_list()) {
                            if (player.PlayerName.equals(name)) {
                                player.points += Integer.parseInt(command[1]);
                                Data.write_message(name + " recover " + command[1] + " HP");
                                Data.next_turn();
                            }
                            else {
                                this.send(error());
                            }
                        }
                    }
                    else {
                        this.send(error());
                    }
                    break;
                }
                case "pass":{
                    if(command.length == 1 && Data.getTurn().equals(name)){
                        Data.next_turn();
                        Data.write_message(name + " passed the turn");
                    }
                    else {
                        this.send(error());
                    }
                    break;
                }
                case "player_join":{
                    this.send(Data);
                    break;
                }
                case "quit":{
                    status = false;
                    clients.remove(this);
                    Data.write_message(name + " leave the game");
                    Data.next_turn();
                    client.close();
                    break;
                }
                default:
                    Data.send_chat_msg(name + ": " + input);
            }
        }

        public void toEveryone(String command) throws IOException {
            compute(command);
            if (Data != null) {
                for (Client client : clients) {
                    if (Data.new_msg && client != this) {
                        client.send(Data);
                    }
                    else if(!Data.new_msg){
                        client.send(Data);
                    }
                }
            }
        }
    }
}
