package code.Socket.Game_Phase_Chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Chat_Client {
    public static void main(String[] args) throws IOException {
        //address and port
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
        Chat_Send s = new Chat_Send(client, name);
        Thread send_thread = new Thread(s);
        send_thread.start();
        Chat_Receive r = new Chat_Receive(client);
        Thread receive_thread = new Thread(r);
        receive_thread.start();
    }
}

