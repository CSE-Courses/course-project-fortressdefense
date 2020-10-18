package code.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class server {
    private static data_pack Data = new data_pack();
    public static void main(String[] args) throws IOException {
        int port = 16225;
        ServerSocket server = new ServerSocket(port);
        System.out.println("\n[Server] Server started at port " + port);
        while (true) {
            Socket socket = server.accept();
            System.out.println("[Server] Client connected" + socket.getInetAddress());
            invoke(socket);
        }
    }

    private static InputStreamReader command_from_client = null;
    private static BufferedReader client_command = null;
    private static final OutputStreamWriter command_to_client = null;
    private static final BufferedWriter server_command = null;
    private static ObjectInputStream from_client = null;
    private static ObjectOutputStream to_client = null;
    private static InputStreamReader reader;
    private static String command = null;

    private static void send_to_client(Socket client) throws IOException {
        assert client != null;
        to_client = new ObjectOutputStream(client.getOutputStream());
        to_client.writeObject(Data);
        to_client.flush();
    }
    private static void receive_from_client(Socket client) throws IOException, ClassNotFoundException {
        assert client != null;
        from_client = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        Object in = from_client.readObject();
        if (in != null) {
            Data = (data_pack) in;
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try (client) {
                    boolean ongoing = true;
                    while (ongoing) {
                        command_from_client = new InputStreamReader(client.getInputStream());
                        client_command = new BufferedReader(command_from_client);
                        command = client_command.readLine();
                        System.out.println("[Client] "+ LocalTime.now() +
                                "\t\t" + client.getInetAddress() +"\t\t" + command);

                        assert command != null;
                        switch (command) {
                            case "client_pull":
                                send_to_client(client);
                                break;
                            case "client_push":
                                receive_from_client(client);
                                break;
                            case "GAME_OVER":
                                ongoing = false;
                                break;
                            default:
                                System.out.println("[Client] * Error: Command Undefined");
                        }
                        command = null; //reset
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        assert from_client != null;
                        from_client.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        to_client.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }).start();
    }
}
