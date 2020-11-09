package code.Socket.Game_Phase;

import code.Player;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Author: Haohua Feng
 * hold keep a copy of all players' status
 * Use to render Game Phase GUI
 * Does not do anything, only receive a object that contains players' status
 * Some lines are going to be removed
 * */
public class Game_Receive implements Runnable{
    private boolean status = true;
    private static data_pack Data = new data_pack();
    private Socket Socket;
    private static ObjectInputStream IS = null;

    @Override
    public void run() {
        while(status){
            object_receive();
        }
    }

    public Game_Receive(Socket socket) throws IOException {
        Socket = socket;
    }

    public void object_receive() {
        try {
            IS = new ObjectInputStream(new BufferedInputStream(Socket.getInputStream()));
            Data = (data_pack) IS.readObject();
            System.out.println("[System] Receive from Server");
            if(Data.new_msg){
                System.out.println("[Chat Message]:\n\t" + Data.receive_chat_msg() + "\n");
            }
            else{
                /**
                 * below is the demo only for sprint grade
                 * Will not exist in final version
                 */
                System.out.println("\t\tPhase: "+ Data.getPhase()
                        +"\t\tRound: " + Data.getRound()
                        +"\t\tTurn: " + Data.getTurn()+"\n\t\tPlayers: ");
                for (Player value : Data.getPlayer_list()) {
                    System.out.println("\t\t" + value.PlayerName + ": " + value.points + " HP");
                }
                System.out.println("\t\tRecent activity: "+Data.getMessage() + "\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            status = false;
        }
    }
}
