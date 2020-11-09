package code.Socket.Game_Phase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Game_Client_Demo {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        int port = 16225;

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name to start");
        String name = console.readLine();
        if(name.equals(""))
            return;
        Socket client = null;
        try {
            client = new Socket(address, port);
        } catch (IOException e) {
            System.err.println(name +" connect failed");
        }

        assert client != null;
        Game_Send s = new Game_Send(client, name);
        Thread send_thread = new Thread(s);
        send_thread.start();
        Game_Receive r = new Game_Receive(client);
        Thread receive_thread = new Thread(r);
        receive_thread.start();
    }
}
