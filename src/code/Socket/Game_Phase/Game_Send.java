package code.Socket.Game_Phase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Game_Send implements Runnable{
    private DataOutputStream os;
    private BufferedReader reader;
    private boolean status = true;

    @Override
    public void run() {
        while(status) {
            send(getMsgFromConsole());
        }
    }

    public Game_Send(Socket client, String name) {
        try {
            os = new DataOutputStream(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
            send(name);
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
    }

    public void send(String msg) {
        try {
            if(msg != null && !msg.equals("")) {
                os.writeUTF(msg);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
    }

    public String getMsgFromConsole() {
        String msg = null;
        try {
            msg = reader.readLine();
            if (msg.equals("quit")){
                status = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }
        return msg;
    }
}
