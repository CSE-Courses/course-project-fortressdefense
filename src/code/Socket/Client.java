package code.Socket;

import code.Command;
import code.Player;
import gui.Join_Game;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Client {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ObjectOutputStream output = null;
    private static ObjectInputStream input = null;
    private static InputStreamReader command_from_server = null;
    private static BufferedReader server_command = null;
    private static OutputStreamWriter command_to_server = null;
    private static BufferedWriter client_command = null;
    private static String player_name = "";
    private static String command = null;
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;
    private Join_Game joinGame;
    private JTextArea chat;
    
    
    public Client(String serverName, int serverPort, Join_Game joinGame, JTextArea chat) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.joinGame = joinGame;
        this.chat = chat;
    }
    
    /**
     * Connects to server
     * @return whether server connection is valid
     * @author Hoahua Feng, Andrew Jank
     */
    public Boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Joins game
     * @param playerName
     * @author Hoahua Feng, Andrew Jank
     */
    public void join(String playerName) {
        try {
            String cmd = Command.Join.toString() + " " + playerName + "\n";
			serverOut.write(cmd.getBytes());
            
			startMessageReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Toggles player ready
     * @param playerName
     * @author Hoahua Feng, Andrew Jank
     */
    public void ready() {
        try {
            String cmd = Command.Ready.toString() + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Leaves game
     * @author Hoahua Feng, Andrew Jank
     */
    public void leave() {
        try {
            String cmd = Command.Leave.toString() + "\n";
			serverOut.write(cmd.getBytes());
			chat.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Closes client connection
     * @author Hoahua Feng, Andrew Jank
     */
    public void close() {
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    public void message(String playerName, String msg) {
		try {
			String cmd = Command.Message.toString() + " " + playerName + ": " + msg + "\n";
			serverOut.write(cmd.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Thread that reads all messages from server
     * @author Hoahua Feng, Andrew Jank
     */
    private void startMessageReader() {
        Thread t = new Thread() {
            @Override
            public void run() {
                readMessageLoop();
            }
        };
        t.start();
    }
    
    /**
     * reads all messages from server
     * @author Hoahua Feng, Andrew Jank
     */
    private void readMessageLoop() {
        try {
            String line;
            while ((line = bufferedIn.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens != null && tokens.length > 0) {
                    switch(Command.valueOf(tokens[0])) {
		                case Shutdown:
		                	// Kludge, should just set it to main frame, dont know how to check with form is visible when in draw/attack phase
		                	JOptionPane.showMessageDialog(joinGame.getPanel(), "Connection to server lost!", "Fortress Defense", JOptionPane.ERROR_MESSAGE);
		                	joinGame.getBackButton().doClick();
		                	break;
		                case Refresh:
		                	joinGame.refresh();
		                	joinGame.refresh_room_detail();
		                	break;
		                case Ready:
		                	if (joinGame.getRoom().getPlayer_status().get(String.join(" ", tokens).replaceAll(tokens[0], "").trim()).equals("Ready")){
			                	joinGame.getRoom().my_status(String.join(" ", tokens).replaceAll(tokens[0], "").trim(), 'w');
		                	}else {
		                		joinGame.getRoom().my_status(String.join(" ", tokens).replaceAll(tokens[0], "").trim(), 'r');
		                	}
	
		                	joinGame.refresh_room_detail();
		                	break;
		                case Join:
		                   	joinGame.refresh();
		                	joinGame.refresh_room_detail();
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + 
		                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
		                	break;
		                case Leave:
		                   	joinGame.refresh();
		                	joinGame.refresh_room_detail();
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + "[System]: " + 
				                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
		                	break;
		                case Message:
		                	chat.setText(chat.getText() + new Time(System.currentTimeMillis()) + "\n" + 
				                	String.join(" ", tokens).replaceAll(tokens[0], "").trim() + "\n\n");
                    	default:
                    		break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public JTextArea getChat() {
    	return chat;
    }
    
    public void setChat(JTextArea chatBox) {
    	chat = chatBox;
    }
    

    /*
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
                        Data.write_message(player_name + " recover " + damage + " HP");
                    }
                    else{
                        System.out.println("\t \t Pass");
                        Data.write_message(player_name + " passed turn");
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
    */
}
