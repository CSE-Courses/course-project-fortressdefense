package code.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("172.20.6.109", 16225);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(1);
            String msg = reader.readLine();
            out.println(msg);
            out.flush();
            if (msg.equals("GAME OVER")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }
}
