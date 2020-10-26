package code.Socket;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Server_Thread extends Thread {
    byte[] data = null;
    DatagramSocket server = null;
    DatagramPacket packet = null;

    public UDP_Server_Thread(byte[] data, DatagramSocket server, DatagramPacket packet) {
        this.server = server;
        this.packet = packet;
        this.data = data;
    }

    public void start(){
        System.out.println("[Thread] Start......");
        String info = new String(data);
        System.out.println("[Client > Server]" + info);

        //to client
        //1. define client address, port, data
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "Welcome!".getBytes();

        //2. create packet, reply message
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);

        //3. Response to client
        try {
            server.send(packet2);
            System.out.println("[Client] Send to Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
