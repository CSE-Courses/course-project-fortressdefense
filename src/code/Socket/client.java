package code.Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    public static void main(String args[]) throws IOException {
        int number, temp;
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
        Socket s = new Socket(socket.getInetAddress(), 1342);
        Scanner sc1 = new Scanner(s.getInputStream());
        System.out.println("Enter any number");
        number = sc.nextInt();
        PrintStream p = new PrintStream(s.getOutputStream());
        p.println(number);
        temp = sc1.nextInt();
        System.out.println(temp);
    }
}
