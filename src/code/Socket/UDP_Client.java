package code.Socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import code.Player;
import code.Socket.data_pack;

public class UDP_Client {
    public static void main(String[] args) throws IOException{
        Player player = new Player("Haohua Feng");
        data_pack Data = new data_pack();

        //1. Define server address, port, packet
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("172.20.5.78");
        int port = 8899;
        socket.connect(address, port);

        ByteArrayOutputStream OS = null;
        ObjectOutputStream os = null;
        ByteArrayInputStream IS = null;
        ObjectInputStream is = null;
        byte[] data = null;

        // Create a Player
        OS = new ByteArrayOutputStream();
        os = new ObjectOutputStream(OS);
        os.writeObject(player);
        data = OS.toByteArray();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.send(packet);
        System.out.println("[Client > Server] Send to Server");

        //first time receive data_pack
        byte[] data1 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data1, data1.length);
        socket.receive(packet2);
        String reply = new String(data1);
        System.out.println("[Client] Receive Data");
        System.out.println("[Server > Client] " + reply);
        //4.close

        boolean client_status = true;
        while (client_status){
            byte[] data2 = new byte[1024];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            data2 = reader.readLine().getBytes();
            DatagramPacket output = new DatagramPacket(data2, data2.length);
            socket.send(output);

            data2 = new byte[1024];
            DatagramPacket input = new DatagramPacket(data2, data2.length);
            socket.receive(input);
            System.out.println("[Server > Client] " + new String(input.getData(),0,input.getLength()));
        }
        socket.close();
    }
}
