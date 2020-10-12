package code.Socket;

import code.Deck.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private static data_pack Data = new data_pack();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(16225);
        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                InputStreamReader isr = null;
                BufferedReader br = null;
                OutputStreamWriter osw = null;
                BufferedWriter bw = null;
                ObjectInputStream input = null;
                ObjectOutputStream output = null;
                InputStreamReader reader;
                String command = null;
                try {
                    while (true) {
                        isr = new InputStreamReader(client.getInputStream());
                        br = new BufferedReader(isr);
                        command = br.readLine();
                        System.out.println(command);

                        assert command != null;

                        if (command.equals("client pull")) {
                            output = new ObjectOutputStream(client.getOutputStream());
                            output.writeObject(Data);
                            output.flush();
                            System.out.println("Sent to client...");
                        }
                        if (command.equals("client push")) {
                            input = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                            Object in = input.readObject();
                            if (in != null) {
                                Data = (data_pack) in;
                            }
                            System.out.println("Receive data from client...");
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        assert input != null;
                        input.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        output.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        client.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }).start();
    }
}
