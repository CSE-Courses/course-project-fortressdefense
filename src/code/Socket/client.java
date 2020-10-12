package code.Socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import code.Deck.Player;
import code.Socket.*;
import code.*;

public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("172.20.6.109", 16225);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String msg = reader.readLine();
            out.println(msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }
}
