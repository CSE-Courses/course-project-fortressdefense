package code.Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client {
    public static void main(String args) throws IOException{
        //1. Define server address, port, packet
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8899;
        byte[] data = "Hello".getBytes();
        //2. create packet
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3. create socket object
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        System.out.println("[Client > Server] Send to Server");

        //1. Create packet for receive
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2. receive
        socket.receive(packet2);
        //3. read packet
        String reply = new String(data2);
        System.out.println("[Client] Receive Data");
        System.out.println("[Server > Client] " + reply);
        //4.close
        socket.close();
    }
}
