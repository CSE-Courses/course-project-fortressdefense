package code.Socket.Game_Phase_Chart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server_Game_Phase_Chat {
    private final HashSet<Client> clients;

    public static void main(String[] args) {
        Server_Game_Phase_Chat serverGamePhase = new Server_Game_Phase_Chat();
        try {
            serverGamePhase.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server_Game_Phase_Chat() {
        clients = new HashSet<>();
    }

    public void start() throws IOException {
        int port = 16225;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server start at " + server.getInetAddress() + ": " + port);
        while(true) {
            Socket client = server.accept();
            System.out.println(client + " joined");
            Client c = new Client(client);
            clients.add(c);
            new Thread(c).start();
        }
    }

    private class Client implements Runnable{
        private String name;
        public String getName() {
            return name;
        }
        private DataInputStream is;
        private DataOutputStream os;
        private boolean status;

        public Client(Socket client) {
            try {
                is = new DataInputStream(client.getInputStream());
                os = new DataOutputStream(client.getOutputStream());
                status = true;
            } catch (IOException e) {
                e.printStackTrace();
                status = false;
            }
            try {
                name = is.readUTF();
                String toAll = "Welcome, " + name + " !";
                toEveryone(toAll,true);
                String toThisClient = "[Server] You just joined";
                send(toThisClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(status) {
                toEveryone(this.receive(),false);
            }
        }

        public String receive() {
            String msg = null;
            try {
                if (is != null) {
                    msg = is.readUTF();
                    System.out.println(this.name + ": " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

        public void send(String msg) {
            try {
                os.writeUTF(msg);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void toEveryone(String msg, boolean server) {
            if (msg != null) {
                for (Client client : clients) {
                    if (client != this) {
                        if (server) {
                            client.send("[Server] " + msg);
                        } else {
                            client.send(name + ": " + msg);
                        }

                    }
                }
            }
        }
    }
}
