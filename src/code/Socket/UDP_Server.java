package code.Socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import code.Player;
import code.Socket.data_pack;

public class UDP_Server {
    private static data_pack Data = new data_pack();
    private static ArrayList<DatagramPacket> thread_list = new ArrayList();
    private static ByteArrayOutputStream OS = null;
    private static ObjectOutputStream os = null;
    private static ByteArrayInputStream IS = null;
    private static ObjectInputStream is = null;

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8899);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("[Server] Start......");
        data_pack Data = new data_pack();

        while(true){
            server.receive(packet);
            UDP_Server_Thread thread = new UDP_Server_Thread(data, server, packet);
            thread_list.add(packet);
            thread.start();
        }
        //server.close();
    }

    static class UDP_Server_Thread extends Thread {
        byte[] data = null;
        DatagramSocket server = null;
        DatagramPacket packet = null;

        public UDP_Server_Thread(byte[] data, DatagramSocket server, DatagramPacket packet) {
            this.server = server;
            this.packet = packet;
            this.data = data;
        }

        public void start(){
            System.out.println("[Thread] " + packet.getAddress() +": "+ packet.getPort() + " connected");
            //will be player join
            data = packet.getData();
            try {
                IS = new ByteArrayInputStream(data);
                is = new ObjectInputStream(IS);
                Player player = (Player) is.readObject();
                Data.add_player(player);
                Data.write_message(player.PlayerName + " Joined");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("[Client > Server] " + Data.getMessage());
            //1. define client address, port, data
            InetAddress client_address = packet.getAddress();
            int client_port = packet.getPort();
            //Send Data to Client
            try {
                byte[] greeting  = "Welcome!".getBytes();
                DatagramPacket greet = new DatagramPacket(greeting, greeting.length, client_address, client_port);
                server.send(greet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[Server > Client] Send to Client");

            boolean thread_status = true;
            while (thread_status){
                try {
                    byte[] data = new byte[1024];
                    DatagramPacket input = new DatagramPacket(data, data.length);
                    server.receive(input);
                    System.out.println("[Client > Server] " + new String(input.getData(),0,input.getLength()));
                    //Data = input
                    for(DatagramPacket value : thread_list){
                        data = "Data received".getBytes();
                        DatagramPacket output = new DatagramPacket(data, data.length, value.getAddress(), value.getPort());
                        server.send(output);
                        System.out.println("[Server > Each Client] Send to Everyone");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

