package code.Socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8899);
        byte[] data = new byte[1024];
        int thread_count = 0;
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("[Server] Start......");

        while(true){
            server.receive(packet);
            UDP_Server_Thread thread = new UDP_Server_Thread(data, server, packet);
            thread.start();
            thread_count += 1;
            System.out.println("[Server] New Thread Started\tThread Count: " + thread_count);
        }
        //server.close();
    }
}
