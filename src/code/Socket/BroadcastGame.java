package code.Socket;

import java.io.IOException;
import java.net.*;

import code.ServerModel;

public class BroadcastGame implements Runnable  {
	private ServerModel model;
    private DatagramSocket socket;
    private boolean running;
    private byte[] sendBuf = new byte[256];
    private byte[] recieveBuf = new byte[256];
	public BroadcastGame(int serverPort, ServerModel serverModel) {
        try {
			socket = new DatagramSocket(serverPort);
			model = serverModel;
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }


    @Override
    public void run() {
    	running = true;
    	
    	try {
            while (running) {
                DatagramPacket packet = new DatagramPacket(recieveBuf, recieveBuf.length);
                socket.receive(packet);
                packet = new DatagramPacket(recieveBuf, recieveBuf.length, packet.getAddress(), packet.getPort());

                String received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                if (received.equals("end")) {
                    running = false;
                    continue;
                }else if (received.contains("whoami")) {
                	received = createMessage();
                }
                
                sendBuf = received.getBytes();
                packet = new DatagramPacket(sendBuf, sendBuf.length, packet.getAddress(), packet.getPort());
                socket.send(packet);
            }
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void close() {
    	socket.close();
    	running = false;
    }
    
    public String createMessage() {

        try {
        	Socket ip = new Socket();
			ip.connect(new InetSocketAddress("google.com", 80));
			String message = model.GetHostName() + "/" + ip.getLocalAddress().getHostAddress() + "/" + model.GetCurrentPlayers() + 
					"/" + model.GetMaxPlayers() + "/" + model.GetAccessType() + "/" + model.GetPassword();
			for (int i = 0; i < model.getPlayers().size(); i++) {
				message += "/" + model.getPlayers().get(0).PlayerName;
			}
			
			return message;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "";
    }
}
