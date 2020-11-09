package code.Socket.Game_Phase_Chart;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Chat_Receive implements Runnable{
    private DataInputStream is;
    private boolean status=true;

    @Override
    public void run() {
        while(status){
            receive();
        }
    }

    public Chat_Receive(Socket client) {
        try {
            is = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
    }

    public void receive() {
        String msg=null;
        try {
            msg = is.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
        System.out.println(msg);
    }

}

