package code.Socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import code.Player;
import code.Socket.data_pack;

public class UDP_Client {
    private static ByteArrayOutputStream OS = null;
    private static ObjectOutputStream os = null;
    private static ByteArrayInputStream IS = null;
    private static ObjectInputStream is = null;
    private static  DatagramPacket input = null;
    private static  DatagramPacket output = null;
    private static data_pack Data = new data_pack();
    private static byte[] data = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Player player = new Player("Haohua Feng");

        //1. Define server address, port, packet
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("172.20.5.78");
        int port = 8899;
        socket.connect(address, port);

        while (true){

        }

    }
    private static void receive(DatagramSocket socket) throws IOException, ClassNotFoundException {
        System.out.println("[Server > Client] Receive from Server");
        data = new byte[1024];
        input = new DatagramPacket(data, data.length);
        socket.receive(input);
        data = input.getData();
        IS = new ByteArrayInputStream(data);
        is = new ObjectInputStream(IS);
        Data = (data_pack) is.readObject();
        System.out.println("[Client] Receive Data");
        System.out.println("\t \t Round: " + Data.getRound() + "\tTurn: " + Data.getTurn());
        for (Player value : Data.getPlayer_list()) {
            System.out.println("\t \t " + value.PlayerName + ": " + value.points + " HP");
        }
        System.out.println("\t \t Recent activity: " + Data.getMessage());
    }

    private static void send(DatagramSocket socket, DatagramPacket packet) throws IOException {
        Data.next_turn();
        OS = new ByteArrayOutputStream();
        os = new ObjectOutputStream(OS);
        os.writeObject(Data);
        data = OS.toByteArray();
        DatagramPacket p = new DatagramPacket(data, data.length);
        socket.send(p);
        System.out.println("[Client > Server] Send to Server");
    }
}
