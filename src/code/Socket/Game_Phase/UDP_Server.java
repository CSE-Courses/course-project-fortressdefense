package code.Socket.Game_Phase;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import code.Player;

public class UDP_Server {
    private static ArrayList<DatagramPacket> thread_list = new ArrayList();
    private static ArrayList<InetAddress> thread_address = new ArrayList<>();
    private static ByteArrayOutputStream OS = null;
    private static ObjectOutputStream os = null;
    private static ByteArrayInputStream IS = null;
    private static ObjectInputStream is = null;
    private static  DatagramPacket input = null;
    private static  DatagramPacket output = null;
    private static data_pack Data = new data_pack();
    private static byte[] data = null;
    private static int room_size = 2;

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8899);
        System.out.println("[Server] Start......");
        Data.setRoom_size(room_size);
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            server.receive(packet);
            UDP_Server_Thread thread = new UDP_Server_Thread(server, packet);
            thread.start();
        }
    }

    static class UDP_Server_Thread extends Thread {
        DatagramSocket server = null;
        DatagramPacket packet = null;
        public UDP_Server_Thread(DatagramSocket server, DatagramPacket packet) {
            this.server = server;
            this.packet = packet;
        }

        public void run() {
            System.out.println("[Thread] " + packet.getAddress() +": "+ packet.getPort() + " connected");
            data = packet.getData();
            if(!thread_address.contains(packet.getAddress())) {
                thread_address.add(packet.getAddress());
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
            }

            /**
             * waiting for players
             * start the game if number of players equals to room size
             */
            while (Data.getRoom_size() > Data.getPlayer_list().size()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(Data.getTurn().equals("")) {
                Data.next_turn();
            }
            send(server, packet);

            boolean thread_status = true;
            while (thread_status){
                try {
                    receive(server, packet);
                    //Data = input
                    for(DatagramPacket value : thread_list){
                        send(server, value);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void send(DatagramSocket server, DatagramPacket packet){
            try {
                OS = new ByteArrayOutputStream();
                os = new ObjectOutputStream(OS);
                os.writeObject(Data);
                data = OS.toByteArray();
                DatagramPacket p = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
                server.send(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[Server > Client] Send to " + packet.getAddress() +": " + packet.getPort());
        }

        private static void receive(DatagramSocket server, DatagramPacket packet) throws IOException, ClassNotFoundException {
            data = new byte[1024];
            input = new DatagramPacket(data, data.length);
            server.receive(input);
            data = input.getData();
            IS = new ByteArrayInputStream(data);
            is = new ObjectInputStream(IS);
            Data = (data_pack) is.readObject();
            System.out.println("[Client > Server] Receive from " + packet.getAddress() +": " + packet.getPort());
        }
    }
}

